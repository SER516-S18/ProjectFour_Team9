package ser516.project3.server.Components.Utility;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * The NumberTextField class is an extension of JTextField class which
 * implements input validations on this element
 *
 * @author vsriva12
 */
public class NumberTextField extends JTextField {
    private static final long serialVersionUID = 1L;

	/**
	 * constructor to construct the textfield with input string and if it can be double value
	 *
	 * @param input	The string entered in the textfield
	 * @param isDouble	True if the textfield supports double value
	 */
	public NumberTextField(String input) {
		setText(input);
	}

	/**
	 * prevents the processing of keys except the ones specified
	 *
	 * @param keyEvent intakes the key pressed
	 */
	@Override
	public void processKeyEvent(KeyEvent keyEvent) {
		if ((keyEvent.getKeyChar() == VK_BACK_SPACE || keyEvent.getKeyChar() == VK_DELETE
				|| keyEvent.getKeyChar() == VK_ENTER || keyEvent.getKeyCode() == VK_LEFT
				|| keyEvent.getKeyCode() == VK_RIGHT)
				|| (getText().length() < 4 && (Character.isDigit(keyEvent.getKeyChar())))) {
			super.processKeyEvent(keyEvent);
		} else if(getText().length() < 4 && keyEvent.getKeyChar() == VK_PERIOD) {
			super.processKeyEvent(keyEvent);
		}
		keyEvent.consume();
	}
	
	/**
	 * This method gets the value of text in the text field
	 *
	 * @return returns the number
	 */
	public Long getNumber() {
		Long result = null;
		String text = getText();
		if (text != null && !(text.equals(""))) {
			result = Long.valueOf(text);
		}
		return result;
	}
}
