package ser516.project3.client.observers;

import ser516.project3.client.Components.Expressions.ExpressionsDataObservable;
import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.client.controller.ClientControllerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * On receiving new data in ExpressionsDataObservable object update function of
 * this class can be used to update corresponding UI elements
 *
 * @author Manish Tandon
 */
public class ExpressionsGraphObserver implements Observer {

    /**
     * Update for expressions
     *
     * @param observable observable for expression
     * @param observerObj observer obj for expression
     */
    @Override
    public void update(Observable observable, Object observerObj) {
        ExpressionsDataObservable expressionsDataObservable = (ExpressionsDataObservable) observable;

        GraphController graphController = ClientControllerFactory.getInstance().getExpressionsGraphController();

        graphController.setGraphData(expressionsDataObservable.getExpressionsData());
        graphController.setNoOfChannels(12);
        graphController.updateView();
    }

}
