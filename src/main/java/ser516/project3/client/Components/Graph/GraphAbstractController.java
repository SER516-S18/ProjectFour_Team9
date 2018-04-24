package ser516.project3.client.Components.Graph;

import ser516.project3.interfaces.ControllerInterface;

public abstract class GraphAbstractController implements ControllerInterface {

	protected GraphModel graphModel;
	protected GraphAbstractView graphView;

    /**
     * Initializes an instance the graph controller with <code>GraphModel</code> and
     * <code>GraphView</code> objects.
     *
     * @param graphModel a model object containing required graph data.
     * @param graphView  a view object used to display the graph.
     * @see GraphModel
     * @see GraphView
     */
    public GraphAbstractController(GraphModel graphModel, GraphView graphView) {
        this.graphModel = graphModel;
        this.graphView = graphView;
    }
    
	/**
     * initializes the graph view with respective values
     */
    @Override
    public void initializeView() {
        graphView.initializeView(null);
    }

    /**
     * Method to get the Graph View
     */
    @Override
    public GraphAbstractView getView() {
        return graphView;
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
