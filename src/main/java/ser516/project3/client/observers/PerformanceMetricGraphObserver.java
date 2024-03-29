package ser516.project3.client.observers;

import org.apache.log4j.Logger;

import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricDataObservable;
import ser516.project3.client.controller.ClientControllerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * This class observes data changes in performance metrics data set and informs
 * the Graph object to populate new incoming data and repaint/update the user
 * interface graph element.
 *
 * @author Manish Tandon
 */
public class PerformanceMetricGraphObserver implements Observer {

    final static Logger logger = Logger.getLogger(PerformanceMetricGraphObserver.class);

    /**
     * Update the graph
     *
     * @param observable observable for graph
     * @param observerObj observer obj for graph
     */
    @Override
    public void update(Observable observable, Object observerObj) {

        PerformanceMetricDataObservable performanceMetricDataObservable = (PerformanceMetricDataObservable) observable;

        GraphController graphController = ClientControllerFactory.getInstance().getPerformanceMetricGraphController();

        graphController.setGraphData(performanceMetricDataObservable.getPerformanceMetricData());
        graphController.updateView();
    }

}
