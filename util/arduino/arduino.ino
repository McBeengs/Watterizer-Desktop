#include "EmonLib.h"

EnergyMonitor pin0, pin1, pin2, pin3, pin4, pin5;
int volt = 110.0;

void setup() {
  Serial.begin(9600);
  // Pin, Calibration (Ratio / BurdenR = 1800 / 62)
  pin0.current(0, 29);
  pin1.current(1, 29);
  pin2.current(2, 29);
  pin3.current(3, 29);
  pin4.current(4, 29);
  pin5.current(5, 29);
}

void loop() {
  double wattPin0 = pin0.calcIrms(1480) * volt;
  double wattPin1 = pin1.calcIrms(1480) * volt;
  double wattPin2 = pin2.calcIrms(1480) * volt;
  //double wattPin3 = pin3.calcIrms(1480) * volt;
  //double wattPin4 = pin4.calcIrms(1480) * volt;
  //double wattPin5 = pin5.calcIrms(1480) * volt;
  //Serial.println("a0(" + doubleToString(wattPin0, 2) + ") | a1(" + doubleToString(wattPin1, 2) + ") | a2(" + doubleToString(wattPin2, 2) +") | a3(" + doubleToString(wattPin3, 2) +") | a4(" + doubleToString(wattPin4, 2) +") | a5(" + doubleToString(wattPin5, 2) + ")");
  Serial.println("a0(" + doubleToString(wattPin0, 2) + ") | a1(" + doubleToString(wattPin1, 2) + ") | a2(" + doubleToString(wattPin2, 2) +")");
  delay(250);
}

String doubleToString(double input, int decimalPlaces){
  if(decimalPlaces != 0){
    String string = String((int)(input * pow(10, decimalPlaces)));
    if(abs(input) < 1){
      if(input > 0)
      string = "0" + string;
    else if(input < 0)
      string = string.substring(0,1) + "0"+string.substring(1);
  }
    return string.substring(0, string.length() - decimalPlaces) + "." + string.substring(string.length() - decimalPlaces);
  }
  else {
    return String((int)input);
  }
}
