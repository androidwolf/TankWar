import java.awt.*;
//import java.awt.List;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	Tank myTank = new Tank( 550, 550, true, Tank.Direction.STOP, this);
	//Tank enemyTank = new Tank(100, 100, false, this);
	
	Wall w1 = new Wall(300, 200, 20, 150, this), w2 = new Wall(500, 100, 300, 20, this);
	
	Explode e = new Explode(70, 70, this);
	
	List<Explode> explode = new ArrayList<Explode>();
	List<Missile> missile = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	
	Missile m = null;
	Image offScreenImage = null;
	
	
	public void paint(Graphics g) {
		g.drawString("missiles cout:" + missile.size(), 10, 50);
		g.drawString("explodes cout:" + explode.size(), 10, 70);
		g.drawString("Tanks    cout:" + tanks.size(), 10, 90);
		g.drawString("Tanks    life:" + myTank.getLife(), 10, 110);
		
		for(int i = 0; i < missile.size(); i ++) {
			Missile m = missile.get(i);
			//m.hitTank(enemyTank);
			//if(!m.isLive()) missile.remove(m);
			//else 
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
		}
		
		for(int i = 0; i < explode.size(); i ++) {
			Explode e = explode.get(i);
			e.draw(g);
		}
		
		for(int i = 0; i < tanks.size(); i ++) {
			Tank t = tanks.get(i);
			t.collodesWithWall(w1);
			t.collodesWithWall(w2);
			t.collodesWithTanks(tanks);
			t.draw(g);
		}
		
		myTank.draw(g);
		//enemyTank.draw(g);
		
		w1.draw(g);
		w2.draw(g);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void lauchFrame() {
		
		for( int i = 0; i < 10; i ++) {
			tanks.add(new Tank(50 + 40*(i + 1), 50, false, Tank.Direction.D, this));
		}
		
		//this.setLocation(400, 300);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		
		this.addKeyListener(new KeyMonitor());
		
		setVisible(true);
		
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
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

		public void keyReleased(KeyEvent e ) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		
	}
}
