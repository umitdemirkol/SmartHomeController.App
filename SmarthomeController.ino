#include <Servo.h>
char gelenVeri;
int pos=0;
int pirPin=8;
int ledPin=10;
 int ledPin2=12;
 int ledPin3=6;
bool deger=0;
int buzzer=4;

Servo myservo;
void setup() {
  pinMode(ledPin3,OUTPUT);
   myservo.attach(5);
  Serial.begin(9600);
  pinMode(pirPin, INPUT);
pinMode(ledPin, OUTPUT);
pinMode(ledPin2, OUTPUT);
pinMode(buzzer,OUTPUT);

}

void loop() {
  

deger = digitalRead(pirPin); // Dijital pin okunuyor
Serial.println(deger); // Okunan değer seri porttan okunuyor.
if (deger == HIGH) {
digitalWrite(ledPin, HIGH);// Eğer okunan değer 1 ise LED yakılıyor.
delay(100);
digitalWrite(ledPin, LOW);


}


  if (Serial.available()>0){
    
    gelenVeri=Serial.read();
    Serial.println(gelenVeri);
     if(gelenVeri=='1'){
      digitalWrite(ledPin3,HIGH);
      
      
      
      Serial.println("LED Yakildi.");
    }
    
    if (gelenVeri=='2'){
      digitalWrite(ledPin3,LOW);
      Serial.println("LED Sonduruldu." );
       
    }
     if (gelenVeri=='3') {

  
// myservo.write(20);
for(pos = 90; pos>=0; pos-=1){ // Servo 1'den 0 pozisyonuna 90 derece dˆnecek.

myservo.write(pos); // Belirlenen pozisyona gitmesi isteniyor. 

delay(50); // Pozisyona 5 ms de ulasiyor

} 
  
   

  }
   if (gelenVeri=='4'){
    //myservo.write(90);

 
  
for(pos = 20; pos < 90; pos += 1) {

  myservo.write(pos); // Belirlenen pozisyona gitmesi isteniyor. 

   delay(50); // Pozisyona 5 ms de ulasiyor. 

   } 

  }
if (gelenVeri=='5'){

  digitalWrite(ledPin2,HIGH);    

  }
  
  if (gelenVeri=='6'){

  digitalWrite(ledPin2,LOW);

    

  }
  if (gelenVeri=='7'){
    int i=0;
 while(i<15){
  i++;
    digitalWrite(buzzer,HIGH);
    delay(200);
    digitalWrite(buzzer,LOW);
    delay(200);
 
 }

  }
  if (gelenVeri=='8'){

  digitalWrite(buzzer,LOW);    

  } 

 

    }
  
  

  delay(100);
}
