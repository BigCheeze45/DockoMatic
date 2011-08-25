#! /usr/bin/python

import sys
import os

seq = sys.argv[1]
tmpl = sys.argv[2]
out = sys.argv[3]
sub = sys.argv[4]

#print seq+'.ali'
#print tmpl+'.pdb'
#print seq+'-'+tmpl+'.ali'
#print seq+'-'+tmpl+'.pap'
#sys.exit(0)

from modeller import *

env = environ()
aln = alignment(env)
mdl = model(env, file=tmpl, model_segment=('FIRST:'+sub,'END:'+sub))
aln.append_model(mdl, align_codes=tmpl, atom_files=tmpl+'.pdb')
aln.append(file=seq+'.ali', align_codes=seq)
aln.align2d()
aln.write(file=out+'/'+seq+'.pir', alignment_format='PIR')
aln.write(file=out+'/'+seq+'.pap', alignment_format='PAP')

os.system("ls *.pap > alLog");

