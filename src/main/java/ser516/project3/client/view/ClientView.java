package ser516.project3.client.view;

import ser516.project3.client.Components.BodyVitals.BodyVitalsView;
import ser516.project3.client.Components.Expressions.ExpressionsView;
import ser516.project3.client.Components.Header.HeaderView;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;

/**
 * The ClientView class extends JFrame class to create the main clientUI window
 * and add the corresponding listeners to it
 *
 * @author vsriva12
 */
@SuppressWarnings("serial")
public class ClientView extends JFrame implements ViewInterface {

    private static ClientView clientViewInstance = null;

    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem serverMenuItem;
    private JTabbedPane expressionsEmotionsCombinedTab;

    private HeaderView headerView;
    private PerformanceMetricView performanceMetricView;
    private BodyVitalsView bodyVitalsView;
    private ExpressionsView expressionsView;

    private final static int FONT_SIZE = 15;
    private final static int FRAME_WIDTH = 1400;
    private final static int FRAME_HEIGHT = 700;

    /**
     * This method gets client view.
     *
     * @return clientViewInstance an object of class ClientView
     */
    public static ClientView getClientView() {
        if (clientViewInstance == null) {
            clientViewInstance = new ClientView();
        }
        return clientViewInstance;
    }

    /**
     * This method initializes views, creates components
     * and configures view.
     *
     * @param subviews[] an array of ViewInterface objects
     */
    @Override
    public void initializeView(ViewInterface subviews[]) {
        headerView = (HeaderView) subviews[0];
        performanceMetricView = (PerformanceMetricView) subviews[1];
        bodyVitalsView = (BodyVitalsView) subviews[2];
        expressionsView = (ExpressionsView) subviews[3];

        createMenuBar();
        createTabs();
        createLayout();
        setJMenuBar(menuBar);

        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub
		
	}

    /**
     * This method creates an option menu with item to open server
     * from client.
     */
    private void createMenuBar() {
        menuBar = new JMenuBar();
        optionsMenu = new JMenu(ClientConstants.OPTIONS);
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        serverMenuItem = new JMenuItem(ClientConstants.OPEN_SERVER);
        serverMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        optionsMenu.add(serverMenuItem);
        menuBar.add(optionsMenu);
    }

    /**
     * This method creates expression and
     * performance metric tabs.
     */
    private void createTabs() {
        expressionsEmotionsCombinedTab = new JTabbedPane();
        expressionsEmotionsCombinedTab.addTab(ClientConstants.PERFORMANCE_METRICS, performanceMetricView);
        expressionsEmotionsCombinedTab.addTab(ClientConstants.BODY_VITALS, bodyVitalsView);
        expressionsEmotionsCombinedTab.addTab(ClientConstants.EXPRESSIONS, expressionsView);
        expressionsEmotionsCombinedTab.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
    }

    /**
     * This method creates layout with desired
     * configuration.
     */
    private void createLayout() {
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.decode(ClientConstants.FRAME_COLOR_HEX));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(15, 10, 10, 10);
        add(headerView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(10, 10, 15, 10);
        add(expressionsEmotionsCombinedTab, gridBagConstraints);
    }

    /**
     * This method handles the events on menu item
     * in options menu.
     */
    public void addServerMenuItemListener(ActionListener actionListener) {
        serverMenuItem.addActionListener(actionListener);
    }

    /**
     * This method handles events on window.
     */
    public void addClientWindowListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    /**
     * This method handles events on the tabs.
     */
    public void addTabbedPaneSelectionListener(ChangeListener changeListener) {
        expressionsEmotionsCombinedTab.addChangeListener(changeListener);
    }
}
