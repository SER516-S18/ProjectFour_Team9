package ser516.project3.client.controller;

import ser516.project3.client.Components.BodyVitals.BodyVitalsController;
import ser516.project3.client.Components.BodyVitals.BodyVitalsModel;
import ser516.project3.client.Components.BodyVitals.BodyVitalsView;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpAbstractView;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpController;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpModel;
import ser516.project3.client.Components.ConnectionPopUp.ConnectionPopUpView;
import ser516.project3.client.Components.Expressions.ExpressionsController;
import ser516.project3.client.Components.Expressions.ExpressionsModel;
import ser516.project3.client.Components.Expressions.ExpressionsView;
import ser516.project3.client.Components.Face.FaceController;
import ser516.project3.client.Components.Face.FaceModel;
import ser516.project3.client.Components.Face.FaceView;
import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.client.Components.Graph.GraphModel;
import ser516.project3.client.Components.Graph.GraphView;
import ser516.project3.client.Components.Header.HeaderController;
import ser516.project3.client.Components.Header.HeaderModel;
import ser516.project3.client.Components.Header.HeaderView;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricController;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricModel;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * The ControllerFactory class is a factory class that handles creation of
 * specific controllers throughout the application
 *
 * @author vsriva12
 */
public class ClientControllerFactory {
    private ClientController clientController;
    private HeaderController headerController;
    private FaceController faceController;
    private GraphController performanceMetricGraphController;
    private GraphController bodyVitalsGraphController;
    private GraphController expressionsGraphController;

    private static ClientControllerFactory instance;

    /**
     * Creates a singleton instance of ControllerFactory.
     * If exists, returns it, else creates it.
     *
     * @return instance of the ControllerFactory
     */
    public static ClientControllerFactory getInstance() {
        if (instance == null) {
            instance = new ClientControllerFactory();
        }
        return instance;
    }

    /**
     * The getController method creates Controller classes based on the value of controller type
     *
     * @param controllerType - the Type of the controller
     * @param model          - the controllers model
     * @param view           - the controllers view
     * @param subControllers the sub controller
     * @return the controller object
     */
    public ControllerInterface getController(String controllerType, ModelInterface model, ViewInterface view,
                                             ControllerInterface subControllers[]) {
        if (controllerType == null) {
            return null;
        }
        if (controllerType.equalsIgnoreCase(ClientConstants.CLIENT)) {
            clientController = new ClientController();
            return clientController;
        } else if (controllerType.equalsIgnoreCase(ClientConstants.HEADER)) {
            headerController = new HeaderController((HeaderModel) model, (HeaderView) view,
                    (ConnectionPopUpController) subControllers[0]);
            return headerController;
        } else if (controllerType.equalsIgnoreCase(ClientConstants.PERFORMANCE_METRICS)) {
            performanceMetricGraphController = (GraphController) subControllers[0];
            return new PerformanceMetricController((PerformanceMetricModel) model, (PerformanceMetricView) view,
                    performanceMetricGraphController);
        } else if (controllerType.equalsIgnoreCase(ClientConstants.BODY_VITALS)) {
            bodyVitalsGraphController = (GraphController) subControllers[0];
            return new BodyVitalsController((BodyVitalsModel) model, (BodyVitalsView) view,
                    bodyVitalsGraphController);
        }else if (controllerType.equalsIgnoreCase(ClientConstants.EXPRESSIONS)) {
            expressionsGraphController = (GraphController) subControllers[0];
            return new ExpressionsController((ExpressionsModel) model, (ExpressionsView) view,
                    expressionsGraphController, (FaceController) subControllers[1]);
        } else if (controllerType.equalsIgnoreCase(ClientConstants.GRAPH)) {
            return new GraphController((GraphModel) model, (GraphView) view);
        } else if (controllerType.equalsIgnoreCase(ClientConstants.FACE)) {
            faceController = new FaceController((FaceModel) model, (FaceView) view);
            return faceController;
        } else if (controllerType.equalsIgnoreCase(ClientConstants.CONNECTION_POP_UP)) {
            return new ConnectionPopUpController((ConnectionPopUpModel) model, (ConnectionPopUpAbstractView) view);
        }

        return null;
    }

    /**
     * @return the instance of the Client Controller
     */
    public ClientController getClientController() {
        return clientController;
    }

    /**
     * @return returns the instance of the header controller
     */
    public HeaderController getHeaderController() {
        return headerController;
    }

    /**
     * @return returns the instance of the face controller
     */
    public FaceController getFaceController() {
        return faceController;
    }

    /**
     * @return returns the instance of the Graph performance controller
     */
    public GraphController getPerformanceMetricGraphController() {
        return performanceMetricGraphController;
    }
    
    /**
     * @return returns the instance of the Graph body vitals controller
     */
    public GraphController getBodyVitalsGraphController() {
        return bodyVitalsGraphController;
    }


    /**
     * @return returns the instance of the Graph expressions Controller
     */
    public GraphController getExpressionsGraphController() {
        return expressionsGraphController;
    }
}
