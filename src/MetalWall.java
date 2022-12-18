import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MetalWall extends Object{
	private static final int width = 36;
	private static final int length = 37;
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { tk.getImage(CommonWall.class
				.getResource("Images/metalWall.gif")), };
	}

	public MetalWall(int x, int y, Main main) {
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
