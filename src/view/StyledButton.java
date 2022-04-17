package view;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.Colors;

public class StyledButton extends JButton {

	private static final long serialVersionUID = -3050104391262531524L;

	public StyledButton(String text) {
		super();
		setText(text);
		setBackground(Colors.YELLOW.getActualColor());
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
}
