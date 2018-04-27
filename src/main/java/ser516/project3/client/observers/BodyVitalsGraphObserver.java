package ser516.project3.client.observers;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import ser516.project3.client.Components.BodyVitals.BodyVitalsDataObservable;
import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.client.controller.ClientControllerFactory;

/**
 * This class observes data changes in body vitals data set and informs
 * the Graph object to populate new incoming data and repaint/update the user
 * interface graph element.
 */
public class BodyVitalsGraphObserver implements Observer {
	
	final static Logger logger = Logger.getLogger(BodyVitalsGraphObserver.class);
	
	/**
     * Update the graph
     *
     * @param observable observable for graph
     * @param observerObj observer obj for graph
     */
	@Override
	public void update(Observable observable, Object observerObj) {
		BodyVitalsDataObservable bodyVitalsDataObservable = (BodyVitalsDataObservable) observable;

        GraphController graphController = ClientControllerFactory.getInstance().getBodyVitalsGraphController();

        graphController.setGraphData(bodyVitalsDataObservable.getBodyVitalsData());
        graphController.updateView();
		
	}
	
}
