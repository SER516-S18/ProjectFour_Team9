package ser516.project3.client.Components.BodyVitals;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.client.Components.BodyVitals.BodyVitalsModel;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

public abstract class BodyVitalsAbstractView extends JPanel implements ViewInterface {
	
	protected BodyVitalsModel bodyVitalsModel;
	
	/**
     * This constructor initializes the model of performance metric view
     *
     * @param bodyVitalsModel
     */
    public BodyVitalsAbstractView(BodyVitalsModel bodyVitalsModel) {
        this.bodyVitalsModel = bodyVitalsModel;
    }
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setLayout(new GridBagLayout());
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
	}
	
	public abstract void addListener(EventListener eventListener, String listenerType, String componentName);
}
