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
import com.watterizer.arduino.ArduinoBridge;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private XmlManager xml;
    private ArduinoBridge arduinoBridge;
    private SocketNodeJS socket;
    private JFreeChart chart;
    private Second second;
    DateAxis range;
    private Hour hour;
    private boolean isDBConnectionOk = true;
    private long lastUpdate;
    private double totalSpent;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel() throws IOException {
        initComponents();
        //arduinoBridge = UsefulMethods.getArduinoInstance();
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        socket = new SocketNodeJS();
        try {
            socket.socketConnect(xml.getContentByName("webServiceHost", 0), Integer.parseInt(xml.getContentByName("socketPort", 0)));
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

//                arduinoBridge.addConsoleHandler((ArduinoBridge.ConsoleEvent evt) -> {
//                    String console = evt.getConsoleOutput();
//                    double r = parseConsole(console, "a0");
//                    System.out.println(r);
//                    updateChart(r);
//
//                    if (isDBConnectionOk) {
//                        socket.echo("{"
//                                + "\"arduino\" : \"2\","
//                                + "\"gasto\" : \"" + r + "\""
//                                + "}");
//                    }
//                });
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                //socket.
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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
            .addGap(0, 249, Short.MAX_VALUE)
        );

        jSeparator1.setBackground(new java.awt.Color(255, 200, 20));
        jSeparator1.setForeground(new java.awt.Color(255, 200, 20));

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 33);
        jLabel1.setFont(header);
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Acelere, pah, pere, perrepetugamalepinogumalasinu");

        header = header.deriveFont(Font.PLAIN, 20);
        jLabel2.setFont(header);
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("- Sala 10");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
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
            dataset.getSeries("Consumo atual").removeAgedItems(bottom, true);
            range.setRange(new Range(bottom, top));
        }
    }

    private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private double parseConsole(String console, String port) {
        console = console.substring(console.indexOf(port) + 3);
        console = console.substring(0, console.indexOf(")"));
        return Double.parseDouble(console);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
