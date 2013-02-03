/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot
{
	Encoder leftEncoder, rightEncoder;
	Jaguar leftJag1, leftJag2, rightJag1, rightJag2;
	PIDController leftPID1, leftPID2, rightPID1, rightPID2;
	//Joystick driveJoystick;
	DriverStationLCD ds;
	boolean setOnce;
	double Kp, Ki, Kd, x, y;
	
	//cat is the xbox controller
	MadCatz cat;
	Retrieval retrieval;
	//Launcher launcher;
	Hockey hockeysticks;
	
	public void robotInit()
	{
		System.out.println("V2.1");
		
		cat = new MadCatz(1);
		retrieval = new Retrieval(5);
		hockeysticks = new Hockey(6, 8, 7);
		//launcher = new Launcher(9,10,0.12,7);
		
		setOnce = false;
		ds = DriverStationLCD.getInstance();
		//driveJoystick = new Joystick(1);
		
		leftEncoder = new Encoder(1, 2);
		rightEncoder = new Encoder(3, 4);
		
		leftJag1 = new Jaguar(3);
		leftJag2 = new Jaguar(4);
		rightJag1 = new Jaguar(1);
		rightJag2 = new Jaguar(2);
		
		Kp = 0.1;
		Ki = 0.0;
		Kd = 0.09;
		
		leftPID1 = new PIDController(Kp, Ki, Kd, leftEncoder, leftJag1);
		leftPID2 = new PIDController(Kp, Ki, Kd, leftEncoder, leftJag2);
		rightPID1 = new PIDController(Kp, Ki, Kd, rightEncoder, rightJag1);
		rightPID2 = new PIDController(Kp, Ki, Kd, rightEncoder, rightJag2);
		
		leftEncoder.setDistancePerPulse(0.000623);
		rightEncoder.setDistancePerPulse(0.000623);
		
		leftEncoder.start();
		rightEncoder.start();
		
		printData();
		
		leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
		rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
		
		
		//leftPID1.enable();
		//leftPID2.enable();
		//rightPID1.enable();
		//rightPID2.enable();
		
		
		System.out.println("zomg");
		
		
	}
    
	public void autonomousPeriodic() 
	{
		if (!setOnce)
		{
			leftPID1.setPID(Kp, Ki, Kd);
			leftPID2.setPID(Kp, Ki, Kd);
			rightPID1.setPID(Kp, Ki, Kd);
			rightPID2.setPID(Kp, Ki, Kd);
			
			leftPID1.setSetpoint(-24);
			leftPID2.setSetpoint(-24);
			rightPID1.setSetpoint(24);
			rightPID2.setSetpoint(24);
			System.out.println(Kp);
			System.out.println(Kd);
			System.out.println(Kd);
			setOnce = true;
		}
		printData();
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void teleopPeriodic() 
	{
		printData();
		
		//joystick code
		double x = cat.getX(GenericHID.Hand.kLeft);
		double y = cat.getY(GenericHID.Hand.kLeft);
		
		
		//x = Lib.round(x, 2);
		//y = Lib.round(y, 2);
		
		
		x = Lib.signSquare(x);
		y = Lib.signSquare(y);
		//joystick code
		//x = Lib.limitOutput(x);
		//y = Lib.limitOutput(y);	
		
		leftJag1.set(Lib.limitOutput(y - x));
		leftJag2.set(Lib.limitOutput(y - x));
		rightJag1.set(-Lib.limitOutput(y + x));
		rightJag1.set(-Lib.limitOutput(y + x));
		
		if (cat.getButton(MadCatz.XboxButton.kAButton)) 
			retrieval.setSpeed(1.0);
		else if (cat.getButton(MadCatz.XboxButton.kYButton))
			retrieval.setSpeed(-1.0);
		else
			retrieval.setSpeed(0.0);
		
		if (cat.getButton(MadCatz.XboxButton.kXButton))
			hockeysticks.set(-0.5);
		else if (cat.getButton(MadCatz.XboxButton.kBButton))
			hockeysticks.set(0.5);
	    else 
			hockeysticks.set(0.0);			
		/*	
		if (cat.isPressed()) {
			launcher.setSpeed(1.0);
		} else
			launcher.setSpeed(0.0);
		*/
			
		//ds.println(DriverStationLCD.Line.kUser1, 1, "dpad x: " + String.valueOf(cat.getRawAxis(5)));
		//ds.println(DriverStationLCD.Line.kUser2, 1, "dpad y: " + String.valueOf(cat.getRawAxis(6)));
		ds.println(DriverStationLCD.Line.kUser3, 1, "b button: " + String.valueOf(cat.getButton(MadCatz.XboxButton.kBButton)));
		ds.println(DriverStationLCD.Line.kUser4, 1, "x button: " + String.valueOf(cat.getButton(MadCatz.XboxButton.kXButton)));
		//ds.println(DriverStationLCD.Line.kUser3, 1, "retreival speed: " + String.valueOf(retrieval.getSpeed()));
		//ds.println(DriverStationLCD.Line.kUser4, 1, "launcher speed: " + String.valueOf(launcher.getSpeed()));
		ds.updateLCD();
		
/*
		solenoid.get();
		if (solenoid.get()) {
			System.out.println("True");
		} else if (!solenoid.get()) {
			System.out.println("False");
		} else
			System.out.println("WTF is going on???");
			 */
    } 
	public void disabledPeriodic()
	{
		leftEncoder.reset();
		rightEncoder.reset();
		/*
		 * leftPID1.disable();
		leftPID2.disable();
		rightPID1.disable();
		rightPID2.disable();
		*/
		Kp = SmartDashboard.getNumber("P", Kp);
		Ki = SmartDashboard.getNumber("I", Ki);
		Kd = SmartDashboard.getNumber("D", Kd);
		
		setOnce = false;	
	}  
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() 
	{
		
    }
	public void printData()
	{		
		SmartDashboard.putNumber("leftside",(leftEncoder.getRate()) * -1);
		SmartDashboard.putNumber("rightside",rightEncoder.getRate());
		
		SmartDashboard.putNumber("leftsideD",(leftEncoder.getDistance()));
		SmartDashboard.putNumber("rightsideD",rightEncoder.getDistance());
		
		SmartDashboard.putNumber("P", Kp);
		SmartDashboard.putNumber("I", Ki);
		SmartDashboard.putNumber("D", Kd);
		
	}
}
