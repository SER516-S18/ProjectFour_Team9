package ser516.project3.client.Components.PerformanceMetric;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

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
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setLayout(new GridBagLayout());
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
	}
	
	public abstract void addListener(EventListener eventListener, String listenerType, String componentName);
}
