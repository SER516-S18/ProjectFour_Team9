package ser516.project3.server.Components.Expressions;

import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.EventListener;


/**
 * Class to create components in expressions panel
 * and listeners for each component
 *
 * @author Janani, Sangeetha, Ganesh
 */
public class ExpressionsView extends ExpressionsAbstractView {
    private JComboBox<Object> lowerFaceCombo;
    private JComboBox<Object> upperFaceCombo;
    private JComboBox<Object> eyeCombo;
    private JSpinner lowerFaceSpinner;
    private JSpinner upperFaceSpinner;
    private WebToggleButton eyeActionToggle;
    private WebButton eyeActionButton;
    private JCheckBox eyeCheckBox;
    
    String[] lowerFaceList = {"Smile", "Clench", "Smirk Left", "Smirk Right", "Laugh"};
    String[] upperFaceList = {"Raise Brow", "Furrow Brow"};
    String[] eyeList = {"Blink", "Wink Left", "Wink Right", "Look Left", "Look Right"};
    Dimension spinnerDimension = new Dimension(80, 30);
    private static final Font SUBFONT = new Font("Courier New", Font.BOLD, 14);

    /**
     * Method to set expressions model
     *
     * @param expressionsModel model object containing required expressions data.
     */
    public ExpressionsView(ExpressionsModel expressionsModel) {
        super(expressionsModel);
    }

    /**
     * Method to initialize the expressions view panel
     *
     * @param subViews object of type ViewInterface
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        super.initializeView(subViews);
        setBorder(new TitledBorder(null, "Expressions", TitledBorder.LEADING,
                        TitledBorder.TOP, SUBFONT, null));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        createLabels(gridBagConstraints);
        createComboBoxes(gridBagConstraints);
        createSpinners(gridBagConstraints);
        createActivateToggleButton(gridBagConstraints);
        createActivateButton(gridBagConstraints);
        createEyeCheckBox(gridBagConstraints);
    }
    
    @Override
	public void addListener(EventListener eventListener, String componentName) {
		switch(componentName) {
		case "COMBO_LOWERFACE":
			lowerFaceCombo.addActionListener((ActionListener)eventListener);
			break;
		case "COMBO_UPPERFACE":
			upperFaceCombo.addActionListener((ActionListener)eventListener);
			break;
		case "COMBO_EYE":
			eyeCombo.addActionListener((ActionListener)eventListener);
			break;
		case "SPINNER_LOWERFACE":
			lowerFaceSpinner.addChangeListener((ChangeListener)eventListener);
			break;
		case "SPINNER_UPPERFACE":
			upperFaceSpinner.addChangeListener((ChangeListener)eventListener);
			break;
		case "TOGGLE_EYE":
			eyeActionToggle.addItemListener((ItemListener)eventListener);
			break;
		case "BUTTON_EYE":
			eyeActionButton.getModel().addChangeListener((ChangeListener)eventListener);
			break;
		case "CHECKBOX_EYE":
			eyeCheckBox.addActionListener((ActionListener)eventListener);
			break;
		}
	}
    
    @Override
    public void changeActivateButtonType() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        if (expressionsModel.isEyeCheckBoxChecked()) {
            eyeActionToggle.setVisible(false);
            eyeActionButton.setVisible(true);
        } else {
            eyeActionToggle.setVisible(true);
            eyeActionButton.setVisible(false);
        }
    }

    /**
     * Method to create labels in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for each label
     */
    private void createLabels(GridBagConstraints gridBagConstraints) {
        JLabel lowerFaceLbl = new JLabel("Lower Face");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(lowerFaceLbl, gridBagConstraints);

        JLabel upperFaceLbl = new JLabel("Upper Face");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(upperFaceLbl, gridBagConstraints);

        JLabel eyeLabel = new JLabel("Eye");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(eyeLabel, gridBagConstraints);
    }

    /**
     * Method to create ComboBoxes in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for each combo box
     */
    private void createComboBoxes(GridBagConstraints gridBagConstraints) {
        lowerFaceCombo = new JComboBox<Object>();
        lowerFaceCombo.setModel(new DefaultComboBoxModel<Object>(lowerFaceList));
        lowerFaceCombo.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(lowerFaceCombo, gridBagConstraints);

        upperFaceCombo = new JComboBox<Object>();
        upperFaceCombo.setModel(new DefaultComboBoxModel<Object>(upperFaceList));
        upperFaceCombo.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        add(upperFaceCombo, gridBagConstraints);

        eyeCombo = new JComboBox<Object>();
        eyeCombo.setModel(new DefaultComboBoxModel<Object>(eyeList));
        eyeCombo.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(eyeCombo, gridBagConstraints);
    }

    /**
     * Method to create spinners in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for each spinners
     */
    private void createSpinners(GridBagConstraints gridBagConstraints) {
        SpinnerModel lowerFaceModel = new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1);
        lowerFaceSpinner = new JSpinner(lowerFaceModel);
        lowerFaceSpinner.setPreferredSize(spinnerDimension);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        add(lowerFaceSpinner, gridBagConstraints);

        SpinnerModel upperFaceModel = new SpinnerNumberModel(0.0, 0.0, 1.0, 0.1);
        upperFaceSpinner = new JSpinner(upperFaceModel);
        upperFaceSpinner.setPreferredSize(spinnerDimension);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        add(upperFaceSpinner, gridBagConstraints);
    }

    /**
     * Method to create toggleButton in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for toggleButton
     */
    private void createActivateToggleButton(GridBagConstraints gridBagConstraints) {
        eyeActionToggle = new WebToggleButton("Activate");
        eyeActionToggle.setPreferredSize(new Dimension(90, 30));
        eyeActionToggle.setBottomBgColor(Color.BLACK);
        eyeActionToggle.setTopBgColor(Color.BLACK);
        eyeActionToggle.setBottomSelectedBgColor(Color.WHITE);
        eyeActionToggle.setTopSelectedBgColor(Color.WHITE);
        eyeActionToggle.setForeground(Color.WHITE);
        eyeActionToggle.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, 15));
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        add(eyeActionToggle, gridBagConstraints);
    }

    /**
     * Method to create toggleButton in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for toggleButton
     */
    private void createActivateButton(GridBagConstraints gridBagConstraints) {
        eyeActionButton = new WebButton("Activate");
        eyeActionButton.setPreferredSize(new Dimension(90, 30));
        eyeActionButton.setBottomBgColor(Color.BLACK);
        eyeActionButton.setTopBgColor(Color.BLACK);
        eyeActionButton.setBottomSelectedBgColor(Color.WHITE);
        eyeActionButton.setTopSelectedBgColor(Color.WHITE);
        eyeActionButton.setForeground(Color.WHITE);
        eyeActionButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, 15));
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        add(eyeActionButton, gridBagConstraints);
        eyeActionButton.setVisible(false);
    }

    /**
     * Method to create CheckBox in expressions panel
     *
     * @param gridBagConstraints GridBagConstraints object to set the position
     *                           for CheckBox
     */
    private void createEyeCheckBox(GridBagConstraints gridBagConstraints) {
        eyeCheckBox = new JCheckBox("Auto Reset");
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        add(eyeCheckBox, gridBagConstraints);
    }
}