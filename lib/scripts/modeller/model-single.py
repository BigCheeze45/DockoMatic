#! /usr/bin/python
import sys
import os

seq = sys.argv[1]
tmpl = sys.argv[2]
num = sys.argv[3]
out = sys.argv[4]

from modeller import *
from modeller.automodel import *

env = environ()
a = automodel(env, alnfile=out+'/'+seq+'-'+tmpl+'.ali',
              knowns=tmpl, sequence=seq,
              assess_methods=(assess.DOPE, assess.GA341))
#a.starting_model = int(num)
a.starting_model = 1
a.ending_model = int(num)
a.make()

