
import com.watterizer.arduino.ArduinoBridge;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 15153769
 */
public class NovoClass {
    public static void main(String[] args) {
        ArduinoBridge bridge = new ArduinoBridge("COM8");
        bridge.waitForConnection(5000);
        bridge.addConsoleHandler(new ArduinoBridge.ConsoleHandler() {
            @Override
            public void onConsoleUpdated(ArduinoBridge.ConsoleEvent evt) {
                System.out.println(evt.getConsoleOutput());
            }
        });
    }
}
