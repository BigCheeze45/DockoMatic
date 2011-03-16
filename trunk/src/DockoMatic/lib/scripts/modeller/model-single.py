#! /usr/bin/python
import sys

seq = sys.argv[1]
tmpl = sys.argv[2]
out = sys.argv[3]

from modeller import *
from modeller.automodel import *

env = environ()
a = automodel(env, alnfile=out+'/'+seq+'-'+tmpl+'.ali',
              knowns=tmpl, sequence=seq,
              assess_methods=(assess.DOPE, assess.GA341))
a.starting_model = 1
a.ending_model = 5
a.make()
