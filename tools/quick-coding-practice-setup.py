#!/usr/bin/python

import sys
import os
import stat

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

f = open(dir_path + '/run', 'w')
f.write('#!/bin/bash\n')
f.write('javac Solution.java\n')
f.write('java Solution\n')
f.close()

st = os.stat(dir_path + '/run')
os.chmod(dir_path + '/run', st.st_mode | stat.S_IEXEC)
