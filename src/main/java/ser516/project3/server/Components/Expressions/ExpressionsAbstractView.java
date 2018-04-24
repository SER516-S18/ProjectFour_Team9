package ser516.project3.server.Components.Expressions;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

public abstract class ExpressionsAbstractView extends JPanel implements ViewInterface {

	protected ExpressionsModel expressionsModel;
	
	/**
     * Method to set expressions model
     *
     * @param expressionsModel model object containing required expressions data.
     */
    public ExpressionsAbstractView(ExpressionsModel expressionsModel) {
        this.expressionsModel = expressionsModel;
    }
	
	@Override
	public void initializeView(ViewInterface[] subViews) {
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#747b83"));
	}

	@Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub

	}
	
	public abstract void addListener(EventListener eventListener, String componentName);
	
	public abstract void changeActivateButtonType();
}
