package ser516.project3.client.Components.PerformanceMetric;

import ser516.project3.interfaces.ControllerInterface;

public abstract class PerformanceMetricAbstractController implements ControllerInterface {

	protected PerformanceMetricModel performanceMetricModel;
	protected PerformanceMetricAbstractView performanceMetricView;
	
	/**
     * Constructor to set the private variables with the passed parameters
     *
     * @param performanceMetricModel the model to store the performance metrics
     * @param performanceMetricView  the view to show the performance metrics
     */
    public PerformanceMetricAbstractController(PerformanceMetricModel performanceMetricModel,
                                       PerformanceMetricView performanceMetricView) {
        this.performanceMetricModel = performanceMetricModel;
        this.performanceMetricView = performanceMetricView;
    }

	/**
     * Method to get PerformanceMetric view
     *
     * @return performancemetric view object
     */
    @Override
    public PerformanceMetricAbstractView getView() {
        return performanceMetricView;
    }
}
