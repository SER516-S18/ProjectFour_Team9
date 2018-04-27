package ser516.project3.server.controller;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.Components.ServerCommonData;
import ser516.project3.server.Components.Health.HealthAbstractController;
import ser516.project3.server.Components.Health.HealthModel;
import ser516.project3.server.Components.Health.HealthView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.EventListener;

public class HealthController extends HealthAbstractController {

    public HealthController(HealthModel healthModel, HealthView healthView) {
        super(healthModel, healthView);
    }

    public void initializeView() {
        healthView.initializeView(null);
        healthView.addListener(new HealthController.SpinnerChangeListener(),
                "SPINNER_HEALTH");
    }


    class SpinnerChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner source = (JSpinner) e.getSource();
            switch (source.getName()) {
                case "Pulse":
                    healthModel.setPulse((double) source.getValue());
                    break;
                case "HeartRate":
                    healthModel.setHeartRate((double) source.getValue());
                    break;
                case "Temperature":
                    healthModel.setBodyTemperature((double) source.getValue());
                    break;
                case "BloodSugar":
                    healthModel.setBloodSugar((double) source.getValue());
                    break;
                case "Height":
                    healthModel.setHeight((double) source.getValue());
                    break;
                case "Weight":
                    healthModel.setWeight((double) source.getValue());
                    break;
            }
            ServerCommonData.getInstance().getMessage().setEmotion(source.getName(),
                    (Double) source.getValue());
        }
    }
}

