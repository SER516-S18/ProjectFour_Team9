package ser516.project3.server.Components.Console;

import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

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
	
	public abstract void addListener(EventListener eventListener, String componentName);
	
	public abstract void clearConsole();
}
