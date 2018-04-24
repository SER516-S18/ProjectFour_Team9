package ser516.project3.client.Components.Expressions;

import ser516.project3.client.Components.Face.FaceView;
import ser516.project3.client.Components.Graph.GraphView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;

/**
 * The ExpressionsView class creates the expressions panel on the client UI.
 *
 * @author vsriva12
 */
@SuppressWarnings("serial")
public class ExpressionsView extends ExpressionsAbstractView {

    /**
     * This constructor initializes the model of Expressions view.
     *
     * @param expressionsModel an object of ExpressionsModel class
     */
    public ExpressionsView(ExpressionsModel expressionsModel) {
        super(expressionsModel);
    }

    /**
     * this method initializes the views and configures the
     * panel.
     *
     * @param subViews an array of objects of ViewInterface class
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        GraphView graphView = (GraphView) subViews[0];
        FaceView faceView = (FaceView) subViews[1];

        setLayout(new GridLayout(1, 2, 8, 8));
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));

        add(faceView, BorderLayout.LINE_START);
        add(graphView, BorderLayout.LINE_END);
        setVisible(true);
    }
}
