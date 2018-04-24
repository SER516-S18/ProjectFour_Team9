package ser516.project3.server.Components.Expressions;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for expression view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
public abstract class ExpressionsAbstractView extends JPanel implements ViewInterface {

	protected ExpressionsModel expressionsModel;
	
	/**
     * Method to set expressions model
     *
     * @param expressionsModel model object containing required expressions data.
     */
    public ExpressionsAbstractView(ExpressionsModel expressionsModel) {
        this.expressionsModel = expressionsModel;
    }
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#747b83"));
	}

	@Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub

	}

    /**
     * Adds the listener to the respective component in the view class
     *
     * @param eventListener the listener for the respective component
     * @param componentName component to which the listener has to be added
     */
	public abstract void addListener(EventListener eventListener, String componentName);

    /**
     *
     * Changes the expressions activate button status depending on the
     * auto-reset value in the checkbox
     */
	public abstract void changeActivateButtonType();
}
