package ser516.project3.server.controller;

import ser516.project3.client.Components.Header.HeaderController;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.server.Components.Console.ConsoleModel;
import ser516.project3.server.Components.Emotions.EmotionsModel;
import ser516.project3.server.Components.Expressions.ExpressionsModel;
import ser516.project3.server.Components.Expressions.ExpressionsView;
import ser516.project3.server.Components.Health.HealthModel;
import ser516.project3.server.Components.Health.HealthView;
import ser516.project3.server.Components.Timer.TimerModel;
import ser516.project3.server.Components.Console.ConsoleView;
import ser516.project3.server.Components.Emotions.EmotionsView;
import ser516.project3.server.Components.Timer.TimerView;
import ser516.project3.server.Components.Top.TopModel;
import ser516.project3.server.Components.Top.TopView;

/**
 * The ControllerFactory class is a factory class that handles creation of
 * specific controllers throughout the application
 *
 * @author vsriva12
 */
public class ServerControllerFactory {
    private static ServerControllerFactory instance;

    /**
     * Creates a singleton instance of ControllerFactory. If exists, returns it,
     * else creates it.
     *
     * @return instance of the ControllerFactory
     */
    public static ServerControllerFactory getInstance() {
        if (instance == null) {
            instance = new ServerControllerFactory();
        }
        return instance;
    }

    /**
     * The getController method creates Controller classes based on the value of
     * controller type
     *
     * @param controllerType - the Type of the controller
     * @param model          - the controllers model
     * @param view           - the controllers view
     * @return  			 - the controller object
     */
    public ControllerInterface getController(String controllerType, ModelInterface model, ViewInterface view, ControllerInterface subControllers[]) {
        if (controllerType == null) {
            return null;
        }
        if (controllerType.equalsIgnoreCase("SERVER")) {
            return new ServerController();
        } else if (controllerType.equalsIgnoreCase("TOP")) {
            return new TopController((TopModel) model, (TopView) view, (ConsoleController)subControllers[0]);
        } else if (controllerType.equalsIgnoreCase("TIMER")) {
            return new TimerController((TimerModel) model, (TimerView) view);
        } else if (controllerType.equalsIgnoreCase("EMOTIONS")) {
            return new EmotionsController((EmotionsModel) model, (EmotionsView) view);
        } else if (controllerType.equalsIgnoreCase("SERVER_EXPRESSIONS")) {
            return new ExpressionsController((ExpressionsModel) model,
                    (ExpressionsView) view, (TopController)subControllers[0]);
        } else if (controllerType.equalsIgnoreCase("CONSOLE")) {
            return new ConsoleController((ConsoleModel) model, (ConsoleView) view);
        } else if(controllerType.equalsIgnoreCase("HEALTH")){
            return new HealthController((HealthModel) model,(HealthView) view);
        }

        return null;
    }
}
