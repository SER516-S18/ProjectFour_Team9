package ser516.project3.client.Components.Expressions;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

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
