package ser516.project3.server.Components.Top;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.log4j.Logger;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.Components.ServerCommonData;
import ser516.project3.server.controller.ServerController;
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

    private ServerConnectionServiceInterface serverConnectionService;

    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final String SEND = "Send";

    /**
     * Constructor to set the top view and model object
     *
     * @param topModel TopModel object
     * @param topView  TopView object
     */
    public TopController(TopModel topModel, TopView topView) {
        super(topModel, topView);
    }

    /**
     * Method to initialize the top view and to add listeners
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

    /**
     * Inner class to add document listener to timer interval
     * component in the top panel
     */
    class IntervalDocumentListener implements DocumentListener {

        /**
         * Method to remove update of time interval
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
         * Method to update the time interval
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
                serverConnectionService.stopServerEndpoint();
                topModel.setServerStarted(false);
                topModel.setSendButtonEnabled(false);
                topModel.setServerStartStopButtonText(ServerConstants.START_SERVER);
                setBlinking(false);
            } else {
                serverConnectionService.initServerEndpoint();
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
                        ServerController.getInstance().getConsoleController().getConsoleModel().
                                logMessage(ServerConstants.DATA_TO_CLIENT);
                    } else {
                        topModel.setSendButtonText(START);
                        ServerController.getInstance().getConsoleController().getConsoleModel().
                                logMessage(ServerConstants.DATA_STOPPED_SENDING);
                    }
                    topModel.setAutoRepeatEnabled(!topModel.isAutoRepeatEnabled());
                    topModel.setIntervalEditable(!topModel.isIntervalEditable());
                    topView.updateView(topModel);
                }
                if (topModel.isShouldSendData()) {
                    topModel.setShouldSendData(false);
                } else {
                    topModel.setShouldSendData(true);
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
     */
    public void setServerConnectionService(ServerConnectionServiceInterface serverConnectionService) {
        this.serverConnectionService = serverConnectionService;
    }

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
