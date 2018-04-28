package ser516.project3.server.Components.Top;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
public abstract class TopAbstractView extends JPanel implements ViewInterface {

	protected TopModel topModel;
	
	/**
     * Method to set top model
     *
     * @param topModel model object containing required data for the
     *                 server settings panel
     */
    public TopAbstractView(TopModel topModel) {
        this.topModel = topModel;
    }
	
    /**
     * Override Method to initialize view
     * @param subViews object of type ViewInterface
     */
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setLayout(new GridBagLayout());
        setBackground(Color.decode("#747b83"));
	}
	
	/**
	 * Adds the listener to the respective component in the view class
	 *
	 * @param eventListener the listener for the respective component
	 * @param componentName component to which the listener has to be added
	 */
	public abstract void addListener(EventListener eventListener, String componentName);
	
	/**
	 * Sets the blinking status according to the connection 
	 *
	 * @param status boolean status - If true shows green blinking dot
	 * 								else shows red dot
	 */
	public abstract void setBlinking(boolean status);
}
