/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.util;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 15153769
 */
public class OpaqueScreen {

    private JFrame frame = new JFrame();
    private static int INSTANCES = 0;
    private final Thread thread = new Thread(() -> {
        while (frame != null) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM " + "taskmgr.exe").waitFor();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(OpaqueScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });

    public OpaqueScreen() throws IOException, InterruptedException {
        this(null);
    }

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public OpaqueScreen(Component component) throws IOException, InterruptedException {
        INSTANCES++;
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 255, 0, 0));

        frame.setLayout(new BorderLayout());
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(0);

        ContentPane pane = new ContentPane();

        if (component != null) {
            pane.setLayout(new GridBagLayout());
            pane.add(component);
        }

        frame.setContentPane(pane);
        frame.getContentPane().setBackground(Color.BLACK);
    }

    public void setRootFrame(Component component) {
        ContentPane pane = new ContentPane();

        if (component != null) {
            pane.setLayout(new GridBagLayout());
            pane.add(component);
        }

        frame.setContentPane(pane);
        frame.getContentPane().setBackground(Color.BLACK);
    }

    public JFrame getRootFrame() {
        return frame;
    }

    public void setVisible(boolean bln) {
        frame.setVisible(bln);
        try {
            Runtime.getRuntime().exec("taskkill /F /IM " + "explorer.exe").waitFor();
            thread.start();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(OpaqueScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            if (INSTANCES == 1) {
                Runtime.getRuntime().exec("explorer.exe");
            }
            INSTANCES--;
            frame.dispose();
            frame = null;
        } catch (IOException ex) {
            Logger.getLogger(OpaqueScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ContentPane extends JPanel {

        public ContentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Allow super to paint
            super.paintComponent(g);
            // Apply our own painting effect
            Graphics2D g2d = (Graphics2D) g.create();
            // 50% transparent Alpha
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(getBackground());
            g2d.fill(getBounds());
            g2d.dispose();
        }
    }
}
