package ser516.project3.client.Components.BodyVitals;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import ser516.project3.client.Components.Graph.GraphController;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;

public class BodyVitalsController extends BodyVitalsAbstractController{

	public BodyVitalsController(BodyVitalsModel bodyVitalsModel, BodyVitalsView bodyVitalsView) {
		super(bodyVitalsModel, bodyVitalsView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ControllerInterface[] getSubControllers() {
		// TODO Auto-generated method stub
		return null;
	}

}
