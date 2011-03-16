#!/usr/bin/perl
#
# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "changePyHeaders.pl"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#
# Last Modified
#   Date: "August 6, 2009"
#   Date: "May 7, 2010"
#
# Copyright 2009, Casey Bullock
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

# Do the python scripts...
chdir ("scripts");
my $curDir = qx(pwd);
chomp $curDir;
opendir DIR, "MGLTools/MGLToolsPckgs/AutoDockTools/Utilities24" or die "Can't open scripts directory [$curDir/MGLTools/MGLToolsPckgs/AutoDockTools/Utilities24]!";

# get all filenames inside Utilities24 directory.
my @files = readdir(DIR);
close DIR;

chdir ("MGLTools/MGLToolsPckgs/AutoDockTools/Utilities24/");

# walk through all filenames, changing header values in all 
# python scripts to reflect location of MGLTools.
foreach (@files){
  if($_ =~ /\.py/){
#print"Modifying $_\n";
    open FILE, $_;
    my @lines = <FILE>;
    close FILE;
    #$lines[0] =~ s/(.*)\/MGLTools-1\.5\.4/#!\/usr\/bin\/env $curDir\/MGLTools/;
    $lines[0] = "#!\/usr\/bin\/env $curDir\/MGLTools\/bin\/pythonsh\n";
    open FILE, ">$_";
    print FILE @lines;
    close FILE;
  }
}

# Do the pythonsh file...
# First we open file and read contents...
chdir ("$curDir/MGLTools/bin");
open PYTHONSH, "pythonsh";
my @lines = <PYTHONSH>;
close PYTHONSH;

# Then we rewrite file with modifications.
open PYTHONSH, ">pythonsh";
foreach (@lines){
    $_ =~ s/MGL_ROOT="(.*)"/MGL_ROOT="$curDir\/MGLTools"/;
    
    print PYTHONSH $_;
}
close PYTHONSH;


#print "DONE\n";
