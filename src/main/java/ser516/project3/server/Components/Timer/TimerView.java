package ser516.project3.server.Components.Timer;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create timer Panel on server
 * to set time elapsed.
 *
 * @author ravi teja, Adhiraj Tikku
 */
public class TimerView extends TimerAbstractView {
    private JTextField timeElapsedInputTextField;
    private JLabel timeElapsedLabel;

    private static final String TIME_ELAPSED = "Time Elapsed(ms):  ";

    /**
     * Method to set timer model
     *
     * @param timerModel model object containing required timer data.
     */
    public TimerView(TimerModel timerModel) {
        super(timerModel);
    }

    /**
     * Method to initialize the timer view panel
     *
     * @param subViews object of type ViewInterface
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        super.initializeView(subViews);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        createTimeElapsedLabel(gridBagConstraints);
        createTimeElapsedInputTextField(gridBagConstraints);
    }
    
    /**
     * Method to update the time stamp in the timer panel
     */
    @Override
    public void updateView(ModelInterface model) {
    	timerModel = (TimerModel) model;
        timeElapsedInputTextField.setText("" + timerModel.getTimeElapsed());
    }

    /**
     * Method to create labels in timer panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for each label
     */
    private void createTimeElapsedLabel(GridBagConstraints gridBagConstraints) {
        timeElapsedLabel = new JLabel(TIME_ELAPSED);
        timeElapsedLabel.setBackground(Color.decode(ServerConstants.COLOR_CODE));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 10;
        add(timeElapsedLabel, gridBagConstraints);
    }

    /**
     * Method to create text fields in timer panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for each text field
     */
    private void createTimeElapsedInputTextField(GridBagConstraints gridBagConstraints) {
        timeElapsedInputTextField = new JTextField("0");
        timeElapsedInputTextField.setBackground(Color.decode(ServerConstants.COLOR_CODE2));
        timeElapsedInputTextField.setBorder(null);
        timeElapsedInputTextField.setColumns(10);
        timeElapsedInputTextField.setHorizontalAlignment(SwingConstants.CENTER);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 10;
        add(timeElapsedInputTextField, gridBagConstraints);
    }
}
