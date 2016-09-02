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
import com.watterizer.json.JSONObject;
import com.watterizer.util.UsefulMethods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class MeasurerPanel extends SLPanel {

    private JFreeChart chart;
    private Second second;
    private Hour hour;
    private ArrayList<Double> stored;
    private ArrayList<Double> measure;
    private boolean dayIsAlreadyStored = false;
    private boolean isDBConnectionOk = true;
    private long timeOfOpening = new Time(new Date().getTime()).getTime();
    private double totalSpent;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel() {
        initComponents();
        measure = new ArrayList<>();

        jPanel1.setLayout(new GridLayout(0, 1));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                jPanel1.repaint();
            }
        });

        addMouseWheelListener((MouseWheelEvent mwe) -> {
            System.out.println(mwe.getScrollAmount());
        });

        new Thread() {
            @Override
            public void run() {
                createChart();

                while (true) {
                    // Obter valores do arduino... Enquanto isso
                    Random rgn = new Random();
                    double currentSpent = rgn.nextInt(100) / 100.0;

                    currentSpentLabel.setText(currentSpent + " kWh");
                    totalSpent += currentSpent;
                    totalSpentLabel.setText(String.format("%.2f", totalSpent) + " kWh");

                    measure.add(currentSpent / 60.0);
                    updateChart(currentSpent / 60.0);

                    if (isDBConnectionOk) {
                        updateDB();
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
        jSlider1 = new javax.swing.JSlider();

        setBackground(new java.awt.Color(0, 0, 0));
        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
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

        jSlider1.setOpaque(false);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(currentSpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(totalSpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(moneySpentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSlider1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 206, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(totalSpentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentSpentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moneySpentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
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
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
//        int value = jSlider1.getValue();
//        long minimum = domainAxis.getMinimumDate().getTime();
//        long maximum = domainAxis.getMaximumDate().getTime();
//        if (value < lastValue) { // left
//            minimum = minimum - delta;
//            maximum = maximum - delta;
//        } else { // right
//            minimum = minimum + delta;
//            maximum = maximum + delta;
//        }
//        DateRange range = new DateRange(minimum, maximum);
//        domainAxis.setRange(range);
    }//GEN-LAST:event_jSlider1StateChanged

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries spent = new TimeSeries("Consumo atual");

        if (stored != null && !stored.isEmpty()) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String prefix;
//            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//
//            if (day == 2 || day == 3 || day == 4 || day == 5 || day == 6) {
//                prefix = "última ";
//            } else {
//                prefix = "último ";
//            }
//
//            String storedAxis = "Consumo " + prefix + new SimpleDateFormat("EEEE").format(storedSqlDate).toLowerCase()
//                    + " - Dia " + sdf.format(storedSqlDate);
//            TimeSeries strd = new TimeSeries(storedAxis);
//            hour = new Hour();
//            hour = new Hour(); // a week
//            strd.add(hour, 0.0);
//            hour = (Hour) hour.next();
//
//            stored.stream().map((d) -> {
//                strd.add(hour, d);
//                return d;
//            }).forEach((_item) -> {
//                hour = (Hour) hour.next();
//            });
//
//            dataset.addSeries(strd);
        }

        second = new Second();
        spent.add(second, 0.0);
        second = (Second) second.next();

        if (measure.size() > 0) {
            measure.stream().map((d) -> {
                spent.add(second, d);
                return d;
            }).forEach((_item) -> {
                second = (Second) second.next();
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
            String json = UsefulMethods.getWebServiceResponse("http://10.0.4.70:1515/gasto/hoje/1", "GET", null);
            json = json.substring(1, json.length() - 1);

            if (!json.isEmpty()) {
                json = "{" + json.replaceAll("\'", "\"") + "}";
                JSONObject obj = new JSONObject(json);
                measure = convertSpentJSONToArray(obj);

                //16:46:47
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date lastUpdate = sdf.parse(obj.getString("hora"));
                Date now = sdf.parse(sdf.format(new Date()));

                timeOfOpening = now.getTime();
                long diffInSeconds = getDateDiff(lastUpdate, now, TimeUnit.SECONDS);
                System.out.println(obj.getString("hora"));
                System.err.println((int) diffInSeconds);

                try {
                    URL url = new URL("http://10.0.4.70:1515/gasto/nulo");
                    URLConnection con = url.openConnection();
                    HttpURLConnection http = (HttpURLConnection) con;
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);

                    json = "{"
                            + "\"intervalo\" : \"" + diffInSeconds + "\""
                            + "}";

                    byte[] out = json.getBytes(StandardCharsets.UTF_8);
                    int length = out.length;

                    http.setFixedLengthStreamingMode(length);
                    http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    http.connect();
                    try (OutputStream os = http.getOutputStream()) {
                        os.write(out);
                    }

                    for (int i = 0; i < diffInSeconds; i++) {
                        measure.add(0.0);
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        chart = ChartFactory.createTimeSeriesChart(null, "Horas", "Consumo em kWh", createDataset());

        chart.setBackgroundPaint(Color.BLACK);
        //new Color(255, 200, 20)
        ValueAxis values = chart.getXYPlot().getRangeAxis();
        values.setLabelPaint(Color.WHITE);
        values.setTickMarkPaint(Color.WHITE);
        values.setTickLabelPaint(Color.WHITE);

        DateAxis range = (DateAxis) chart.getXYPlot().getDomainAxis();
        range.setLabelPaint(Color.WHITE);
        range.setTickMarkPaint(Color.WHITE);
        range.setTickLabelPaint(Color.WHITE);
        range.setRange(new Time(new Date().getTime()), new Time(new Date().getTime() + (1000 * 5)));

        XYPlot plot = chart.getXYPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(false);
        plot.setRangeCrosshairVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(false);
        panel.setPopupMenu(null);
        panel.setFillZoomRectangle(false);
        jPanel1.add(panel);
        jPanel1.revalidate();
    }

    private void updateChart(double spent) {
        if (isDBConnectionOk) {
            TimeSeriesCollection dataset = (TimeSeriesCollection) chart.getXYPlot().getDataset();
            second = (Second) second.next();
            dataset.getSeries("Consumo atual").add(second, spent);
        }
    }

    private ArrayList<Double> convertSpentJSONToArray(JSONObject obj) throws ParseException {
        ArrayList<Double> send = new ArrayList<>();
        for (int i = 0; i < obj.length() - 1; i++) {
            send.add(Double.parseDouble(obj.getString("" + i)));
        }
        return send;
    }

    private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private void updateDB() {
        try {
            URL url = new URL("http://10.0.4.70:1515/gasto");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String json = "{"
                    + "\"gasto\" : \"" + measure.get(measure.size() - 1) + "\""
                    + "}";

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            jSlider1.setMaximum(measure.size());
            jSlider1.setValue(jSlider1.getMaximum());
        } catch (MalformedURLException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.net.ConnectException ex) {
            isDBConnectionOk = false;
            System.err.println("");
            JOptionPane.showMessageDialog(this, "A conexão com o banco de dados caiu", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentSpentLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JLabel moneySpentLabel;
    private javax.swing.JLabel totalSpentLabel;
    // End of variables declaration//GEN-END:variables
}
