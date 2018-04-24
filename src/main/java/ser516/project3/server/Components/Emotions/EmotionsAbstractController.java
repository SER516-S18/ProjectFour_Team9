package ser516.project3.server.Components.Emotions;

import ser516.project3.interfaces.ControllerInterface;

public abstract class EmotionsAbstractController implements ControllerInterface {

	protected EmotionsModel emotionsModel;
	protected EmotionsAbstractView emotionsView;
	
	/**
     * Constructor to set the emotions view and model object
     *
     * @param emotionsModel - EmotionsModel object
     * @param emotionsView  - EmotionsView object
     */
    public EmotionsAbstractController(EmotionsModel emotionsModel, EmotionsView emotionsView) {
        this.emotionsModel = emotionsModel;
        this.emotionsView = emotionsView;
    }

	/**
     * Method to get the EmotionsView object
     *
     * @return EmotionsView object
     */
    @Override
    public EmotionsAbstractView getView() {
        return emotionsView;
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
