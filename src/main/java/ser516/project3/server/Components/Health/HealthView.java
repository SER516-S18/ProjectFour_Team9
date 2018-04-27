package ser516.project3.server.Components.Health;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.server.Components.Console.ConsoleView;
import ser516.project3.server.Components.Timer.TimerView;
import ser516.project3.server.Components.Top.TopView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;

public class HealthView extends HealthAbstractView {

    private static HealthView serverViewInstance = null;

    private TopView topView;
    private TimerView timerView;
    private HealthView healthView;
    private ConsoleView consoleView;

    private static final Font FONT = new Font(ServerConstants.FONT_NAME, Font.BOLD, 17);

    public HealthView(HealthModel healthModel) {
        super(healthModel);
    }

    /**
     * Method to return the ServerView instance
     *
     */
    public static HealthView getServerView() {
        if (serverViewInstance == null) {
            serverViewInstance = new HealthView();
        }
        return serverViewInstance;
    }

    /**
     * Method to initialize the expressions view panel
     *
     * @param subViews
     *            object of type ViewInterface
     *
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        topView = (TopView) subViews[0];
        timerView = (TimerView) subViews[1];
        healthView = (HealthView) subViews[2];
        consoleView = (ConsoleView) subViews[3];

        setLayout(new BorderLayout());
        add(topView, BorderLayout.PAGE_START);
        add(createConfigurationPanels(), BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 800));
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setTitle(ServerConstants.SERVER);
        setVisible(true);
    }

    @Override
    public void updateView(ModelInterface model) {
        // TODO Auto-generated method stub

    }

    /**
     * This method will initialize the second sub panel of the Server window
     *
     * @return the second sub-panel
     */
    private Component createConfigurationPanels() {
        JPanel configPanel = new JPanel();
        JPanel timerPanel = timerView;
        JPanel healthPanel = healthView;
        JPanel consolePanel = consoleView;

        JSplitPane splitTimerPanel = new JSplitPane();
        JSplitPane splitHealthPanel = new JSplitPane();


        configPanel.setOpaque(false);

        configPanel.add(timerPanel);
        configPanel.add(healthPanel);
        configPanel.add(consolePanel);

        Border titledBorder = new TitledBorder(null, "Configuration", TitledBorder.LEADING,
                TitledBorder.TOP, FONT, null);
        Border marginBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);

        Border compound = BorderFactory.createCompoundBorder(marginBorder, titledBorder);
        configPanel.setBorder(compound);

        configPanel.setLayout(new BorderLayout(0, 0));



        splitHealthPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitHealthPanel.setDividerLocation(150);
        splitHealthPanel.setTopComponent(healthPanel);
        splitHealthPanel.setBottomComponent(consolePanel);
        splitHealthPanel.setDividerSize(0);
        splitHealthPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        splitTimerPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitTimerPanel.setDividerLocation(50);
        splitTimerPanel.setTopComponent(timerPanel);
        splitTimerPanel.setBottomComponent(splitHealthPanel);
        splitTimerPanel.setDividerSize(0);
        splitTimerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        configPanel.add(splitTimerPanel);
        return configPanel;
    }
}

