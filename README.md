# Log4Shell // (n). Log4j Vulnerability Environment Box
This repo purposely built for Log4j vulnerability testing environment that based on ```CVE-2021-44228```. This environment provide guidance to build the sample client-server infrastructure in GUI desktop platform client, and some external exploit scripts that are used to penetrate the system. The whole infrastructure are based and tested on Linux system

## Java GUI-Client Structure ☕
```
.                                                                                                                                                                                                         
├── pom.xml 
├── src/                                                                                                                                                                                                                    
│   ├── log/                                                                                                                                                                                                                
│   │   ├── 03-2022/                                                                                                                                                                                                        
│   │   │   ├── app.log.21_03_2022                                                                                                                                                                                         
│   │   │   ├── app.log.22_03_2022                                                                                                                                                                                         
│   │   │   ├── app.log.23_03_2022                                                                                                                                                                                         
│   │   │   ├── app.log.25_03_2022                                                                                                                                                                                         
│   │   │   ├── app.log.26_03_2022                                                                                                                                                                                         
│   │   │   └── ...                                                                                                                                                                                        
│   │   ├── 04-2022/                                                                                                                                                                                                                                                                                                                                                                                             
│   │   ├── 05-2022/                                                                                                                                                                                                                                                                                                                                                                                              
│   │   └── app.starter.log                                                                                                                                                                                                
│   └── main/                                                                                                                                                                                                               
│       ├── java/                                                                                                                                                                                                           
│       │   └── hpc/                                                                                                                                                                                                        
│       │       └── mvn/                                                                                                                                                                                                    
│       │           └── ldap/                                                                                                                                                                                               
│       │               └── client/                                                                                                                                                                                         
│       │                   ├── Main.java                                                                                                                                                                                  
│       │                   ├── connection/                                                                                                                                                                                 
│       │                   │   ├── GetContext.java                                                                                                                                                                        
│       │                   │   └── LoadProperties.java                                                                                                                                                                    
│       │                   ├── controller/                                                                                                                                                                                 
│       │                   │   ├── Auth.java                                                                                                                                                                              
│       │                   │   ├── LdapOperation.java                                                                                                                                                                     
│       │                   │   ├── LogPanel.java                                                                                                                                                                          
│       │                   │   └── Util.java                                                                                                                                                                              
│       │                   └── view/                                                                                                                                                                                       
│       │                       ├── Login.form                                                                                                                                                                             
│       │                       └── Login.java                                                                                                                                                                             
│       └── resources/                                                                                                                                                                                                      
│           ├── config.properties                                                                                                                                                                                          
│           ├── icons8-active-directory-48.png                                                                                                                                                                             
│           ├── icons8-arrow-48.png                                                                                                                                                                                        
│           ├── icons8-npc-face-30.png                                                                                                                                                                                     
│           ├── icons8-password-1-24.png                                                                                                                                                                                   
│           ├── ldap-logo.png                                                                                                                                                                                              
│           └── log4j2.properties                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
└── target/                                                                                                                                                                                                                 
    ├── archive-tmp                                                                                                                                                                                                        
    ├── classes                                                                                                                                                                                                                                                                                                                                                                                                       
    ├── generated-sources                                                                                                                                                                                                                                                                                                                                                                                                  
    ├── maven-status/                                                                                                                                                                                                       
    │   └── maven-compiler-plugin/                                                                                                                                                                                          
    │       └── compile/                                                                                                                                                                                                    
    │           └── default-compile/                                                                                                                                                                                        
    │               ├── createdFiles.lst                                                                                                                                                                                   
    │               └── inputFiles.lst                                                                                                                                                                                     
    └── mvn-ldap-client-2.05.2021-jar-with-dependencies.jar
```
## Author 👋
This repository is built to met one of the requirements of author's essay per 2021/2022. This program / content will be used in its purposes only
```bash
name   : Muhammad Nur Irsyad
id     : 1807422020
major  : TIK // TMJ
class  : CCIT SEC 8
```
