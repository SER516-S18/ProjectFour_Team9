package ser516.project3.client.Components.Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

public abstract class GraphAbstractView extends JPanel implements ViewInterface {

	protected GraphModel graphModel;
	
	protected static final int TITLE_FONT_SIZE = 17;
    protected static final int GRAPH_AXIS_FONT_SIZE = 14;
	
	/**
     * Initializes a graph instance and creates a default empty
     * graph.
     */
    public GraphAbstractView(GraphModel graphModel) {
        this.graphModel = graphModel;
    }
	
	/**
     * this method initializes the view and configures the positions of
     * components.
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        setLayout(new GridLayout(1, 1, 8, 8));
        setBorder(new TitledBorder(null, ClientConstants.GRAPH,
                TitledBorder.CENTER, TitledBorder.TOP, new Font(ClientConstants.FONT_NAME, Font.BOLD, TITLE_FONT_SIZE), null));
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
    }

}
