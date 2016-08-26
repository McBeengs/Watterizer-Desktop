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

import aurelienribon.slidinglayout.SLPanel;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.watterizer.json.JSONObject;
import com.watterizer.util.UsefulMethods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class MeasurerPanel extends SLPanel {

    private JFreeChart chart;
    private Minute minute;
    private Connection conn;
    private java.sql.Date sqlDate;
    private java.sql.Time sqlTime;
    private ArrayList<Double> stored;
    private ArrayList<Double> measure;
    private boolean dayIsAlreadyStored = false;
    private double totalSpent;
    private java.sql.Date storedSqlDate;
    private java.sql.Time storedSqlTime;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel() {
        initComponents();
        conn = UsefulMethods.getDBInstance();
        sqlDate = new java.sql.Date(new java.util.Date().getTime());

        jPanel1.setLayout(new GridLayout(0, 1));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                jPanel1.repaint();
            }
        });

        new Thread() {
            @Override
            public void run() {
                int seconds = 0;
                double spentAverage = 0;

                try {
                    measure = new ArrayList<>();
                    String json = UsefulMethods.getWebServiceResponse("http://10.0.4.70:1515/gasto/hoje", "GET", null);
                    if (json != null) {
                        dayIsAlreadyStored = true;
                        //measure = readGastosArrayOfToday();

//                        measure.stream().forEach((d) -> {;
//                            totalSpent += d * 60.0; // foi dividido por 60, então isso restora o valor original
//                        });
                    } else {
                        measure = new ArrayList<>();
                        sqlTime = new Time(sqlDate.getTime());
                    }
                } catch (IOException ex) {

                }

                createChart();

                while (true) {
                    // Obter valores do arduino... Enquanto isso
                    Random rgn = new Random();
                    double currentSpent = rgn.nextInt(100) / 100.0;
                    spentAverage += currentSpent;
                    seconds++;

                    currentSpentLabel.setText(currentSpent + " kWh");
                    totalSpent += currentSpent;
                    totalSpentLabel.setText(String.format("%.2f", totalSpent) + " kWh");

                    if (seconds % 1 == 0) {
                        measure.add(spentAverage / 60.0);
                        updateChart(spentAverage / 60.0);
                        spentAverage = 0;
                    }

                    if (seconds % 1 == 0) { // 1 min
                        seconds = 0;

                        new Thread("Updating Spent on DB") {
                            @Override
                            public void run() {
                                if (dayIsAlreadyStored) {
//                                        try (PreparedStatement prepared = conn.prepareStatement("UPDATE gasto SET consumo_array = ? WHERE data = ? AND id_usuario = ?")) {
//                                            prepared.setObject(1, measure);
//                                            prepared.setDate(2, sqlDate);
//                                            prepared.setInt(3, UsefulMethods.getCurrentUserModel().getId());
//                                            prepared.execute();
//                                        }
                                    Gson gson = new Gson();

                                    System.out.println(gson.toJson(measure));
                                } else {
//                                        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO gasto(id_usuario, data, hora_inicio, consumo_array) VALUES (?, ?, ?, ?)")) {
//                                            statement.setInt(1, UsefulMethods.getCurrentUserModel().getId());
//                                            statement.setDate(2, sqlDate);
//                                            Time time = new Time(UsefulMethods.getTimeOfOpening());
//                                            statement.setTime(3, time);
//                                            statement.setObject(4, measure);
//                                            statement.execute();
//                                        }
                                    dayIsAlreadyStored = true;
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

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        currentSpentLabel = new javax.swing.JLabel();
        totalSpentLabel = new javax.swing.JLabel();
        moneySpentLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jSeparator1.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator1.setForeground(new java.awt.Color(255, 200, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consumo atual:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Consumo total:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gasto total:");

        currentSpentLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        currentSpentLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentSpentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentSpentLabel.setText("0,54 kWh");

        totalSpentLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        totalSpentLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalSpentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSpentLabel.setText("2,18 kWh");

        moneySpentLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        moneySpentLabel.setForeground(new java.awt.Color(255, 255, 255));
        moneySpentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moneySpentLabel.setText("R$ 3,70");

        setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(currentSpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(totalSpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(moneySpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 181, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(totalSpentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentSpentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moneySpentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 174, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(currentSpentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(totalSpentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(moneySpentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries spent = new TimeSeries("Consumo atual");

        if (stored != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String prefix;
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            if (day == 2 || day == 3 || day == 4 || day == 5 || day == 6) {
                prefix = "última ";
            } else {
                prefix = "último ";
            }

            String storedAxis = "Consumo " + prefix + new SimpleDateFormat("EEEE").format(storedSqlDate).toLowerCase()
                    + " - Dia " + sdf.format(storedSqlDate);
            TimeSeries strd = new TimeSeries(storedAxis);
            minute = new Minute(); // a week
            strd.add(minute, 0.0);
            minute = (Minute) minute.next();

            stored.stream().map((d) -> {
                strd.add(minute, d);
                return d;
            }).forEach((_item) -> {
                minute = (Minute) minute.next();
            });

            dataset.addSeries(strd);
        }

        minute = new Minute();
        spent.add(minute, 0.0);
        minute = (Minute) minute.next();

        if (measure.size() > 0) {
            measure.stream().map((d) -> {
                spent.add(minute, d);
                return d;
            }).forEach((_item) -> {
                minute = (Minute) minute.next();
            });
        }

        dataset.addSeries(spent);
        return dataset;
    }

    private void createChart() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -7);
//        String last = new java.sql.Date(cal.getTimeInMillis()).toString();

        try {
            String json = UsefulMethods.getWebServiceResponse("http://10.0.4.70:1515/gasto", "GET", null);
            json = json.substring(1, json.length() - 1);
            if (!json.isEmpty()) {
                JSONObject obj = new JSONObject(json);
                if (!obj.isNull("gasto")) {
                    measure = convertSpentJSONToArray(obj);
//                stored = readGastosArrayOfLastDOTW(check);
//                check.close();
                }
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        chart = ChartFactory.createTimeSeriesChart(null, "Horas", "Consumo em kWh", createDataset());

        chart.setBackgroundPaint(Color.BLACK);
        //new Color(255, 200, 20)
        ValueAxis values = chart.getXYPlot().getRangeAxis();
        values.setLabelPaint(Color.WHITE);
        values.setTickMarkPaint(Color.WHITE);
        values.setTickLabelPaint(Color.WHITE);

        ValueAxis range = chart.getXYPlot().getDomainAxis();
        range.setLabelPaint(Color.WHITE);
        range.setTickMarkPaint(Color.WHITE);
        range.setTickLabelPaint(Color.WHITE);

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        jPanel1.add(panel);
        jPanel1.revalidate();
    }

    private void updateChart(double spent) {
        TimeSeriesCollection dataset = (TimeSeriesCollection) chart.getXYPlot().getDataset();
        minute = (Minute) minute.next();
        dataset.getSeries("Consumo atual").add(minute, spent);
    }

    private ArrayList<Double> convertSpentJSONToArray(JSONObject obj) throws IOException, ClassNotFoundException {
        ArrayList<Double> send = new ArrayList<>();
//        String data = obj.getJSONObject("gasto").get("data").toString();
//        data = data.substring(1, data.length() - 1);

        System.out.println(obj.toString());
        //String[] n = data.split(",");
//        char[] chars = new char[n.length];
//
//        for (int i = 0; i < n.length; i++) {
//            chars[i] = (char) Integer.parseInt(n[i]);
//        }
//        
//        String object = new String(chars);
//        System.out.println(object);
//        
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(object.getBytes()));
//        send = (ArrayList<Double>) ois.readObject();
//        
//        for (Double d : send) {
//            System.out.println(d);
//        }
        return send;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentSpentLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel moneySpentLabel;
    private javax.swing.JLabel totalSpentLabel;
    // End of variables declaration//GEN-END:variables
}
