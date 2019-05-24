package robot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.UIManager;

public class MainDraw extends JComponent {
	private Robot robot;
	private Cargo cargo;
	private Hatch hatch;
	private final Color Orange = new Color(0xff8726);
	private final Color Yellow = new Color(0xffd335);
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		//Robot States
		g.drawString("Seconds left in auto: " + this.robot.toString("autoTime"), 50, 50);
		g.drawString("Seconds left in match: " + this.robot.toString("matchTime"), 50, 100);g.drawString("The flywheels are: " + this.robot.toString("flyWheel"), 50, 150);
		g.drawString("The arm is: " + this.robot.toString("armValue"), 50, 200);
		g.drawString("The arm motor is: " + this.robot.toString("armMotorOn"), 50, 250);
		g.drawString("The arm encoder value is: " + this.robot.toString("armEncoder"), 50, 300);
		g.drawString("The OTB is: " + this.robot.toString("OTBDown"), 50, 350);
		g.drawString("The flower is: " + this.robot.toString("hatchOpen"), 50, 400);
		g.drawString("The front pistons are: "  + this.robot.toString("frontPistonsIn"), 50, 450);
		g.drawString("The back pistons are: "  + this.robot.toString("backPistonsIn"), 50, 500);
		g.drawString("Has cargo", 350, 50);
		g.drawString("Has hatch", 350, 100);
		
		//Robot
		g.drawLine(600, 0, 600, 600);
		g.drawLine(this.robot.xPosition, 0, this.robot.xPosition, 600);
		g.drawLine(600, this.robot.yPosition, 1200, this.robot.yPosition);
		
		//g2d.rotate(Math.toRadians(10));
	    g.fillRect(this.robot.xPosition - 25, this.robot.yPosition - 25, 50, 50);
	    //g2d.rotate(Math.toRadians(-10));
	    
	    g.setColor(Color.GREEN);
	    g.fillRect(this.robot.xPosition - 20, this.robot.yPosition - 35, 40, 10);
	    
	    //Cargo
	    updateCargo(g);
	    
	    //Hatch
	    updateHatch(g, g2d);
	    
	    //Game Pieces
		if (this.cargo.inBot()) g.setColor(Color.GREEN);
		else g.setColor(Color.RED);
		g.fillRect(350, 30, 150, 75);
		if (this.robot.toString("hasHatch").equals("true")) g.setColor(Color.GREEN);
		else g.setColor(Color.RED);
		g.fillRect(350, 135, 150, 75);
		g.setColor(Color.BLACK);
		g.drawString("Has cargo", 350, 100);
		g.drawString("Has hatch", 350, 205);
		
		System.out.println("Updated");
	}
	
	public void newBot(Robot robot) {
		this.robot = robot;
	}
	
	public void newCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void newHatch(Hatch hatch) {
		this.hatch = hatch;
	}
	
	public void moveUp() {
		if(this.robot.yPosition > 30) this.robot.yPosition -= 10;
		//System.out.println(robot.xPosition + ", " + robot.yPosition);
		repaint();
	}
	
	public void moveDown() {
		if(this.robot.yPosition < 570) this.robot.yPosition += 10;
		//System.out.println(robot.xPosition + ", " + robot.yPosition);
		repaint();
	}
	
	public void moveRight() {
		if(this.robot.xPosition < 1170) this.robot.xPosition += 10;
		//System.out.println(robot.xPosition + ", " + robot.yPosition);
		repaint();
	}
	
	public void moveLeft() {
		if(this.robot.xPosition > 630) this.robot.xPosition -= 10;
		//System.out.println(robot.xPosition + ", " + robot.yPosition);
		repaint();
	}
	
	public void updateCargo(Graphics g) {
		g.setColor(Orange);
		if(this.cargo.inBot()) {
			this.cargo.xPosition = this.robot.xPosition;
			this.cargo.yPosition = this.robot.yPosition;
			g.fillOval(this.cargo.xPosition - 20, this.cargo.yPosition - 20, 40, 40);
		}
		else {
			g.fillOval(this.cargo.xPosition - 20, this.cargo.yPosition - 20, 40, 40);
		}
	}
	
	public void updateHatch(Graphics g, Graphics2D g2d) {
		g.setColor(Yellow);
		if(this.hatch.inBot()) {
			this.hatch.xPosition = this.robot.xPosition;
			this.hatch.yPosition = this.robot.yPosition;
			Shape ring = createRingShape(this.hatch.xPosition, this.hatch.yPosition, 20, 10);
			g2d.fill(ring);
		}
		else {
			Shape ring = createRingShape(this.hatch.xPosition, this.hatch.yPosition, 20, 10);
			g2d.fill(ring);
		}
	}
	
	private static Shape createRingShape(
	        double centerX, double centerY, double outerRadius, double thickness)
	    {
	        Ellipse2D outer = new Ellipse2D.Double(
	            centerX - outerRadius, 
	            centerY - outerRadius,
	            outerRadius + outerRadius, 
	            outerRadius + outerRadius);
	        Ellipse2D inner = new Ellipse2D.Double(
	            centerX - outerRadius + thickness, 
	            centerY - outerRadius + thickness,
	            outerRadius + outerRadius - thickness - thickness, 
	            outerRadius + outerRadius - thickness - thickness);
	        Area area = new Area(outer);
	        area.subtract(new Area(inner));
	        return area;
	    }
}
