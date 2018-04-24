package ser516.project3.server.Components.Console;

import ser516.project3.interfaces.ControllerInterface;

public abstract class ConsoleAbstractController implements ControllerInterface {
	
	protected ConsoleModel consoleModel;
	protected ConsoleAbstractView consoleView;
	
	/**
     * Constructor to set the console view and model and to add observer
     * to the components in console
     */
    public ConsoleAbstractController(ConsoleModel consoleModel, ConsoleView consoleView) {
        this.consoleModel = consoleModel;
        this.consoleView = consoleView;
    }

	/**
     * Method to get console view object
     *
     * @return ConsoleView object
     */
    @Override
    public ConsoleAbstractView getView() {
        return consoleView;
    }

    /**
     * Returns the set of sub controllers in case any
     *
     * @return array containing sub controllers
     */
    @Override
    public ControllerInterface[] getSubControllers() {
        return null;
    }
}
