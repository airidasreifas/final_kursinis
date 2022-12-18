import java.awt.*;


public class CommonWall extends Object{
	private static final int width = 22;
	private static final int length = 21;
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { 
		tk.getImage(CommonWall.class.getResource("Images/commonWall.gif")), };
	}

	public CommonWall(int x, int y, Main main) {
		this.x = x;
		this.y = y;
		this.main = main;
	}

	public void draw(Graphics g) {
		g.drawImage(wallImags[0], x, y, null);
	}
	public Rectangle getRect() {  
		return new Rectangle(x, y, width, length);
	}
}
