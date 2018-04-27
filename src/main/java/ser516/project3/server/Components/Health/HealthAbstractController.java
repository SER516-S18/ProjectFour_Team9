package ser516.project3.server.Components.Health;

import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.server.Components.Expressions.ExpressionsAbstractView;
import ser516.project3.server.Components.Expressions.ExpressionsModel;
import ser516.project3.server.Components.Expressions.ExpressionsView;

public abstract class HealthAbstractController implements ControllerInterface {

    protected HealthModel healthModel;
    protected HealthAbstractView healthView;

    /**
     * Constructor to set the health view and model object
     *
     * @param healthModel HealthModel object
     * @param healthView  HealthView object
     */
    public HealthAbstractController(HealthModel healthModel, HealthAbstractView healthAbstractView) {
        this.healthModel = healthModel;
        this.healthView = healthAbstractView;
    }
    /**
     * Method to get the HealthView object
     *
     * @return HealthView object
     */
    @Override
    public HealthAbstractView getView() {
        return healthView;
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
