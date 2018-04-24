package ser516.project3.server.Components.Expressions;

import static java.lang.Thread.sleep;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ser516.project3.constants.ServerConstants;
import ser516.project3.model.MessageModel;
import ser516.project3.server.Components.ServerCommonData;
import ser516.project3.server.controller.ServerController;

/**
 * Class that helps communicate between ExpressionsView and ExpressionsModel.
 * The controller can receive and update data from the ExpressionsView,
 * and use this data to update the ExpressionsModel.
 *
 * @author Adhiraj Tikku
 */
public class ExpressionsController extends ExpressionsAbstractController {

    /**
     * Constructor to set the emotions view and model object
     *
     * @param expressionsModel ExpressionsModel object
     * @param expressionsView  ExpressionsView object
     */
    public ExpressionsController(ExpressionsModel expressionsModel, ExpressionsView expressionsView) {
        super(expressionsModel, expressionsView);
    }

    /**
     * Method to initialize the expressions view and to add listeners
     * to all the components in the panel
     */
    @Override
    public void initializeView() {
        expressionsView.initializeView(null);
        expressionsView.addListener(new LowerFaceComboListener(), "COMBO_LOWERFACE");
        expressionsView.addListener(new UpperFaceComboListener(), "COMBO_UPPERFACE");
        expressionsView.addListener(new EyeComboListener(), "COMBO_EYE");
        expressionsView.addListener(new LowerFaceSpinnerChangeListener(), "SPINNER_LOWERFACE");
        expressionsView.addListener(new UpperFaceSpinnerChangeListener(), "SPINNER_UPPERFACE");
        expressionsView.addListener(new ActivateToggleButtonItemListener(), "TOGGLE_EYE");
        expressionsView.addListener(new ActivateButtonChangeListener(), "BUTTON_EYE");
        expressionsView.addListener(new EyeCheckBoxListener(), "CHECKBOX_EYE");
    }

    /**
     * Inner class to add action listener to lower face combo box
     * component in the expressions panel
     */
    class LowerFaceComboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();
            expressionsModel.setLowerFaceItem(String.valueOf(comboBox.getSelectedItem()));
            updateLowerFace(expressionsModel.getLowerFaceItem(), expressionsModel.getLowerFaceValue());
        }
    }

    /**
     * Inner class to add action listener to upper face combo box
     * component in the expressions panel
     */
    class UpperFaceComboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();
            expressionsModel.setUpperFaceItem(String.valueOf(comboBox.getSelectedItem()));
            updateUpperFace(expressionsModel.getUpperFaceItem(), expressionsModel.getUpperFaceValue());
        }
    }

    /**
     * Inner class to add action listener to eye action combo box
     * component in the expressions panel
     */
    class EyeComboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();
            expressionsModel.setEyeItem(String.valueOf(comboBox.getSelectedItem()));
            updateEye(expressionsModel.getEyeItem(), expressionsModel.isEyeValue());
        }
    }

    /**
     * Inner class to add change listener to lower face action spinner
     * component in the expressions panel
     */
    class LowerFaceSpinnerChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            expressionsModel.setLowerFaceValue((double) jSpinner.getValue());
            updateLowerFace(expressionsModel.getLowerFaceItem(), expressionsModel.getLowerFaceValue());
        }
    }

    /**
     * Inner class to add change listener to upper face action spinner
     * component in the expressions panel
     */
    class UpperFaceSpinnerChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner jSpinner = (JSpinner) e.getSource();
            expressionsModel.setUpperFaceValue((double) jSpinner.getValue());
            updateUpperFace(expressionsModel.getUpperFaceItem(), expressionsModel.getUpperFaceValue());
        }
    }

    /**
     * Inner class to add item listener to eye action toggle button
     * component in the expressions panel
     */
    class ActivateToggleButtonItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent ev) {
            JToggleButton jToggleButton = (JToggleButton) ev.getSource();
            expressionsModel.setEyeValue(jToggleButton.isSelected());
            updateEye(expressionsModel.getEyeItem(), expressionsModel.isEyeValue());
        }
    }

    /**
     * Listener for activate button.
     */
    private class ActivateButtonChangeListener implements ChangeListener {
        private boolean pressed = false;

        @Override
        public void stateChanged(ChangeEvent e) {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isPressed() != pressed) {
                expressionsModel.setEyeValue(true);
                try {
                    sleep((long) ServerController.getInstance().getTopController().getTopModel().getInterval() * 1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                updateEye(expressionsModel.getEyeItem(), expressionsModel.isEyeValue());
                pressed = model.isPressed();
            } else {
                expressionsModel.setEyeValue(false);
                updateEye(expressionsModel.getEyeItem(), expressionsModel.isEyeValue());
            }
        }
    }

    /**
     * Inner class to add action listener to eye action auto-reset
     * check box in the expressions panel
     */
    class EyeCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox jCheckBox = (JCheckBox) e.getSource();
            expressionsModel.setEyeCheckBoxChecked(jCheckBox.isSelected());
            expressionsView.changeActivateButtonType();
        }
    }

    /**
     * Method to update the lower face attributes based on the values
     * set for each lower face expression in the expression panel
     *
     * @param lowerFaceAttribute type of lower face expression
     * @param lowerFaceVal       value set for a particular expression
     */
    private static void updateLowerFace(String lowerFaceAttribute, Double lowerFaceVal) {
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.smile.name(),
                lowerFaceAttribute.equals(ServerConstants.SMILE) ? lowerFaceVal : 0.0);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.clench.name(),
                lowerFaceAttribute.equals(ServerConstants.CLENCH) ? lowerFaceVal : 0.0);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.leftSmirk.name(),
                lowerFaceAttribute.equals(ServerConstants.SMIRK_LEFT) ? lowerFaceVal : 0.0);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.rightSmirk.name(),
                lowerFaceAttribute.equals(ServerConstants.SMIRK_RIGHT) ? lowerFaceVal : 0.0);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.laugh.name(),
                lowerFaceAttribute.equals(ServerConstants.LAUGH) ? lowerFaceVal : 0.0);

        if (lowerFaceAttribute.equals(ServerConstants.LAUGH)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag(
                    ServerConstants.LOWER_FACE, MessageModel.AbstractExpression.laugh.name());
        } else if (lowerFaceAttribute.equals(ServerConstants.CLENCH)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.LOWER_FACE, MessageModel.AbstractExpression.clench.name());
        } else if (lowerFaceAttribute.equals(ServerConstants.SMIRK_LEFT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.LOWER_FACE, MessageModel.AbstractExpression.leftSmirk.name());
        } else if (lowerFaceAttribute.equals(ServerConstants.SMIRK_RIGHT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.LOWER_FACE, MessageModel.AbstractExpression.rightSmirk.name());
        } else {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.LOWER_FACE, MessageModel.AbstractExpression.smile.name());
        }

    }

    /**
     * Method to update the upper face attributes based on the values
     * set for each upper face expression in the expression panel
     *
     * @param upperFaceAttribute type of upper face expression
     * @param upperFaceVal       value set for a particular expression
     */
    private static void updateUpperFace(String upperFaceAttribute, Double upperFaceVal) {
        ServerCommonData.getInstance().getMessage().setSelectionFlag(ServerConstants.UPPER_FACE,
                upperFaceAttribute);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.raiseBrow.name(),
                upperFaceAttribute.equals(ServerConstants.RAISE_BROW) ? upperFaceVal : 0.0);
        ServerCommonData.getInstance().getMessage().setAbstractExpression(
                MessageModel.AbstractExpression.furrowBrow.name(),
                upperFaceAttribute.equals(ServerConstants.FURROW_BROW) ? upperFaceVal : 0.0);
    }

    /**
     * Method to update the eye related attributes based on the values
     * set for each upper face expression in the expression panel
     *
     * @param eyeAttribute type of eye expression
     * @param eyeVal       value set for a particular eye expression
     */
    private static void updateEye(String eyeAttribute, Boolean eyeVal) {
        ServerCommonData.getInstance().getMessage().setSelectionFlag(ServerConstants.EYE, eyeAttribute);
        ServerCommonData.getInstance().getMessage().setConcreteExpression(
                MessageModel.ConcreteExpression.blink.name(),
                eyeAttribute.equals(ServerConstants.BLINK) ? eyeVal : false);
        ServerCommonData.getInstance().getMessage().setConcreteExpression(
                MessageModel.ConcreteExpression.leftWink.name(),
                eyeAttribute.equals(ServerConstants.WINK_LEFT) ? eyeVal : false);
        ServerCommonData.getInstance().getMessage().setConcreteExpression(
                MessageModel.ConcreteExpression.rightWink.name(),
                eyeAttribute.equals(ServerConstants.WINK_RIGHT) ? eyeVal : false);
        ServerCommonData.getInstance().getMessage().setConcreteExpression(
                MessageModel.ConcreteExpression.lookingLeft.name(),
                eyeAttribute.equals(ServerConstants.LOOK_LEFT) ? eyeVal : false);
        ServerCommonData.getInstance().getMessage().setConcreteExpression(
                MessageModel.ConcreteExpression.lookingRight.name(),
                eyeAttribute.equals(ServerConstants.LOOK_RIGHT) ? eyeVal : false);

        if (eyeAttribute.equals(ServerConstants.BLINK)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag(
                    ServerConstants.EYE, MessageModel.ConcreteExpression.blink.name());
        } else if (eyeAttribute.equals(ServerConstants.WINK_LEFT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.EYE, MessageModel.ConcreteExpression.leftWink.name());
        } else if (eyeAttribute.equals(ServerConstants.WINK_RIGHT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.EYE, MessageModel.ConcreteExpression.rightWink.name());
        } else if (eyeAttribute.equals(ServerConstants.LOOK_LEFT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.EYE, MessageModel.ConcreteExpression.lookingLeft.name());
        } else if (eyeAttribute.equals(ServerConstants.LOOK_RIGHT)) {
            ServerCommonData.getInstance().getMessage().setSelectionFlag
                    (ServerConstants.EYE, MessageModel.ConcreteExpression.lookingRight.name());
        }
    }
}
