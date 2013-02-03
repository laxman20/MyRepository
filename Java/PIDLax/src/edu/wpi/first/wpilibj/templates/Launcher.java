/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author Laxman
 */
public class Launcher 
{
	Encoder launcherEncoder;
	PIDController launcherPID;
	double Kp, Ki, Kd;
	Jaguar launcherJag; 
	
	public Launcher(int aChannel, int bChannel, double dPP, int jagChannel)
	{
		Kp = 1.0;
		Ki = 0.0;
		Kd = 0.0;
		
		//instanciate encoder, set distance per pulse, and start
		launcherEncoder = new Encoder(aChannel, bChannel);
		launcherEncoder.setDistancePerPulse(dPP);
		launcherEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
		launcherEncoder.start();
		launcherJag = new Jaguar(jagChannel);
		//instanciate PID
		launcherPID = new PIDController(Kp, Ki, Kd, launcherEncoder, launcherJag);
		
		//instanciate Jaguar
		
	}
	//set RPM
	public void setRPM(double RPM)
	{
		launcherPID.enable();
		launcherPID.setSetpoint(RPM);
	}
	//stop launcher
	public void stop()
	{
		launcherPID.disable();
		launcherJag.disable();
	}
	//set speed of launcher
	public void setSpeed(double speed)
	{
		launcherJag.set(speed);
	}
	public double getSpeed()
	{
		return launcherJag.getSpeed();
	}
	
	
}
