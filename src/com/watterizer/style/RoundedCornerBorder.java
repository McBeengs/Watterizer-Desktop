/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author 15153769
 */
public class RoundedCornerBorder extends javax.swing.border.AbstractBorder {

    private final float arcWidth, arcHeight;

    public RoundedCornerBorder(float arcWidth, float arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D round = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, arcWidth, arcHeight);
        Container parent = c.getParent();
        if (parent != null) {
            g2.setColor(parent.getBackground());
            Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
            corner.subtract(new Area(round));
            g2.fill(corner);
        }
        g2.setColor(Color.GRAY);
        g2.draw(round);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }
}
