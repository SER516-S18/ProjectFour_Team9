package ser516.project3.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ser516.project3.client.Components.BodyVitals.BodyVitalsModel;
import ser516.project3.client.Components.BodyVitals.BodyVitalsView;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpAbstractView;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpModel;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpView;
import ser516.project3.client.Components.Expressions.ExpressionsController;
import ser516.project3.client.Components.Expressions.ExpressionsModel;
import ser516.project3.client.Components.Expressions.ExpressionsView;
import ser516.project3.client.Components.Face.FaceModel;
import ser516.project3.client.Components.Face.FaceView;
import ser516.project3.client.Components.Graph.GraphModel;
import ser516.project3.client.Components.Graph.GraphView;
import ser516.project3.client.Components.Header.HeaderController;
import ser516.project3.client.Components.Header.HeaderModel;
import ser516.project3.client.Components.Header.HeaderView;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricModel;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricView;
import ser516.project3.client.service.ClientConnectionServiceImpl;
import ser516.project3.client.service.ClientConnectionServiceInterface;
import ser516.project3.client.view.ClientView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.CommonDataInterface;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.server.controller.ServerController;
import ser516.project3.server.helper.ServiceHelperModel;

/**
 * The Controller class to handle requests from the Client UI
 * @author vsriva12, Adhiraj Tikku
 *
 */
public class ClientController implements ControllerInterface, CommonDataInterface {
	private ClientConnectionServiceInterface clientConnectionService;
	private ClientViewFactory viewFactory;
	private ClientView clientView;
	private ServerController serverController;
	private ServerController healthServerController;
	private HeaderController headerController;
	private ControllerInterface performanceMetricController;
	private ControllerInterface bodyVitalsController;
	private ExpressionsController expressionsController;
	private ControllerInterface performanceMetricsGraphController;
	private ControllerInterface bodyVitalsGraphController;
	private ControllerInterface expressionGraphController;
	private ControllerInterface faceController;
	private ControllerInterface connectionPopUpController;

	/**
	 * Constructor created to intialize
	 * Header, PerformanceMetrics and Face Expressions
	 *
	 */
	public ClientController() {
		viewFactory = new ClientViewFactory();
		ClientControllerFactory controllerFactory = ClientControllerFactory.getInstance();
		initializeHeader(viewFactory, controllerFactory);
		initializePerformanceMetrics(viewFactory, controllerFactory);
		initializeBodyVitals(viewFactory, controllerFactory);
		initializeExpressions(viewFactory, controllerFactory);
	}

	/**
	 * Initializes Client view
	 */
	@Override
	public void initializeView() {
		clientView = (ClientView) viewFactory.getView(ClientConstants.CLIENT, null);
		ViewInterface subViews[] = {
				headerController.getView(),
				performanceMetricController.getView(),
				bodyVitalsController.getView(),
				expressionsController.getView()};
		clientView.initializeView(subViews);
		clientView.addServerMenuItemListener(new ServerMenuItemListener());
		clientView.addClientWindowListener(new WindowClosingEventListener());
        clientView.addTabbedPaneSelectionListener(new TabSelectionListener());
        setTabSelected(false);
	}

	/**
	 * Method to get Client view
	 * and @return Client view object
	 */
	@Override
	public ViewInterface getView() {
		return null;
	}

	/**
	 * Returns the set of sub controllers in case any
	 *
	 * @return array containing sub controllers
	 */
	@Override
	public ControllerInterface[] getSubControllers() {
		ControllerInterface[] subControllers = {headerController, performanceMetricController, bodyVitalsController, expressionsController,
				performanceMetricsGraphController, bodyVitalsGraphController, expressionGraphController, faceController, connectionPopUpController, serverController};
		return subControllers;
	}

    /**
     * Overridden method to set the connection status of the client to the server
     *
     * @param connectionStatus the status of the connection to server
     */
	@Override
	public void setConnectionStatus(boolean connectionStatus) {
		headerController.setConnectionStatus(connectionStatus);
	}

    /**
     *
     * @param tabSelected Current selected tab.
     */
	public void setTabSelected(boolean tabSelected) {
		expressionsController.setTabSelected(tabSelected);
	}

	/**
	 *  Header panel is initalized  where connection to server
	 *  dialog box is created.
     *
     * @param controllerFactory the factory object to create the instances of the controller classes
     * @param viewFactory the object to create the instances of the views
	 */
	private void initializeHeader(ClientViewFactory viewFactory, ClientControllerFactory controllerFactory) {
		serverController = new ServerController(ServiceHelperModel.getInstance(), "EMOTIONS_SERVER");
		healthServerController = new ServerController(ServiceHelperModel.getInstance(), "HEALTH_SERVER");
		clientConnectionService = new ClientConnectionServiceImpl();
		
		ConnectionPopUpModel connectionPopUpModel = new ConnectionPopUpModel();
		ConnectionPopUpAbstractView connectionPopUpView = (ConnectionPopUpView) viewFactory.getView(ClientConstants.CONNECTION_POP_UP, connectionPopUpModel);
		connectionPopUpController = controllerFactory.getController(ClientConstants.CONNECTION_POP_UP, connectionPopUpModel, connectionPopUpView, null, null);

		ControllerInterface subControllers[] = {connectionPopUpController, serverController, healthServerController};

		HeaderModel headerModel = new HeaderModel();
		HeaderView headerView = (HeaderView) viewFactory.getView(ClientConstants.HEADER, headerModel);
		headerController = (HeaderController) controllerFactory.getController(ClientConstants.HEADER, headerModel, headerView, subControllers, clientConnectionService);
		headerController.initializeView();
	}
	/**
	 * Performance Metrics panel is created where graph controller and performance metric
	 * views are initialized.
     *
     * @param controllerFactory the factory object to create the instances of the controller classes
     * @param viewFactory the object to create the instances of the views
	 */
	private void initializePerformanceMetrics(ClientViewFactory viewFactory, ClientControllerFactory controllerFactory) {
		GraphModel performanceMetricGraphModel = new GraphModel();
		GraphView performanceMetricGraphView = (GraphView) viewFactory.getView(ClientConstants.GRAPH, performanceMetricGraphModel);
		performanceMetricsGraphController = controllerFactory.getController(ClientConstants.GRAPH, performanceMetricGraphModel, performanceMetricGraphView, null, null);
		performanceMetricsGraphController.initializeView();

		ControllerInterface subControllers[] = {performanceMetricsGraphController};

		PerformanceMetricModel performanceMetricModel = new PerformanceMetricModel();
		PerformanceMetricView performanceMetricView = (PerformanceMetricView) viewFactory.getView(ClientConstants.PERFORMANCE_METRICS, performanceMetricModel);
		performanceMetricController = controllerFactory.getController(ClientConstants.PERFORMANCE_METRICS, performanceMetricModel, performanceMetricView, subControllers, null);
		performanceMetricController.initializeView();
	}
	
	/**
	 * Body Vitals panel is created where graph controller and body vitals
	 * views are initialized.
     *
     * @param controllerFactory the factory object to create the instances of the controller classes
     * @param viewFactory the object to create the instances of the views
	 */
	private void initializeBodyVitals(ClientViewFactory viewFactory, ClientControllerFactory controllerFactory) {
		GraphModel bodyVitalsGraphModel = new GraphModel();
		GraphView bodyVitalsGraphView = (GraphView) viewFactory.getView(ClientConstants.GRAPH, bodyVitalsGraphModel);
		bodyVitalsGraphController = controllerFactory.getController(ClientConstants.GRAPH, bodyVitalsGraphModel, bodyVitalsGraphView, null, null);
		bodyVitalsGraphController.initializeView();

		ControllerInterface subControllers[] = {bodyVitalsGraphController};

		BodyVitalsModel bodyVitalsModel = new BodyVitalsModel();
		BodyVitalsView bodyVitalsView = (BodyVitalsView) viewFactory.getView(ClientConstants.BODY_VITALS, bodyVitalsModel);
		bodyVitalsController = controllerFactory.getController(ClientConstants.BODY_VITALS, bodyVitalsModel, bodyVitalsView, subControllers, null);
		bodyVitalsController.initializeView();
	}
	/**
	 * Expression panel is created where expression controller graph
	 * and expression controller views are created
     *
     * @param controllerFactory the factory object to create the instances of the controller classes
     * @param viewFactory the object to create the instances of the views
	 */
	private void initializeExpressions(ClientViewFactory viewFactory, ClientControllerFactory controllerFactory) {
		GraphModel expressionsGraphModel = new GraphModel();
		GraphView expressionsGraphView = (GraphView) viewFactory.getView(ClientConstants.GRAPH, expressionsGraphModel);
		expressionGraphController = controllerFactory.getController(ClientConstants.GRAPH, expressionsGraphModel, expressionsGraphView, null, null);
		expressionGraphController.initializeView();

		FaceModel faceModel = new FaceModel();
		FaceView faceView = (FaceView) viewFactory.getView(ClientConstants.FACE, faceModel);
		faceController = controllerFactory.getController(ClientConstants.FACE, faceModel, faceView, null, null);
		faceController.initializeView();

		ControllerInterface subControllers[] = {expressionGraphController, faceController};
		ExpressionsModel expressionsModel = new ExpressionsModel();
		ExpressionsView expressionsView = (ExpressionsView) viewFactory.getView(ClientConstants.EXPRESSIONS, expressionsModel);
		expressionsController = (ExpressionsController) controllerFactory.getController(ClientConstants.EXPRESSIONS, expressionsModel, expressionsView, subControllers, null);
		expressionsController.initializeView();
	}

	/**
	 * Forces the client to stop
	 */
	public void stopClientConnector() {
		if(clientConnectionService != null)
			clientConnectionService.stopClientConnection();
		headerController.setConnectionStatus(false);
	}
	
	/**
	 *
	 * Class implemented to handle action listener of all server menu item components
	 * like lower face, upper face.
	 */
	class ServerMenuItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			headerController.connectToHealthServer();
		}
	}
	/**
	 *
	 *Class to deal with windows status like
	 *opened, closed, activated or deactivated
	 */
	class WindowClosingEventListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent windowEvent) {
			stopClientConnector();
		}
		@Override
		public void windowOpened(WindowEvent arg0) {}
		@Override
		public void windowClosed(WindowEvent arg0) {}
		@Override
		public void windowIconified(WindowEvent arg0) {}
		@Override
		public void windowDeiconified(WindowEvent arg0) {}
		@Override
		public void windowActivated(WindowEvent arg0) {}
		@Override
		public void windowDeactivated(WindowEvent arg0) {}
	}

	/**
	 * Class implemented to handle changes in tab selection.
	 *
	 */
	class TabSelectionListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JTabbedPane) {
				JTabbedPane pane = (JTabbedPane) e.getSource();
				int selectedIndex = pane.getSelectedIndex();
				expressionsController.setTabSelected((selectedIndex == 2) ? true : false);
			}
		}
	}
}
