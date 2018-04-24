package ser516.project3.client.Components.ConnectionPopUp;

import ser516.project3.interfaces.ControllerInterface;

public abstract class ConnectionPopUpAbstractController implements ControllerInterface {
	
	protected ConnectionPopUpAbstractView connectionPopUpView;
	protected ConnectionPopUpModel connectionPopUpModel;
	
	/**
     * Constructor to add popup model and view in
     * Connection popup controller
     */
    public ConnectionPopUpAbstractController(ConnectionPopUpModel connectionPopUpModel, ConnectionPopUpAbstractView connectionPopUpView) {
        this.connectionPopUpModel = connectionPopUpModel;
        this.connectionPopUpView = connectionPopUpView;
    }

	/**
     * Method to get ConnectionPopUp view
     * @return ConnectionPopUp view object
     */
    @Override
    public ConnectionPopUpAbstractView getView() {
        return connectionPopUpView;
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
