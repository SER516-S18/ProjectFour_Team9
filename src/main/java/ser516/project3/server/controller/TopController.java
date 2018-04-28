package ser516.project3.server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.log4j.Logger;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.Components.Top.TopAbstractController;
import ser516.project3.server.Components.Top.TopModel;
import ser516.project3.server.Components.Top.TopView;
import ser516.project3.server.Components.Utility.ServerCommonData;
import ser516.project3.server.service.ServerConnectionServiceImpl;
import ser516.project3.server.service.ServerConnectionServiceInterface;

/**
 * Class that helps communicate between TopView and TopModel.
 * The controller can receive and update data from the TopView,
 * and use this data to update the TopModel.
 *
 * @author Adhiraj Tikku
 */
public class TopController extends TopAbstractController {
    final static Logger logger = Logger.getLogger(TopController.class);

    private ServerConnectionServiceImpl serverConnectionService;
    private ConsoleController consoleController;
    private String serverType;

    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final String SEND = "Send";

    /**
     * Constructor to set the top view and model object
     *
     * @param topModel TopModel object
     * @param topView  TopView object
     */
    public TopController(TopModel topModel, TopView topView, ConsoleController consoleController) {
        super(topModel, topView);
        serverConnectionService = new ServerConnectionServiceImpl();
        this.consoleController = consoleController;
    }

    /**
     * Override Method to initialize the top view and to add listeners
     * to all the components in the panel
     */
    @Override
    public void initializeView() {
        topView.initializeView(null);
        topView.addListener(new IntervalDocumentListener(), "TEXTFIELD_INTERVAL");
        topView.addListener(new AutoRepeatCheckBoxListener(), "CHECKBOX_AUTOREPEAT");
        topView.addListener(new ServerStartStopButtonListener(), "BUTTON_SERVER");
        topView.addListener(new SendButtonListener(), "BUTTON_SEND");
        ServerCommonData.getInstance().getMessage().setInterval(topModel.getInterval());
    }
    
    public void setServerType(String serverType) {
    	this.serverType = serverType;
    }

    public void stopServerConnection() {
//                ServiceModel.getInstance().setServerStatus(false);
        boolean status = serverConnectionService.stopServerEndpoint();
        topModel.setServerStarted(false);
    	topModel.setSendButtonEnabled(false);
    	topModel.setServerStartStopButtonText(ServerConstants.START_SERVER);
    	setBlinking(false);
    	updateServerStartStopButtonText();
    	updateEnableDisableSendButton();
        if(status) {
        	 consoleController.getConsoleModel().logMessage(ServerConstants.SERVER_STOPPED);
        }
    }

    /**
     * Inner class to add document listener to timer interval
     * component in the top panel
     */
    class IntervalDocumentListener implements DocumentListener {

        /**
         * Overide Method to remove update of time interval
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                if (e.getDocument().getLength() == 0) {
                    topModel.setInterval(1);
                } else {
                    topModel.setInterval(Double.parseDouble(e.getDocument().getText(0,
                            e.getDocument().getLength())));
                }
                topModel.setIntervalError(false);
                ServerCommonData.getInstance().getMessage().setInterval(topModel.getInterval());
                logger.info(ServerConstants.REMOVED_VALUE_INTERVAL);
            } catch (NumberFormatException ex) {
                topModel.setIntervalError(true);
                JOptionPane.showMessageDialog(null, ServerConstants.INVALID_INTERVAL);
            } catch (BadLocationException ex) {
                logger.info(ServerConstants.INTERVAL_PROBLEM);
            }
        }

        /**
         * Override Method to update the time interval
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                topModel.setInterval(Double.parseDouble(e.getDocument().getText(0,
                        e.getDocument().getLength())));
                ServerCommonData.getInstance().getMessage().setInterval(topModel.getInterval());
                topModel.setIntervalError(false);
                logger.info(ServerConstants.INSERT_INTERVAL_VALUE);
            } catch (NumberFormatException ex) {
                topModel.setIntervalError(true);
                JOptionPane.showMessageDialog(null, ServerConstants.INVALID_INTERVAL);
            } catch (BadLocationException ex) {
                logger.info(ServerConstants.INTERVAL_PROBLEM);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    /**
     * Inner class to add action listener to auto repeat check box
     * component in the top panel
     */
    class AutoRepeatCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isChecked = !topModel.isAutoRepeatCheckBoxChecked();
            topModel.setAutoRepeatCheckBoxChecked(isChecked);
            if (isChecked) {
                topModel.setSendButtonText(START);
            } else {
                topModel.setSendButtonText(SEND);
            }
            topView.updateView(topModel);
            logger.info(ServerConstants.TOGGLE_VALUE_CHANGED + isChecked);
        }
    }

    /**
     * Inner class to add action listener to server start/stop button
     * in the top panel
     */
    class ServerStartStopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.info(ServerConstants.START_STOP_PRESSED);
            boolean isStarted = topModel.isServerStarted();
            if (isStarted) {
//                ServiceModel.getInstance().setServerStatus(false);
            	stopServerConnection();
            } else {
            	serverConnectionService.setServerType(serverType);
//                ServiceModel.getInstance().setServerStatus(true);
        		serverConnectionService.initServerEndpoint();
                consoleController.getConsoleModel().logMessage(ServerConstants.SERVER_STARTED);
                topModel.setServerStarted(true);
                topModel.setSendButtonEnabled(true);
                topModel.setServerStartStopButtonText(ServerConstants.STOP_SERVER);
                setBlinking(true);
            }
            topView.updateView(topModel);
        }
    }

    /**
     * Inner class to add action listener to server data send button
     * in the top panel
     */
    class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.info(ServerConstants.SEND_PRESSED);
            if (!topModel.isIntervalError()) {
                if (topModel.isAutoRepeatCheckBoxChecked()) {
                    if (topModel.getSendButtonText().equals(START)) {
                        topModel.setSendButtonText(STOP);
                        consoleController.getConsoleModel().logMessage(ServerConstants.DATA_TO_CLIENT);
                    } else {
                        topModel.setSendButtonText(START);
                        consoleController.getConsoleModel().logMessage(ServerConstants.DATA_STOPPED_SENDING);
                    }
                    topModel.setAutoRepeatEnabled(!topModel.isAutoRepeatEnabled());
                    ServerCommonData.getInstance().setShouldRepeat(true);
                    topModel.setIntervalEditable(!topModel.isIntervalEditable());
                    topView.updateView(topModel);
                } else
                	ServerCommonData.getInstance().setShouldRepeat(false);
                if (topModel.isShouldSendData()) {
                    topModel.setShouldSendData(false);
                    ServerCommonData.getInstance().setShouldSendData(false);
                } else {
                    topModel.setShouldSendData(true);
                    ServerCommonData.getInstance().setShouldSendData(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, ServerConstants.INVALID_INTERVAL);
            }
        }
    }

    /**
     * Method to set the server connection service interface
     *
     * @param serverConnectionService ServerConnectionServiceInterface object

    public void setServerConnectionService(ServerConnectionServiceInterface serverConnectionService) {
        this.serverConnectionService = serverConnectionService;
    }*/

    /**
     * Method to set the server status indicator
     *
     * @param status Status of the server
     */
    public void setBlinking(boolean status) {
        topView.setBlinking(status);
    }

    /**
     * Method to update the text in start/stop button
     */
    public void updateServerStartStopButtonText() {
        topView.updateView(topModel);
    }

    /**
     * Method to enable/disable the send button
     */
    public void updateEnableDisableSendButton() {
        topView.updateView(topModel);
    }

    /**
     * Method to get the TopModel object
     *
     * @return TopModel object
     */
    public TopModel getTopModel() {
        return topModel;
    }
}
