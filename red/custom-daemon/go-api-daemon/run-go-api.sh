#!/bin/bash
ps axjf | grep "./main" | grep -wv color | tail -n 1 | tr -s '\t' ' ' | cut -d ' ' -f 3 | { read msg; kill -9 "$msg"; } 2> /dev/null
echo "running Go API in persistence ..."
/home/cookie/Git/log4shell-white-box/red/custom-daemon/go-api-daemon/./main &
#/root/tools/log4shell-white-box/red/custom-daemon/go-api-daemon/./main
