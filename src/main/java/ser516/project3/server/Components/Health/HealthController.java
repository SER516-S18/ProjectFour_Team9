package ser516.project3.server.Components.Health;

import ser516.project3.server.Components.Expressions.ExpressionsController;

public class HealthController extends HealthAbstractController {

    public HealthController(HealthModel healthModel, HealthView healthView) {
        super(healthModel,healthView);
    }

    public void initializeView() {
        healthView.initializeView(null);
//        healthView.addListener(new HealthController.)
//
//        expressionsView.addListener(new ExpressionsController.LowerFaceComboListener(), "COMBO_LOWERFACE");
//        expressionsView.addListener(new ExpressionsController.UpperFaceComboListener(), "COMBO_UPPERFACE");
//        expressionsView.addListener(new ExpressionsController.EyeComboListener(), "COMBO_EYE");
//        expressionsView.addListener(new ExpressionsController.LowerFaceSpinnerChangeListener(), "SPINNER_LOWERFACE");
//        expressionsView.addListener(new ExpressionsController.UpperFaceSpinnerChangeListener(), "SPINNER_UPPERFACE");
//        expressionsView.addListener(new ExpressionsController.ActivateToggleButtonItemListener(), "TOGGLE_EYE");
//        expressionsView.addListener(new ExpressionsController.ActivateButtonChangeListener(), "BUTTON_EYE");
//        expressionsView.addListener(new ExpressionsController.EyeCheckBoxListener(), "CHECKBOX_EYE");
    }
}
