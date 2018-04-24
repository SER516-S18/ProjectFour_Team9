package ser516.project3.server.Components.Timer;

/**
 * Class that helps communicate between TimerView and TimerModel.
 * The controller can receive and update data from the TimerView,
 * and use this data to update the TimerModel.
 *
 * @author Adhiraj Tikku
 */
public class TimerController extends TimerAbstractController {

    /**
     * Constructor to set the timer view and model object
     *
     * @param timerModel TimerModel object
     * @param timerView  TimerView object
     */
    public TimerController(TimerModel timerModel, TimerView timerView) {
        super(timerModel, timerView);
    }

    /**
     * Method to initialize the timer view
     */
    @Override
    public void initializeView() {
        timerView.initializeView(null);
    }

    /**
     * Updates the time stamp in TimerModel  and TimerView
     *
     * @param timeStamp value to be set for timeStamp in the server
     */
    public void updateTimeStamp(double timeStamp) {
        timerModel.setTimeElapsed(timeStamp);
        timerView.updateView(timerModel);
    }
}
