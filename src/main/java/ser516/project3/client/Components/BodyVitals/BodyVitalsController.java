package ser516.project3.client.Components.BodyVitals;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;

public class BodyVitalsController extends BodyVitalsAbstractController{
	
	private GraphController graphController;
	
	/**
	 * Constructor to set the private variables with the passed parameters
	 *  
	 * @param bodyVitalsModel the model to store the body vitals
	 * @param bodyVitalsView  the view to show the body vitals
	 * @param graphController the controller which controls the graph view
     *                        on the body vitals tab
	 */
	public BodyVitalsController(BodyVitalsModel bodyVitalsModel, BodyVitalsView bodyVitalsView, GraphController graphController) {
		super(bodyVitalsModel, bodyVitalsView);
		this.graphController = graphController;
	}
	
	/**
     * Method is used to initialize body vitals view
     * where body vitals buttons and display length text field is added
     */
	@Override
	public void initializeView() {
		graphController.setNoOfChannels(5);
        graphController.setXLength(bodyVitalsModel.getDisplayLength());
        Color channelColors[] = {bodyVitalsModel.getPulseColor(),
        		bodyVitalsModel.getHeartRateColor(), bodyVitalsModel.getBodyTemperatureColor(),
        		bodyVitalsModel.getBloodSugarColor(), bodyVitalsModel.getBmiColor()
        		};
        graphController.setChannelColors(channelColors);
        graphController.updateView();
        ViewInterface clientViewInterface[] = {graphController.getView()};
        bodyVitalsView.initializeView(clientViewInterface);
        bodyVitalsView.addListener(new EmotionButtonsListener(), null, "BUTTON_EMOTIONS");
        bodyVitalsView.addListener(new DisplayLengthKeyListener(), "KEY", "TEXTFIELD_DISPLAYLENGTH");
        bodyVitalsView.addListener(new DisplayLengthDocumentListener(), "DOCUMENT", "TEXTFIELD_DISPLAYLENGTH");
	}
	
	/**
     * Returns the set of sub controllers in case any
     *
     * @return subControllers array containing sub controllers
     */
	@Override
	public ControllerInterface[] getSubControllers() {
		ControllerInterface[] subControllers = {graphController};
        return subControllers;
	}
	
    /**
     * Class implemented to handle action listener
     * of all emotion buttons like Stress, Interest, Focus, Excitement, Engagement
     * and Relaxation
     */
    public class EmotionButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color initialBackground;
            Color newBackground;
            switch (e.getActionCommand()) {
                case ClientConstants.PULSE:
                    initialBackground = bodyVitalsModel.getPulseColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.PULSE + " Color",
                            initialBackground);
                    if (newBackground != null) {
                    	bodyVitalsModel.setPulseColor(newBackground);
                    }
                    break;
                case ClientConstants.HEART_RATE:
                    initialBackground = bodyVitalsModel.getHeartRateColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.HEART_RATE + " Color",
                            initialBackground);
                    if (newBackground != null) {
                    	bodyVitalsModel.setHeartRateColor(newBackground);
                    }
                    break;
                case ClientConstants.BODY_TEMPERATURE:
                    initialBackground = bodyVitalsModel.getBodyTemperatureColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.BODY_TEMPERATURE + " Color",
                            initialBackground);
                    if (newBackground != null) {
                    	bodyVitalsModel.setBodyTemperatureColor(newBackground);
                    }
                    break;
                case ClientConstants.BLOOD_SUGAR:
                    initialBackground = bodyVitalsModel.getBloodSugarColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.BLOOD_SUGAR + " Color",
                            initialBackground);
                    if (newBackground != null) {
                    	bodyVitalsModel.setBloodSugarColor(newBackground);
                    }
                    break;
                case ClientConstants.BMI:
                    initialBackground = bodyVitalsModel.getBmiColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.BMI + " Color",
                            initialBackground);
                    if (newBackground != null) {
                    	bodyVitalsModel.setBmiColor(newBackground);
                    }
                    break;
            }

            Color channelColors[] = {bodyVitalsModel.getPulseColor(),
            		bodyVitalsModel.getHeartRateColor(), bodyVitalsModel.getBodyTemperatureColor(),
            		bodyVitalsModel.getBloodSugarColor(), bodyVitalsModel.getBmiColor() };
            graphController.setChannelColors(channelColors);
            graphController.updateView();
            bodyVitalsView.revalidate();
            bodyVitalsView.repaint();
            bodyVitalsView.updateView(bodyVitalsModel);
        }
    }

    /**
     * Class created to support display length key listener
     * to update graph controller and performance metric view
     * based on key event
     */
    public class DisplayLengthKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                graphController.updateView();
                bodyVitalsView.updateView(bodyVitalsModel);
                bodyVitalsView.revalidate();
                bodyVitalsView.repaint();
            }
        }
    }

    /**
     * Class implemented to handle document listener of all
     * display length related components
     */
    class DisplayLengthDocumentListener implements DocumentListener {
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                if (e.getDocument().getLength() == 0) {
                	bodyVitalsModel.setDisplayLength(1);
                    graphController.setXLength(1);
                } else {
                	bodyVitalsModel.setDisplayLength(
                            Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
                    graphController.setXLength(bodyVitalsModel.getDisplayLength());
                }
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                if (Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())) == 0) {
                	bodyVitalsModel.setDisplayLength(1);
                    graphController.setXLength(1);
                } else {
                	bodyVitalsModel.setDisplayLength(
                            Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
                    graphController.setXLength(bodyVitalsModel.getDisplayLength());
                }
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
