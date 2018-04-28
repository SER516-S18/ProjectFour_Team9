package ser516.project3.server.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.server.Components.Console.ConsoleModel;
import ser516.project3.server.Components.Console.ConsoleView;
import ser516.project3.server.Components.Emotions.EmotionsModel;
import ser516.project3.server.Components.Emotions.EmotionsView;
import ser516.project3.server.Components.Expressions.ExpressionsModel;
import ser516.project3.server.Components.Expressions.ExpressionsView;
import ser516.project3.server.Components.Health.HealthModel;
import ser516.project3.server.Components.Health.HealthView;
import ser516.project3.server.Components.Timer.TimerModel;
import ser516.project3.server.Components.Timer.TimerView;
import ser516.project3.server.Components.Top.TopModel;
import ser516.project3.server.Components.Top.TopView;
import ser516.project3.server.helper.ServiceHelperModel;
import ser516.project3.server.service.ServerConnectionServiceImpl;
import ser516.project3.server.service.ServerConnectionServiceInterface;
import ser516.project3.server.view.ServerView;
import ser516.project3.server.view.ServerViewFactory;

/**
 * The ServerController class is the main controller class for the server
 * application. This class handles all the requests from the server UI and
 * interacts with the Server Service classes to perform the requested tasks
 *
 * @author vsriva12
 */
public class ServerController implements ControllerInterface, Observer {
    final static Logger logger = Logger.getLogger(ServerView.class);
    
    public boolean serverOpen;
    
    private ServerViewFactory viewFactory;
    private ServerControllerFactory serverControllerFactory;
    private ServerView serverView;
    private TopController topController;
    private HealthController healthController;
    private TimerController timerController;
    private EmotionsController emotionsController;
    private ExpressionsController expressionsController;
    private ConsoleController consoleController;
    private ServerConnectionServiceInterface serverConnectionService;
    private String selectedServer;

    /**
     * Constructor to initialize all components in the
     * server UI and also the  set up a Server service object
     * to perform requested tasks
     */
    public ServerController() {
        viewFactory = new ServerViewFactory();
        serverControllerFactory = ServerControllerFactory.getInstance();
        serverConnectionService = new ServerConnectionServiceImpl();
        
    }
    
    public ServerController(Observable observable, String selectedServer) {
    	this();
    	this.selectedServer = selectedServer;
    	
    	initializeConsole();
        initializeTop();
        initializeTimer();
        
        if(selectedServer.equals("EMOTIONS_SERVER")) {
        	initializeEmotions();
        	initializeExpressions();
        } else if (selectedServer.equals("HEALTH_SERVER")){
        	initializeHealth();
        }
    	observable.addObserver(this);
    }

    /**
     * Override Method to initialize all the sub panels in the server
     */
    @Override
    public void initializeView() {
        serverView = (ServerView) viewFactory.getView(ServerConstants.SERVER, null);
        serverView.setSelectedServer(selectedServer);
        if(selectedServer.equals("EMOTIONS_SERVER")) {
        	ViewInterface subViews[] = {topController.getView(), timerController.getView(),
        			consoleController.getView(), emotionsController.getView(),
        			expressionsController.getView()};
        	serverView.initializeView(subViews);
        } else {
        	ViewInterface subViews[] = {topController.getView(), timerController.getView(),
        			consoleController.getView(), healthController.getView()};
        	serverView.initializeView(subViews);
        }
        serverView.addServerWindowListener(new ServerWindowsListener());
        serverOpen = true;
    }
    
    public boolean isServerOpen() {
    	return serverOpen;
    }

    /**
     * Override Method to get Server view
     * @return Server view object
     */
    @Override
    public ViewInterface getView() {
        return serverView;
    }

    /**
     * Override Method to return set of sub controllers in case any
     *
     * @return array containing sub controllers
     */
    @Override
    public ControllerInterface[] getSubControllers() {
        ControllerInterface subControllers[] = {topController, timerController, emotionsController, expressionsController, consoleController};
        return subControllers;
    }

    /**
     * Method to initialize the top server settings panel
     */
    private void initializeTop() {
        TopModel topModel = new TopModel();
        TopView topView = (TopView) viewFactory.getView("TOP", topModel);
        ControllerInterface subControllers[] = {consoleController};
        topController = (TopController) serverControllerFactory.getController("TOP", topModel, topView, subControllers);
        topController.setServerType(selectedServer);
        topController.initializeView();
    }

    /**
     * Method to intialize the Health panel
     */
    private void initializeHealth() {
        HealthModel healthModel = new HealthModel();
        HealthView healthView = (HealthView) viewFactory.getView("HEALTH",healthModel);
        healthController  = (HealthController) serverControllerFactory.getController("HEALTH",healthModel,healthView, null);
        healthController.initializeView();
    }

    /**
     * Method to initialize the server timer panel
     */
    private void initializeTimer() {
        TimerModel timerModel = new TimerModel();
        TimerView timerView = (TimerView) viewFactory.getView("TIMER", timerModel);
        timerController = (TimerController) serverControllerFactory.getController("TIMER", timerModel, timerView, null);
        timerController.initializeView();
    }

    /**
     * Method to initialize the emotions panel
     */
    private void initializeEmotions() {
        EmotionsModel emotionsModel = new EmotionsModel();
        EmotionsView emotionsView = (EmotionsView) viewFactory.getView("EMOTIONS", emotionsModel);
        emotionsController = (EmotionsController) serverControllerFactory.getController("EMOTIONS", emotionsModel,
                emotionsView, null);
        emotionsController.initializeView();
    }

    /**
     * Method to initialize the expressions panel
     */
    private void initializeExpressions() {
        ExpressionsModel expressionsModel = new ExpressionsModel();
        ExpressionsView expressionsView = (ExpressionsView) viewFactory.getView(
                "SERVER_EXPRESSIONS", expressionsModel);
        ControllerInterface subControllers[] = {topController};
        expressionsController = (ExpressionsController) serverControllerFactory.getController(
                "SERVER_EXPRESSIONS", expressionsModel, expressionsView, subControllers);
        expressionsController.initializeView();
    }

    /**
     * Method to initialize the console panel
     */
    private void initializeConsole() {
        ConsoleModel consoleModel = new ConsoleModel();
        ConsoleView consoleView = (ConsoleView) viewFactory.getView("CONSOLE", consoleModel);
        consoleController = (ConsoleController) serverControllerFactory.getController("CONSOLE",
                consoleModel, consoleView, null);
        consoleController.initializeView();
    }

    /**
     * Method to make the server frame visible
     */
    public void showServer() {
        serverView.setVisible(true);
    }

    /**
     * Inner class to add window listener to server window
     */
    class ServerWindowsListener extends WindowAdapter {
        public void windowClosed(WindowEvent e) {
            topController.stopServerConnection();
            serverOpen = false;
            serverConnectionService.stopServerEndpoint();
            logger.info(ServerConstants.SERVER_CLOSE_MESSAGE);
        }
    }

    /**
     * Gets the Top panel controller object
     *
     * @return TopController object
     */
    public TopController getTopController() {
        return topController;
    }

    /**
     * Gets the Timer panel controller object
     *
     * @return TimerController object
     */
    public TimerController getTimerController() {
        return timerController;
    }

    /**
     * Gets the Console panel controller object
     *
     * @return ConsoleController object
     */
    public ConsoleController getConsoleController() {
        return consoleController;
    }
    
    @Override
    public void update(Observable o, Object arg) {
    	// TODO Auto-generated method stub
    	ServiceHelperModel helperModel = (ServiceHelperModel)o;
    	if (helperModel.getLogMessage().length() > 0) {
    		consoleController.getConsoleModel().logMessage(helperModel.getLogMessage());
    		helperModel.setLogMessage("");
    	}
    	timerController.updateTimeStamp(helperModel.getTimeElapsed());
		if (helperModel.isServerError()) {
			consoleController.getConsoleModel().logMessage(ServerConstants.ERROR_SERVER_START);
			helperModel.setServerError(false);
		}
    }
}
