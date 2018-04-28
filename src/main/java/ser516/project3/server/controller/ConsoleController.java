package ser516.project3.server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ser516.project3.server.Components.Console.ConsoleAbstractController;
import ser516.project3.server.Components.Console.ConsoleModel;
import ser516.project3.server.Components.Console.ConsoleView;

/**
 * Class that controls the console on the server which
 * shows server status and any error messages
 *
 * @author Adhiraj Tikku
 */
public class ConsoleController extends ConsoleAbstractController {

    /**
     * Constructor to set the console view and model and to add observer
     * to the components in console
     */
    public ConsoleController(ConsoleModel consoleModel, ConsoleView consoleView) {
        super(consoleModel, consoleView);
        consoleModel.addObserver(consoleView);
    }

    /**
     * Override Method to initialize the console view and to add listeners
     * to the component
     */
    @Override
    public void initializeView() {
        consoleView.initializeView(null);
        consoleView.addListener(new ClearConsoleListener(), "BUTTON_CLEARCONSOLE");
    }

    /**
     * Inner class to add action listener to console
     */
    class ClearConsoleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            consoleView.clearConsole();
        }
    }

    /**
     * Method to get console model object
     *
     * @return ConsoleModel object
     */
    public ConsoleModel getConsoleModel() {
        return consoleModel;
    }
}
