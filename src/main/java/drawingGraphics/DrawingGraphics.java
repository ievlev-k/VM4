package drawingGraphics;

import method.CalculateFunctionInPoint;
import method.Function;
import method.Spline;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class DrawingGraphics {
    Spline splines;
    String function;
    public DrawingGraphics(Spline splines, String function) {
        this.function = function;
        this.splines = splines;
    }


    public void drawingFunction() {
        CalculateFunctionInPoint calculateFunctionInPoint = new CalculateFunctionInPoint();
        Function function = new Function();
        double[] xData = new double[10000];
        double[] yData = new double[10000];
        double[] yData2 = new double[10000];

        XYSeries series1 = new XYSeries("xySeries1");
        XYSeries series2 = new XYSeries("xySeries2");

        double step = (splines.getXi()[splines.getXi().length - 1] - splines.getXi()[0]) / 1000;

        double x = splines.getXi()[0] - 0.1;

        xData[0] = x;
        yData[0] = splines.getF(x, 0);
        yData2[0] = function.func(x);
        double courantX = splines.getXi()[0];
        int k = 0;

        int i = 0;
        while (x < splines.getXi()[splines.getXi().length - 1]) {
            i++;
            x += step;

            if (x > courantX && k < splines.getXi().length - 1) {
                k++;
                courantX = splines.getXi()[k];
            }
            xData[i] = x;
            yData[i] = splines.getF(x, k);
            yData2[i] = calculateFunctionInPoint.calculatingValue(this.function, x);
            series1.add(xData[i], yData[i]);
            series2.add(xData[i], yData2[i]);

        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "F(x)",
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-2.5, -2.5, 5, 5));
        plot.setDataset(0, dataset);


        for (int j = 0; j < splines.getXi().length; j++) {
            XYSeries series3 = new XYSeries("xySeries3");
            XYSeriesCollection pointDataset = new XYSeriesCollection();
            pointDataset.addSeries(series3);
            series3.add(splines.getXi()[j], splines.getA()[j]);
            plot.setDataset(j + 1, pointDataset);
            plot.setRenderer(j + 1, renderer);
        }

        ChartFrame frame = new ChartFrame("", chart);
        frame.pack();
        frame.setVisible(true);
    }
}

