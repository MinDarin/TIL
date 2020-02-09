void setup() {
  // put your setup code here, to run once:
pinMode(11,OUTPUT);
pinMode(10,OUTPUT);
pinMode(9,OUTPUT);
Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
digitalWrite(11,LOW);
/*for(int i=0; i<256; i++)
  {
    analogWrite(10,i);
    delay(500);
  }*/
for(int i = 0 ; i <3000; i++)
  {
    analogWrite(9,i);
    
    delay(100);   
   }
}
