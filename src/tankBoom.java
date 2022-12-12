import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



public class tankBoom {
	private int x, y;
	private boolean live = true; 
	private main tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs = {
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom1.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom2.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom3.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom4.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom5.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom6.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom7.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom8.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom9.gif")),
			tk.getImage(tankBoom.class.getClassLoader().getResource(
					"images/boom10.gif")), };
	int step = 0;

	public tankBoom(int x, int y, main tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { 

		if (!live) { 
			tc.tankBooms.remove(this);
			return;
		}
		if (step == imgs.length) {
			live = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
	}
}
