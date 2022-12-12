import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class tank {
	public static  int speedX = 6, speedY =6; 
	public static int count = 0;
	public static final int width = 35, length = 35;
	private Direction direction = Direction.STOP;
	private Direction kdirection = Direction.U;
	main tc;
	private int player=0;
	private boolean good;
	private int x, y;
	private int oldX, oldY;
	private boolean live = true;
	private int life = 200;
	private int rate=1;
	private static Random r = new Random();
	private int step = r.nextInt(10)+5 ; 

	private boolean bL = false, bU = false, bR = false, bD = false;
	

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImags = null; 
	static {
		tankImags = new Image[] {
				tk.getImage(tankBoom.class.getResource("Images/enemyTankDown.gif")),
				tk.getImage(tankBoom.class.getResource("Images/enemyTankUp.gif")),
				tk.getImage(tankBoom.class.getResource("Images/enemyTankLeft.gif")),
				tk.getImage(tankBoom.class.getResource("Images/enemyTankRight.gif")),
				tk.getImage(tankBoom.class.getResource("Images/myTankDown.gif")),
				tk.getImage(tankBoom.class.getResource("Images/myTankUp.gif")),
				tk.getImage(tankBoom.class.getResource("Images/myTankLeft.gif")),
				tk.getImage(tankBoom.class.getResource("Images/myTankRight.gif")),
				};

	}

	public tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}

	public tank(int x, int y, boolean good, Direction dir, main tc, int player) {
		this(x, y, good);
		this.direction = dir;
		this.tc = tc;
		this.player=player;
	}

	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.tanks.remove(this);
			}
			return;
		}

		switch (kdirection) {
							
		case D:
			if(player==1){	g.drawImage(tankImags[4], x, y, null);
			} else{
			g.drawImage(tankImags[0], x, y, null);}
			break;

		case U:
			if(player==1){	g.drawImage(tankImags[5], x, y, null);
			}else{
			g.drawImage(tankImags[1], x, y, null);}
			break;

		case L:
			if(player==1){	g.drawImage(tankImags[6], x, y, null);
		}else{
			g.drawImage(tankImags[2], x, y, null);}
			break;

		case R:
			if(player==1){	g.drawImage(tankImags[7], x, y, null);
		}else{
			g.drawImage(tankImags[3], x, y, null);}
			break;

		}

		move();   
	}

	void move() {

		this.oldX = x;
		this.oldY = y;

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

		if (this.direction != direction.STOP) {
			this.kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 40)     
			y = 40;
		if (x + tank.width > main.Fram_width)
			x = main.Fram_width - tank.width;
		if (y + tank.length > main.Fram_length)
			y = main.Fram_length - tank.length;

		if (!good) {
			Direction[] directons = direction.values();
			if (step == 0) {                  
				step = r.nextInt(12) + 3;  
				int mod=r.nextInt(9);
				if (playertankaround()){
					if(x==tc.myTank.x){ if(y>tc.myTank.y) direction=directons[1];
					else if (y<tc.myTank.y) direction=directons[3];
					}else if(y==tc.myTank.y){ if(x>tc.myTank.x) direction=directons[0];
					else if (x<tc.myTank.x) direction=directons[2];
					}
					else{
						int rn = r.nextInt(directons.length);
						direction = directons[rn];
					}
					rate=2;
				}else if (mod==1){
					rate=1;
				}else if(1<mod&&mod<=3){
					rate=1;
				}else{
				int rn = r.nextInt(directons.length);
				direction = directons[rn];
				rate=1;}    
			}
			step--;
			if(rate==2){
				if (r.nextInt(40) > 35)
					this.fire();
			}else if (r.nextInt(40) > 38)
				this.fire();
		}
	}
	public boolean playertankaround(){
		int rx=x-15,ry=y-15;
		if((x-15)<0) rx=0;
		if((y-15)<0)ry=0;
		Rectangle a=new Rectangle(rx, ry,60,60);
		if (this.live && a.intersects(tc.myTank.getRect())) {
		return true;	
		}
		return false;	
	}

	private void changToOldDir() {  
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) {  
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_R:  
			tc.tanks.clear();
			tc.bullets.clear();
			tc.otherWall.clear();
			tc.homeWall.clear();
			tc.metalWall.clear();
			tc.myTank.setLive(false);
			if (tc.tanks.size() == 0) {
				for (int i = 0; i < 20; i++) {
					if (i < 9)                             
						tc.tanks.add(new tank(150 + 70 * i, 40, false,
								direction.R, tc,0));
					else if (i < 15)
						tc.tanks.add(new tank(700, 140 + 50 * (i -6), false,
								direction.D, tc,0));
					else
						tc.tanks.add(new tank(10,  50 * (i - 12), false,
								direction.L, tc,0));
				}
			}
			
			tc.myTank = new tank(300, 560, true, direction.STOP, tc,0);
			if (!tc.home.isLive()) 
				tc.home.setLive(true);
			main abc=new main();
		case KeyEvent.VK_D:
			bR = true;
			break;
			
		case KeyEvent.VK_A:
			bL = true;
			break;
		
		case KeyEvent.VK_W:  
			bU = true;
			break;
		
		case KeyEvent.VK_S:
			bD = true;
			break;
		}
		decideDirection();
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD) 
			direction = direction.R;

		else if (bL && !bU && !bR && !bD) 
			direction = direction.L;

		else if (!bL && bU && !bR && !bD) 
			direction = direction.U;

		else if (!bL && !bU && !bR && bD) 
			direction = direction.D;

		else if (!bL && !bU && !bR && !bD)
			direction = direction.STOP;
	}
	public void keyReleased(KeyEvent e) {  
		int key = e.getKeyCode();
		switch (key) {
		
		case KeyEvent.VK_F:
			fire();
			break;
			
		case KeyEvent.VK_D:
			bR = false;
			break;
		
		case KeyEvent.VK_A:
			bL = false;
			break;
		
		case KeyEvent.VK_W:
			bU = false;
			break;

		case KeyEvent.VK_S:
			bD = false;
			break;
		}
		decideDirection(); 
	}

	public bullets fire() {
		if (!live)
			return null;
		int x = this.x + tank.width / 2 - bullets.width / 2;
		int y = this.y + tank.length / 2 - bullets.length / 2;
		bullets m = new bullets(x, y + 2, good, kdirection, this.tc);
		tc.bullets.add(m);                                                
		return m;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isGood() {
		return good;
	}

	public boolean collideWithWall(commonWall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			 this.changToOldDir();    
			return true;
		}
		return false;
	}

	public boolean collideWithWall(metalWall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();     
			return true;
		}
		return false;
	}

	public boolean collideHome(home h) {
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive()
						&& this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}