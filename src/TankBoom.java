import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



public class TankBoom extends Object{
	private boolean live = true;
	private static Image[] imgs = {
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom1.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom2.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom3.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom4.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom5.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom6.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom7.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom8.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom9.gif")),
			tk.getImage(TankBoom.class.getClassLoader().getResource(
					"images/boom10.gif")), };
	private int step = 0;

	public TankBoom(int x, int y, Main main) {
		this.x = x;
		this.y = y;
		this.main = main;
	}

	public void draw(Graphics g) { 

		if (!live) { 
			main.tankBooms.remove(this);
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
