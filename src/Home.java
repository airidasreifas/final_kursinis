import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Home extends Object{
	private static final int width = 43, length = 43;
	private boolean live = true;
	private static Home homeObject;
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(CommonWall.class
				.getResource("Images/home.jpg")), };
	}



	public void gameOver(Graphics g) {

		main.Tanks.clear();
		main.metalWall.clear();
		main.otherWall.clear();
		main.tankBooms.clear();
		main.bullets.clear();
		main.myTank.setLive(false);
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

			for (int i = 0; i < main.homeWall.size(); i++) {
				CommonWall w = main.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); 

		}
	}

	private Home(Main main) {
	this.main = main;
	x=373;
	y=557;
	}
	public static Home getInstance(Main tc) {

		if (homeObject == null) {
			homeObject = new Home(tc);
		}
		return homeObject;
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
