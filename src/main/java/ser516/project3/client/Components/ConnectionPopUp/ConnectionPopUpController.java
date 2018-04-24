package ser516.project3.client.Components.ConnectionPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import ser516.project3.client.controller.ClientControllerFactory;
import ser516.project3.constants.ClientConstants;

/**
 * It controls the flow of Connection Details pop up to
 * enter the ip address and port number to connect to the
 * server.
 *
 * @author vishakhasingal, Adhiraj Tikku
 */

public class ConnectionPopUpController extends ConnectionPopUpAbstractController {

    /**
     * Constructor to add popup model and view in
     * Connection popup controller
     */
    public ConnectionPopUpController(ConnectionPopUpModel connectionPopUpModel, ConnectionPopUpAbstractView connectionPopUpView) {
        super(connectionPopUpModel, connectionPopUpView);
    }

    /**
     * Method to initialize connection popup
     * view and add Connect, IP document and Port components to it
     */

    @Override
    public void initializeView() {
        connectionPopUpView.initializeView(null);
        connectionPopUpView.addListener(new ConnectListener(), "BUTTON_OK");
        connectionPopUpView.addListener(new IPDocumentListener(), "TEXTFIELD_IP");
        connectionPopUpView.addListener(new PortDocumentListener(), "TEXTFIELD_PORT");
    }

    /**
     * Inner class to control click on ok button in Pop up
     *
     * @author Adhiraj Tikku
     */
    class ConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (connectionPopUpModel.getIpAddress().length() == 0) {
                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, ClientConstants.NO_IP_ADDRESS_MESSAGE);
            } else if (connectionPopUpModel.getPortNumber() <= 0) {
                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, ClientConstants.NO_PORT_NO_MESSAGE);
            } else {
                ClientControllerFactory.getInstance().getClientController().toggleConnectionToServer(connectionPopUpModel.getIpAddress(), connectionPopUpModel.getPortNumber());
                connectionPopUpView.dispose();
            }
        }
    }

    /**
     * Inner class to control the updations in the ip address
     * text field and update the model accordingly.
     *
     * @author Adhiraj Tikku
     */
    class IPDocumentListener implements DocumentListener {
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                connectionPopUpModel.setIpAddress(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                connectionPopUpModel.setIpAddress(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    /**
     * Inner class to control the updations in the port number
     * text field and update the model accordingly.
     *
     * @author Adhiraj Tikku
     */
    class PortDocumentListener implements DocumentListener {
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                connectionPopUpModel.setPortNumber(Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
            } catch (NumberFormatException ex) {
                connectionPopUpModel.setPortNumber(0);
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                connectionPopUpModel.setPortNumber(Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
