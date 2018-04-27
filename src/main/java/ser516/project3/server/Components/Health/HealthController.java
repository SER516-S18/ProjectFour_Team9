package ser516.project3.server.Components.Health;

import ser516.project3.server.Components.Expressions.ExpressionsController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.EventListener;

public class HealthController extends HealthAbstractController {

    public HealthController(HealthModel healthModel, HealthView healthView) {
        super(healthModel,healthView);
    }

    public void initializeView() {
        healthView.initializeView(null);

        healthView.addListener(new HealthController.PulseChangeListener(), "PULSE_CHANGE");
        healthView.addListener(new HealthController.HeartRateChangeListener(), "HEART_RATE");
        healthView.addListener(new HealthController.BodyTemperatureChangeListener(), "BODY_TEMPERATURE");
        healthView.addListener(new HealthController.BmiChangeListener(), "BMI");
        healthView.addListener(new HealthController.HeightChangeListener(), "HEIGHT");
        healthView.addListener(new HealthController.WeightChangeListener(), "WEIGHT");
    }


    private class PulseChangeListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setPulse((Double) jSpinner.getValue());

        }
    }

    private class HeartRateChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setHeartRate((Double) jSpinner.getValue());
        }
    }

    public class BodyTemperatureChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setBodyTemperature((Double) jSpinner.getValue());
        }
    }


    private class BmiChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setBmi((Double) jSpinner.getValue());
        }
    }

    private class HeightChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setHeight((Double) jSpinner.getValue());
        }
    }

    private class WeightChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            healthModel.setWeight((Double) jSpinner.getValue());
        }
    }
}


