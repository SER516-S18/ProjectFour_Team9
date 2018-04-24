package ser516.project3.server.Components.Expressions;

import ser516.project3.interfaces.ControllerInterface;

public abstract class ExpressionsAbstractController implements ControllerInterface {

	protected ExpressionsModel expressionsModel;
	protected ExpressionsAbstractView expressionsView;
	
	/**
     * Constructor to set the emotions view and model object
     *
     * @param expressionsModel ExpressionsModel object
     * @param expressionsView  ExpressionsView object
     */
    public ExpressionsAbstractController(ExpressionsModel expressionsModel, ExpressionsView expressionsView) {
        this.expressionsModel = expressionsModel;
        this.expressionsView = expressionsView;
    }
	/**
     * Method to get the ExpressionsView object
     *
     * @return ExpressionsView object
     */
    @Override
    public ExpressionsAbstractView getView() {
        return expressionsView;
    }

    /**
     * Returns the set of sub controllers in case any
     *
     * @return array containing sub controllers
     */
    @Override
    public ControllerInterface[] getSubControllers() {
        return null;
    }
}
