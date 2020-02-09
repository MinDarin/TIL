void setup() {
  // put your setup code here, to run once:
  pinMode(11,OUTPUT);
  Serial.begin(9600);
//  Serial.open();
}

void loop() {
   // put your main code here, to run repeatedly:
/*  if(Serial.available())
  {
   int r = Serial.parseInt();
    Serial.println(r);
    analogWrite(11,r);
   }
*/
  if(Serial.available())
  {
  int i = Serial.parseInt();
  Serial.println(i);
  Serial.write(i);    
  }
}
