#!/usr/bin/perl
#
# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "dockOmatic.pl"
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
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#
#

# perl Modules
use File::Spec;
use File::Spec::Functions;
use File::Temp;
use File::Copy;
use Cwd;
use Cwd qw(abs_path);
use Getopt::Std;

###  Global Variables  ###

# hash of input file names. 
my %fnames = (
    "Adn", catfile('inputFiles', 'ALA_A_Down.pdb'),
    "Aup", catfile('inputFiles', 'ALA_A_UP.pdb'),
    "Bdn", catfile('inputFiles', 'Backbone_Down.pdb'),
    "Bup", catfile('inputFiles', 'Backbone_Up.pdb'),
    "Rdn", catfile('inputFiles', 'ARG_R_Down.pdb'),
    "Rup", catfile('inputFiles', 'ARG_R_UP.pdb'),
    "Ndn", catfile('inputFiles', 'ASN_N_Down.pdb'),
    "Nup", catfile('inputFiles', 'ASN_N_UP.pdb'),
    "Ddn", catfile('inputFiles', 'ASP_D_Down.pdb'),
    "Dup", catfile('inputFiles', 'ASP_D_UP.pdb'),
    "Cdn", catfile('inputFiles', 'CYS_C_Down.pdb'),
    "Cup", catfile('inputFiles', 'CYS_C_UP.pdb'),
    "Qdn", catfile('inputFiles', 'GLN_Q_Down.pdb'),
    "Qup", catfile('inputFiles', 'GLN_Q_UP.pdb'),
    "Edn", catfile('inputFiles', 'GLU_E_Down.pdb'),
    "Eup", catfile('inputFiles', 'GLU_E_UP.pdb'),
    "Gdn", catfile('inputFiles', 'GLY_G_Down.pdb'),
    "Gup", catfile('inputFiles', 'GLY_G_UP.pdb'),
    "Hdn", catfile('inputFiles', 'HIS_H_Down.pdb'),
    "Hup", catfile('inputFiles', 'HIS_H_UP.pdb'),
    "Idn", catfile('inputFiles', 'ILE_I_Down.pdb'),
    "Iup", catfile('inputFiles', 'ILE_I_UP.pdb'),
    "Ldn", catfile('inputFiles', 'LEU_L_Down.pdb'),
    "Lup", catfile('inputFiles', 'LEU_L_UP.pdb'),
    "Kdn", catfile('inputFiles', 'LYS_K_Down.pdb'),
    "Kup", catfile('inputFiles', 'LYS_K_UP.pdb'),
    "Mdn", catfile('inputFiles', 'MET_M_Down.pdb'),
    "Mup", catfile('inputFiles', 'MET_M_UP.pdb'),
    "Fdn", catfile('inputFiles', 'PHE_F_Down.pdb'),
    "Fup", catfile('inputFiles', 'PHE_F_UP.pdb'),
    "Pdn", catfile('inputFiles', 'PRO_P_Down.pdb'),
    "Pup", catfile('inputFiles', 'PRO_P_UP.pdb'),
    "Sdn", catfile('inputFiles', 'SER_S_Down.pdb'),
    "Sup", catfile('inputFiles', 'SER_S_UP.pdb'),
    "Tdn", catfile('inputFiles', 'THR_T_Down.pdb'),
    "Tup", catfile('inputFiles', 'THR_T_UP.pdb'),
    "Wdn", catfile('inputFiles', 'TRP_W_Down.pdb'),
    "Wup", catfile('inputFiles', 'TRP_W_UP.pdb'),
    "Ydn", catfile('inputFiles', 'TYR_Y_Down.pdb'),
    "Yup", catfile('inputFiles', 'TYR_Y_UP.pdb'),
    "Vdn", catfile('inputFiles', 'VAL_V_Down.pdb'),
    "Vup", catfile('inputFiles', 'VAL_V_UP.pdb')
    );
        
my $obconfPid = 0;
my $gridPid = 0;
my $dockPid = 0;
my $treePid = 0;

# lines of text from first and second input files
my @flines_sec;
my @flines_first;

# Array of values from lines with "CONECT" at the beginning
my @conArr;

# Array of values from lines with "Atom" at the beginning
my @atomArr;

# Array holding individual tokens from input string (ligand to be created)
my @tokens;

my $INFILE;
my $OUTFILE;

my $i = 0;

# 3D coordinates for F (or I) from first input file.  These are the 
# values that the next amino added will be normalized to.
my $first_x = 0;
my $first_y = 0;
my $first_z = 0;

# Connect values from F, CL, and N from first and second files.
my $firstF_Con = 0;
my $secondF_Con = 0;
my $secondCl_Con = 0;
my $secondN_Con = 0;

my $maxCount = 0;

# New 3D values obtained by normalizing
my $x_const;
my $y_const;
my $z_const;

my $dockLog;
my $dirPath;
my $fileCount;
my $aminoName;

my $sourceDir = abs_path($0);
$sourceDir =~ s/dockOmatic.pl//;;

#chdir($sourceDir);
#system("changePyHeaders.pl");


my $pdbname;
my $enwRecept;
my $tmpBox;
$opt_o = ".";

###  Start Of Code  Get Command Line Arg & call createLigand if applicable  ### 

    getopts ('kho:p:r:b:a:m:t:');

    if ((!($opt_p or $opt_d) and !($opt_m and $opt_t)) or $opt_h) {die "USAGE: dockOmatic.pl -p <peptide> [-a <filename>] [-k] [-o <directory>] [-r <filename> -b <filename>] [-h] 
      -p name of peptide, either already in .pdb format (must supply .pdb extension), or string of Amino Acids (e.g. AGTHY).
         Usually a drug would be used in the .pdb format, and would be used along with the -a option.
      -a name of peptide, already in .pdb format. This would be the peptide we will append to the receptor to see how the '-p' peptide (drug) will dock to it.
      -k option to keep the tmpOut* files. Each tmpOut* file represents the state prior to the next append.
         For instance, for sequence ARG, tmpOut2 would represent a pdb file for AR, but without the Oxygen 
         appended, since this step occurs at the end of processing.
      -o option for output directory. Directory to store output files.
      -r receptor file name (.pdb file).  Must be used in conjunction with -b option.
      -m Use Modeller.
      -b box coordinate file name (.gpf file). Must be used in conjunction with -r option.
      -h displays this message.\n";}
    

    # Modeller 
    if($opt_m =~ /(\w+)/){
        # Just in case no extensions, we add them.
        $opt_m =~ s/\.ali//; 
        $opt_t =~ s/\.pdb//; 
        #copy($opt_m.'.ali', $opt_o);
        #copy($opt_t.'.pdb', $opt_o);
        chdir($opt_o);
        my ($mVolume, $mDirs, $mName) = File::Spec->splitpath($opt_m);
        my ($tVolume, $tDirs, $tName) = File::Spec->splitpath($opt_t);

        my $newAli = makeNewAli($mName, $tName);
        runAlign($newAli, $tName); 
        runModel($newAli, $tName); 
        #runAlign($mName, $tName); 
        #runModel($mName, $tName); 
        die"Ran Modeller... DONE\n"; 
    }
    if($opt_p =~ /\.pdb/){
        if($opt_r){
            copy($opt_r, $opt_o);
            copy($opt_b, $opt_o);
            my ($rVolume, $rDirs, $rName) = File::Spec->splitpath($opt_r);
            my ($bVolume, $bDirs, $bName) = File::Spec->splitpath($opt_b);
            $newRecept = $rName;
            $tmpBox = $bName;
            if($opt_a){
                copy($opt_a, $opt_o);
                my ($aVolume, $aDirs, $aName) = File::Spec->splitpath($opt_a);
                $newRecept = ligPlusRecept($aName, $rName); 
                $newRecept =~ /(\w+).pdb/;
                $tmpBox = $1. ".gpf";
            }
        }
        if($opt_p =~ /:/){
            $pdbname = sidechainExchange();
            if(!$opt_r){ die "DONE\n"; }
        }else{
            copy($opt_p, $opt_o);
            my ($pVolume, $pDirs, $pName) = File::Spec->splitpath($opt_p);
            $pdbname = $pName;
            chdir($opt_o);
            extractLig($pName, 1);
        }
        gridAndDock($newRecept, $tmpBox, $pdbname);

    }else{
        $opt_p =~ tr/[a-z]/[A-Z]/;
        my $pdb = createLigand();
        if($opt_r and $opt_b){
            copy($opt_r, $opt_o);
            copy($opt_b, $opt_o);
            my ($rVolume, $rDirs, $rName) = File::Spec->splitpath($opt_r);
            my ($bVolume, $bDirs, $bName) = File::Spec->splitpath($opt_b);
            my $newRecept = $rName;
            my $tmpBox = $bName;
    
            if($opt_a){
                copy($opt_a, $opt_o);
                my ($aVolume, $aDirs, $aName) = File::Spec->splitpath($opt_a);
                $newRecept = ligPlusRecept($aName, $rName); 
                $newRecept =~ /(\w+).pdb/;
                $tmpBox = $1. ".gpf";
            }

            gridAndDock($newRecept, $tmpBox, $pdb);
        }
    }


###  Subroutines  ###

# Main subroutine loops through Amino Acid files appending the next to the info obtained from the previous one(s).
# Called it createLigand because, well, it is the main method used for creating ligands.  Kind of a monstrosity.
sub createLigand{

    my $pdbOut;

    # Interleave B for backbones, unless the amino is Proline.
    my $toks = join( ":B:", split (//, $opt_p));
    $toks = "B:" . $toks;
    $toks =~ s/B:P/P/g;
    @tokens = split (/:/, $toks);
    $fileCount = 0;

    chdir($opt_o);

    $dirPath = catfile($sourceDir, 'inputFiles', 'Beginning.pdb');
    open $INFILE, $dirPath or die "$!\nDIRPATH $dirPath";
    @flines_first = <$INFILE>;
    close $INFILE;
    calcCounts();
    $i = 1;
    my $orientCounter = 0;

    my $aminoFile;
    # Loop through Amino Acids
    foreach $token (@tokens){
        if(($orientCounter < 2)){
            $token .= "up";
            if($token =~ /P/){ $orientCounter++; }
        }else{
            $token .= "dn";
            if($token =~ /P/){ $orientCounter++; }
        }
        if($orientCounter == 3){ 
            $orientCounter = 0; 
        }else{
            $orientCounter++;
        }

        open $OUTFILE, ">tmpOut$i.pdb" or die"$!";
        getFirstFInfo($token);
        calcCounts();

    # Open second file and get info.
        $aminoFile = catfile($sourceDir, $fnames{$token});
        if(-e $aminoFile){
            open $INFILE, $aminoFile or die"$!";
        }else{
            cleanupTmpFiles();
            die "Can't open File for Amino Acid [$aminoFile]!";
        }
        @flines_sec = <$INFILE>;
        close $INFILE;

        getSecondClInfo($token);
        printFirstFInfo($token);
        if($token !~ /G/){ 
            addFiles($token);
        }
        if($token !~ /B/){ ++$fileCount; }
        print $OUTFILE "END\n";
        close $OUTFILE;
        open $OUTFILE, "tmpOut$i.pdb" or die "$!";
        @flines_first = <$OUTFILE>;
        close $OUTFILE;
        ++$i;
    
    }
    my $tmpToks = join ("", @tokens);
    $i = (length $tmpToks)/3;
    open $OUTFILE, "tmpOut$i.pdb" or die "$!";
    @flines_first = <$OUTFILE>;
    close $OUTFILE;
    $pdbOut = "$opt_p.pdb";

    open $OUTFILE, ">$pdbOut" or die "Unable to open [$pdbOut] for writing!";
    print $OUTFILE ("COMPND    $opt_p\n");
    print $OUTFILE ("AUTHOR    GENERATED BY dockOmatic.pl\n");
    getFirstFInfo("End");
    calcCounts();

    $dirPath = catfile($sourceDir, 'inputFiles', 'Ending.pdb');
    open $INFILE, $dirPath or die "$!\nDIRPATH $dirPath";
    @flines_sec = <$INFILE>;
    close $INFILE;
    getSecondClInfo("End");
    printFirstFInfo("End");
    addFiles("End");

    print $OUTFILE "END\n";
    close $OUTFILE;

    # run through obconformer to get H and O correct.
    my $conf = "conf_".$pdbOut;
    my $orig = "Orig_".$pdbOut;

    # To avoid zombie children if user kills jobs.
    $SIG{TERM} = \&REAPER;
    $obconfPid = fork;
    if($obconfPid){
        waitpid $obconfPid, 0;
        $obconfPid = 0;
    }else{
        exec("obconformer 5 10 $pdbOut > $conf");
    } 
    #qx(obconformer 5 10 $pdbOut > $conf);
    rename($pdbOut, $orig);
    rename($conf, $pdbOut);
    my $curDir = curdir();
    chomp $curDir;
    $dirPath = catfile($curDir, $pdbOut);
    print "PDB OUTPUT WRITTEN TO $dirPath\n";
    if(!$opt_k){ cleanupTmpFiles(); }

    return $pdbOut;
}
sub makeNewAli{
    my $seq = shift;
    my $tmplt = shift;

    my $ali = $seq."_".$tmplt;
    open $TMP, $seq.".ali" or die "Not able to open seq file [$seq\.ali]!";
    my @lines = <$TMP>;
    close $TMP;
    $lines[0] = ">P1;".$seq."_".$tmplt."\n";
    $lines[1] = "sequence:".$seq."_".$tmplt.":::::::0.00: 0.00\n";
    open $TMP, ">$ali\.ali" or die "Not able to open new ali file [$ali\.ali]!";
    print $TMP @lines;
    close $TMP;

    return $ali;
}

# runs Modeller code
sub runAlign{
    my $seq = shift;
    my $tmplt = shift;
    my $modDir = catfile($sourceDir, 'scripts', 'modeller');
  print "Running Align...\n"; 

    system("$modDir/align2d.py $seq $tmplt $opt_o");

    my $alnSntnl = catfile($opt_o, "AlnSntnl");
    open $ALIGNSNTNL, ">$alnSntnl" or die "Unable to open [$alnSntnl] for writing!";
    print $ALIGNSNTNL ("Align Done\n");
    close $ALIGNSNTNL;
    
}

# runs Modeller code
sub runModel{
    my $seq = shift;
    my $tmplt = shift;
    my $modDir = catfile($sourceDir, 'scripts', 'modeller');
  print "Running model-single...\n"; 

    system("$modDir/model-single.py $seq $tmplt $opt_o");

    my $modSntnl = catfile($opt_o, "ModSntnl");
    open $MODSNTNL, ">$modSntnl" or die "Unable to open [$modSntnl] for writing!";
    print $MODSNTNL ("Modeller Done\n");
    close $MODSNTNL;
    
}

# Exchanges sidechains in ligand or protein.
# should we run the ligand through extractLig method?
sub sidechainExchange{
    my @list = split(/:/, $opt_p);
    my $bigLig = shift @list;
    #copy($bigLig, $opt_o);

    my ($pVolume, $pDirs, $newPdb) = File::Spec->splitpath($opt_p);
    $newPdb .= "\.pdb";
    $newPdb =~ s/:/_/g;

    #my ($pVolume, $pDirs, $lig) = File::Spec->splitpath($bigLig);
    my $dir = catfile($opt_o, $newPdb);
    copy($bigLig, $dir);
    chdir($opt_o);

    extractLig($newPdb, 0);

    foreach (@list){
        $_ =~ /(\w)(\d+)(\w)/;
        modLig($1, $2, $3, $newPdb);
    }
    return $newPdb;
}

sub modLig{
    my ($before, $molNum, $after, $mainLig) = @_;
    my ($LIGFILE, $IN);
    my $found = 0;
    my @oldAtoms = ();
    my $preAA = $molNum - 1;
    my $postAA = $molNum + 1;
    
    # If we use one letter amino identifier use this to get AA name, or if three letter, don't
    $after = $fnames{$after."up"};
    $after =~ s/inputFiles\/(\w{3})_.*/$1/;
    $before = $fnames{$before."up"};
    $before =~ s/inputFiles\/(\w{3})_.*/$1/;


    open $LIG, "$mainLig" or die "$!\nUnable to open [$mainLig] for reading!";
    my @ligLines = <$LIG>;
    close $LIG;

    #find residue in main ligand file and obtain range of line numbers.
    foreach (@ligLines){
        if($_ =~ /^ATOM/){
            $_ !~ /(\w+)(\s+)(\d+)(\s+)(\w+)(\s+)(\w{3})(\s+)(\w)(\s+)(\d+)(.*)/;
            if($11 == $molNum or $11 == $preAA or $11 == $postAA){
                push(@oldAtoms, $_); 
            }
            if($11 > $postAA){ last; }
        }
    }

    getSChain(\@oldAtoms, $before, $after, $molNum);
    runTreePack();
    chdir($opt_o);
    replaceAtoms($mainLig, $before, $after, $molNum);
}

# Gets the atoms for the old side chain plus the sidechain before and after
# so that when we call TreePack, the side chain atoms are adjusted in reference
# to the ones next to it.
sub getSChain{
    my ($oldAtoms_ref, $oldAA, $newAA, $molNum) = @_;
    my @oldAtoms = @{$oldAtoms_ref};
    my $atom;

    my $good = 1;
    open TMP, ">tmpSwitch.pdb", or die "$! Could not open tmp switch file!";
    

    foreach $atom (@oldAtoms){
        if(!$good and ($atom !~ /^ATOM(.*)$oldAA(.*)$molNum(.*)/)){ $good = 1; }
        if($good){ $atom =~ s/^ATOM(.*)$oldAA(.*)$molNum(.*)/ATOM$1$newAA$2$molNum$3/; print TMP $atom; }
        if($atom =~ /^ATOM(.*)CB\s+$newAA(.*)$molNum(.*)/){ $good = 0; }
    }
    close TMP;

}

# Calls the TreePack program for atom adjustment... Later this will be replaced with
# our own code.
sub runTreePack{
    my $in = "tmpSwitch.pdb";
    $in = catfile($opt_o, $in);
    $dirPath = catfile($sourceDir, 'scripts');
    my $out = catfile($opt_o, 'treeOut.pdb');
    chdir($dirPath);

    $treePid = fork;
    if($treePid){
        waitpid $treePid, 0;
        $treePid = 0;
    }else{
        exec("./SCATD -i $in -o $out");
    } 

}

# Replaces the atoms in the ligand/protein with those from the newly adjusted sidechain.
sub replaceAtoms{
    my ($lig, $oldAA, $newAA, $scNum) = @_;
    my $countFlag = 0;
    my $oldCount = 1;
    my @tmpArr;

    open IN, "treeOut.pdb";
    my @newAtoms = <IN>;
    close IN;

    open LIG, $lig; 
    my @oldAtoms = <LIG>;
    close LIG;
    
    open $OUT, ">$lig";

    foreach $atom (@oldAtoms){
        if(!$countFlag and $atom =~ /^ATOM(.*)$oldAA(.*)(\s+)$scNum(\s+)(.*)/){ 
            foreach $newatom (@newAtoms){
                if($newatom =~ /^ATOM(.*)$newAA(.*)/){
                    @tmpArr = split(/(\s+)/, $newatom);
                if($tmpArr[4] =~ /\d{2}/){
                printf $OUT (
                       "%-6s%5d %-5s%3s %s%4d    %8.3f%8.3f%8.3f%6.2f%6.2f           %-2s\n", 
                       $tmpArr[0],  $oldCount, $tmpArr[4], $tmpArr[6], $tmpArr[8], $tmpArr[10], 
                       $tmpArr[12], $tmpArr[14], $tmpArr[16], $tmpArr[18], $tmpArr[20],  $tmpArr[22]
                );
                }else{
                    printf $OUT (
                           "%-6s%5d  %-4s%3s %s%4d    %8.3f%8.3f%8.3f%6.2f%6.2f           %-2s\n", 
                           $tmpArr[0], $oldCount, $tmpArr[4], $tmpArr[6], $tmpArr[8], $tmpArr[10], $tmpArr[12], 
                           $tmpArr[14], $tmpArr[16], $tmpArr[18], $tmpArr[20], $tmpArr[22]
                    );
                }
                    $oldCount++;
                }
            }
            $countFlag = 1;
        }
        if($atom !~ /(.*)$oldAA(.*)(\s+)$scNum(\s+)(.*)/){
            if($countFlag and ($atom =~ /^ATOM|^HETATM/)){ 
                @tmpArr = split(/(\s+)/, $atom);
                if($tmpArr[4] =~ /\d{2}/){
                printf $OUT (
                       "%-6s%5d %-5s%3s %s%4d    %8.3f%8.3f%8.3f%6.2f%6.2f           %-2s\n", 
                       $tmpArr[0],  $oldCount, $tmpArr[4], $tmpArr[6], $tmpArr[8], $tmpArr[10], 
                       $tmpArr[12], $tmpArr[14], $tmpArr[16], $tmpArr[18], $tmpArr[20],  $tmpArr[22]
                );
                }else{
                printf $OUT (
                       "%-6s%5d  %-4s%3s %s%4d    %8.3f%8.3f%8.3f%6.2f%6.2f           %-2s\n", 
                       $tmpArr[0],  $oldCount, $tmpArr[4], $tmpArr[6], $tmpArr[8], $tmpArr[10], 
                       $tmpArr[12], $tmpArr[14], $tmpArr[16], $tmpArr[18], $tmpArr[20],  $tmpArr[22]
                );
                }
            }elsif($atom =~ /^TER/){ 
                @tmpArr = split(/(\s+)/, $atom);
                printf $OUT (
                       "%-6s%5d  %-4s%3s %s%4d                                            \n", 
                       $tmpArr[0],  $oldCount, "", $tmpArr[4], $tmpArr[6], $tmpArr[8], 
                );
                #print $OUT $atom; 
            }else{ 
                print $OUT $atom; 
            }
            if($atom =~ /^ATOM/ or $atom =~ /^HETATM/ or $atom =~ /^TER/){ $oldCount++; }
        }
        #$oldCount++;
    }

    close $OUT;
}

# Extracts ligand info from suplied pbd file.  Some pdb files have a lot of
# extra info that was causing problems for us, so we just get the white meat.
# Bool arg added to remove Hydrogen atoms from output.
sub extractLig{
    my $lig = shift;
    my $bool = shift;
    my $line;

    open LIG, $lig or die "UNABLE TO OPEN $lig\n";

    my @lines = <LIG>;
    close LIG;
    open NEWLIG, ">tmplig" or die "UNABLE TO OPEN tmplig\n";

    foreach $line (@lines)
    {

        if($line =~ /^ENDMDL/){
            print NEWLIG "END\n";
            last;
        }
        if($line =~ /^TER/){
            print NEWLIG $line;
            print NEWLIG "END\n";
            last;
        }
        if($bool){
            print NEWLIG $line;
        }elsif($line !~ /^ATOM(\s*)(\d+)(\s*)H/){
            print NEWLIG $line;
        }
    }

    close NEWLIG;

    copy($lig, $lig."_old");
    copy("tmplig", $lig);
    unlink("tmplig");
}


# Calls the autogrid and autodock methods, if we are running a docking job.
sub gridAndDock{
    my $receptor = shift;
    my $box = shift;
    my $pdb = shift;

print "\n\n -------- GRID AND DOCK GOT  $receptor, $box, $pdb\n\n";
    prepLig( $pdb);
    prepRec( $receptor);

    my $dpfFile = prepDPF4( $pdb."qt", $receptor."qt");
    prepGPF($pdb, $receptor."qt", $box);
    autogridCmd( $box);
    autodockCmd( $dpfFile);
    
    parseDLG( $dockLog, $pdb);
    
}

# Removes tmpOut files for particular run.
sub cleanupTmpFiles{
    my $tmp = join ("", @tokens);
    for(1..((length $tmp)/3)){
        unlink("tmpOut$_.pdb"); 
    }
}

# Appends candidate ligand file (.pdb) to candidate receptor (.pdb) and 
# renames file to reflect the two inputs.
sub ligPlusRecept{
    my $pdb = shift;
    my $receptor = shift;

    $receptor =~ /(\w+)(\.pdb)/;
    $LigRecFile = "$1_$pdb";

    open(REC, $receptor) or die("Unable to Open File");
    open(PDB, $pdb) or die("Unable to Open PDB File");
    open(LIGOUT, ">>$LigRecFile") or die("Unable to open Lir_Rec OutPut File");

    my @lines = <REC>;
    close(REC);
    print LIGOUT @lines;
    @lines = <PDB>;
    print LIGOUT @lines;
    close(PDB);
    close(LIGOUT);

    return $LigRecFile;

}

# Prints starting file contents out.  Later this will have the next Amino Acid's adjusted info appended to it.
sub printFirstFInfo{
    my $tok = shift;

    if($tok =~ /B/ || $tok =~ /End/){
    foreach $line (@flines_first){
        if($line =~ /^CONECT/){
             @conArr = split(/(\s+)/, $line);
             foreach $arg (@conArr){
                 if($arg == $firstF_Con){ $arg = $secondN_Con+$maxCount;}

                 $arg =~ s/(\s+)/ /;
                 if($arg !~ /(\d+)/){
                     printf $OUTFILE ("%s", $arg);
                 }elsif($arg =~ /(\d+)/) {
                     printf $OUTFILE ("%4d", $arg);
                 }elsif($arg =~ /(\s+)/){ 
                     print $OUTFILE ($arg);
                 }
             }
             print $OUTFILE "\n";

        }elsif($line !~ /END/ && $line !~ /F/ && $line !~ /^MASTER/ && $line !~ /^AUTHOR/){ print $OUTFILE $line;}
    }
    }else{
    foreach $line (@flines_first){
        $line =~ s/UNK/$aminoName/;
        if($line =~ /^CONECT/){
             @conArr = split(/(\s+)/, $line);
             foreach $arg (@conArr){
                 if($arg == $firstF_Con && $tok !~ /G/){ $arg = $secondN_Con+$maxCount;}

                 $arg =~ s/(\s+)/ /;
                 if($arg !~ /(\d+)/){
                     printf $OUTFILE ("%s", $arg);
                 }elsif($arg =~ /(\d+)/) {
                     printf $OUTFILE ("%4d", $arg);
                 }elsif($arg =~ /(\s+)/){ 
                     print $OUTFILE ($arg);
                 }
             }
             print $OUTFILE "\n";

        }elsif($tok =~ /G/ && $line =~ / I /){ 
            $line =~ s/ I / H /; 
            print $OUTFILE $line;
        }elsif($line !~ /END/ && $line !~ / I / && $line !~ /^MASTER/ && $line !~ /^AUTHOR/){ print $OUTFILE $line;}
    }
    }
}
    
# Gets info from file that is to be appended to.
# Obtains the coordinates and atom number of the F or I atom, so we know where to conect the N, CB, or O 
# atom from the molecule we will be adding and how to properly adjust the coordinates of the accompanying atoms.
sub getFirstFInfo {
    my $tok = shift;

    if($tok =~ /B/ || $tok =~ /P/ || $tok =~ /End/){
      foreach $line (@flines_first){
        if($line =~ /^ATOM(\s+)(\d+)(\s+)F(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
            $firstF_Con = $2;
            $first_x = $6;
            $first_y = $8;
            $first_z = $10;
        }
        if($line =~ /^CONECT(\s+)$firstF_Con(\s+)(\d+)/){
            $secondF_Con = $3;
        }
      }
    }else{
      foreach $line (@flines_first){
        if($line =~ /^ATOM(\s+)(\d+)(\s+)I(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
            $firstF_Con = $2;
            $first_x = $6;
            $first_y = $8;
            $first_z = $10;
        }
        if($line =~ /^CONECT(\s+)$firstF_Con(\s+)(\d+)/){
            $secondF_Con = $3;
        }
      }
    }
}

# Calculate new count numbers for appended file ( second column of pdb file used to identify molecules that are connected ).
# Allows us to update atom numbers of new molecules being added.
sub calcCounts{
        foreach (@flines_first){
            if($_ =~ /^ATOM(\s+)(\d+)(\s+)(.*)/){ if($maxCount<$2){ $maxCount=$2;} }
        }

}
    
# Gets and adjusts info from second input file ( Amino Acid to be appended to existing one(s) ).
# Looks for CL in the file and saves atom number so that later we can modify any CONECT entries that
# for CL.  Also gets the coordinates for N so that we can get the value to normalize all other atoms by.
# The constant is the difference between the F or I atom on the existing chain and the N, O, or CB atom
# on the molecule we are adding.
# Three scenarios exist depending upon if we are adding a backbone, sidechain, or the End molecule.
sub getSecondClInfo {
    my $tok = shift;
    $secondCl_Con = 0;
    if($tok =~ /B/ or $tok =~ /P/){
      foreach $line (@flines_sec){
        if($line =~ /^ATOM(\s+)(\d+)(\s+)CL(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondCl_Con = $2;
        }
        if($line =~ /^ATOM(\s+)(\d+)(\s+)N(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondN_Con = $2;
            $x_const = $first_x - $6;
            $y_const = $first_y - $8;
            $z_const = $first_z - $10;
        }
        if($line =~ /^CONECT/){
            last;
        }
      }
    }elsif($tok =~ /End/){
      foreach $line (@flines_sec){
        if($line =~ /^ATOM(\s+)(\d+)(\s+)CL(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondCl_Con = $2;
        }
        if($line =~ /^ATOM(\s+)(\d+)(\s+)O(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondN_Con = $2;
            $x_const = $first_x - $6;
            $y_const = $first_y - $8;
            $z_const = $first_z - $10;
        }
        if($line =~ /^CONECT/){
            last;
        }
      }
    }else{
      foreach $line (@flines_sec){
        if($line =~ /^ATOM(\s+)(\d+)(\s+)BR(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondCl_Con = $2;
          $aminoName = $4;
          $aminoName =~ s/.*(\w\w\w).*/$1/;
        }
        if($line =~ /^ATOM(\s+)(\d+)(\s+)CB(.*)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)([- ]\d+\.\d+)(\s+)(.*)/){
          $secondN_Con = $2;
            $x_const = $first_x - $6;
            $y_const = $first_y - $8;
            $z_const = $first_z - $10;
        }
        if($line =~ /^CONECT/){
            last;
        }
      }
    }

}

# Combines the first file and the second by adjusting the atoms in the second to relate to those of the first.
# Ignores any CL or BR atoms, since they go away.
sub addFiles {
    my $tok = shift;

    foreach $line (@flines_sec){
       if($line =~ /^ATOM/ && $line !~ /CL/ && $line !~ /BR/){
           @atomArr = split(/(\s+)/, $line);
           $atomArr[1] = "     ";
           $atomArr[2] += $maxCount;
           $atomArr[8] += $fileCount;
           $atomArr[10] += $x_const;
           $atomArr[12] += $y_const;
           $atomArr[14] += $z_const;

           if($tok =~ /End/){ $atomArr[6] = $aminoName;}
           printf $OUTFILE ("%-6s%5d  %-4s%3s  %4d    %8.3f%8.3f%8.3f                        %-2s\n", 
                   $atomArr[0],  $atomArr[2], $atomArr[4], $atomArr[6], $atomArr[8], $atomArr[10], $atomArr[12], $atomArr[14], $atomArr[20]);
       }elsif($line =~ /^CONECT/){
         @conArr = split(/(\s+)/, $line);
         foreach $arg (@conArr){ 
             if($arg =~ /(\s+)/){next;}

             if($arg !~ /(\d+)/){
                 printf $OUTFILE ("%-6s", $arg);
             }elsif($arg == $secondCl_Con){
                 $arg = $secondF_Con;
                 printf $OUTFILE ("%5d", $arg);
             }elsif($arg =~ /(\d+)/) {
                 $arg += $maxCount;
                 printf $OUTFILE ("%5d", $arg);
             }
         }
         print $OUTFILE "\n";
       }
    }
}   

# prepares receptor file to make it Autodock friendly. Converts pdb file to pdbqt file.
# Calls MGLTools script.
sub prepRec {

    my $recFileIn = shift;
    my $pdbqt = $recFileIn."qt";

    system("prepare_receptor4.py -r $recFileIn -o $pdbqt" );

}

# prepares ligand file to make it Autodock friendly. Converts pdb file to pdbqt file.
# Calls MGLTools script.
sub prepLig {

    my $ligFileIn = shift;
    my $pdbqt = $ligFileIn."qt";

    system( "prepare_ligand4.py -B 32 -l $ligFileIn -o $pdbqt" );

}

# Calls MGLTools script to prepare the gpf file.
sub prepGPF{
    my $pdb = shift;
    my $receptor = shift;
    my $gpf = shift;

    system( "prepare_gpf4.py -l $pdb -r $receptor -i $opt_b -o $gpf" );
}

# Calls MGLTools script to prepare the dpf file.
sub prepDPF4 {

    my $ligFileIn = shift;
    my $recFileIn = shift;

    $ligFileIn =~ /(\w+)\.pdbqt/;
    my $dpfFile = $1 . "_";
    $recFileIn =~ /(\w+)\.pdbqt/;
    $dpfFile .= $1 . ".dpf";

    system( "prepare_dpf4.py -p ga_run=100  -l $ligFileIn -r $recFileIn -o $dpfFile");
    return $dpfFile;
}

# Reaper comes and takes away orphaned children so they don't become zombies.
sub REAPER{
  my $sig = shift;
  kill 9, $obconfPid unless $obconfPid == 0;
  kill 9, $gridPid unless $gridPid == 0;
  kill 9, $dockPid unless $dockPid == 0;
  kill 9, $treePid unless $treePid == 0;
  die "DockoMatic got sig $sig\n";
}

# Calls autogrid with appropriate files.
sub autogridCmd {

    my $gpfIn = shift;
    print "Running autogrid.  This may take awhile...\n";

    $gpfIn =~ /(\w+)\.gpf/;
    my $tmpFname = $1;
    my $gridLog = $tmpFname . "_gridLog.glg";

    #system( "autogrid4 -p $gpfIn -l $gridLog" );
    $gridPid = fork;
    if($gridPid){
        waitpid $gridPid, 0;
        $gridPid = 0;
    }else{
    exec("autogrid4 -p $gpfIn -l $gridLog");
    } 
   print"autogrid log output written to $gridLog\n\n";
}

# Calls autodock with appropriate files.
sub autodockCmd {

    my $dpfIn = shift;
    print "Running autodock.  This may take quite awhile...\n";

    $dpfIn =~ /(\w+)\.dpf/;
    $dockLog = $1 . "_dockLog.dlg";

    #system( "autodock4 -p $dpfIn -l $dockLog" );
    $dockPid = fork;
    if($dockPid){
        waitpid $dockPid, 0;
        $dockPid = 0;
    }else{
        exec( "autodock4 -p $dpfIn -l $dockLog" );
    } 
    print"autodock log output written to $dockLog\n\n";

}

# Parses Autodock's output dlg file for relevant information.
sub parseDLG{

    my $dlgFile = shift;
    my $pdbIn = shift;
    my $PDBOUT;

    $pdbIn =~ s/\.pdb//;

    open DLG, $dlgFile or die"$!";
    my @dlg = <DLG>;
    close $DLG;

    my $ref = $pdbIn."_Reference";
    open REFFILE, ">$ref" or die "Can't open reference file [$ref] for writing!\n";

    foreach $line (@dlg){
       
        if($line =~ /^Number of conformations/){
            print REFFILE "$line\n\n";
        }

        if($line =~ /Cluster Rank = (\d+)/){
            open $PDBOUT, ">$pdbIn"."_rank_$1.pdb" or die "Could not open rank file [$PDBOUT] for writing.\n";
            $line =~ s/USER(\s+)//;
            print REFFILE $line;
            print $PDBOUT $line;
        }

        if($line =~ /^USER(.*)conformations/){
            $line =~ s/USER(\s+)//;
            print REFFILE $line;
            print $PDBOUT $line;
        }
        if($line =~ /^USER(.*)Energy of Binding/){
            $line =~ s/USER(\s+)//;
            print REFFILE $line;
            print $PDBOUT $line;
        }
        if($line =~ /^USER(.*)Inhibition Constant/){
            $line =~ s/USER(\s+)//;
            print REFFILE "$line\n";
            print $PDBOUT $line;
        }
        if($line =~ /^ATOM/){
            print $PDBOUT $line;
        }

        if($line =~ /^TER/){
            print $PDBOUT "END\n";
            close $PDBOUT;
        }
    }
    close REFFILE;

}
