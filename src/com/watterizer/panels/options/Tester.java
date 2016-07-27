/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels.options;

import com.sun.java.swing.plaf.windows.WindowsSeparatorUI;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 15153769
 */
public class Tester {

    public static void main(String[] args) {
        UIManager.put("SeparatorUI", "com.sun.java.swing.plaf.windows.WindowsSeparatorUI");
        UIManager.put("TreeUI", "com.sun.java.swing.plaf.windows.WindowsTreeUI");
        UIManager.put("Button.background", Color.red);

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        new OptionsJFrame().setVisible(true);
    }
}
