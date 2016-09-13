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
package com.watterizer.panels;

import aurelienribon.slidinglayout.SLPanel;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.util.UsefulMethods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.json.JSONException;
import org.json.JSONObject;

public class MeasurerPanel extends SLPanel {

    private SocketNodeJS socket;
    private JFreeChart chart;
    private Second second;
    DateAxis range;
    private Hour hour;
    private boolean isDBConnectionOk = true;
    private long lastUpdate;
    private double totalSpent;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel() {
        initComponents();
        socket = new SocketNodeJS();
        try {
            socket.socketConnect("10.0.4.70", 3000);
        } catch (IOException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                    double r = Math.random();
                    totalSpent += r;
                    //String.format("%.2f", totalSpent)

                    updateChart(r);

                    if (isDBConnectionOk) {
                        //updateDB(r);
                        socket.echo("{"
                                + "\"arduino\" : \"2\","
                                + "\"gasto\" : \"" + r + "\""
                                + "}");
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

        setBackground(new java.awt.Color(0, 0, 0));
        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jSeparator1.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator1.setForeground(new java.awt.Color(255, 200, 20));

        setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries spent = new TimeSeries("Consumo atual");

        second = new Second();
        spent.add(second, 0.0);
        second = (Second) second.next();

        dataset.addSeries(spent);
        return dataset;
    }

    private void createChart() {
        try {
            String json = UsefulMethods.getWebServiceResponse("http://10.0.4.70:1515/gasto/hoje/1", "GET", null);
            json = json.substring(1, json.length() - 1);

            if (!json.isEmpty()) {
                json = "{" + json.replaceAll("\'", "\"") + "}";
                JSONObject obj = new JSONObject(json);

                //16:46:47
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date update = sdf.parse(obj.getString("hora"));
                Date now = sdf.parse(sdf.format(new Date()));
                lastUpdate = update.getTime();

                long diffInSeconds = getDateDiff(update, now, TimeUnit.SECONDS);
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
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException | JSONException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        chart = ChartFactory.createTimeSeriesChart(null, "Horas", "Consumo em kWh", createDataset());

        chart.setBackgroundPaint(Color.BLACK);
        //new Color(255, 200, 20)
        ValueAxis values = chart.getXYPlot().getRangeAxis();
        values.setLabelPaint(Color.WHITE);
        values.setTickMarkPaint(Color.WHITE);
        values.setTickLabelPaint(Color.WHITE);

        range = (DateAxis) chart.getXYPlot().getDomainAxis();
        range.setLabelPaint(Color.WHITE);
        range.setTickMarkPaint(Color.WHITE);
        range.setTickLabelPaint(Color.WHITE);

        XYPlot plot = chart.getXYPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(false);
        plot.setRangeCrosshairVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
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
            Calendar cal = Calendar.getInstance();
            long top = cal.getTimeInMillis();
            cal.add(Calendar.SECOND, -15);
            long bottom = cal.getTimeInMillis();
            range.setRange(new Range(bottom, top));
        }
    }

    private ArrayList<Double> convertSpentJSONToArray(JSONObject obj) throws ParseException, JSONException {
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

    private void updateDB(double spent) {
        try {
            URL url = new URL("http://10.0.4.70:1515/gasto");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String json = "{"
                    + "\"gasto\" : \"" + spent + "\""
                    + "}";

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            socket.echo("" + spent);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
