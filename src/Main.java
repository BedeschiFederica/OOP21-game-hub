
public class Main implements Runnable {
	
	ViewField game = new ViewField();
	
	public static void main(String[] args) {
		(new Thread(new Main())).start();
	}

	@Override
	public void run() {
		while(true) {
			//game.doIt();
			//game.winCheck();
		}
	}

}