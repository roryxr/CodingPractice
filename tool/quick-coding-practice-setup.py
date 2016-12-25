#!/usr/bin/python

import sys
import os

if len(sys.argv) < 2:
  sys.exit('Usage: %s <directory-name>' % sys.argv[0])

dir_path = sys.argv[1]
if not os.path.exists(dir_path):
  os.makedirs(dir_path);
else:
  sys.exit('Directory %s already exists' % dir_path)

f = open(dir_path + '/Solution.java', 'w')
f.write('public class Solution {\n\n}')
f.close()
