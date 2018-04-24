package ser516.project3.client.Components.Graph;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * GraphView is a class to represent the basic view template of a graph. A graph can
 * be created as well as updated with new values. The updated graph will reflect the values
 * present in the graphModel.
 *
 * @author Adhiraj Tikku
 * @version 1.0
 * @since 2018-03-30
 */
public class GraphView extends GraphAbstractView {
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private boolean legendDisplay;
    private double currentXCoordinate;

    /**
     * Initializes a graph instance and creates a default empty
     * graph.
     */
    public GraphView(GraphModel graphModel) {
        super(graphModel);
        currentXCoordinate = 0;
    }
    
    /**
     * this method initializes the view and configures the positions of
     * components.
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        super.initializeView(subViews);
        XYSeriesCollection dataSet = new XYSeriesCollection();
        chart = createChart(dataSet);
        chartPanel = new ChartPanel(chart);
        add(chartPanel);
        setVisible(true);
    }

    /**
     * Updates the Graph with new model data.
     *
     * @param graphModel a model object containing required graph data.
     * @see GraphModel
     */
    @Override
	public void updateView(ModelInterface model) {
    	this.graphModel = (GraphModel)model;
        legendDisplay = true;
        if (graphModel.getNoOfChannels() == 6)
            legendDisplay = false;
        remove(chartPanel);
        XYDataset dataSet = createDataSet();
        chart = createChart(dataSet);
        chartPanel = new ChartPanel(chart);
        add(chartPanel);
        setVisible(true);
	}

    /**
     * this method creates data set to plot graph
     *
     * @return dataset an object of XYDataset
     */
    private XYDataset createDataSet() {
        XYSeries series[] = new XYSeries[graphModel.getNoOfChannels()];
        XYSeriesCollection dataSet = new XYSeriesCollection();

        for (int i = 0; i < graphModel.getNoOfChannels(); i++) {
            if (legendDisplay)
                series[i] = new XYSeries(graphModel.getLegendNames()[i]);
            else
                series[i] = new XYSeries(i);
        }

        if (graphModel.getGraphData() != null) {
            for (ArrayList<CoordinatesModel> data : graphModel.getGraphData()) {
                for (int i = 0; i < graphModel.getNoOfChannels(); i++) {
                    double xCoordinate = data.get(i).getXCoordinate();
                    double yCoordinate = data.get(i).getYCoordinate();
                    if (graphModel.getNoOfChannels() == 6)
                        series[i].add(graphModel.getXLength() - xCoordinate, yCoordinate);
                    else
                        series[i].add(xCoordinate, yCoordinate);
                    currentXCoordinate = xCoordinate;
                }
            }
        }

        for (int i = 0; i < graphModel.getNoOfChannels(); i++) {
            dataSet.addSeries(series[i]);
        }

        return dataSet;
    }

    /**
     * This method creates chart for graphs.
     *
     * @param dataSet XYDataset
     * @return chart JFreeChart
     */
    private JFreeChart createChart(final XYDataset dataSet) {
        JFreeChart chart = ChartFactory.createXYLineChart("", "",
                "", dataSet, PlotOrientation.VERTICAL, legendDisplay, true,
                false);
        chart.setBackgroundPaint(Color.decode(ClientConstants.PANEL_COLOR_HEX));

        XYPlot plot = chart.getXYPlot();

        ValueAxis range = plot.getRangeAxis();
        if (graphModel.getNoOfChannels() == 6) {
            range.setTickLabelPaint(Color.WHITE);
            range.setTickLabelFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, GRAPH_AXIS_FONT_SIZE));
        } else {
            range.setTickLabelsVisible(false);
            range.setTickMarksVisible(false);
            range.setAxisLineVisible(false);
        }

        range = plot.getDomainAxis();
        if (graphModel.getNoOfChannels() == 12 && currentXCoordinate > graphModel.getXLength()) {
            int diff = (int) currentXCoordinate - graphModel.getXLength();
            graphModel.setXStartPoint(graphModel.getXStartPoint() + diff);
            graphModel.setXLength(graphModel.getXLength() + diff);
            range.setRange(graphModel.getXStartPoint(), graphModel.getXLength());
        } else {
            if(graphModel.getXLength() == 0)
                graphModel.setXLength(1);
            range.setRange(0, graphModel.getXLength());
        }
        range.setTickLabelPaint(Color.WHITE);
        range.setTickLabelFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, GRAPH_AXIS_FONT_SIZE));


        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < graphModel.getNoOfChannels(); i++) {
            if (graphModel.getChannelColors() != null)
                renderer.setSeriesPaint(i, graphModel.getChannelColors()[i]);
            renderer.setSeriesShapesVisible(i, false);
        }

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.decode(ClientConstants.GRAPH_COLOR_HEX));

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        return chart;
    }
}