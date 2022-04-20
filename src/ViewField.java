import javax.swing.*;

public class ViewField {

	 private static JFrame frame;
	    private static String title;

	    public ViewField(int width, int height, int gridSize, String title, Game game, Handler handler) {
	        ViewField.title = title;
	        frame = new JFrame(title);

	        //frame.setPreferredSize(new Dimension(width, height));
	        //frame.setMinimumSize(new Dimension(width, height));
	        //frame.setMaximumSize(new Dimension(width, height));
	        //frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);

	        //JPanel panel = new Grid(new GridLayout(gridSize, gridSize), handler);

	        //frame.setContentPane(panel);
	        frame.pack();

	        frame.setVisible(true);
	    }
	
}
