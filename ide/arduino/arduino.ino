#include "EmonLib.h"

EnergyMonitor pin1, pin2;
int volt = 110.0;

void setup() {
  Serial.begin(9600);
  // Pin, Calibration (Ratio / BurdenR = 1800 / 62)
  pin1.current(1, 29);
  pin2.current(2, 29);
}

void loop() {
  double wattPin1 = pin1.calcIrms(1480) * volt;
  double wattPin2 = pin2.calcIrms(1480) * volt;

  Serial.print("a1(");
  Serial.print(wattPin1);
  Serial.print(") | a2(");
  Serial.print(wattPin2);
  Serial.println(")");

  delay(1000);
}
