package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

public class Hockey
{
	Jaguar driveJag;
	LimitSwitch topLimit, bottomLimit;
	
	/**
	 * Creates object for hockey sticks.
	 * @param jagChannel Channel for jaguar.
	 * @param topLimitChannel Channel for top limit switch.
	 * @param bottomLimitChannel Channel for bottom limit switch
	 */
	public Hockey(int jagChannel, int topLimitChannel, int bottomLimitChannel)
	{
		driveJag = new Jaguar(jagChannel);
		topLimit = new LimitSwitch(topLimitChannel, true);
		bottomLimit = new LimitSwitch(bottomLimitChannel, true);	
	}
	
	/**
	 * Set method for hockey sticks.
	 * @param speed Sets speed of jaguar.
	 */
	public void set(double speed)
	{	
		/*
            if (!topLimit.get() && !bottomLimit.get()) {
				System.out.println("top and bottom limit false");
                driveJag.set(speed);
			} else if (bottomLimit.get() && speed < 0) {
				System.out.println("bottomlimit true and speed less than 0");
                driveJag.set(speed);
			} else if (topLimit.get() && speed > 0) {
				System.out.println("top limit true and speed more than 0");
                driveJag.set(speed);
			} else
				System.out.println("else");
                driveJag.set(0.0);
				* */
			driveJag.set(speed);
	}
	
	/**
	 * Stop method for hockey sticks.
	 */
	public void stop()
	{
		driveJag.stopMotor();
	}
}
