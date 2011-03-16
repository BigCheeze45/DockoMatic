#!/usr/bin/perl
#
# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "resultCheck.pl"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#
# Last Modified
#   Date: "June 14, 2010"
#
#    This file is part of DockoMatic.
#
#    DockoMatic is free software: you can redistribute it and/or modify
#    it under the terms of the GNU Lesser General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    DockoMatic is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU Lesser General Public License for more details.
#
#    You should have received a copy of the GNU Lesser General Public License
#    along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#


use File::Spec::Functions;
use Getopt::Std;

# Global variables... Not sure why there are so many here. This
# should be changed.
# Maybe because there are no methods, and it is one big
# script... this should be changed.
my $nptsX;
my $nptsY;
my $nptsZ;

my $maxX;
my $maxY;
my $maxZ;
my $minX;
my $minY;
my $minZ;

my $centerX;
my $centerY;
my $centerZ;

my $outx;
my $outy;
my $outz;

my $pass = 0;
my $nopass = 0;
my $passTotal = 0;
my $nopassTotal = 0;
my $numConfs = 0;
my $totalLigs = 0;
my $rank = 0;
my $totalKi;
my $kiCount = 0;
my $totalFe;
my $feCount = 0;
my $highestFe;
my $outdir = "./";


getopts('d:f:b:o:');

if(!$opt_b and !($opt_d or $opt_f)){die "USAGE: resultsChecker.pl -f <.dlg filename> -b <.gpf filename>\n OR
       resultsChecker.pl -d <Directory to check> -b <.gpf filename> -o <Directory to write output to>\n"; 
} 

if($opt_o){ $outdir = $opt_o; }

# get output (.dlg) filename and grid box (.gpf) filename
my $gbfname = $opt_b;

my @dirs;
my @subdirFiles;
my @files;

# get all subirectory names from main output directory.  
# Then get all the file names from the dock_# directories.
# Then save names of only the .dlg files.
if($opt_d){
    my $dir;
    opendir MAINDIR, $opt_d or die "Unable to open directory $opt_d!!\n$!\n";
    @dirs = readdir(MAINDIR);
    close MAINDIR;
    foreach $dir (@dirs){
      $dir = catfile($opt_d,$dir);

      if($dir =~ /dock_/){
        opendir SUBDIR, $dir;
        @subdirFiles = readdir(SUBDIR);
        close SUBDIR;
        foreach (@subdirFiles){
                if($_ =~ /\.dlg/){  push (@files, catfile($dir, $_));  }
        }
      }
    }
}else{
    @files = ($opt_f);
}

# walk through all dlg files.
my $ofname;
foreach $ofname (@files){
    $totalLigs = 0;    
    $passTotal = 0;    
    $nopassTotal = 0;    
    $totalKi = 0;
    $totalFe = 0;
    my $ki = 0;    
    my $fe = 0;    
    my $bestKi;
    my $bestFe;
    my $feRank;
    my $kiRank;
    my $gotFirst = 0;

    $ofname =~ /(\/*)(\w+)\.dlg/;
    #$outdir .= "/CheckResultsLog_$2.txt";

#printf"WRITING res to $outdir/CheckResultsLog_$2.txt\n";
    # open log file
    open RESLOG, ">$outdir/CheckResultsLog_$2.txt" or die "Could not open results log file for writing!!\n";
    
    # open, read in AutoDock output (.dlg) file contents, close file
    open OFFILE, $ofname or die "Unable to open dlg $ofname for reading!!!\n$_\n";
    my @oflines = <OFFILE>;
    close OFFILE;
    
    # open, read in Grid Box Coordinate (.gpf) file contents, close file
    open GBFFILE, $gbfname or die "Unable to open gbf $gbfname for reading!!!\n$_\n";
    my @gbflines = <GBFFILE>;
    close GBFFILE;
    
    # get gridcenter, npts, and spacing granularity from gridbox file.
    # The spacing must be multiplied by npts to get the true values
    # for the values of the outside of the box.  The npts is total 
    # box size instead of size from center, sort of like diameter
    # vs radius in a circle.
    foreach $_ (@gbflines){
        if($_ =~ /npts/){
            $_ =~ /npts (\d+\.*\d*)\s(\d+\.*\d*)\s(\d+\.*\d*)/;
            $nptsX = $1;
            $nptsY = $2;
            $nptsZ = $3;
        }

        if($_ =~ /spacing/){
            $_ =~ /spacing (\d+\.*\d*)\s+\#/;
            $nptsX *= $1;
            $nptsY *= $1;
            $nptsZ *= $1;
        }

        if($_ =~ /gridcenter/){
            $_ =~ /gridcenter (\d+\.*\d*)\s(\d+\.*\d*)\s(\d+\.*\d*)/;
            $centerX = $1;
            $centerY = $2;
            $centerZ = $3;
    
            last;
        }
    }
    
    # Maximum coordinates of box.
    $maxX = $centerX + $nptsX/2;
    $maxY = $centerY + $nptsY/2;
    $maxZ = $centerZ + $nptsZ/2;
    $minX = $centerX - $nptsX/2;
    $minY = $centerY - $nptsY/2;
    $minZ = $centerZ - $nptsZ/2;

    # walk through ligand looking for atoms that are inside range.
    # If any are inside, tally as pass and we go to next ligand. 
    # Setting pass to 1 multiple times is OK, since we only add
    # to the total ligand count at the end.
    foreach (@oflines){
        # get rank # we are looking at for logging purposes.
        if($_ =~ /^USER\s+Cluster Rank = (\d+)/){
            $rank = $1;
            print RESLOG "Looking at Rank #$rank\n";
        }
    
        # Take into consideration repeated ligands.  We need
        # to count these as multiple ligands.
        if($_ =~ /^USER(.*)conformations(.*)= (\d+)/){
            $numConfs = $3;
        }
        
        if($_ =~ /^USER(\s+)Estimated Free Energy(.*)=(.*)kcal(.*)/){
            $fe = $3;
            chomp $fe; 
        }

        if($_ =~ /^USER(\s+)Estimated Inhibition(.*)=(.*)(\s+)(.)M(.*)/){
            $ki = $3;
            chomp $ki; 
            if($5 =~ /m/){ $ki *= 1000;} #milli
            if($5 =~ /n/){ $ki /= 1000;} #nano
        }
        
        # skip to meat.
        if($_ !~ /^ATOM/ && $_ !~ /^ENDMDL/){
            next;
        }
    
        # walk through ligands
        if($_ =~ /^ATOM/){
            $_ =~ /^ATOM(.*)\s+(\d+\.*\d*)\s+(\d+\.*\d*)\s+(\d+\.*\d*)\s+[+-](.*)/;
            $outx = $2;
            $outy = $3;
            $outz = $4;
    
    
            # calculate x, y, z stuff.  If any atoms inside box, pass = 1.
            if($pass == 0){
                if(($outx >= $minX and $outx <= $maxX) and 
                    ($outy >= $minY and $outy <= $maxY) and 
                     ($outz >= $minZ and $outz <= $maxZ)){ $pass = 1;}# print RESLOG "CULPRIT $_";}
       
                }
        }
    
        # end of ligand, so add the pass value and increment total number of ligands
        # evaluated.
        if($_ =~ /^ENDMDL/){
            if($pass == 1){
                print RESLOG "PASS\n\n"; 
                if($gotFirst == 0){ 
                    $bestFe = $fe; $feRank = $rank;
                    $bestKi = $ki; $kiRank = $rank;
                    $gotFirst = 1;
                }
                if($bestKi > $ki){ $bestKi = $ki; $kiRank = $rank; }
                if($bestFe > $fe){ $bestFe = $fe; $feRank = $rank; }
                $totalKi += ($ki * $numConfs);
                $totalFe += ($fe * $numConfs);
             }else{
                print RESLOG "NO PASS\n\n";
             }
            $totalLigs += $numConfs;
            $passTotal += ($pass*$numConfs);
            $pass = 0;
        }
    }
    
    my $perc = 0;
    if ($passTotal > 0){ $perc = 100*($passTotal/$totalLigs);}
    $nopassTotal = 100 - $passTotal;

    # report percentage pass to log file
    print RESLOG "$ofname  &&\n$opt_b\n    $passTotal ligands out of $totalLigs PASSED ==> $perc %\n";
    print RESLOG "    NOT pass ==> $nopassTotal %\n";
    
    # report percentage pass to stdout
    print "$ofname  &&\n$opt_b\n    $passTotal ligands out of $totalLigs PASSED ==> $perc %\n";
    print "    NOT pass ==> $nopassTotal %\n";
    if($passTotal > 0){
        $totalKi /= $passTotal;
        $totalFe /= $passTotal;
    }
        
    print RESLOG "Best Estimated Free Energy of Binding for passing conformations = $bestFe kcal/mol from rank #$feRank\n";
    print        "Best Estimated Free Energy of Binding for passing conformations = $bestFe kcal/mol from rank #$feRank\n";
    print RESLOG "Avg Estimated Free Energy of Binding for passing conformations = $totalFe kcal/mol\n";
    print        "Avg Estimated Free Energy of Binding for passing conformations = $totalFe kcal/mol\n";
    print RESLOG "Best Ki of passing conformations = $bestKi uM (micromolar) from rank #$kiRank\n";
    print        "Best Ki of passing conformations = $bestKi uM (micromolar) from rank #$kiRank\n";
    print RESLOG "Avg Ki of passing conformations = $totalKi uM (micromolar)\n";
    print        "Avg Ki of passing conformations = $totalKi uM (micromolar)\n\n";
    close RESLOG;
}

