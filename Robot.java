package robot;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Robot {
	final private String name = "Baby Dino";
	
	private int autoTime = 15;
	private int matchTime = 135;
	
	private boolean backPistonsIn = true;
	private boolean frontPistonsIn = true;
	
	public int xPosition = 900;
	public int yPosition = 300;
	
	private boolean OTBDown = false;
	final public String[] armValues = {"Accumulate", "FrontShoot", "BackShoot", "Hatch", "Up", "Moving"};
	private String armValue = armValues[4];
	
	final private String[] flyWheelValues = {"Off", "Accumulate", "Shoot"};
	private String flyWheel = flyWheelValues[0];
	
	private boolean hatchOpen = false;
	
	private boolean hasCargo = true;
	private boolean hasHatch = false;
	
	private boolean armMotorOn = false;
	
	private int armEncoder = 0;
	
	private void printStuff() {
		System.out.print(this.armValue + "    ");
		System.out.print(this.matchTime + "    ");
		System.out.print(this.armMotorOn + "    ");			
		System.out.print(this.armEncoder);
		System.out.println();
	}
	
	private void moveArm(int armDegrees, int goalPosition) {
		
		if (this.armEncoder < armDegrees) this.armEncoder += 10;
		else if(this.armEncoder > armDegrees) this.armEncoder -= 10;
		
		if(this.armEncoder == armDegrees) {
			this.armValue = this.armValues[goalPosition];
			this.armMotorOn = false;
		}	
		else {
			this.armValue = this.armValues[5];
			this.armMotorOn = true;
			this.matchTime -= 1;
		}
	}
	
	public void armFrontShoot() { //Not working 
		moveArm(30, 1);
		printStuff();	
		}
	
	public void armBackShoot() {
		moveArm(-40, 2);
		printStuff();	
		}
	
	public void armAccumulate() {
		moveArm(110, 0);
		printStuff();
	}
	
	public void frontPistons() {
		if (this.frontPistonsIn) frontPistonsIn = false;
		else frontPistonsIn = true;
		matchTime -= 1;
	}
	
	public void backPistons() {
		if (this.backPistonsIn) backPistonsIn = false;
		else backPistonsIn = true;
		matchTime -= 1;
	}
	
	public void flyWheels(String value) {
		if (value == "shoot") {
			flyWheel = flyWheelValues[2];
			hasCargo = false;
		}
		else if (value == "accumulate") {
			flyWheel = flyWheelValues[1];
			hasCargo = true;
		}
		else if (value == "off") flyWheel = flyWheelValues[0];
		//matchTime -= 1;
	}
	
	public void flower() {
		if (hatchOpen) {
			hatchOpen = false;
			hasHatch = false;
		}
		else {
			hatchOpen = true;
			hasHatch = true;
		}
	}
	
	public void creightonIsExtraSpecial() { //Mapping stuff for later
		HashMap<String, Object> partToStatus = new HashMap<String, Object>();
		
		partToStatus.put("autoTime", autoTime);
		partToStatus.put("matchTime", matchTime);
		
		partToStatus.get("autoTime");
		
		
	}
	
	public String toString(String field) {
		if (field.equals("name")) return this.name;
		else if (field.equals("autoTime")) return String.valueOf(this.autoTime);
		else if (field.equals("matchTime")) return String.valueOf(this.matchTime);
		else if (field.equals("backPistonsIn")) {
			if (backPistonsIn) return "In";
			else return "Out";
		}
		else if (field.equals("frontPistonsIn")) {
			if (frontPistonsIn) return "In";
			else return "Out";
		}
		else if (field.equals("xPosition")) return String.valueOf(this.xPosition);
		else if (field.equals("yPosition")) return String.valueOf(this.yPosition);
		else if (field.equals("OTBDown")) {
			if (OTBDown) return "Down";
			else return "Up";
		}
		else if (field.equals("armValue")) return this.armValue;
		else if (field.equals("flyWheel")) return this.flyWheel;
		else if (field.equals("hatchOpen")) {
			if (hatchOpen) return "Open";
			else return "Closed";
		}
		else if (field.equals("hasCargo")) return String.valueOf(this.hasCargo);
		else if (field.equals("hasHatch")) return String.valueOf(this.hasHatch);
		else if (field.equals("armMotorOn")) {
			if (armMotorOn) return "On";
			else return "Off";
		}
		else if (field.equals("armEncoder")) return String.valueOf(this.armEncoder);
		else return("");
	}
}
