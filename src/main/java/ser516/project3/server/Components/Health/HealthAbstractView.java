package ser516.project3.server.Components.Health;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import java.util.EventListener;

public class HealthAbstractView extends JPanel implements ViewInterface {
    protected HealthModel healthModel;

    public HealthAbstractView(HealthModel healthModel) {
        this.healthModel = healthModel;
    }

    @Override
    public void initializeView(ViewInterface[] subViews) {

    }

    @Override
    public void updateView(ModelInterface model) {

    }

    public void addListener(EventListener eventListener, String componentName) {
    }
}
