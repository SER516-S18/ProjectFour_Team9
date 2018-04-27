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
    private WebButton connectButton;
    private WebButton serverOpenButton;
    private WebComboBox connectServerChoice;
    private WebComboBox openServerChoice;

    private final static int FONT_SIZE = 15;
    private final static String EMOTION_SERVER_LABEL = "Emotion Server";
    private final static String HEALTH_SERVER_LABEL = "Health Server";

    private String[] ServerChoice = {"Select Server", "Emotion Server", "Client Server"};

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
        createConnectButton(bagConstraints);
        createServerOpenButton(bagConstraints);
    }
    
    @Override
	public void updateView(ModelInterface model) {
    	this.headerModel = (HeaderModel)model;
    	updateConnectionData();
    	updateTimeStamp();
	}

	@Override
	public void addListener(EventListener eventListener, String componentName) {
		switch(componentName) {
			case "BUTTON_CONNECT":
				connectButton.addActionListener((ActionListener)eventListener);
				break;
			case "BUTTON_OPENSERVER":
				serverOpenButton.addActionListener((ActionListener)eventListener);
				break;
		}
	}
    
    /**
     * This method updates the connection status on panel.
     */
    private void updateConnectionData() {
        // May need to update this
        if (headerModel.isConnectionStatus()) {
            connectButton.setText(ClientConstants.DISCONNECT);
            connectionEmotionStatusValueLabel.setText(ClientConstants.CONNECTED);
        } else {
            connectButton.setText(ClientConstants.CONNECT);
            connectionEmotionStatusValueLabel.setText(ClientConstants.DISCONNECTED);
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

        // Creating Drop down
        connectServerChoice = new WebComboBox(ServerChoice);
        connectServerChoice.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 4;
        gridbagConstraints.gridy = 1;
        gridbagConstraints.insets = new Insets(0, 0, 10, 10);
        add(connectServerChoice, gridbagConstraints);

        openServerChoice = new WebComboBox(ServerChoice);
        openServerChoice.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        gridbagConstraints.gridx = 4;
        gridbagConstraints.gridy = 2;
        gridbagConstraints.insets = new Insets(0, 0, 10, 10);
        add(openServerChoice, gridbagConstraints);

    }

    /**
     * This method creates connect button on the Panel.
     *
     * @param bagConstraints to set the position of button on panel.
     */
    private void createConnectButton(GridBagConstraints bagConstraints) {
        connectButton = new WebButton(ClientConstants.CONNECT);
        connectButton.setPreferredSize(new Dimension(120, 35));
        connectButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        connectButton.setBottomBgColor(Color.BLACK);
        connectButton.setTopBgColor(Color.BLACK);
        connectButton.setBottomSelectedBgColor(Color.WHITE);
        connectButton.setTopSelectedBgColor(Color.WHITE);
        connectButton.setForeground(Color.WHITE);
        connectButton.setDrawShade(false);
        connectButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = 5;
        bagConstraints.gridy = 1;
        bagConstraints.gridheight = 3;
        bagConstraints.insets = new Insets(0, 20, 70, 10);
        add(connectButton, bagConstraints);
    }

    /**
     * This method creates server open button on panel.
     *
     * @param bagConstraints to set position of button on panel.
     */
    private void createServerOpenButton(GridBagConstraints bagConstraints) {
        serverOpenButton = new WebButton(ClientConstants.OPEN_SERVER);
        serverOpenButton.setPreferredSize(new Dimension(120, 35));
        serverOpenButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        serverOpenButton.setBottomBgColor(Color.BLACK);
        serverOpenButton.setTopBgColor(Color.BLACK);
        serverOpenButton.setBottomSelectedBgColor(Color.WHITE);
        serverOpenButton.setTopSelectedBgColor(Color.WHITE);
        serverOpenButton.setForeground(Color.WHITE);
        serverOpenButton.setDrawShade(false);
        serverOpenButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = 5;
        bagConstraints.gridy = 2;
        bagConstraints.gridheight = 3;
        bagConstraints.insets = new Insets(0, 20, 40, 20);
        add(serverOpenButton, bagConstraints);

    }
}
