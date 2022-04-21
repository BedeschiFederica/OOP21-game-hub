package main.games.floodIt.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import main.games.floodIt.model.Colors;

public class StyledButton extends JButton {

	private static final long serialVersionUID = -3050104391262531524L;

	public StyledButton(String text) {
		super();
		setText(text);
		setBackground(Colors.YELLOW.getActualColor());
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
}
