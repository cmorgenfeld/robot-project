package robot;

public class Hatch {
	public int xPosition;
	public int yPosition;
	
	private boolean inBot = false;
	
	public void init(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public boolean inBot() {
		return this.inBot;
	}
	
	public void inBot(boolean b) {
		this.inBot = b;
	}
}
