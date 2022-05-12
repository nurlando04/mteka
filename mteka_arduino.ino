#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#include <SD.h>
#include <DHT.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
#include <NTPtimeESP.h>
#define DEBUG_ON

NTPtime NTPch("pool.ntp.org");    
strDateTime dateTime;
LiquidCrystal_I2C lcd(0x27, 16, 2); 

#define DHTPIN D3
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);
#define CS_PIN  D8

int i = 1;
float sumH = 0;
float sumT = 0;
float avgH;
float avgT;
int val;
int sec;
int minutes;
int hours;

void setup()
{
  Wire.begin(D2, D1); // D1 = SCL | D2 = SDA
  lcd.init(); // Iniciamos el LCD
  lcd.backlight(); 
  lcd.setCursor(0,1);
  Serial.print("Initializing");
  dht.begin();
  if (!SD.begin(CS_PIN)) {
    Serial.println("error");
    return;
  }
  Serial.println("Initialized");
  Serial.begin(115200);                            //Serial connection
  WiFi.begin("KZ", "1234567890");   //WiFi connection
 
  while (WiFi.status() != WL_CONNECTED) {  //Wait for the WiFI connection completion
 
    delay(500);
    Serial.println("Waiting for connection");
 
  }
  Serial.println("connected");
 
}


void loop(){
  float h = dht.readHumidity();
  float t = dht.readTemperature();
//  Serial.print("Hum");
//  Serial.println(h);
//  Serial.print("Temp");
//  Serial.println(t);
  lcd.setCursor(0,0);
  lcd.print("Temp:");
  lcd.setCursor(0,1);
  lcd.print("Humidity:");
  lcd.setCursor(11,0);
  lcd.print(t);
  lcd.setCursor(11,1);
  lcd.print(h);
  String dataString = "";
  dateTime = NTPch.getNTPtime(6, 1);
  if(dateTime.valid){
    byte actualHour = dateTime.hour;
    byte actualMinute = dateTime.minute;
    int actualyear = dateTime.year;
    byte actualMonth = dateTime.month;
    byte actualday =dateTime.day;
    dataString += String(actualday);
    dataString += "/";
    dataString += String(actualMonth);
    dataString += "/";
    dataString += String(actualyear);
    dataString += "  ";
    dataString += String(actualHour);
    dataString += ":";
    dataString += String(actualMinute);
    dataString += " Температура:";
    dataString += String(t);
    dataString += " Влажность:";
    dataString += String(h);
    dataString += "\r\n";
  }
  Serial.print(dataString);
  File humTemp = SD.open("humTemp.txt", FILE_WRITE);
  if (humTemp) {
    Serial.println("Success");
      humTemp.println(dataString);
      humTemp.close();
  }
  else {
    Serial.println("couldn't do it my man");
  }

  sumH=sumH+h;
  sumT=sumT+t;
  avgH=sumH/i;
  avgT=sumT/i;
  i++;
  Serial.println(avgH);
  Serial.println(avgT);
  saveAvg();

  
  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
 
    DynamicJsonDocument doc(2048);
    doc["humidity"] = h;
    doc["temperature"] = t;

    // Serialize JSON document
    String json;
    serializeJson(doc, json);

    WiFiClient client;  // or WiFiClientSecure for HTTPS
    HTTPClient http;

    // Send request
    http.begin(client, "http://192.168.73.200:8090/api/v1/post2");
    http.addHeader("Content-Type","application/json");
    http.POST(json);

// Read response
    Serial.print(http.getString());

// Disconnect
    http.end();
   } else {
 
    Serial.println("Error in WiFi connection");
 
  }
  
  delay(3000);
}
void saveAvg(){
  if(SD.exists("tAvg.txt")){
    SD.remove("tAvg.txt");
  }
  if(SD.exists("hAvg.txt")){
    SD.remove("hAvg.txt");
  }
  
  File avgTf= SD.open("tAvg.txt", FILE_WRITE);
  if (avgTf) {
    Serial.println("Success");
      avgTf.println(avgT);
      avgTf.close();
  }
  else {
    Serial.println("couldn't do it my man");
  }

  
  File avgHf = SD.open("hAvg.txt", FILE_WRITE);
  if (avgHf) {
    Serial.println("Success");
      avgHf.println(avgH);
      avgHf.close();
  }
  else {
    Serial.println("couldn't do it my man");
  }

 
}
  
