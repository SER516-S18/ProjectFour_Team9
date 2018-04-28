package ser516.project3.client.Components.PerformanceMetric;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;
/**
 * Abstract view class for Performance Metric view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
public abstract class PerformanceMetricAbstractView extends JPanel implements ViewInterface {

	protected PerformanceMetricModel performanceMetricModel;
	
	/**
     * This constructor initializes the model of performance metric view
     *
     * @param performanceMetricModel
     */
    public PerformanceMetricAbstractView(PerformanceMetricModel performanceMetricModel) {
        this.performanceMetricModel = performanceMetricModel;
    }
	
    /**
     * This method configures the view
     */
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setLayout(new GridBagLayout());
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
	}

	/**
	 *
	 * Adds the listener to the respective component in the view class
	 *
	 * @param eventListener listener to be added to the component
	 * @param listenerType type of listener to be added
	 * @param componentName the name of the component to which the
     *                         listener has to be added
	 */
	public abstract void addListener(EventListener eventListener, String listenerType, String componentName);
}
