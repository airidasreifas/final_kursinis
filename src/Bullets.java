import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bullets extends Object{
	private static  int speedX = 12;
	private static  int speedY = 12;
	public static final int width = 10;
	public static final int length = 10;
	private Direction direction;
	private boolean good;
	private boolean live = true;
	private static Image[] bulletImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); 
	static {
		bulletImages = new Image[] {
				tk.getImage(Bullets.class.getClassLoader().getResource(
						"images/bulletLeft.gif")),

				tk.getImage(Bullets.class.getClassLoader().getResource(
						"images/bulletUp.gif")),

				tk.getImage(Bullets.class.getClassLoader().getResource(
						"images/bulletRight.gif")),

				tk.getImage(Bullets.class.getClassLoader().getResource(
						"images/bulletDown.gif")),

		};

		imgs.put("L", bulletImages[0]); 

		imgs.put("U", bulletImages[1]);

		imgs.put("R", bulletImages[2]);

		imgs.put("D", bulletImages[3]);

	}

	public Bullets(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.direction = dir;
	}

	public Bullets(int x, int y, boolean good, Direction dir, Main main) {
		this(x, y, dir);
		this.good = good;
		this.main = main;
	}

	public void move() {

		switch (direction) {
		case L:
			x -= speedX;
			break;

		case U:
			y -= speedY;
			break;

		case R:
			x += speedX; 
			break;

		case D:
			y += speedY;
			break;

		case STOP:
			break;
		}

		if (x < 0 || y < 0 || x > Main.Fram_width
				|| y > Main.Fram_length) {
			live = false;
		}
	}

	public void draw(Graphics g) {
		if (!live) {
			main.bullets.remove(this);
			return;
		}

		switch (direction) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;

		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;

		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;

		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;

		}

		move(); 
	}

	public boolean isLive() { 
		return live;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean hitTanks(List<Tank> Tanks) {
		for (int i = 0; i < Tanks.size(); i++) {
			if (hitTank(Tanks.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) {

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive()
				&& this.good != t.isGood()) {

			TankBoom e = new TankBoom(t.getX(), t.getY(), main);
			main.tankBooms.add(e);
			if (t.isGood()) {
				t.setLife(t.getLife() - 50); 
				if (t.getLife() <= 0)
					t.setLive(false); 
			} else {
				t.setLive(false); 
			}

			this.live = false;

			return true; 
		}
		return false;
	}

	public boolean hitWall(CommonWall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			this.main.otherWall.remove(w);
			this.main.homeWall.remove(w);
			return true;
		}
		return false;
	}
	public boolean hitBullet(Bullets w){
		if (this.live && this.getRect().intersects(w.getRect())){
			this.live=false;
			this.main.bullets.remove(w);
			return true;
		}
		return false;
	}
	public boolean hitWall(MetalWall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			return true;
		}
		return false;
	}

	public boolean hitHome() { 
		if (this.live && this.getRect().intersects(main.home.getRect())) {
			this.live = false;
			this.main.home.setLive(false);
			return true;
		}
		return false;
	}



}
