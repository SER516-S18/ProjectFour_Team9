package ser516.project3.server.Components.Console;

import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * Abstract view class for console view to define the basic
 * functions to be implemented by all implementing classes
 *
 * @author Adhiraj Tikku
 */
public abstract class ConsoleAbstractView extends JPanel implements ViewInterface {

	protected ConsoleModel consoleModel;
	
	/**
     * Method to set console model
     *
     * @param consoleModel model object containing required console data.
     */
    public ConsoleAbstractView(ConsoleModel consoleModel) {
        this.consoleModel = consoleModel;
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

	/**
	 * method to clear all the data from the console view in the server
	 */
	public abstract void clearConsole();
}
