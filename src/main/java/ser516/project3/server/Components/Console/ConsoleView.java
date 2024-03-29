package ser516.project3.server.Components.Console;

import com.alee.laf.button.WebButton;
import ser516.project3.constants.ClientConstants;
import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Class to create console and print the status to console
 * with time stamp.
 *
 * @author Vishakha, Zain, Pratik
 */

public class ConsoleView extends ConsoleAbstractView implements Observer {
    private JTextArea consoleOutput;
    private WebButton clearConsole;
    private JScrollPane consoleScroll;

    private static final Font SUBFONT = new Font(ServerConstants.FONT_NAME, Font.BOLD, 14);
    private static final Color LIGHTGREY = new Color(245, 245, 245);

    /**
     * Method to set console model
     *
     * @param consoleModel model object containing required console data.
     */
    public ConsoleView(ConsoleModel consoleModel) {
        super(consoleModel);
    }

    /**
     * Override Method to initialize the expressions view panel
     *
     * @param subViews object of type ViewInterface
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        setBorder(new TitledBorder(null, ServerConstants.CONSOLE, TitledBorder.LEADING,
                TitledBorder.TOP, SUBFONT, null));
        setBackground(Color.decode(ServerConstants.COLOR_CODE));
        setLayout(new FlowLayout());

        consoleOutput = new JTextArea();
        consoleOutput.setEditable(false);
        consoleOutput.setBackground(LIGHTGREY);
        consoleOutput.setFont(new Font(ServerConstants.FONT_NAME, Font.PLAIN, 12));

        consoleScroll = new JScrollPane(consoleOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        consoleScroll.setPreferredSize(new Dimension(400, 90));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        consoleScroll.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        clearConsole = new WebButton();
        clearConsole.setText(ServerConstants.CLEAR);
        clearConsole.setBottomBgColor(Color.BLACK);
        clearConsole.setTopBgColor(Color.BLACK);
        clearConsole.setBottomSelectedBgColor(Color.WHITE);
        clearConsole.setTopSelectedBgColor(Color.WHITE);
        clearConsole.setForeground(Color.WHITE);
        clearConsole.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, 15));
        add(consoleScroll);
        add(clearConsole);
    }

    /**
     * Override Method to append the new messages to console
     * 
     * @param messageArrayObject object of Observable
     * 
     */
    @Override
    public void update(Observable messageArrayObject, Object observerObj) {
        ConsoleModel model = (ConsoleModel) messageArrayObject;
        String message = model.getLogArray().get(model.getLogArray().size() - 1);
        DateFormat dateFormat = new SimpleDateFormat(ServerConstants.DATE_FORMAT);
        Date dateObj = new Date();

        consoleOutput.append("[" + dateFormat.format(dateObj) + "] : " + message);
        consoleOutput.append("\n");
    }
    
    /**
     * Adds the listener to the respective component in the view class
     *
     * @param eventListener the listener for the respective component
     * @param componentName component to which the listener has to be added
     */
    @Override
	public void addListener(EventListener eventListener, String componentName) {
		if(componentName.equals("BUTTON_CLEARCONSOLE")) {
			clearConsole.addActionListener((ActionListener)eventListener);
		}
	}

    /**
     * Method to clear the console
     */
    @Override
    public void clearConsole() {
        consoleOutput.setText(null);
    }
}
