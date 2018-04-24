package ser516.project3.server.Components.Emotions;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.Components.ServerCommonData;

/**
 * Class that helps communicate between EmotionsView and EmotionsModel.
 * The controller can receive and update data from the EmotionsView,
 * and use this data to update the EmotionsModel.
 *
 * @author Adhiraj Tikku
 */
public class EmotionsController extends EmotionsAbstractController {

    /**
     * Constructor to set the emotions view and model object
     *
     * @param emotionsModel - EmotionsModel object
     * @param emotionsView  - EmotionsView object
     */
    public EmotionsController(EmotionsModel emotionsModel, EmotionsView emotionsView) {
        super(emotionsModel, emotionsView);
    }

    /**
     * Method to initialize the emotions view and to add listeners
     * to the component  in the panel
     */
    @Override
    public void initializeView() {
        emotionsView.initializeView(null);
        for (int i = 0; i < 6; i++) {
            emotionsView.addListener(new SpinnerChangeListener(), "SPINNER_EMOTION");
        }
    }

    /**
     * Inner class to add change listeners to spinner components
     * in the emotions panel
     */
    class SpinnerChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner source = (JSpinner) e.getSource();
            switch (source.getName()) {
                case ServerConstants.INTEREST:
                    emotionsModel.setInterest((double) source.getValue());
                    break;
                case ServerConstants.ENGAGEMENT:
                    emotionsModel.setEngagement((double) source.getValue());
                    break;
                case ServerConstants.STRESS:
                    emotionsModel.setStress((double) source.getValue());
                    break;
                case ServerConstants.RELAXATION:
                    emotionsModel.setRelaxation((double) source.getValue());
                    break;
                case ServerConstants.EXCITEMENT:
                    emotionsModel.setExcitement((double) source.getValue());
                    break;
                case ServerConstants.FOCUS:
                    emotionsModel.setFocus((double) source.getValue());
                    break;
            }
            ServerCommonData.getInstance().getMessage().setEmotion(source.getName(),
                    (Double) source.getValue());
        }
    }
}
