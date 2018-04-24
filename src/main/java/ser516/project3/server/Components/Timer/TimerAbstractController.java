package ser516.project3.server.Components.Timer;

import ser516.project3.interfaces.ControllerInterface;

public abstract class TimerAbstractController implements ControllerInterface {

	protected TimerModel timerModel;
    protected TimerAbstractView timerView;
    
    /**
     * Constructor to set the timer view and model object
     *
     * @param timerModel TimerModel object
     * @param timerView  TimerView object
     */
    public TimerAbstractController(TimerModel timerModel, TimerView timerView) {
        this.timerModel = timerModel;
        this.timerView = timerView;
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

    /**
     * Method to get Timer view
     * and @return Timer view object
     */
    @Override
    public TimerAbstractView getView() {
        return timerView;
    }

}
