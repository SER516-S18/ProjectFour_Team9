package ser516.project3.server.Components.Health;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import java.util.EventListener;
/**
 * Abstract class for health view
 * 
 * @author abhinab, garv
 *
 */
public class HealthAbstractView extends JPanel implements ViewInterface {
    protected HealthModel healthModel;

    /**
     * View constructor to initialize the view with model 
     * 
     * @param healthModel model with which view has to be updated
     */
    public HealthAbstractView(HealthModel healthModel) {
        this.healthModel = healthModel;
    }

    /**
     * Initializes the view with the subviews if required
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {

    }

    /**
     * Updates the view with regards to the model interafce passed
     */
    @Override
    public void updateView(ModelInterface model) {

    }
    /**
     * Adds the listener for the respective class
     * 
     * @param eventListener the listener to be added
     * @param componentName name of the componenet to which the listener has to be added
     */
    public void addListener(EventListener eventListener, String componentName) {
    }
}
