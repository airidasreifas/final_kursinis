import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

public class main extends Frame implements ActionListener {
	public static final int Fram_width = 800; 
	public static final int Fram_length = 600;
	public static boolean printable = true;
	MenuBar cmb = null;
	Menu cm1 = null, cm2 = null;
	MenuItem cmi1 = null, cmi2 = null, cmi3 = null;
	Image screenImage = null;
	tank myTank = new tank(300, 560, true, Direction.STOP, this,1);
	home home = new home(373, 557, this);
	Boolean win=false,lose=false;
	List<tank> tanks = new ArrayList<tank>();
	List<tankBoom> tankBooms = new ArrayList<tankBoom>();
	List<bullets> bullets = new ArrayList<bullets>();
	List<commonWall> homeWall = new ArrayList<commonWall>();
	List<commonWall> otherWall = new ArrayList<commonWall>();
	List<metalWall> metalWall = new ArrayList<metalWall>();

	public void update(Graphics g) {

		screenImage = this.createImage(Fram_width, Fram_length);

		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.BLACK);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);
		framPaint(gps);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void framPaint(Graphics g) {

		Color c = g.getColor();
		g.setColor(Color.blue);

		Font f1 = g.getFont();
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("tanks Remaining: ", 50, 70);
		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.drawString("" + tanks.size(), 210, 70);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Health: ", 580, 70);
		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.drawString("" + myTank.getLife(), 650, 70);
		g.setFont(f1);

			if (tanks.size() == 0 && home.isLive() && myTank.isLive()&&lose==false) {
			Font f = g.getFont();
			g.setFont(new Font("Times New Roman", Font.BOLD, 60)); 
			this.otherWall.clear();
			this.metalWall.clear();
			this.homeWall.clear();
			g.drawString("Hey! You Won.. ", 200, 300);
			g.setFont(f);
			win=true;
		}

		if (myTank.isLive() == false&&win==false) {
			Font f = g.getFont();
			g.setFont(new Font("Times New Roman", Font.BOLD, 40));
			tanks.clear();
			bullets.clear();
			homeWall.clear();
			this.otherWall.clear();
			this.metalWall.clear();
			this.homeWall.clear();
			g.drawString("Sorry. You lose!", 200, 300);
			lose=true;
			g.setFont(f);
		}
		g.setColor(c);

		home.draw(g); 
		myTank.draw(g);
		
		for (int i = 0; i < bullets.size(); i++) { 
			bullets m = bullets.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitHome(); 
			for(int j=0;j<bullets.size();j++){
				if (i==j) continue;
				bullets bts=bullets.get(j);
				m.hitBullet(bts);
			}
			for (int j = 0; j < metalWall.size(); j++) { 
				metalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}
			for (int j = 0; j < otherWall.size(); j++) {
				commonWall w = otherWall.get(j);
				m.hitWall(w);
			}
			for (int j = 0; j < homeWall.size(); j++) {
				commonWall cw = homeWall.get(j);
				m.hitWall(cw);
			}
			m.draw(g); 
		}

		for (int i = 0; i < tanks.size(); i++) {
			tank t = tanks.get(i);

			for (int j = 0; j < homeWall.size(); j++) {
				commonWall cw = homeWall.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) {
				commonWall cw = otherWall.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) {
				metalWall mw = metalWall.get(j);
				t.collideWithWall(mw);
				mw.draw(g);
			}
			t.collideWithTanks(tanks);
			t.collideHome(home);

			t.draw(g);
		}


		for (int i = 0; i < tankBooms.size(); i++) {
			tankBoom bt = tankBooms.get(i);
			bt.draw(g);
		}
		for (int i = 0; i < otherWall.size(); i++) {
			commonWall cw = otherWall.get(i);
			cw.draw(g);
		}
		for (int i = 0; i < metalWall.size(); i++) {
			metalWall mw = metalWall.get(i);
			mw.draw(g);
		}

		myTank.collideWithTanks(tanks);
		myTank.collideHome(home);

		for (int i = 0; i < metalWall.size(); i++) {
			metalWall w = metalWall.get(i);
			myTank.collideWithWall(w);
		}
		for (int i = 0; i < otherWall.size(); i++) {
			commonWall cw = otherWall.get(i);
			myTank.collideWithWall(cw);
		}
		for (int i = 0; i < homeWall.size(); i++) {
			commonWall w = homeWall.get(i);
			myTank.collideWithWall(w);
		}
	}

	public main() {
		cmb = new MenuBar();

		cm1 = new Menu("Game");
		cm2 = new Menu("Help");
		cm1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cm2.setFont(new Font("Times New Roman", Font.BOLD, 15));

		cmi1 = new MenuItem("New Game");
		cmi2 = new MenuItem("Exit");
		cmi3 = new MenuItem("Controls");
		cmi1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cmi2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cmi3.setFont(new Font("Times New Roman", Font.BOLD, 15));

		//Game
		cmb.add(cm1);
		cm1.add(cmi1);
		cm1.add(cmi2);

		//Help
		cmb.add(cm2);
		cm2.add(cmi3);

		cmi1.addActionListener(this);
		cmi1.setActionCommand("NewGame");

		cmi2.addActionListener(this);
		cmi2.setActionCommand("Exit");
                

		cmi3.addActionListener(this);
		cmi3.setActionCommand("Controls");

		this.setMenuBar(cmb);
		this.setVisible(true);

		for (int i = 0; i < 10; i++) {
			if (i < 4)
				homeWall.add(new commonWall(350, 580 - 21 * i, this));
			else if (i < 7)
				homeWall.add(new commonWall(372 + 22 * (i - 4), 517, this));
			else
				homeWall.add(new commonWall(416, 538 + (i - 7) * 21, this));
		}

		for (int i = 0; i < 32; i++) {
			if (i < 16) {
				otherWall.add(new commonWall(75 + 21 * i, 300, this));
				otherWall.add(new commonWall(350+ 21 * i, 300, this));
				otherWall.add(new commonWall(500 + 21 * i, 180, this));
				otherWall.add(new commonWall(200, 400 + 21 * i, this));
				otherWall.add(new commonWall(500, 400 + 21 * i, this));
			} else if (i < 32) {
				otherWall.add(new commonWall(75 + 21 * (i - 16), 320, this));
				otherWall.add(new commonWall(350 + 21 * (i - 16), 320, this));
				otherWall.add(new commonWall(500 + 21 * (i - 16), 200, this));
				otherWall.add(new commonWall(222, 400 + 21 * (i - 16), this));
				otherWall.add(new commonWall(522, 400 + 21 * (i - 16), this));
			}
		}

		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				metalWall.add(new metalWall(295 + 30 * i, 400, this));
			} else if (i < 10)
				metalWall.add(new metalWall(600, 300 + 20 * (i), this));

				metalWall.add(new metalWall(100, 460 + 20 * (i), this));
				metalWall.add(new metalWall(380 + 30 * (i - 10), 180, this));

			
		}


		for (int i = 0; i < 20; i++) {
			if (i < 9) 
				tanks.add(new tank(150 + 70 * i, 40, false, Direction.D, this,0));
			else if (i < 15)
				tanks.add(new tank(700, 140 + 50 * (i - 6), false, Direction.D, this,0));
			else
				tanks.add(new tank(10, 50 * (i - 12), false, Direction.D, this,0));
		}

		this.setSize(Fram_width, Fram_length);
		this.setLocation(280, 50); 
		this.setTitle("Battle city");

		this.addWindowListener(new WindowAdapter() { 
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);

		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start(); 
	}

	public static void main(String[] args) {
		new main();
	}

	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {
		public void keyReleased(KeyEvent e) { 
			myTank.keyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewGame")) {
				printable = true;
				this.dispose();
				new main();

		} else if (e.getActionCommand().equals("Exit")) {
			printable = false;
			System.out.println("break down");
			System.exit(0);

		} else if (e.getActionCommand().equals("Controls")) {
			printable = false;
			JOptionPane.showMessageDialog(null, " Controls: \n [ W : Up ]  [ S : Down ] \n [ A : Left ]  [ D : Right ]  [ F : fire ] \n [ R : Restart]",
					"Controls", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); 
		}
	}
}
