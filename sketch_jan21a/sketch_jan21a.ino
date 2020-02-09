int car_red = 12;
int car_yellow = 11;
int car_green = 10;
int human_red = 9;
int human_green = 8;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(13,INPUT_PULLUP);
  pinMode(car_red,OUTPUT);
  pinMode(car_yellow,OUTPUT);
  pinMode(car_green,OUTPUT);
  pinMode(human_red,OUTPUT);
  pinMode(human_green,OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(car_green ,HIGH);
  digitalWrite(car_yellow ,LOW);
  digitalWrite(car_red ,LOW);

  digitalWrite(human_red ,HIGH);
  digitalWrite(human_green ,LOW);
    
 int sw = digitalRead(13);
  Serial.println(sw);

  if(sw == 0)
  {
   digitalWrite(car_yellow,HIGH); 
   digitalWrite(car_green,LOW); 
   delay(500);
   digitalWrite(car_red,HIGH);
   digitalWrite(car_yellow,LOW);
   digitalWrite(human_green,HIGH); 
   digitalWrite(human_red,LOW);
   delay(3000);
  for(int i = 0; i< 5; i++)
   {
    digitalWrite(human_green,LOW);
    delay(500);
    digitalWrite(human_green,HIGH);
    delay(500);
   }
  }
}
