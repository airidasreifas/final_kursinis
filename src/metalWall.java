import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class metalWall {
	public static final int width = 36; 
	public static final int length = 37;
	private int x, y;
	main tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { tk.getImage(commonWall.class
				.getResource("Images/metalWall.gif")), };
	}

	public metalWall(int x, int y, main tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { 
		g.drawImage(wallImags[0], x, y, null);
	}
	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}
}
