package ser516.project3.client.Components.ConnectionPopUp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.EventListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.alee.laf.button.WebButton;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for connection pop up view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
@SuppressWarnings("serial")
public abstract class ConnectionPopUpAbstractView extends JDialog implements ViewInterface {
	protected ConnectionPopUpModel connectionPopUpModel;
	protected JPanel mainPanel;
	protected JLabel ipAddressLabel;
	protected JLabel portNumberLabel;
	protected JTextField ipAddressTextField;
	protected JTextField portNumberTextField;
	protected WebButton okButton;
	
	/**
     * This constructor initializes the instance of connection PopUp dialog.
     *
     * @param connectionPopUpModel an instance of ConnectionPopUpModel class
     */
    public ConnectionPopUpAbstractView(ConnectionPopUpModel connectionPopUpModel) {
        this.connectionPopUpModel = connectionPopUpModel;
    }
    
    /**
     * This method initializes the view with basic configurations for every type of Connection Pop Up.
     *
     * @param subViews an array of objects of ViewInterface class
     */
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(280, 200));
        setResizable(false);
        setTitle(ClientConstants.CONNECTION_POP_UP_TITLE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
	}
	
	/**
	 * Intentionally left empty
	 */
	@Override
	public void updateView(ModelInterface model) {
		
	}

    /**
     * Adds the listener to the respective component in the view class
     *
     * @param eventListener the listener for the respective component
     * @param componentName component to which the listener has to be added
     */
	public abstract void addListener(EventListener eventListener, String componentName);
}
