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
import com.watterizer.modals.MainSeederJFrame;
import com.watterizer.net.SocketNodeJS;
import com.watterizer.util.UsefulMethods;
import com.watterizer.xml.XmlManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.json.JSONException;
import org.json.JSONObject;

public class MeasurerPanel extends SLPanel {

    private XmlManager xml;
    private SocketNodeJS socket;
    private JFreeChart chart;
    private Second second;
    private int secondsSpent = 0;
    private DateAxis range;
    private Hour hour;
    private boolean isDBConnectionOk = true;
    private long lastUpdate;
    private double totalSpent;
    private int equipamentoId;
    private int count = 0;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
    public MeasurerPanel(JSONObject machine) throws IOException {
        initComponents();
        xml = UsefulMethods.getManagerInstance(UsefulMethods.OPTIONS);
        socket = new SocketNodeJS();
        try {
            socket.socketConnect(xml.getContentByName("webServiceHost", 0), Integer.parseInt(xml.getContentByName("socketPort", 0)));
        } catch (IOException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            jLabel1.setText(machine.getString("nome"));
            jLabel2.setText(" - " + machine.getString("setor"));
            equipamentoId = Integer.parseInt(machine.getString("id"));
        } catch (JSONException ex) {
            Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                createChart();

//                while (true) {
//                    // Obter valores do arduino... Enquanto isso
//                    double r = Math.random();
//                    totalSpent += r;
//                    //String.format("%.2f", totalSpent)
//
//                    updateChart(r);
//
//                    if (isDBConnectionOk) {
//                        try {
//                            socket.echo("{"
//                                + "\"equipamento\" : \"" + arduinoId + "\","
//                                + "\"gasto\" : \"" + r + "\""
//                                + "}");
//                        } catch (IOException ex) {
//                            isDBConnectionOk = false;
//                            JOptionPane.showMessageDialog(null, "deu ruim no webservice");
//                            ex.printStackTrace();
//                        }
//                    }
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
                MainSeederJFrame.addConsoleListener((ArduinoBridge.ConsoleEvent evt) -> {
                    try {
                        String console = evt.getConsoleOutput();
                        System.out.println(console);
                        double r = parseConsole(console, "a" + machine.getString("numero_porta"));
//                        if (secondsSpent > 5) {
//                            if (r < 0) {
//                                MainSeederJFrame.fireMeasurerError("-");
//                                return;
//                            } else if (r > 100) {
//                                MainSeederJFrame.fireMeasurerError("+");
//                                return;
//                            }
//                        }
                        //System.out.println("a" + machine.getString("numero_porta"));

                        //if ((count > 10 && r < 0) || (count > 10 && r > 100) || (count > 10 && (r == 0))) {
                        //    JOptionPane.showMessageDialog(null, "deu ruim no medidor");
                        //    isDBConnectionOk = false;
                        //    arduinoBridge.removeAllConsoleHandlers();
                        //}
                        if (isDBConnectionOk && secondsSpent >= 5) {
                            try {
                                if (socket.isSocketConnected()) {
                                    socket.echo("{"
                                            + "\"equipamento\" : \"" + equipamentoId + "\","
                                            + "\"gasto\" : \"" + r + "\","
                                            + "\"arduino\":\"" + UsefulMethods.getPcModel().getArduinoId() + "\""
                                            + "}");
                                    updateChart(r);
                                } else {
                                    socket.socketConnect(xml.getContentByName("webServiceHost", 0), Integer.parseInt(xml.getContentByName("socketPort", 0)));
                                    String s = "{"
                                            + "\"mac\":\"" + UsefulMethods.getPcModel().getMac() + "\""
                                            + "}";

                                    UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":"
                                            + xml.getContentByName("webServicePort", 0) + "/pcligado", "POST", s);

                                    s = "{"
                                            + "\"valor\":" + xml.getContentByName("kwh", 0)
                                            + "}";
                                    UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":" + xml.getContentByName("webServicePort", 0)
                                            + "/kilowatt", "POST", s);
                                    MainSeederJFrame.closeWebServiceError();
                                }
                            } catch (IOException | NumberFormatException ex) {
                                socket.close();
                                MainSeederJFrame.fireWebServiceError();
                            }
                            count++;
                        } else if (secondsSpent < 5) {
                            updateChart(0.15);
                        } else {
                            System.err.println("removed");
                            MainSeederJFrame.removeAllListeners();
                        }

                        secondsSpent++;
                    } catch (JSONException ex) {
                        Logger.getLogger(MeasurerPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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
            .addGap(0, 428, Short.MAX_VALUE)
        );

        Font header = UsefulMethods.getHeaderFont();
        header = header.deriveFont(Font.PLAIN, 33);
        jLabel1.setFont(header);
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("[text]");

        header = header.deriveFont(Font.PLAIN, 20);
        jLabel2.setFont(header);
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("[text]");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(518, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    @SuppressWarnings("InfiniteRecursion")
    private void createChart() {
        try {
            String[] keys = new String[]{"Content-Type", "token"};
            String[] values = new String[]{"application/json; charset=UTF-8", UsefulMethods.getUserModel().getTokenDesktop()};

            String json = UsefulMethods.getWebServiceResponse("http://" + xml.getContentByName("webServiceHost", 0) + ":"
                    + xml.getContentByName("webServicePort", 0) + "/dados/gasto/hoje/" + equipamentoId, "GET", keys, values, null);

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
        } catch (java.lang.StringIndexOutOfBoundsException ex) {
            createChart();
        }

        chart = ChartFactory.createTimeSeriesChart(null, "Horas", "Consumo em Amp", createDataset());

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

    ArrayList<Second> seconds = new ArrayList<>();
    ArrayList<Double> spents = new ArrayList<>();

    private void updateChart(double spent) {
        if (isDBConnectionOk) {
            TimeSeriesCollection dataset = (TimeSeriesCollection) chart.getXYPlot().getDataset();
            second = (Second) second.next();

            if (seconds.size() == 15) {
                seconds.remove(0);
                seconds.add(second);
                spents.remove(0);
                spents.add(spent);
            } else {
                seconds.add(second);
                spents.add(spent);
            }
            dataset.getSeries("Consumo atual").clear();

            for (int i = 0; i < seconds.size(); i++) {
                try {
                    dataset.getSeries("Consumo atual").add(seconds.get(i), spents.get(i));
                } catch (Exception ex) {

                }
            }

//            Calendar cal = Calendar.getInstance();
//            long top = cal.getTimeInMillis();
//            cal.add(Calendar.SECOND, -15);
//            long bottom = cal.getTimeInMillis();
//            range.setRange(new Range(bottom, top));
        }
    }

    private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private double parseConsole(String console, String port) {
        console = console.substring(console.indexOf(port) + 3);
        console = console.substring(0, console.indexOf(")"));
        if (console.startsWith(".")) {
            console = "0" + console;
        }

        return Double.parseDouble(console);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
