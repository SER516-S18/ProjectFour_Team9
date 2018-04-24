package ser516.project3.client.Components.Header;

import ser516.project3.interfaces.CommonDataInterface;
import ser516.project3.interfaces.ControllerInterface;

public abstract class HeaderAbstractController implements ControllerInterface, CommonDataInterface {

	protected HeaderAbstractView headerView;
	protected HeaderModel headerModel;
    
    /**
     * Constructor overloaded to initiate the model and view as well
     *
     * @param headerModel
     * @param headerView
     */
    public HeaderAbstractController(HeaderModel headerModel, HeaderView headerView) {
        this.headerView = headerView;
        this.headerModel = headerModel;
    }

	/**
     * Returns the instance of headerView
     *
     * @return headerView instance which is on top of the screen UI
     */
    @Override
    public HeaderAbstractView getView() {
        return headerView;
    }
}
