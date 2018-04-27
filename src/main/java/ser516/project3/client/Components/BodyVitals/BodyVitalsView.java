package ser516.project3.client.Components.BodyVitals;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;

import com.alee.laf.button.WebButton;

import ser516.project3.client.Components.NumberTextField;
import ser516.project3.client.Components.Graph.GraphView;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricModel;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

public class BodyVitalsView extends BodyVitalsAbstractView {
	
	private JPanel mainPanel;
    private JLabel displaylengthLabel;
    private JLabel secondsLabel;
    private JTextField displayLengthField;
    private WebButton pulseButton;
    private WebButton heartrateButton;
    private WebButton bodytemperatureButton;
    private WebButton bloodsugarButton;
    private WebButton bmiButton;
    
    private static final int FONT_SIZE = 17;

	public BodyVitalsView(BodyVitalsModel bodyVitalsModel) {
		super(bodyVitalsModel);
	}
	
    /**
     * This method initializes the Graph view, creates components and
     * configures positions on layout.
     *
     * @param subViews an array of ViewInterface objects
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
    	super.initializeView(subViews);
        GraphView graphView = (GraphView) subViews[0];

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(graphView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);

        createEmotionPanel();
        GridBagConstraints bagConstraints = new GridBagConstraints();
        createEmotionButtons(bagConstraints);
        createDisplayLengthLabel(bagConstraints);
        createDisplayLengthField(bagConstraints);
        createSecondsLabel(bagConstraints);

        add(mainPanel, gridBagConstraints);
        setVisible(true);
    }
    
    /**
     * This method updates the performance metric view buttons on color selection.
     *
     * @param model
     */
	@Override
	public void updateView(ModelInterface model) {
		this.bodyVitalsModel = (BodyVitalsModel)model;
		pulseButton.setBottomBgColor(this.bodyVitalsModel.getPulseColor());
		heartrateButton.setBottomBgColor(this.bodyVitalsModel.getHeartRateColor());
		bodytemperatureButton.setBottomBgColor(this.bodyVitalsModel.getBodyTemperatureColor());
		bloodsugarButton.setBottomBgColor(this.bodyVitalsModel.getBloodSugarColor());
		bmiButton.setBottomBgColor(this.bodyVitalsModel.getBmiColor());
        displayLengthField.setText("" + this.bodyVitalsModel.getDisplayLength());
        

        pulseButton.setTopSelectedBgColor(this.bodyVitalsModel.getPulseColor());
        heartrateButton.setTopSelectedBgColor(this.bodyVitalsModel.getHeartRateColor());
        bodytemperatureButton.setTopSelectedBgColor(this.bodyVitalsModel.getBodyTemperatureColor());
        bloodsugarButton.setTopSelectedBgColor(this.bodyVitalsModel.getBloodSugarColor());
        bmiButton.setBottomBgColor(this.bodyVitalsModel.getBmiColor());
        displayLengthField.setText("" + this.bodyVitalsModel.getDisplayLength());
		
	}

	@Override
	public void addListener(EventListener eventListener, String listenerType, String componentName) {
		switch(componentName) {
		case "BUTTON_EMOTIONS":
			pulseButton.addActionListener((ActionListener)eventListener);
			heartrateButton.addActionListener((ActionListener)eventListener);
			bodytemperatureButton.addActionListener((ActionListener)eventListener);
			bloodsugarButton.addActionListener((ActionListener)eventListener);
			break;
		case "TEXTFIELD_DISPLAYLENGTH":
			if(listenerType.equals("KEY"))
				displayLengthField.addKeyListener((KeyAdapter)eventListener);
			if(listenerType.equals("DOCUMENT"))
				displayLengthField.getDocument().addDocumentListener((DocumentListener)eventListener);
			break;
		}
	}
	
	/**
     * This method creates panel and configures components on the panel.
     */
    private void createEmotionPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new TitledBorder(null, ClientConstants.BODY_VITALS, TitledBorder.CENTER,
                TitledBorder.TOP, new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE), null));
        mainPanel.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
    }
    
    /**
     * This method creates buttons for body vitals.
     *
     * @param gridBagConstraints an object of GridBagContraints class
     *                           to configure the positions of buttons on
     *                           panel.
     */
    private void createEmotionButtons(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        pulseButton = createEmotionButton(ClientConstants.INTEREST, bodyVitalsModel.getPulseColor());
        mainPanel.add(pulseButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        heartrateButton = createEmotionButton(ClientConstants.ENGAGEMENT, bodyVitalsModel.getHeartRateColor());
        mainPanel.add(heartrateButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        bodytemperatureButton = createEmotionButton(ClientConstants.STRESS, bodyVitalsModel.getBodyTemperatureColor());
        mainPanel.add(bodytemperatureButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        bloodsugarButton = createEmotionButton(ClientConstants.RELAXATION, bodyVitalsModel.getBloodSugarColor());
        mainPanel.add(bloodsugarButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        bmiButton = createEmotionButton(ClientConstants.EXCITEMENT, bodyVitalsModel.getBmiColor());
        mainPanel.add(bmiButton, gridBagConstraints);

    }
    
    /**
     * This method creates label to show display length
     *
     * @param gridBagConstraints
     */
    private void createDisplayLengthLabel(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.insets = new Insets(10, 5, 10, 5);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        displaylengthLabel = new JLabel(ClientConstants.DISPLAY_LENGTH);
        displaylengthLabel.setForeground(Color.WHITE);
        displaylengthLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        displaylengthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displaylengthLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        mainPanel.add(displaylengthLabel, gridBagConstraints);
    }

    /**
     * This method creates text field to input display length.
     *
     * @param gridBagConstraints
     */
    private void createDisplayLengthField(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        displayLengthField = new NumberTextField("" + bodyVitalsModel.getDisplayLength());
        displayLengthField.setBackground(Color.decode(ClientConstants.TEXT_FIELD_COLOR_HEX));
        displayLengthField.setForeground(Color.WHITE);
        displayLengthField.setHorizontalAlignment(SwingConstants.CENTER);
        displayLengthField.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        displayLengthField.setBorder(null);
        mainPanel.add(displayLengthField, gridBagConstraints);
    }

    /**
     * This method creates label to display units for display length.
     *
     * @param gridBagConstraints
     */
    private void createSecondsLabel(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        secondsLabel = new JLabel(ClientConstants.SECONDS);
        secondsLabel.setForeground(Color.WHITE);
        secondsLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        secondsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        secondsLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        mainPanel.add(secondsLabel, gridBagConstraints);
    }

    /**
     * This method adds Emotion buttons into the panel where dimensions,color
     * and emotion name is passed as arguments
     *
     * @param emotion String
     * @param color   Color object
     * @return emotionButton a WebButton
     */
    private WebButton createEmotionButton(String emotion, Color color) {
        WebButton emotionButton = new WebButton(emotion);
        emotionButton.setBottomBgColor(color);
        emotionButton.setTopSelectedBgColor(color);
        emotionButton.setForeground(Color.WHITE);
        emotionButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        emotionButton.setOpaque(true);
        emotionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        emotionButton.setRound(30);
        emotionButton.setHorizontalAlignment(SwingConstants.CENTER);
        emotionButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        return emotionButton;
    }

}
