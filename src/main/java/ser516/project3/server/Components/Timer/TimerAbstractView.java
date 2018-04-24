package ser516.project3.server.Components.Timer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ViewInterface;

public abstract class TimerAbstractView extends JPanel implements ViewInterface {

	protected TimerModel timerModel;
	
	/**
     * Method to set timer model
     *
     * @param timerModel model object containing required timer data.
     */
    public TimerAbstractView(TimerModel timerModel) {
        this.timerModel = timerModel;
    }
    
    /**
     * Method to initialize the timer view panel
     *
     * @param subViews object of type ViewInterface
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.decode(ServerConstants.COLOR_CODE));
    }

}
