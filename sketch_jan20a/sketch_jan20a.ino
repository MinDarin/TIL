int led_red = 13;
int led_yellow = 12;
int led_green = 11;
int led_white = 10;
int delay_time = 1000;
void setup() {
  // put your setup code here, to run once:
  for(int i = 13; i >=10; i--)
  {
  pinMode(i,1);
  }
}
void loop() {
  // put your main code here, to run repeatedly:
  for(int i = 13; i >=10; i--)
  {
  digitalWrite(i,HIGH);
  delay(delay_time);
  }
  for(int i = 13; i >=10; i--)
  {
  digitalWrite(i,LOW);
  delay(delay_time);
  }
}
