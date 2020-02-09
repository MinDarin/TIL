int use_pin = 11;
void setup() {
  // put your setup code here, to run once:
  pinMode(use_pin,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  int ar = analogRead(A0);
  if(ar>900)
    digitalWrite(13,HIGH);
  else if(ar<700)
    digitalWrite(13,LOW);
  Serial.println((String)ar);
  delay(100);
}
