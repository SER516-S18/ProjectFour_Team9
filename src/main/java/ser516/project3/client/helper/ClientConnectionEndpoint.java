package ser516.project3.client.helper;

import org.apache.log4j.Logger;

import ser516.project3.client.Components.BodyVitals.BodyVitalsDataObservable;
import ser516.project3.client.Components.Expressions.ExpressionsDataObservable;
import ser516.project3.client.Components.Face.FaceExpressionsObservable;
import ser516.project3.client.Components.Header.HeaderObservable;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricDataObservable;
import ser516.project3.client.controller.ClientControllerFactory;
import ser516.project3.constants.ClientConstants;
import ser516.project3.model.*;
import ser516.project3.server.Components.Utility.ServerCommonData;

import javax.swing.*;
import javax.websocket.*;
import java.io.IOException;

/**
 * This class acts as an end point of the connection and provides the message
 * bean that is further used to instantiate singleton data objects for
 * performance metrics and expressions.
 *
 * @author Varun Srivastava, Manish Tandon
 */
@ClientEndpoint(decoders = {MessageDecoder.class})
public class ClientConnectionEndpoint {

    final static Logger logger = Logger.getLogger(ClientConnectionEndpoint.class);

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected to endpoint: " + session.getBasicRemote());
        try {
            session.getBasicRemote().sendText(ServerCommonData.getInstance().getMessage().toString());
        } catch (IOException ex) {
            logger.error("Exception in onOpen method::::" + ex.getMessage());
        }
    }

    /**
     * This method receives data on client side sent by server 
     * @param messageModelBean
     * @param session
     */
    @OnMessage
    public void processMessage(MessageModel messageModelBean, Session session) {
        logger.info("Received data:::: " + messageModelBean);
        PerformanceMetricDataObservable.getInstance().addToListValues(MessageFormatConverter.convertMessageToPeformanceMetrics(messageModelBean));
        BodyVitalsDataObservable.getInstance().addToListValues(MessageFormatConverter.convertMessageToPeformanceMetrics(messageModelBean));
        ExpressionsDataObservable.getInstance().addToListValues(MessageFormatConverter.convertMessageToExpressionsData(messageModelBean));
        FaceExpressionsObservable.getInstance().setMessageBean(messageModelBean);
        HeaderObservable.getInstance().setHeaderData(messageModelBean.getTimeStamp(), messageModelBean.getInterval());
    }

    /**
     * This method records error on console log
     * @param t
     */
    @OnError
    public void processError(Throwable t) {

        logger.error("Error occurred in Client End Point");
        t.printStackTrace();
    }

    @OnClose
    public void processClose(Session userSession, CloseReason reason) {
        logger.error("On Close called");
        if (reason.getReasonPhrase().length() > 0) {
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, ClientConstants.SERVER_STOPPED_MESSAGE, ClientConstants.ERROR_STRING, JOptionPane.ERROR_MESSAGE);
            ClientControllerFactory.getInstance().getClientController().stopClientConnector();
        }
    }

}
