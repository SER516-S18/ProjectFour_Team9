package ser516.project3.server.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.server.Components.Console.ConsoleView;
import ser516.project3.server.Components.Emotions.EmotionsView;
import ser516.project3.server.Components.Expressions.ExpressionsView;
import ser516.project3.server.Components.Health.HealthView;
import ser516.project3.server.Components.Timer.TimerView;
import ser516.project3.server.Components.Top.TopView;

/**
 * The JFrame class of Server application
 * @author Ganesh, Janani, Sangeetha
 */
@SuppressWarnings("serial")
public class ServerView extends JFrame implements ViewInterface {
    private static ServerView serverViewInstance = null;

    private TopView topView;
    private TimerView timerView;
    private EmotionsView emotionsView;
    private ExpressionsView expressionsView;
    private ConsoleView consoleView;
    private HealthView healthView;
    
    private String selectedServer;

    private static final Font FONT = new Font(ServerConstants.FONT_NAME, Font.BOLD, 17);

	/**
	 * Override Method to initialize the expressions view panel
	 * @param subViews object of type ViewInterface
	 */
	@Override
	public void initializeView(ViewInterface[] subViews) {
		topView = (TopView) subViews[0];
		timerView = (TimerView) subViews[1];
		consoleView = (ConsoleView) subViews[2];
		switch(selectedServer) {
		case "EMOTIONS_SERVER":
			emotionsView = (EmotionsView) subViews[3];
			expressionsView = (ExpressionsView) subViews[4];
			break;
		case "HEALTH_SERVER":
			healthView = (HealthView) subViews[3];
			break;
		}

		setLayout(new BorderLayout());
		add(topView, BorderLayout.PAGE_START);
		add(createConfigurationPanels(), BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 800));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(ServerConstants.SERVER);
		setVisible(true);
	}
	
	/**
	 * Override Method to update the view
	 * Empty but mandatory as it is an override method
	 * @param model object of type ModelInterface
	 */
	
	@Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Setter method to set the selected server
	 * 
	 * @param selectedServer the name of the server selected
	 */
	public void setSelectedServer(String selectedServer) {
		this.selectedServer = selectedServer;
	}

	/**
	 * This method will initialize the second sub panel of the Server window
	 * @return the second sub-panel
	 */
	private Component createConfigurationPanels() {
		JPanel configPanel = new JPanel();
		JPanel timerPanel = timerView;
		JPanel emotionsPanel = emotionsView;
		JPanel expressionPanel = expressionsView;
		JPanel consolePanel = consoleView;
		JPanel healthPanel = healthView;

		JSplitPane splitTimerPanel = new JSplitPane();
		JSplitPane splitEmotionsPanel = new JSplitPane();
		JSplitPane splitExpressionPanel = new JSplitPane();
		JSplitPane splitHealthPanel = new JSplitPane();

		configPanel.setOpaque(false);
		
		configPanel.add(timerPanel);
		if(selectedServer.equals("EMOTIONS_SERVER")) {
			configPanel.add(emotionsPanel);
			configPanel.add(expressionPanel);
		} else {
			configPanel.add(healthPanel);
		}
		configPanel.add(consolePanel);

		Border titledBorder = new TitledBorder(null, "Configuration", TitledBorder.LEADING, 
				TitledBorder.TOP, FONT, null);
		Border marginBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);

		Border compound = BorderFactory.createCompoundBorder(marginBorder, titledBorder);
		configPanel.setBorder(compound);

		configPanel.setLayout(new BorderLayout(0, 0));

		splitExpressionPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitExpressionPanel.setDividerLocation(150);
		splitExpressionPanel.setTopComponent(expressionPanel);
		splitExpressionPanel.setBottomComponent(splitHealthPanel);
		splitExpressionPanel.setDividerSize(0);
		splitExpressionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

		splitEmotionsPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitEmotionsPanel.setDividerLocation(150);
		splitEmotionsPanel.setTopComponent(emotionsPanel);
		splitEmotionsPanel.setBottomComponent(splitExpressionPanel);
		splitEmotionsPanel.setDividerSize(0);
		splitEmotionsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));


		splitHealthPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitHealthPanel.setDividerLocation(150);
		splitHealthPanel.setTopComponent(healthPanel);
		splitHealthPanel.setBottomComponent(consolePanel);
		splitHealthPanel.setDividerSize(0);
		splitHealthPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

		splitTimerPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitTimerPanel.setDividerLocation(50);
		splitTimerPanel.setTopComponent(timerPanel);
		splitTimerPanel.setBottomComponent(splitEmotionsPanel);
		splitTimerPanel.setDividerSize(0);
		splitTimerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

		configPanel.add(splitTimerPanel);
		return configPanel;
	}

	/**
	 * Method to add WindowListener to the Server window
	 * @param windowAdapter WindowAdapter object
	 */
	public void addServerWindowListener(WindowAdapter windowAdapter) {
		addWindowListener(windowAdapter);
	}
}
