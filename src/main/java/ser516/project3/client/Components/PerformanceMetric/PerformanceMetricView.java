package ser516.project3.client.Components.PerformanceMetric;

import com.alee.laf.button.WebButton;

import ser516.project3.client.Components.NumberTextField;
import ser516.project3.client.Components.Graph.GraphView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.EventListener;

/**
 * This class contains performance metrics view
 * where emotion button is displayed on the right
 * and graph is displayed on the left.
 *
 * @author Mohan Vasantrao Yadav, Adhiraj Tikku
 */
public class PerformanceMetricView extends PerformanceMetricAbstractView {

    private JPanel mainPanel;
    private JLabel displaylengthLabel;
    private JLabel secondsLabel;
    private JTextField displayLengthField;
    private WebButton interestButton;
    private WebButton engagementButton;
    private WebButton stressButton;
    private WebButton relaxationButton;
    private WebButton excitementButton;
    private WebButton focusButton;

    private static final int FONT_SIZE = 17;

    /**
     * This constructor initializes the model of performance metric view
     *
     * @param performanceMetricModel
     */
    public PerformanceMetricView(PerformanceMetricModel performanceMetricModel) {
        super(performanceMetricModel);
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
     * @param performanceMetricModel
     */
    @Override
    public void updateView(ModelInterface model) {
        this.performanceMetricModel = (PerformanceMetricModel)model;
        interestButton.setBottomBgColor(this.performanceMetricModel.getInterestColor());
        engagementButton.setBottomBgColor(this.performanceMetricModel.getEngagementColor());
        stressButton.setBottomBgColor(this.performanceMetricModel.getStressColor());
        relaxationButton.setBottomBgColor(this.performanceMetricModel.getRelaxationColor());
        excitementButton.setBottomBgColor(this.performanceMetricModel.getExcitementColor());
        focusButton.setBottomBgColor(this.performanceMetricModel.getFocusColor());
        displayLengthField.setText("" + this.performanceMetricModel.getDisplayLength());

        interestButton.setTopSelectedBgColor(this.performanceMetricModel.getInterestColor());
        engagementButton.setTopSelectedBgColor(this.performanceMetricModel.getEngagementColor());
        stressButton.setTopSelectedBgColor(this.performanceMetricModel.getStressColor());
        relaxationButton.setTopSelectedBgColor(this.performanceMetricModel.getRelaxationColor());
        excitementButton.setTopSelectedBgColor(this.performanceMetricModel.getExcitementColor());
        focusButton.setTopSelectedBgColor(this.performanceMetricModel.getFocusColor());
        displayLengthField.setText("" + this.performanceMetricModel.getDisplayLength());
    }

	@Override
	public void addListener(EventListener eventListener, String listenerType, String componentName) {
		switch(componentName) {
			case "BUTTON_EMOTIONS":
				interestButton.addActionListener((ActionListener)eventListener);
		        engagementButton.addActionListener((ActionListener)eventListener);
		        stressButton.addActionListener((ActionListener)eventListener);
		        relaxationButton.addActionListener((ActionListener)eventListener);
		        excitementButton.addActionListener((ActionListener)eventListener);
		        focusButton.addActionListener((ActionListener)eventListener);
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
        mainPanel.setBorder(new TitledBorder(null, ClientConstants.EMOTIONS, TitledBorder.CENTER,
                TitledBorder.TOP, new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE), null));
        mainPanel.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
    }

    /**
     * This method creates buttons for performance metric.
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
        interestButton = createEmotionButton(ClientConstants.INTEREST, performanceMetricModel.getInterestColor());
        mainPanel.add(interestButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        engagementButton = createEmotionButton(ClientConstants.ENGAGEMENT, performanceMetricModel.getEngagementColor());
        mainPanel.add(engagementButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        stressButton = createEmotionButton(ClientConstants.STRESS, performanceMetricModel.getStressColor());
        mainPanel.add(stressButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        relaxationButton = createEmotionButton(ClientConstants.RELAXATION, performanceMetricModel.getRelaxationColor());
        mainPanel.add(relaxationButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        excitementButton = createEmotionButton(ClientConstants.EXCITEMENT, performanceMetricModel.getExcitementColor());
        mainPanel.add(excitementButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        focusButton = createEmotionButton(ClientConstants.FOCUS, performanceMetricModel.getFocusColor());
        mainPanel.add(focusButton, gridBagConstraints);
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
        displayLengthField = new NumberTextField("" + performanceMetricModel.getDisplayLength());
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
