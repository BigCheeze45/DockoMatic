#!/bin/bash
source log4bash/log4bash.sh
source functions.sh
export SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"


# Make sure we're running as root
log_info "Checking for root asscess"
if [[ $(whoami) != root ]]; then
    log_error "The installer must be ran as root!"
    echo -e "\n\tUsage:" \
	" sudo ./install.sh <MODELLER license key>"
    exit 1
fi

# Check for MODELLER License
log_info "Checking FOR MODELLER license"
if [[ "$1" == "" ]]; then
	log_error "MODELLER license key missing.\n\tUsage:" \
	" sudo ./install.sh <MODELLER license key>"
	exit 1
else
	log_success "MODELLER license key found"
	KEY_MODELLER="$1"
fi
ARCH=$(echo $HOSTTYPE)

if [[ "$2" != "" ]]; then
	log_info "Using provided Linux distribution"
	LINUX_DISTRO=$2
else
	# Try to figure out what system we're on
	log_info "Acquring Linux distribution"
	if [[ -f /etc/os-release ]]; then
	  if [[ $(cat /etc/os-release | awk 'NR==1' | grep -i 'Ubuntu' &>/dev/null; echo $?) -eq 0 ]]; then
	       LINUX_DISTRO=$(cat /etc/os-release | awk 'NR==1' | grep -i 'Ubuntu' | cut -d'=' -f2- | tr -d '"')
	       VERSION_ID=$(cat /etc/os-release | awk 'NR==2' | cut -d'=' -f2- | tr -d '"')
	  elif [[ $(cat /etc/os-release | awk 'NR==1' | grep -i 'Fedora' &>/dev/null; echo $?) -eq 0 ]]; then
	       LINUX_DISTRO=$(cat /etc/os-release | awk 'NR==1' | grep -i 'Fedora' | cut -d'=' -f2- | tr -d '"')
	       VERSION_ID=$(cat /etc/os-release | awk 'NR==4' | cut -d'=' -f2-)
	  elif [[ $(cat /etc/os-release | awk 'NR==1' | grep -i 'CentOS' &>/dev/null; echo $?) -eq 0 ]]; then
	       LINUX_DISTRO=$(cat /etc/os-release | awk 'NR==1' | grep -i 'CentOS' | cut -d'=' -f2- | tr -d '"')
	       VERSION_ID=$(cat /etc/os-release | awk 'NR==2' | cut -d'=' -f2- | tr -d '"')
	  elif [[ $(cat /etc/os-release | awk 'NR==1' | grep -i Red &>/dev/null; echo $?) -eq 0 ]]; then
            LINUX_DISTRO="Red Hat"
	   fi
	else
		# The lsb_release module is usually indicative of an Ubuntu system but never hurts to double check
		log_warning "/etc/os-release file not available."
		if [[ $(which lsb_release &>/dev/null; echo $?) -eq 0 ]]; then
			LINUX_DISTRO=$(lsb_release -i | cut -d':' -f2- | grep -i 'Ubuntu' | xargs)
			VERSION_ID=$(lsb_release -r | cut -d':' -f2- | xargs)
		# Again, this file usually indicate that this is an Ubuntu system but never hurts to double check
		elif [[ -f /etc/lsb-release ]]; then
			LINUX_DISTRO=$(cat /etc/lsb-release | awk 'NR==1' | cut -d'=' -f2-)
			VERSION_ID=$(cat /etc/lsb-release | awk 'NR==2' | cut -d'=' -f2-)
		fi

		if [[ -f /etc/centos-release ]]; then
			LINUX_DISTRO="CentOS"
		fi

		if [[ -f /etc/redhat-release ]]; then
			LINUX_DISTRO="Red Hat"
		fi

		if [[ -f /etc/fedora-release ]]; then
			LINUX_DISTRO="Fedora"
		fi

		if [[ "$LINUX_DISTRO" == "" ]]; then
			log_warning "The installer was unable to detect your Linux distribution. Please restart the installer providing the name of your Linux distribution"
			exit 1
		fi
	fi
fi

if [[ "$LINUX_DISTRO" == "Ubuntu" ]]; then
	if [[ "$ARCH" == "i686" || "$ARCH" == "i386" ]]; then
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 32-bit installation"
		log_info "Entering (function ->UbuntuInstall)"
		UbuntuInstall $KEY_MODELLER
		log_success "UbuntuInstall function complete"
	else
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->UbuntuInstall)"
		UbuntuInstall $KEY_MODELLER
		log_success "UbuntuInstall function complete"
	fi
elif [[ "$LINUX_DISTRO" == "Fedora" ]]; then
	if [[ "$ARCH" == "i686" || "$ARCH" == "i386" ]]; then
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 32-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		FedoraInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
	else
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		FedoraInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
	fi
elif [[ "$LINUX_DISTRO" == "CentOS Linux" || "$LINUX_DISTRO" == "CentOS" || "$LINUX_DISTRO" == "Red Hat" ]]; then
	if [[ "$ARCH" == "i686" || "$ARCH" == "i386" ]]; then
        log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		CentosInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
		log_info "Downloading PyMol from SVN"
		svn co -q svn://svn.code.sf.net/p/pymol/code/trunk/pymol /tmp/pymol
		log_success "PyMol download complete"
		log_info "Running PyMol setup"
		cp pymol_setup.sh /tmp/pymol/pymol_setup.sh; chmod +x /tmp/pymol/pymol_setup.sh
		cd /tmp/pymol/; ./pymol_setup.sh; cd $(echo $SCRIPT_DIR)
		log_success "PyMol setup complete"
	else
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		CentosInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
		log_info "Downloading PyMol from SVN"
		svn co -q svn://svn.code.sf.net/p/pymol/code/trunk/pymol /tmp/pymol
		log_success "PyMol download complete"
		log_info "Running PyMol setup"
		cp pymol_setup.sh /tmp/pymol/pymol_setup.sh; chmod +x /tmp/pymol/pymol_setup.sh
		cd /tmp/pymol/; ./pymol_setup.sh; cd $(echo $SCRIPT_DIR)
		log_success "PyMol setup complete"
    fi
else
	# Assume the OS uses yum as a package manager
	if [[ "$ARCH" == "i686" || "$ARCH" == "i386" ]]; then
        log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		CentosInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
		log_info "Downloading PyMol from SVN"
		svn co -q svn://svn.code.sf.net/p/pymol/code/trunk/pymol /tmp/pymol
		log_success "PyMol download complete"
		log_info "Running PyMol setup"
		cp pymol_setup.sh /tmp/pymol/pymol_setup.sh; chmod +x /tmp/pymol/pymol_setup.sh
		cd /tmp/pymol/; ./pymol_setup.sh; cd $(echo $SCRIPT_DIR)
		log_success "PyMol setup complete"
	else
		log_info "This is going to be a $LINUX_DISTRO $VERSION_ID, 64-bit installation"
		log_info "Entering (function ->FedoraInstall)"
		CentosInstall $KEY_MODELLER
		log_success "FedoraInstall function complete"
		log_info "Downloading PyMol from SVN"
		svn co -q svn://svn.code.sf.net/p/pymol/code/trunk/pymol /tmp/pymol
		log_success "PyMol download complete"
		log_info "Running PyMol setup"
		cp pymol_setup.sh /tmp/pymol/pymol_setup.sh; chmod +x /tmp/pymol/pymol_setup.sh
		cd /tmp/pymol/; ./pymol_setup.sh; cd $(echo $SCRIPT_DIR)
		log_success "PyMol setup complete"
	fi
fi

# Make a call to the dockomaticInstall function
log_info "Entering (function ->dockomaticInstall)"
dockomaticInstall
log_success "DockoMatic install complete"
unset SCRIPT_DIR

echo -e "\nDockoMatic has been installed and setup. Please restart your terminal so it can be updated.\n" \
"Once you've restrated your terminal you can type dockomatic to launch the program. \nSample files have been provided that you can use" \
"to take the program for a test drive."
