package ser516.project3.client.Components.Header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.alee.laf.combobox.WebComboBox;

import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpController;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpView;
import ser516.project3.client.Components.Header.HeaderModel.SelectedServer;
import ser516.project3.client.service.ClientConnectionServiceImpl;
import ser516.project3.client.service.ClientConnectionServiceInterface;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.server.controller.ServerController;

/**
 * This class controls the UI for the header view on client which helps in
 * connection and shows the connection status.
 *
 * @author Adhiraj Tikku, Vishakha Singal
 */

public class HeaderController extends HeaderAbstractController {

    private ConnectionPopUpController connectionPopUpController;
    private ServerController serverController;
    private ServerController healthServerController;
    private ClientConnectionServiceInterface clientConnectionService;
    private boolean tabSelected;

    /**
     * Constructor overloaded to initiate the model and view as well
     *
     * @param headerModel
     * @param headerView
     * @param clientConnectionService 
     * @param subControllers 
     */
    public HeaderController(HeaderModel headerModel, HeaderView headerView,
                            ConnectionPopUpController connectionPopUpController, 
                            ServerController serverController,
                            ServerController healthServerController,
                            ClientConnectionServiceImpl clientConnectionService) {
        super(headerModel, headerView);
        this.connectionPopUpController = connectionPopUpController;
        this.serverController = serverController;
        this.healthServerController = healthServerController;
        this.connectionPopUpController.setConnectionData(clientConnectionService);
        headerModel.setConnectDropdown(SelectedServer.NO_SERVER_SELECTED);
        headerModel.setOpenDropdown(SelectedServer.NO_SERVER_SELECTED);
    }

    /**
     * Method to add Connect and Server Open button in headerview
     */
    @Override
    public void initializeView() {
        headerView.initializeView(null);
        headerView.addListener(new ConnectListener(), "BUTTON_CONNECT_EMOTION");
        headerView.addListener(new ServerOpenListener(), "BUTTON_OPENSERVER_EMOTION");
        headerView.addListener(new HealthConnectListener(), "BUTTON_CONNECT_HEALTH");
        headerView.addListener(new HealthServerOpenListener(), "BUTTON_OPENSERVER_HEALTH");
    }

    /**
     * Returns the set of sub controllers in case any
     *
     * @return array containing connectionPopUp controller which is a sub
     * controller
     */
    @Override
    public ControllerInterface[] getSubControllers() {
        ControllerInterface[] subControllers = {connectionPopUpController};
        return subControllers;
    }

    /**
     * Updates the status of the connection to the server in the model and
     * subsequently, in the view
     *
     * @param connectionStatus The status of the current connection to server
     */
    @Override
    public void setConnectionStatus(boolean connectionStatus) {
        headerModel.setConnectionStatus(connectionStatus);
        headerView.updateView(headerModel);
    }

    /**
     * Overrriden method from interface for selected tab on screen
     *
     * @param tabSelected Current selected tab.
     */
    @Override
    public void setTabSelected(boolean tabSelected) {
        this.tabSelected = tabSelected;
    }
    
    /**
     * Method to set header time stamp
     * and update it once the interval and elapsed time is set in the server dialog
     *
     * @param timeStamp The timestamp is the current timestamp received from the server
     *                  to be updated on the UI
     */
    public void setHeaderTimeStamp(double timeStamp) {
        headerModel.setTimeStamp(timeStamp);
        headerView.updateView(headerModel);
    }

    /**
     * Class implemented to handle action listener
     * of server open button for Emotion server
     */
    class ServerOpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(serverController.isServerOpen()) {
            	serverController.showServer();
            }
            else
            	serverController.initializeView();
        }
    }

    /**
     * Class implemented to handle action listener
     * of Connect to server button for Emotion Server
     */
    class ConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	connectToEmotionServer();
        }
    }
    
    /**
     * Class implemented to handle action listener
     * of Connect to server button for health server
     *
     */
    class HealthConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	connectToHealthServer();
        }
    }
    
    /**
     * Class implemented to handle action listener
     * of server open button for Health server
     */
    class HealthServerOpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(healthServerController.isServerOpen()) {
        		healthServerController.showServer();
            }
            else
            	healthServerController.initializeView();
        }
    }
    
    /**
     * Method to handle the connection to the health server
     */
    public void connectToHealthServer() {
    	if (headerModel.isHealthConnectionStatus()) {
			clientConnectionService.stopClientConnection();
			headerModel.setHealthConnectionStatus(false);
			headerView.updateView(headerModel);
			connectionPopUpController.setConnectionStatus(false);
		} else {
			connectionPopUpController.setServerType("HEALTH");
			connectionPopUpController.initializeView();
			headerModel.setHealthConnectionStatus(connectionPopUpController.getConnectionStatus());
		}
    }
    
    /**
     * Method to handle the connection to the emotion server
     */
    public void connectToEmotionServer() {
    	if (headerModel.isConnectionStatus()) {
			clientConnectionService.stopClientConnection();
			headerModel.setConnectionStatus(false);
			headerView.updateView(headerModel);
			connectionPopUpController.setConnectionStatus(false);
		} else {
			connectionPopUpController.setServerType("EMOTIONS");
			connectionPopUpController.initializeView();
			headerModel.setConnectionStatus(connectionPopUpController.getConnectionStatus());
		}
    }
    
    /**
	 * Method to connect to a server end point
	 * @param ipAddress - the IP address field
	 * @param port - the port field
	 */
	public void toggleConnectionToServer(String ipAddress, int port) {
		if (headerModel.isConnectionStatus()) {
			clientConnectionService.stopClientConnection();
			headerModel.setConnectionStatus(false);
			// TODO: Find a way to stop the client container
		} else {
			clientConnectionService = new ClientConnectionServiceImpl();
			clientConnectionService.createClientConnection(ipAddress, port, ClientConstants.ENDPOINT);
			headerModel.setConnectionStatus(true);
		}
	}
}
