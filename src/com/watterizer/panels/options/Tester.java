/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels.options;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuBar;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 15153769
 */
public class Tester {

    public static void main(String[] args) throws FileNotFoundException, IOException {
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

        UIManager.put("ProgressBarUI", "javax.swing.plaf.metal.MetalProgressBarUI");
        UIManager.put("ProgressBar.cellLength", Integer.MAX_VALUE);
        UIManager.put("ProgressBar.foreground", new Color(51, 153, 255));
        UIManager.put("SeparatorUI", "javax.swing.plaf.metal.MetalSeparatorUI");
        UIManager.put("Separator.foreground", new Color(160, 160, 160));
        UIManager.put("SeparatorUI.shadow", new Color(160, 160, 160));
        UIManager.put("TreeUI", "javax.swing.plaf.metal.MetalTreeUI");
        UIManager.put("SeparatorUI", "javax.swing.plaf.metal.MetalSeparatorUI");
        UIManager.put("Separator.foreground", new Color(255, 200, 20));
        UIManager.put("Separator.background", new Color(255, 200, 20));
        UIManager.put("TreeUI", "javax.swing.plaf.metal.MetalTreeUI");
        UIManager.put("ComboBox.background", new Color(255, 200, 20));
        UIManager.put("Button.background", new Color(255, 200, 20));
        UIManager.put("Button.select", new Color(255, 220, 20));
        UIManager.put("ComboBox.buttonBackground", new Color(255, 220, 20));
        UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("OptionPane.opaque", false);
        UIManager.put("OptionPane.sameSizeButtons", true);
        UIManager.put("Panel.background", Color.black);

        if (UIManager.getLookAndFeel().getName().contains("Nimbus")) {
            UIManager.put("control", Color.black);
            UIManager.put("nimbusBase", new Color(178, 136, 0));
//            UIManager.put("nimbusBase", Color.red);
//            UIManager.put("nimbusBase", Color.red);
//            UIManager.put("nimbusBase", Color.red);
        } else if (UIManager.getLookAndFeel().getName().contains("Windows")) {
            UIManager.put("ButtonUI", "javax.swing.plaf.metal.MetalButtonUI");
            UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 11));
        }

        new OptionsJFrame().setVisible(true);

//        ArrayList<String> java = new ArrayList<>();
//        list("C:\\Users\\15153769\\Desktop\\NetBeansProjects\\Watterizer - Desktop Version", java);
//        System.out.println("Número de arquivos: " + java.size());
//        
//        int lines = 0;
//        for (String s : java) {
//            FileReader reader = new FileReader(new File(s));
//            BufferedReader br = new BufferedReader(reader);
//            while(br.readLine() != null) {
//                lines++;
//            }
//        }
//        
//        System.out.println("Número de linhas: " + lines);
    }

    private static void list(String folder, ArrayList<String> list) {
        File[] files = new File(folder).listFiles();
        for (File f : files) {
            if (f.isFile() && f.getAbsolutePath().endsWith(".java")) {
                list.add(f.getAbsolutePath());
            } else if (f.isDirectory()) {
                list(f.getAbsolutePath(), list);
            }
        }
    }
}
