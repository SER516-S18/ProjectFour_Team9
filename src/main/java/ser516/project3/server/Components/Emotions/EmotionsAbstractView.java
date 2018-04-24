package ser516.project3.server.Components.Emotions;

import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * Emotions view class for connection pop up view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
public abstract class EmotionsAbstractView extends JPanel implements ViewInterface {

	protected EmotionsModel emotionsModel;
	
	/**
     * Method to set emotion model
     *
     * @param emotionsModel model object containing required emotions data.
     */
    public EmotionsAbstractView(EmotionsModel emotionsModel) {
        this.emotionsModel = emotionsModel;
    }

	@Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub

	}

	/**
	 * Adds the listener to the respective component in the view class
	 *
	 * @param eventListener the listener for the respective component
	 * @param componentName component to which the listener has to be added
	 */
	public abstract void addListener(EventListener eventListener, String componentName);
}
