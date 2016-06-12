/* **********   temp.java   **********
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
package com.watterizer.util;


/*
 * SliderDemo1.java
 * ---------------
 * A demo that uses a SlidingXYDataset that provides a window of the
 * underlying dataset
 * 
 * This example uses XYSeries
 *  
 */
import java.awt.BorderLayout;
import java.util.Date;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChartDemo2 extends ApplicationFrame {

    public LineChartDemo2(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static XYDataset createDataset() {
        XYSeries xyseries = new XYSeries("First");
        xyseries.add(1.0D, 1.0D);
        xyseries.add(2D, 4D);
        xyseries.add(3D, 3D);
        xyseries.add(4D, 5D);
        xyseries.add(5D, 5D);
        xyseries.add(6D, 7D);
        xyseries.add(7D, 7D);
        xyseries.add(8D, 8D);
        xyseries.add(9D, 8D);
        xyseries.add(10D, 8D);
        xyseries.add(11D, 8D);
        xyseries.add(12D, 8D);
        xyseries.add(13D, 8D);
        xyseries.add(14D, 8D);
        xyseries.add(15D, 8D);
        xyseries.add(16D, 25D);
        xyseries.add(17D, 8D);
        xyseries.add(18D, 5D);
        xyseries.add(19D, 7D);
        xyseries.add(20D, 6D);
        xyseries.add(21D, 8D);
        xyseries.add(22D, 4D);
        xyseries.add(23D, 4D);
        xyseries.add(24D, 2D);
        xyseries.add(25D, 1.0D);

        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(xyseries);
        
        return xyseriescollection;
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart jfreechart = ChartFactory.createXYLineChart("Line Chart Demo 2", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setDomainPannable(true);
        xyplot.setRangePannable(true);
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(true);
        xylineandshaperenderer.setBaseShapesFilled(true);
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return jfreechart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setMouseWheelEnabled(true);
        return chartpanel;
    }

    public static void main(String args[]) {
        LineChartDemo2 linechartdemo2 = new LineChartDemo2("JFreeChart: LineChartDemo2.java");
        linechartdemo2.pack();
        RefineryUtilities.centerFrameOnScreen(linechartdemo2);
        linechartdemo2.setVisible(true);
    }
}
