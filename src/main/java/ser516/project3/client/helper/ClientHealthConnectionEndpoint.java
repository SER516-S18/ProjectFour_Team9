package ser516.project3.client.helper;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.log4j.Logger;

import ser516.project3.client.Components.BodyVitals.BodyVitalsDataObservable;
import ser516.project3.client.controller.ClientControllerFactory;
import ser516.project3.constants.ClientConstants;
import ser516.project3.model.BodyMessageModel;
import ser516.project3.server.Components.Utility.ServerCommonData;

/**
 * This class acts as an end point of the connection and provides the message
 * bean that is further used to instantiate singleton data objects for
 * performance metrics and expressions.
 *
 * @author Varun Srivastava
 */
@ClientEndpoint(decoders = {BodyMessageModelDecoder.class})
public class ClientHealthConnectionEndpoint {

    final static Logger logger = Logger.getLogger(ClientHealthConnectionEndpoint.class);

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected to endpoint: " + session.getBasicRemote());
        try {
            session.getBasicRemote().sendText(ServerCommonData.getInstance().getBodyMessage().toString());
        } catch (IOException ex) {
            logger.error("Exception in onOpen method::::" + ex.getMessage());
        }
    }

    @OnMessage
    public void processMessage(BodyMessageModel bodyMessageModel, Session session) {
        logger.info("Received data:::: " + bodyMessageModel);
        //BodyVitalsDataObservable.getInstance().addToListValues(MessageFormatConverter.convertMessageToPeformanceMetrics(bodyMessageModel));
    }

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
