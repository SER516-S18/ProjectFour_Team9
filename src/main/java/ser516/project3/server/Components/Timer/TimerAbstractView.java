package ser516.project3.server.Components.Timer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for timer panel view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
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
