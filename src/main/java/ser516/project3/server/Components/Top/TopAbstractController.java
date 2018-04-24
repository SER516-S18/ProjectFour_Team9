package ser516.project3.server.Components.Top;

import ser516.project3.interfaces.ControllerInterface;

public abstract class TopAbstractController implements ControllerInterface {

	protected TopModel topModel;
	protected TopAbstractView topView;
	
	/**
     * Constructor to set the top view and model object
     *
     * @param topModel TopModel object
     * @param topView  TopView object
     */
    public TopAbstractController(TopModel topModel, TopView topView) {
        this.topModel = topModel;
        this.topView = topView;
    }

    /**
     * Method to get the TopView object
     *
     * @return TopView object
     */
    @Override
    public TopAbstractView getView() {
        return topView;
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
