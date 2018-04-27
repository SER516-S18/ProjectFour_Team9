package ser516.project3.client.Components.Expressions;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for Expressions view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */

public abstract class ExpressionsAbstractView extends JPanel implements ViewInterface {

	protected ExpressionsModel expressionsModel;
	
	/**
     * This constructor initializes the model of Expressions view.
     *
     * @param expressionsModel an object of ExpressionsModel class
     */
    public ExpressionsAbstractView(ExpressionsModel expressionsModel) {
        this.expressionsModel = expressionsModel;
    }
	
	@Override
	public void updateView(ModelInterface model) {
		
	}

}
