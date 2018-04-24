package ser516.project3.client.Components.BodyVitals;

import ser516.project3.interfaces.ControllerInterface;

public abstract class BodyVitalsAbstractController implements ControllerInterface {
	
	protected BodyVitalsModel bodyVitalsModel;
	protected BodyVitalsAbstractView bodyVitalsView;
	
	/**
     * Constructor to set the private variables with the passed parameters
     *
     * @param bodyVitalsModel the model to store the performance metrics
     * @param bodyVitalsView  the view to show the performance metrics
     */
    public BodyVitalsAbstractController(BodyVitalsModel bodyVitalsModel,
                                       BodyVitalsView bodyVitalsView) {
        this.bodyVitalsModel = bodyVitalsModel;
        this.bodyVitalsView = bodyVitalsView;
    }

	/**
     * Method to get PerformanceMetric view
     *
     * @return bodyVitalsView object
     */
    @Override
    public BodyVitalsAbstractView getView() {
        return bodyVitalsView;
    }
}
