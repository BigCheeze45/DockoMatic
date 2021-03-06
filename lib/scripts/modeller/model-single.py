#! /usr/bin/python
import sys

seq = sys.argv[1]
tmpl = sys.argv[2]
num = sys.argv[3]
out = sys.argv[4]

from modeller import *
from modeller.automodel import *

env = environ(rand_seed = -2 - int(num))
a = automodel(env, alnfile=out+'/'+seq+'_pir.ali',
              knowns=tmpl, sequence=seq,
              assess_methods=(assess.DOPE, assess.GA341))
a.starting_model = a.ending_model = int(num)
#a.starting_model = 1
#a.ending_model = int(num)
a.make()

