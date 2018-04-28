package ser516.project3.client.Components.Header;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * HeaderView class to implement the header view for client to show and update
 * the client status
 *
 * @author Vishakha Singal, Adhiraj Tikku
 * @version 1.0
 */
public class HeaderView extends HeaderAbstractView {


    private JLabel connectionEmotionStatusLabel;
    private JLabel connectionHealthStatusLabel;
    private JLabel connectionEmotionTextLabel;
    private JLabel connectionHealthTextLabel;
    private JLabel timestampEmotionTextLabel;
    private JLabel timestampHealthTextLabel;
    private JLabel connectionEmotionStatusValueLabel;
    private JLabel connectionHealthStatusValueLabel;
    private JLabel timestampEmotionValueLabel;
    private JLabel timestampHealthValueLabel;
    private WebButton connectButtonEmotion;
    private WebButton serverOpenButtonEmotion;
    private WebButton connectButtonHealth;
    private WebButton serverOpenButtonHealth;

    private final static int FONT_SIZE = 15;
    private final static String EMOTION_SERVER_LABEL = "Emotion Server";
    private final static String HEALTH_SERVER_LABEL = "Health Server";

    private String[] ServerChoice = {"Select Server", "Emotion Server", "Health Server"};

    /**
     * This constructor initializes the model of HeaderView
     *
     * @param headerModel
     */
    public HeaderView(HeaderModel headerModel) {
        super(headerModel);
    }

    /**
     * This method initializes the view of Header and creates components
     * on view.
     *
     * @param subViews
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
    	super.initializeView(subViews);
        GridBagConstraints bagConstraints = new GridBagConstraints();
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;

        createLabels(bagConstraints);
        createConnectButton(bagConstraints, "EMOTIONS", 4, 1, connectButtonEmotion,0,20,70,0);
        createConnectButton(bagConstraints, "HEALTH", 5, 1, serverOpenButtonEmotion,0,20,70,0);
        createServerOpenButton(bagConstraints, "EMOTIONS", 4, 2, connectButtonHealth,0,20,70,0);
        createServerOpenButton(bagConstraints, "HEALTH", 5, 2, serverOpenButtonHealth,0,20,70,0);
    }
    
    /**
     * this method updates connection status on the header section of client
     */
    @Override
	public void updateView(ModelInterface model) {
    	this.headerModel = (HeaderModel)model;
    	updateConnectionData();
    	updateTimeStamp();
	}

    /**
     * This method handles events on buttons present on header section
     */
	@Override
	public void addListener(EventListener eventListener, String componentName) {
		switch(componentName) {
			case "BUTTON_CONNECT_EMOTION":
				connectButtonEmotion.addActionListener((ActionListener)eventListener);
				break;
			case "BUTTON_OPENSERVER_EMOTION":
				serverOpenButtonEmotion.addActionListener((ActionListener)eventListener);
				break;
			case "BUTTON_CONNECT_HEALTH":
				connectButtonHealth.addActionListener((ActionListener)eventListener);
				break;
			case "BUTTON_OPENSERVER_HEALTH":
				serverOpenButtonHealth.addActionListener((ActionListener)eventListener);
				break;
		}
	}
    
    /**
     * This method updates the connection status on panel.
     */
    private void updateConnectionData() {
        if (headerModel.isConnectionStatus()) {
        	connectButtonEmotion.setText(ClientConstants.DISCONNECT);
            connectionEmotionStatusValueLabel.setText(ClientConstants.CONNECTED);
        } else {
        	connectButtonEmotion.setText(ClientConstants.CONNECT);
            connectionEmotionStatusValueLabel.setText(ClientConstants.DISCONNECTED);
        }
        if (headerModel.isHealthConnectionStatus()) {
        	connectButtonHealth.setText(ClientConstants.DISCONNECT);
        	connectionHealthStatusValueLabel.setText(ClientConstants.CONNECTED);
        } else {
        	connectButtonHealth.setText(ClientConstants.CONNECT);
            connectionHealthStatusValueLabel.setText(ClientConstants.DISCONNECTED);
        }
    }

    /**
     * This method updates the time stamp on the panel.
     */
    private void updateTimeStamp() {
       timestampEmotionValueLabel.setText(String.valueOf(headerModel.getTimeStamp()));
    }

    /**
     * This method creates labels on the panel of Header and
     * configures them.
     *
     * @param gridbagConstraints an instance of GridBagConstraints
     *                           for setting position of labels
     *                           on panel
     */
    private void createLabels(GridBagConstraints gridbagConstraints) {
        
    	createEmotionConnectionStatusView(gridbagConstraints);
        createHealthConnectionStatusView(gridbagConstraints);
    }
    

    /**
     * Method to create the view for the emotion server Connection Status
     * 
     * @param gridbagConstraints sets the position of the elements
     */
    private void createEmotionConnectionStatusView(GridBagConstraints gridbagConstraints) {
    	
    	connectionEmotionTextLabel = new JLabel(EMOTION_SERVER_LABEL);
        connectionEmotionTextLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionEmotionTextLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionEmotionTextLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 0;
        gridbagConstraints.gridy = 0;
        gridbagConstraints.insets = new Insets(0, 0, 10, 0);
        add(connectionEmotionTextLabel, gridbagConstraints);

        connectionEmotionStatusLabel = new JLabel(ClientConstants.STATUS);
        connectionEmotionStatusLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionEmotionStatusLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionEmotionStatusLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 0;
        gridbagConstraints.gridy = 1;
        gridbagConstraints.insets = new Insets(0, 0, 10, 60);
        add(connectionEmotionStatusLabel, gridbagConstraints);


        timestampEmotionTextLabel = new JLabel(ClientConstants.TIME_STAMP);
        timestampEmotionTextLabel.setHorizontalAlignment(JLabel.RIGHT);
        timestampEmotionTextLabel.setVerticalTextPosition(JLabel.CENTER);
        timestampEmotionTextLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 0;
        gridbagConstraints.gridy = 2;
        gridbagConstraints.insets = new Insets(0, 0, 10, 20);
        add(timestampEmotionTextLabel, gridbagConstraints);

        connectionEmotionStatusValueLabel = new JLabel(ClientConstants.DISCONNECTED);
        connectionEmotionStatusValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionEmotionStatusValueLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionEmotionStatusValueLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 1;
        gridbagConstraints.gridy = 1;
        gridbagConstraints.insets = new Insets(0, 0, 10, 40);
        add(connectionEmotionStatusValueLabel, gridbagConstraints);


        timestampEmotionValueLabel = new JLabel("0.0");
        timestampEmotionValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        timestampEmotionValueLabel.setVerticalTextPosition(JLabel.CENTER);
        timestampEmotionValueLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 1;
        gridbagConstraints.gridy = 2;
        gridbagConstraints.insets = new Insets(0, 0, 10, 80);
        add(timestampEmotionValueLabel, gridbagConstraints);
    	
    }
    
    /**
     * Method to create the view for the health Connection Status
     * 
     * @param gridbagConstraints sets the position of the elements
     */
    private void createHealthConnectionStatusView(GridBagConstraints gridbagConstraints) {
    	
    	connectionHealthTextLabel = new JLabel(HEALTH_SERVER_LABEL);
        connectionHealthTextLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionHealthTextLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionHealthTextLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 2;
        gridbagConstraints.gridy = 0;
        gridbagConstraints.insets = new Insets(0, 0, 10, 0);
        add(connectionHealthTextLabel, gridbagConstraints);

        connectionHealthStatusLabel = new JLabel(ClientConstants.STATUS);
        connectionHealthStatusLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionHealthStatusLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionHealthStatusLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 2;
        gridbagConstraints.gridy = 1;
        gridbagConstraints.insets = new Insets(0, 20, 10, 50);
        add(connectionHealthStatusLabel, gridbagConstraints);


        timestampHealthTextLabel = new JLabel(ClientConstants.TIME_STAMP);
        timestampHealthTextLabel.setHorizontalAlignment(JLabel.RIGHT);
        timestampHealthTextLabel.setVerticalTextPosition(JLabel.CENTER);
        timestampHealthTextLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 2;
        gridbagConstraints.gridy = 2;
        gridbagConstraints.insets = new Insets(0, 20, 10, 10);
        add(timestampHealthTextLabel, gridbagConstraints);

        connectionHealthStatusValueLabel = new JLabel(ClientConstants.DISCONNECTED);
        connectionHealthStatusValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        connectionHealthStatusValueLabel.setVerticalTextPosition(JLabel.CENTER);
        connectionHealthStatusValueLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 3;
        gridbagConstraints.gridy = 1;
        gridbagConstraints.insets = new Insets(0, 0, 10, 100);
        add(connectionHealthStatusValueLabel, gridbagConstraints);


        timestampHealthValueLabel = new JLabel("0.0");
        timestampHealthValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        timestampHealthValueLabel.setVerticalTextPosition(JLabel.CENTER);
        timestampHealthValueLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 3;
        gridbagConstraints.gridy = 2;
        gridbagConstraints.insets = new Insets(0, 0, 10, 140);
        add(timestampHealthValueLabel, gridbagConstraints);
    }

    /**
     * This method creates connect button on the Panel.
     *
     * @param bagConstraints to set the position of button on panel.
     */
    private void createConnectButton(GridBagConstraints bagConstraints, String connectionType, int gridX, int gridY,
                                     WebButton connectionButton, int top, int left, int bottom, int right) {
        connectionButton = new WebButton(ClientConstants.CONNECT + connectionType);
        connectionButton.setPreferredSize(new Dimension(140, 50));
        connectionButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        connectionButton.setBottomBgColor(Color.BLACK);
        connectionButton.setTopBgColor(Color.BLACK);
        connectionButton.setBottomSelectedBgColor(Color.WHITE);
        connectionButton.setTopSelectedBgColor(Color.WHITE);
        connectionButton.setForeground(Color.WHITE);
        connectionButton.setDrawShade(false);
        connectionButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = gridX;
        bagConstraints.gridy = gridY;
        bagConstraints.gridheight = 3;
        bagConstraints.insets = new Insets(top,left,bottom, right);
        add(connectionButton, bagConstraints);
        if(connectionType.equals("EMOTIONS")) {
        	connectButtonEmotion = connectionButton;
        } else {
        	connectButtonHealth = connectionButton;
        }
    }

    /**
     * This method creates server open button on panel.
     *
     * @param bagConstraints to set position of button on panel.
     */
    private void createServerOpenButton(GridBagConstraints bagConstraints, String connectionType, int gridX, int gridY,
                                        WebButton serverButton, int top, int left, int bottom, int right) {
    	serverButton = new WebButton(ClientConstants.OPEN_SERVER + connectionType);
    	serverButton.setPreferredSize(new Dimension(140, 50));
    	serverButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
    	serverButton.setBottomBgColor(Color.BLACK);
    	serverButton.setTopBgColor(Color.BLACK);
    	serverButton.setBottomSelectedBgColor(Color.WHITE);
    	serverButton.setTopSelectedBgColor(Color.WHITE);
    	serverButton.setForeground(Color.WHITE);
    	serverButton.setDrawShade(false);
    	serverButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = gridX;
        bagConstraints.gridy = gridY;
        bagConstraints.gridheight = 3;
        bagConstraints.insets = new Insets(top,left,bottom, right);
        add(serverButton, bagConstraints);
        if(connectionType.equals("EMOTIONS")) {
        	serverOpenButtonEmotion = serverButton;
        } else {
        	serverOpenButtonHealth = serverButton;
        }
    }
}
