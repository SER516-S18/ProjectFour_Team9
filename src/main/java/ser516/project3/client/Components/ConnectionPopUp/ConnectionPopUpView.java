package ser516.project3.client.Components.ConnectionPopUp;

import com.alee.laf.button.WebButton;

import ser516.project3.client.Components.NumberTextField;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * ConnectioonPopUp class to show the pop up dialog in which user can enter the
 * server information to connect to the server like IP Address and Port Number.
 *
 * @author Vishakha Singal, Adhiraj Tikku
 * @version 1.0
 */
public class ConnectionPopUpView extends ConnectionPopUpAbstractView {

    private final static int FONT_SIZE = 15;

    /**
     * This constructor initializes the instance of connection PopUp dialog.
     *
     * @param connectionPopUpModel an instance of ConnectionPopUpModel class
     */
    public ConnectionPopUpView(ConnectionPopUpModel connectionPopUpModel) {
    	super(connectionPopUpModel);
    }

    /**
     * This method initializes the view, creates components on dialog
     * and configures dialog box.
     *
     * @param subViews an array of objects of ViewInterface class
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        super.initializeView(null);
        
        GridBagConstraints bagConstraints = new GridBagConstraints();
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;

        createMainPanel();
        createLabels(bagConstraints);
        createTextFields(bagConstraints);
        createOkButton(bagConstraints);

        add(mainPanel);
        setVisible(true);
    }
    
    /**
     * This method adds a listener to a component identified by a component name.
     *
     * @param eventListener object of the Listener to be added
     * @param componentName a String to identify the component
     */
	@Override
	public void addListener(EventListener eventListener, String componentName) {
		switch(componentName) {
			case "BUTTON_OK":
				okButton.addActionListener((ActionListener)eventListener);
				break;
			case "TEXTFIELD_IP":
				ipAddressTextField.getDocument().addDocumentListener((DocumentListener)eventListener);
				break;
			case "TEXTFIELD_PORT":
				portNumberTextField.getDocument().addDocumentListener((DocumentListener)eventListener);
				break;
		}
	}

    /**
     * This method creates a main panel for connection pop up
     * dialog with desired configuration.
     */
    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
    }

    /**
     * This method creates labels on connection PopUp dialog.
     *
     * @param bagConstraints object of GridBagConstraints Class
     */
    private void createLabels(GridBagConstraints bagConstraints) {
        ipAddressLabel = new JLabel(ClientConstants.IP_ADDRESS);
        ipAddressLabel.setHorizontalAlignment(JLabel.RIGHT);
        ipAddressLabel.setVerticalTextPosition(JLabel.CENTER);
        ipAddressLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(ipAddressLabel, bagConstraints);

        portNumberLabel = new JLabel(ClientConstants.PORT_NUMBER);
        portNumberLabel.setHorizontalAlignment(JLabel.RIGHT);
        portNumberLabel.setVerticalTextPosition(JLabel.CENTER);
        portNumberLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.insets = new Insets(0, 0, 0, 10);
        mainPanel.add(portNumberLabel, bagConstraints);
    }

    /**
     * This method creates text fields for Ip and port numbers
     * on connection PopUp dialog.
     *
     * @param bagConstraints an object of GridBagConstraints
     */
    private void createTextFields(GridBagConstraints bagConstraints) {
        ipAddressTextField = new JTextField(connectionPopUpModel.getIpAddress());
        ipAddressTextField.setPreferredSize(new Dimension(120, 20));
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        bagConstraints.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(ipAddressTextField, bagConstraints);

        portNumberTextField = new NumberTextField("" + connectionPopUpModel.getPortNumber());
        portNumberTextField.setPreferredSize(new Dimension(80, 20));
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;
        bagConstraints.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(portNumberTextField, bagConstraints);
    }

    /**
     * This method creates button on connection PopUp dailog.
     *
     * @param bagConstraints an object of GridBagConstraints
     */
    private void createOkButton(GridBagConstraints bagConstraints) {
        okButton = new WebButton(ClientConstants.OK);
        okButton.setPreferredSize(new Dimension(120, 35));
        okButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        okButton.setBottomBgColor(Color.BLACK);
        okButton.setTopBgColor(Color.BLACK);
        okButton.setBottomSelectedBgColor(Color.WHITE);
        okButton.setTopSelectedBgColor(Color.WHITE);
        okButton.setForeground(Color.WHITE);
        okButton.setDrawShade(false);
        okButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 2;
        bagConstraints.gridwidth = 2;
        bagConstraints.insets = new Insets(20, 20, 0, 20);
        mainPanel.add(okButton, bagConstraints);
    }
}
