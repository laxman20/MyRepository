/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.parsing.IInputOutput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.communication.UsageReporting;
/**
 *
 * @author Laxman
 */
public class MadCatz extends GenericHID implements IInputOutput 
{
	static final byte kDefaultLXAxis = 1;
	static final byte kDefaultLYAxis = 2;
	static final byte kDefaultRXAxis = 4;
	static final byte kDefaultRYAxis = 5;
	static final byte kDefaultTriggerAxis = 3;
	static final byte kDefaultPadX = 6;
	static final byte kDefaultPadY = 7;
	
	static final int kDefaultAButton = 1;
	static final int kDefaultBButton = 2;
	static final int kDefaultXButton = 3;
	static final int kDefaultYButton = 4;
	static final int kDefaultLBump = 5;
	static final int kDefaultRBump = 6;
	static final int kDefaultStart = 7;
	static final int kDefaultBack = 8;
	static final int kDefaultLStick = 9;
	static final int kDefaultRStick = 10;
	
	
	
	public static class AxisType
	{
		public final int value;
		static final int kLX_val = 0;
		static final int kLY_val = 1;
		static final int kRX_val = 2;
		static final int kRY_val = 3;
		static final int kTriggerAxis_val = 4;
		static final int kPadX_val = 5;
		static final int kPadY_val = 6;
		static final int kNumAxis_val = 7;
		
		public static final AxisType kLX = new AxisType(kLX_val);
		public static final AxisType kRX = new AxisType(kRX_val);
		public static final AxisType kLY = new AxisType(kLY_val);
		public static final AxisType kRY = new AxisType(kRY_val);
		public static final AxisType kTriggerAxis = new AxisType(kTriggerAxis_val);
		public static final AxisType kPadX = new AxisType(kPadX_val);
		public static final AxisType kPadY = new AxisType(kPadY_val);
		public static final AxisType kNumAxis = new AxisType(kNumAxis_val);
		
		
		private AxisType(int value)
		{
			this.value = value;
		}
	}
	
	public static class XboxButton
	{
		public final int value;
		static final int kAButton_val = 0;
		static final int kBButton_val = 1;
		static final int kXButton_val = 2;
		static final int kYButton_val = 3;
		static final int kLBump_val = 4;
		static final int kRBump_val = 5;
		static final int kBack_val = 6;
		static final int kStart_val = 7;
		static final int kLStick_val = 8;
		static final int kRStick_val = 9;
		static final int kNumButton_val = 10;
		
		public static final XboxButton kAButton = new XboxButton(kAButton_val);
		public static final XboxButton kBButton = new XboxButton(kBButton_val);
		public static final XboxButton kXButton = new XboxButton(kXButton_val);
		public static final XboxButton kYButton = new XboxButton(kYButton_val);
		public static final XboxButton kLBump = new XboxButton(kLBump_val);
		public static final XboxButton kRBump = new XboxButton(kRBump_val);
		public static final XboxButton kBack = new XboxButton(kBack_val);
		public static final XboxButton kStart = new XboxButton(kStart_val);
		public static final XboxButton kLStick = new XboxButton(kLStick_val);
		public static final XboxButton kRStick = new XboxButton(kRStick_val);
		public static final XboxButton kNumButton = new XboxButton(kNumButton_val);
		
		private XboxButton(int value)
		{
			this.value = value;
		}
	}

	private DriverStation m_ds;
	private final int m_port;
	private final byte[] m_axes;
	private final byte[] m_buttons;
	
	public MadCatz(final int port)
	{
		this(port, AxisType.kNumAxis.value, XboxButton.kNumButton.value);
		m_axes[AxisType.kLX.value] = kDefaultLXAxis;
		m_axes[AxisType.kRX.value] = kDefaultRXAxis;
		m_axes[AxisType.kLY.value] = kDefaultLYAxis;
		m_axes[AxisType.kRY.value] = kDefaultRYAxis;
		m_axes[AxisType.kTriggerAxis.value] = kDefaultTriggerAxis;
		m_axes[AxisType.kPadX.value] = kDefaultPadX;
		m_axes[AxisType.kPadY.value] = kDefaultPadY;
		
		m_buttons[XboxButton.kAButton.value] = kDefaultAButton;
		m_buttons[XboxButton.kBButton.value] = kDefaultBButton;
		m_buttons[XboxButton.kXButton.value] = kDefaultXButton;
		m_buttons[XboxButton.kYButton.value] = kDefaultYButton;
		m_buttons[XboxButton.kLBump.value] = kDefaultLBump;
		m_buttons[XboxButton.kRBump.value] = kDefaultRBump;
		m_buttons[XboxButton.kStart.value] = kDefaultStart;
		m_buttons[XboxButton.kBack.value] = kDefaultBack;
		m_buttons[XboxButton.kLStick.value] = kDefaultLStick;
		m_buttons[XboxButton.kRStick.value] = kDefaultRStick;
		
	}
	
	protected MadCatz(int port, int numAxisTypes, int numButtonTypes)
	{
		m_ds = DriverStation.getInstance();
		m_axes = new byte[numAxisTypes];
		m_buttons = new byte[numButtonTypes];
		m_port = port;
	}

	public double getY(Hand hand)
	{
		if (hand == Hand.kLeft)
		{
			return getRawAxis(m_axes[AxisType.kLY.value]);
		}
		else
			return getRawAxis(m_axes[AxisType.kRY.value]);
	}

	public double getX(Hand hand) 
	{
		if (hand == Hand.kLeft)
		{
			return getRawAxis(m_axes[AxisType.kLX.value]);
		}
		else
			return getRawAxis(m_axes[AxisType.kRX.value]);
	}

	public double getZ(Hand hand) 
	{
		return 0.0;
	}

	public double getThrottle() 
	{
		return 0.0;
	}

	public double getTwist()
	{
		return 0.0;
	}
	
	public double getRawAxis(int i)
	{
		return m_ds.getStickAxis(m_port, i);
	}
	
	public double getAxis(final AxisType axis)
	{
		return getRawAxis(m_axes[axis.value]);
	}
	
	public boolean getBumper(Hand hand) 
	{
		return false;
	}

	public boolean getTop(Hand hand) 
	{
		return false;
	}

	public boolean getTrigger(Hand hand) 
	{
		return false;
	}
	
	public boolean getRawButton(final int button)
	{
		return ((0x1 << (button - 1)) & m_ds.getStickButtons(m_port)) != 0; 
	}

	public boolean getButton(XboxButton button)
	{
		return getRawButton(m_buttons[button.value]);
	}
	
	public boolean isPressed()
	{
		if (this.getRawAxis(m_axes[AxisType.kTriggerAxis.value]) >= 1.0 || this.getRawAxis(m_axes[AxisType.kTriggerAxis.value]) <= -1.0 ) {
			return true;
		} else
			return false;
	}
}
