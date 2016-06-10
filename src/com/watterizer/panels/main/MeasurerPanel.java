/* **********   MeasurerPanel.java   **********
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
package com.watterizer.panels.main;

import com.watterizer.util.UsefulMethods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MeasurerPanel extends javax.swing.JPanel {

    private ArrayList<Double> measure;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel() {
        initComponents();
        jPanel1.setLayout(new GridLayout(0, 1));
        measure = new ArrayList<>();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                jPanel1.removeAll();
                jPanel1.add(createChart());
                jPanel1.revalidate();
            }
        });

        new Thread() {
            @Override
            public void run() {
                int seconds = 0;
                double spentAverage = 0;

                while (true) {
                    // Obter valores do arduino... Enquanto isso
                    Random rgn = new Random();
                    double currentSpent = rgn.nextInt(100) / 100.0;
                    spentAverage += currentSpent;
                    seconds++;

                    if (seconds % 60 == 0) {
                        measure.add(currentSpent / 60.0);
                        currentSpent = 0;
                    }

                    if (seconds % 120 == 0) { // 10 min
                        System.out.println("salvar no banco");
                        seconds = 0;

                        new Thread("Updating Spent on DB") {
                            @Override
                            public void run() {
                                Connection conn = UsefulMethods.getDBInstance();

                                try {
                                    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                                    ResultSet check = conn.createStatement().executeQuery("SELECT * FROM gastos WHERE data = " + sqlDate.toString()
                                            + " AND id_usuario = " + UsefulMethods.getCurrentUserModel().getId());

                                    if (check.next()) {
                                        PreparedStatement prepared = conn.prepareStatement("UPDATE gastos SET consumo_total = ? WHERE id_usuario = ?");
                                        prepared.setObject(1, measure);
                                        prepared.setInt(2, UsefulMethods.getCurrentUserModel().getId());
                                        prepared.execute();
                                    } else {
                                        PreparedStatement statement = conn.prepareStatement("INSERT INTO gastos(id_usuario, data, hora_inicio, consumo_total) VALUES (?, ?, ?, ?)");
                                        statement.setInt(1, UsefulMethods.getCurrentUserModel().getId());
                                        statement.setDate(2, sqlDate);
                                        Time time = new Time(UsefulMethods.getTimeOfOpening());
                                        statement.setTime(3, time);
                                        statement.setObject(4, measure);
                                        statement.execute();
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }.start();
                    }

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 36)); // NOI18N
        jLabel2.setText("Medidor");

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
    }// </editor-fold>//GEN-END:initComponents

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries axisCurrent = new XYSeries("Consumo atual");

        axisCurrent.add(1.0, 3.0);
        axisCurrent.add(1.0, 2.0);
        axisCurrent.add(2.0, 3.0);

        dataset.addSeries(axisCurrent);
        return dataset;
    }

    private JPanel createChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(null, "Consumo em kWh", "Horas", createDataset());
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        chart.getXYPlot().setRenderer(renderer);

        return new ChartPanel(chart);
    }

    private void updateChart() {

    }

//    public static Object readJavaObject(Connection conn, long id) throws Exception {
//        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
//        pstmt.setLong(1, id);
//        ResultSet rs = pstmt.executeQuery();
//        rs.next();
//        Object object = rs.getObject(1);
//        String className = object.getClass().getName();
//
//        rs.close();
//        pstmt.close();
//        System.out.println("readJavaObject: done de-serializing: " + className);
//        return object;
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
