package ser516.project3.client.Components.Expressions;

import ser516.project3.interfaces.CommonDataInterface;
import ser516.project3.interfaces.ControllerInterface;

public abstract class ExpressionsAbstractController implements ControllerInterface, CommonDataInterface {

	protected ExpressionsModel expressionsModel;
	protected ExpressionsView expressionsView;
	
	public ExpressionsAbstractController(ExpressionsModel expressionsModel, ExpressionsView expressionsView) {
		this.expressionsModel = expressionsModel;
	    this.expressionsView = expressionsView;
	}
	
	/**
	 * Method to get expression view
	 * and @return expression view object
	 */
	@Override
	public ExpressionsView getView() {
	    return expressionsView;
	}

}
