package ser516.project3.client.Components.Header;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for Header view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */

public abstract class HeaderAbstractView extends JPanel implements ViewInterface {

	protected HeaderModel headerModel;
	
	/**
     * This constructor initializes the model of HeaderView
     *
     * @param headerModel
     */
    public HeaderAbstractView(HeaderModel headerModel) {
        this.headerModel = headerModel;
    }
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setBorder(null);
        setLayout(new GridBagLayout());
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
	}
	
	public abstract void addListener(EventListener eventListener, String componentName);
}
