int use_pin = 11;
void setup() {
  // put your setup code here, to run once:
  pinMode(use_pin,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  int ar = analogRead(A0); //0~5V입력 -> 0~1023까지로 읽음 

// int map_ar = map(ar,0,1023,0,5); //그래서 map을 이용해서 0~1023으로 읽히는걸 0~5의 값으로 매핑, 나중에 이 세기만큼 analogWrite해줄수도 잇음 
// 아 위에같이 하면 map_ar을 float로 바꿔도 소숫점 안나오고 그냥 1.00 2.00 3.00이렇게 딱 떨어지게 나오네...
// float map_ar = map(ar,0,1023,0.0,5.0);도 될것같지만 위랑 결과 같음
// 즉 map은 int로만 변환이 된다.

// 그래서 소숫점 결과 얻고싶으면 아래와 같이 해야함.
  int map_ar = map(ar,0,1023,0,50);
  int moc = map_ar/10;
  int na = map_ar%10;
  Serial.println((String)moc +"."+(String)na);
}
