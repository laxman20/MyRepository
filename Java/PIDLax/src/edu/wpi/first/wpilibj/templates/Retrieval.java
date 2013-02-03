/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author Laxman
 */
public class Retrieval 
{
	Jaguar liftJag;
	
	public Retrieval(int liftJagChannel)
	{
		liftJag = new Jaguar(liftJagChannel);
	}
	
	public void setSpeed(double speed)
	{
		liftJag.set(-speed);
	}
	
	public void stop()
	{
		liftJag.stopMotor();
	}
	public double getSpeed()
	{
		return liftJag.getSpeed();
	}
}
