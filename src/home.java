import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class home {
	private int x, y;
	private main tc;
	public static final int width = 43, length = 43; 
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(commonWall.class
				.getResource("Images/home.jpg")), };
	}

	public home(int x, int y, main tc) {
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.tankBooms.clear();
		tc.bullets.clear();
		tc.myTank.setLive(false);
		Color c = g.getColor(); 
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { 
			g.drawImage(homeImags[0], x, y, null);

			for (int i = 0; i < tc.homeWall.size(); i++) {
				commonWall w = tc.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); 

		}
	}

	public boolean isLive() { 
		return live;
	}
	public void setLive(boolean live) { 
		this.live = live;
	}
	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}

}
