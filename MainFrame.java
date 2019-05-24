package robot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.Math.*;

public class MainFrame extends JFrame implements KeyListener {
	private Robot bot;
	private MainDraw draw;
	private Cargo cargo;
	private Hatch hatch;
	private Scanner in = new Scanner(System.in);
	
	public void keyPressed(KeyEvent e) {
		//System.out.println("pressed");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			bot.flyWheels("shoot");
			if (cargo.inBot()) shoot();
			cargo.inBot(false);
			repaint();
		}
		else if(key == KeyEvent.VK_SHIFT) {
			bot.flyWheels("accumulate");
			if(cargoTouchBot() & bot.toString("armValue").equals("Accumulate")) {
				cargo.inBot(true);
			}
			repaint();
		}
		else if(key == KeyEvent.VK_W) {
			draw.moveUp();
		}
		else if(key == KeyEvent.VK_A) {
			draw.moveLeft();
		}
		else if(key == KeyEvent.VK_S) {
			draw.moveDown();
		}
		else if(key == KeyEvent.VK_D) {
			draw.moveRight();
		}
	}
	
	public void keyTyped(KeyEvent e) {
		//System.out.println("typed");
	}
	
	public void keyReleased(KeyEvent e) {
		//System.out.println("released");
		int key = e.getKeyCode();
		//System.out.println(key);
		if(key == KeyEvent.VK_UP) {
			if(!(bot.toString("armEncoder").equals("30"))) {
				bot.armFrontShoot();
				this.waitRepaint();
			}
		}
		else if(key == KeyEvent.VK_DOWN) {
			if (!(bot.toString("armEncoder").equals("-40"))) {
				bot.armBackShoot();
				this.waitRepaint();
			}
		}
		else if(key == KeyEvent.VK_LEFT) {
			if (!(bot.toString("armEncoder").equals("110"))) {
				bot.armAccumulate();
				this.waitRepaint();
			}
		}
		else if(key == KeyEvent.VK_1) {
			bot.frontPistons();
			repaint();
		}
		else if(key == KeyEvent.VK_2) {
			bot.backPistons();
			repaint();
		}
		else if(key == KeyEvent.VK_SPACE | key == KeyEvent.VK_SHIFT) {
			bot.flyWheels("off");
			repaint();
		}
		else if(key == KeyEvent.VK_TAB) {
			if(hatchTouchBot()) {
				bot.flower();
				if (hatch.inBot()) hatch.inBot(false);
				else hatch.inBot(true);
				repaint();
			}
		}
		
	}
	
	public boolean cargoTouchBot() {
		if (Math.abs(cargo.xPosition - bot.xPosition) < 50 & Math.abs(cargo.yPosition - bot.yPosition) < 50) {
			return true;
		}
		return false;
	}
	
	public boolean hatchTouchBot() {
		if (Math.abs(hatch.xPosition - bot.xPosition) < 50 & Math.abs(hatch.yPosition - bot.yPosition) < 50) {
			return true;
		}
		return false;
	}
	
	public void shoot() {
		String arm = this.bot.toString("armValue");
		if (arm.equals("FrontShoot")) {
			System.out.println("Shot: Front");
		}
		else if (arm.equals("BackShoot")) {
			System.out.println("Shot: Back");
		}
	}
	
	public void waitRepaint() {
		//System.out.println(this.bot.toString("armEncoder"));
		draw.repaint();
		// require user input to proceed (user saw the printout)
		//this.in.nextLine();
		/* try {
			Thread.sleep(500);
			}
		catch(InterruptedException ex) {}	*/
	}
	
	public MainFrame() {
		this.bot = new Robot();
		this.draw = new MainDraw();
		this.cargo = new Cargo();
		this.cargo.init(this.bot.xPosition, this.bot.yPosition);
		this.hatch = new Hatch();
		this.hatch.init(1100, 500);
		draw.newBot(bot);
		draw.newCargo(cargo);
		draw.newHatch(hatch);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setTitle("Robot State Thing");
                frame.setResizable(true);
                frame.setSize(1200, 620);
                frame.setMinimumSize(new Dimension(1200, 620));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(frame.draw);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
	
	
	
}
