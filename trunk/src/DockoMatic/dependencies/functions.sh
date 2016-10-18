#!/bin/bash
# Each install function takes one argument
    # $1 - the MODELLER license key passed to install.sh

function FedoraInstall {
    # # JDK installation # #
    log_info "Installing JDK (function ->FedoraInstall)"
    dnf install -y java-1.8.0-openjdk-devel
    if [[ $(echo $?) -ne 0 ]]; then
        yum install -y java-1.8.0-openjdk-devel
    fi
    log_success "JDK installation complete (function ->FedoraInstall)"

    # # PyMOL installation # #
    log_info "Installing PyMOL (function ->FedoraInstall)"
    dnf install -y pymol
    if [[ $(echo $?) -ne 0 ]]; then
        yum install -y pymol
    fi
    log_success "PyMOL installation complete (function ->FedoraInstall)"

    # # AutoDock installation # #
    log_info "Installing AutoDock Suite (function ->FedoraInstall)"
    dnf install -y autodocksuite
    log_success "AutoDock Suite installation complete (function ->FedoraInstall)"

    if [[ "$HOSTTYPE" == "i686" || "$HOSTTYPE" == "i386" ]]; then
        # # MODELLER installation # #
        if [[ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]]; then
            log_info "Installing 32-bit MODELLER (function ->FedoraInstall_32)"
            env KEY_MODELLER=$1 rpm -Uvh modeller/modeller-9.17-1.i386.rpm
        fi
        log_success "MODELLER installation complete (function ->FedoraInstall_32)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Installing 32-bit MGLTools (function ->FedoraInstall_32)"
            log_info "Granting execution permission to MGLTools installer (function ->FedoraInstall_32)"
            chmod +x mgltools_Linux-x86_1.5.6_Install
            log_success "Execution permission granted (function ->FedoraInstall_32)"
            log_info "Running MGLTools setup (function ->FedoraInstall_32)"
            ./mgltools_Linux-x86_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->FedoraInstall_32)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->FedoraInstall_32)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
        done
        log_success "Bash file update complete (function ->FedoraInstall_32)"
        log_success "MGLTools installation complete (function ->FedoraInstall_32)"
    else
        # # MODELLER installation # #
        if [[ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]]; then
            log_info "Installing 64-bit MODELLER (function ->FedoraInstall_64)"
            env KEY_MODELLER=$1 rpm -Uvh modeller/modeller-9.17-1.x86_64.rpm
        fi
        log_success "MODELLER installation complete (function ->FedoraInstall_64)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Installing 64-bit MGLTools (function ->FedoraInstall_64)"
            log_info "Granting execution permission to MGLTools installer (function ->FedoraInstall_64)"
            chmod +x mgltools_Linux-x86_64_1.5.6_Install
            log_success "Execution permission granted (function ->FedoraInstall_64)"
            log_info "Running MGLTools setup (function ->FedoraInstall_64)"
            ./mgltools_Linux-x86_64_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->FedoraInstall_64)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->FedoraInstall_64)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
        done
        log_success "Bash file update complete (function ->FedoraInstall_64)"
        log_success "MGLTools installation complete (function ->FedoraInstall_64)"
    fi
}

function UbuntuInstall {
    apt-get -y update
    # # JDK installation # #
    log_info "Installing JDK (function ->UbuntuInstall)"
    apt-get -y install default-jdk
    log_success "JDK installation complete (function -> UbuntuInstall)"
    log_info "Installing JRE (function -> UbuntuInstall)"
    apt-get -y install default-jre
    log_success "JRE installation complete (function ->UbuntuInstall)"

    # # PyMOL installation # #
    log_info "Installing PyMOL (function ->UbuntuInstall)"
    apt-get -y install pymol
    log_success "PyMOL installation complete (function ->UbuntuInstall)"

    # # AutoDock installation # #
    log_info "Installing AutoDock suite (function ->UbuntuInstall)"
    log_info "Installing autodock"
    apt-get -y install autodock
    log_success "autodock installation complete"
    log_info "Installing autoDock-vina"
    apt-get -y install autodock-vina
    log_success "autoDock-vina installation complete"
    log_info "Installing autodocktools"
    apt-get -y install autodocktools
    log_success "autodocktools installation complete"
    log_info "Installing autogrid"
    apt-get -y install autogrid
    log_success "autogrid installation complete"
    log_success "AutoDock Suite installation complete (function ->UbuntuInstall)"

    if [[ "$HOSTTYPE" == "i686" || "$HOSTTYPE" == "i386" ]]; then
        # # MODELLER installation # #
        if [[ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]]; then
            log_info "Installing 32-bit MODELLER (function ->UbuntuInstall_32)"
            env KEY_MODELLER=$1 dpkg -i modeller/modeller_9.17-1_i386.deb
        fi
        log_success "MODELLER installation complete (function ->UbuntuInstall_32)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Granting execution permission to MGLTools installer (function ->UbuntuInstall_32)"
            chmod +x mgltools_Linux-x86_1.5.6_Install
            log_success "Execution permission granted (function ->UbuntuInstall_32)"
            log_info "Running MGLTools setup (function ->UbuntuInstall_32)"
            ./mgltools_Linux-x86_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->UbuntuInstall_32)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->UbuntuInstall_32)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
            log_success "Bash file update complete (function ->UbuntuInstall_32)"
        done
        log_success "MGLTools installation complete (function ->UbuntuInstall_32)"
    else
        # # MODELLER installation # #
        if [[ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]]; then
            log_info "Installing 64-bit MODELLER (function ->UbuntuInstall_64)"
            env KEY_MODELLER=$1 dpkg -i modeller/modeller_9.17-1_amd64.deb
        fi
        log_success "MODELLER installation complete (function ->UbuntuInstall_64)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Installing 64-bit MGLTools (function ->UbuntuInstall_64)"
            log_info "Granting execution permission to MGLTools installer (function ->UbuntuInstall_64)"
            chmod +x mgltools_Linux-x86_64_1.5.6_Install
            log_success "Execution permission granted (function ->UbuntuInstall_64)"
            log_info "Running MGLTools setup (function ->UbuntuInstall_64)"
            ./mgltools_Linux-x86_64_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->UbuntuInstall_64)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->UbuntuInstall_64)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
        done
        log_success "Bash file update complete (function ->UbuntuInstall_64)"
        log_success "MGLTools installation complete (function ->UbuntuInstall_64)"
    fi
}

function CentosInstall {
    # # JDK installation # #
    log_info "Installing JDK (function ->CentosInstall)"
    yum install -y java-1.8.0-openjdk-devel
    log_success "JDK installation complete (function ->CentosInstall)"

    # # PyMOL installation # #
    log_info "Installing PyMOL prerequsite"
    yum install -y subversion gcc gcc-c++ kernel-devel python-devel tkinter glew-devel \
    freeglut-devel libpng-devel freetype-devel libxml2-devel numpy
    mkdir -p /tmp/pymol
    log_success "PyMOL prerequsites installed"
    log_info "Running Pmw setup"
    cd Pmw-2.0.0; python setup.py install; cd $(echo $SCRIPT_DIR)
    log_success "Pmw setup complete"
    log_success "Prerequsite installation complete"

    if [[ "$HOSTTYPE" == "i686" || "$HOSTTYPE" == "i386" ]]; then
        # # AutoDock installation
        log_info "Installing AutoDock Suite (function ->CentosInstall_32)"
        log_info "Moving AutoDock binaries to /usr/local/bin (function ->CentosInstall_32)"
        cp autodock/x86/autodock/* /usr/local/bin
        log_success "AutoDock installation complete (function ->CentosInstall_32)"
        log_info "Moving AutoDock Vina to /usr/local/bin (function ->CentosInstall_32)"
        cp autodock_vina/bin/* /usr/local/bin
        log_success "AutoDock Vina installation complete (function ->CentosInstall_32)"
        log_success "AutoDock Suite installation complete (function ->CentosInstall_32)"

        # # MODELLER installation # #
        if [ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]; then
            log_info "Installing 32-bit MODELLER (function ->CentosInstall_32)"
            env KEY_MODELLER=$1 rpm -Uvh modeller/modeller-9.17-1.x86_64.rpm
        fi
        log_success "MODELLER installation complete (function ->CentosInstall_32)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Installing 32-bit MGLTools (function ->CentosInstall_32)"
            log_info "Granting execution permission to MGLTools installer (function ->CentosInstall_32)"
            chmod +x mgltools_Linux-x86_1.5.6_Install
            log_success "Execution permission granted (function ->CentosInstall_32)"
            log_info "Running MGLTools setup (function ->CentosInstall_32)"
            ./mgltools_Linux-x86_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->CentosInstall_32)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->CentosInstall_32)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
        done
        log_success "Bash file update complete (function ->CentosInstall_32)"
        log_success "MGLTools installation complete (function ->CentosInstall_32)"
    else
        # # AutoDock installation
        log_info "Installing AutoDock Suite (function ->CentosInstall_64)"
        log_info "Moving AutoDock binaries to /usr/local/bin (function ->CentosInstall_64)"
        cp autodock/x86_64/autodock/* /usr/local/bin
        log_success "AutoDock installation complete (function ->CentosInstall_64)"
        log_info "Moving AutoDock Vina to /usr/local/bin (function ->CentosInstall_64)"
        cp autodock_vina/bin/* /usr/local/bin
        log_success "AutoDock Vina installation complete (function ->CentosInstall_64)"
        log_success "AutoDock Suite installation complete (function ->CentosInstall_64)"

        # # MODELLER installation # #
        if [ $(which mod9.16 &>/dev/null; echo $?) -ne 0 ]; then
            log_info "Installing 64-bit MODELLER (function ->CentoInstall_64)"
            env KEY_MODELLER=$1 rpm -Uvh modeller/modeller-9.17-1.x86_64.rpm
        fi
        log_success "MODELLER installation complete (function ->CentosInstall_64)"

        # # MGLTools installation # #
        if [ ! -d /usr/local/MGLTools-1.5.6 ]; then
            log_info "Installing 64-bit MGLTools (function ->CentosInstall_64)"
            log_info "Granting execution permission to MGLTools installer (function ->CentosInstall_64)"
            chmod +x mgltools_Linux-x86_64_1.5.6_Install
            log_success "Execution permission granted (function ->CentosInstall_64)"
            log_info "Running MGLTools setup (function ->CentosInstall_64)"
            ./mgltools_Linux-x86_64_1.5.6_Install
        fi
        log_success "MGLTools setup complete (function ->CentosInstall_64)"

        for person in $(users); do
            log_info "Updating Bash file for user: $person (function ->CentosInstall_64)"
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/bin' >> /home/$person/.bashrc
            echo 'export PATH=$PATH:/usr/local/MGLTools-1.5.6/MGLToolsPckgs/AutoDockTools/Utilities24' >> /home/$person/.bashrc
        done
        log_success "Bash file update complete (function ->CentosInstall_64)"
        log_success "MGLTools installation complete (function ->CentosInstall_64)"
    fi
}

function dockomaticInstall {
    log_info "Installing DockoMatic"
    for person in $(users); do
        log_info "Copying DockoMatic build into user $person HOME directory"
        cp -R dockomatic /home/$person
        log_success "Copying complete for user $person"
        log_info "Changing ownership from root to $person"
        chown -R $person /home/$person/dockomatic
        log_success "Ownership transfer complete"
        log_info "Granting execution permission to swarm"
        chmod +x /home/$person/dockomatic/dockomatic/lib/swarm
        log_success "Execution permission granted to swarm"
        log_info "Updating Bash file for user $person"
        echo 'export PATH=$PATH:/home/$USER/dockomatic/bin' >> /home/$person/.bashrc
        echo 'export PATH=$PATH:/home/$USER/dockomatic/dockomatic/lib' >> /home/$person/.bashrc
        log_success "Bash file update complete"
    done
    log_success "DockoMatic installation complete"
}
