package ser516.project3.client.Components.Expressions;

import ser516.project3.client.Components.Face.FaceController;
import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;

/**
 * The Controller class for Expressions related tasks
 * @author Adhiraj Tikku, vsriva12
 *
 */
public class ExpressionsController extends ExpressionsAbstractController {

  private GraphController graphController;
  private FaceController faceController;

  private boolean connectionStatus;

  /**
   * Constructor to initialize the expressions controller values with the passed values
   *
   * @param expressionsModel the model to initialize the graph and view
   * @param expressionsView the view to instantiate the complete view
   * @param graphController the controller to instantiate the graph
   * @param faceController  the controller to initialize the face
   */
  public ExpressionsController(ExpressionsModel expressionsModel, ExpressionsView expressionsView,
                               GraphController graphController, FaceController faceController) {
    super(expressionsModel, expressionsView);
    this.graphController = graphController;
    this.faceController = faceController;
  }

  /**
   * Expression view is initialized where face
   * and eyes expression are added along with channel size and length
   */
  @Override
  public void initializeView() {
    String legendNames[] = {"blink", "rightWink", "leftWink", "lookingRight", "lookingLeft",
        "smile", "clench", "leftSmirk", "rightSmirk", "laugh", "furrowBrow", "raiseBrow"};
    graphController.setLegendNames(legendNames);
    graphController.setNoOfChannels(12);
    graphController.setXLength(100);
    graphController.updateView();
    ViewInterface clientViewInterface[] = {graphController.getView(), faceController.getView()};
    expressionsView.initializeView(clientViewInterface);
  }

  /**
   * Overridden method to return the sub controllers in the class
   *
   * @return subcontrollers in the class
   */
  @Override
  public ControllerInterface[] getSubControllers() {
    ControllerInterface[] subControllers = {graphController, faceController};
    return subControllers;
  }

  /**
   * sets the connection status of the server
   *
   * @param connectionStatus
   */
  @Override
  public void setConnectionStatus(boolean connectionStatus) {
    this.connectionStatus = connectionStatus;
  }

  /**
   * sets the value to true if the expressions tab is selected
   *
   * @param tabSelected Current selected tab.
   */
  @Override
  public void setTabSelected(boolean tabSelected) {
    expressionsModel.setTabSelected(tabSelected);
    faceController.setSelected(tabSelected);
  }
}
