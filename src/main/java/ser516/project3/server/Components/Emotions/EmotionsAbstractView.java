package ser516.project3.server.Components.Emotions;

import java.util.EventListener;

import javax.swing.JPanel;

import ser516.project3.interfaces.ModelInterface;
import ser516.project3.interfaces.ViewInterface;

public abstract class EmotionsAbstractView extends JPanel implements ViewInterface {

	protected EmotionsModel emotionsModel;
	
	/**
     * Method to set emotion model
     *
     * @param emotionsModel model object containing required emotions data.
     */
    public EmotionsAbstractView(EmotionsModel emotionsModel) {
        this.emotionsModel = emotionsModel;
    }

	@Override
	public void updateView(ModelInterface model) {
		// TODO Auto-generated method stub

	}
	
	public abstract void addListener(EventListener eventListener, String componentName);
}
