/* **********   ArduinoTester.java   **********
 *
 * This piece of garbage was brought to you by nothing less than the almighty lord
 * of programming, the Java God and ruler of all the non living things, McBeengs, 
 * A.K.A. myself. I don't mind anyone steal or using my codes at their own business,
 * but at least, and I meant VERY least, give me the proper credit for it. I really
 * don't know what the code below does at this point in time while I write this stuff, 
 * but if you took all this time to sit, rip the .java files and read all this 
 * unnecessary bullshit, you know for what you came, doesn't ?
 * 
 * Copyright(c) {YEAR!!!} Mc's brilliant mind. All Rights (kinda) Reserved.
 */

/*
 * {Insert class description here}
 */

package com.watterizer.runtime;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ArduinoTester extends JFrame {
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ArduinoTester() {
        setSize(500, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Por favor termine o diagnóstico antes de sair.");
            }
            
        });
        setLocationRelativeTo(null);
        setTitle("Verificar Arduino");
        WelcomePanel w = new WelcomePanel();
        w.setVisible(true);
        CheckConnection conn = new CheckConnection();
        conn.setVisible(false);
        add(w);
        add(conn);
        setVisible(true);
    }
}