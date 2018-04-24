package ser516.project3.server.Components.Top;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ViewInterface;

public abstract class TopAbstractView extends JPanel implements ViewInterface {

	protected TopModel topModel;
	
	/**
     * Method to set top model
     *
     * @param topModel model object containing required data for the
     *                 server settings panel
     */
    public TopAbstractView(TopModel topModel) {
        this.topModel = topModel;
    }
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
		setLayout(new GridBagLayout());
        setBackground(Color.decode("#747b83"));
	}
	
	public abstract void addListener(EventListener eventListener, String componentName);
	
	public abstract void setBlinking(boolean status);
}
