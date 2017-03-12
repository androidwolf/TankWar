import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	
	public void print(Graphics g) {
		super.print(g);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(50, 50, 30, 30);
		g.setColor(c);
	}
	public void lauchFrame() {
		this.setLocation(400,300);
		this.setSize(800,600);
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setTitle("TankWar");
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		setVisible(true);
	}
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
	}

}
