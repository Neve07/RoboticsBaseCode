// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot
{
    private Joystick driver1;
    private Joystick driver2;
    private VictorSP leftDrive1;
    private VictorSP leftDrive2;
    private VictorSP rightDrive1;
    private VictorSP rightDrive2;
    Joystick controller1;
    double leftStick;
    double rightStick;
    private Object camera;
    
    public Robot() {
        this.controller1 = new Joystick(1);
    }
    
    public void robotInit() {
        this.driver1 = new Joystick(1);
        this.driver2 = new Joystick(5);
        this.leftDrive1 = new VictorSP(1);
        this.leftDrive2 = new VictorSP(1);
        this.rightDrive1 = new VictorSP(5);
        this.rightDrive2 = new VictorSP(5);
    }
    
    public void autonomousInit() {
    }
    
    public void autonomousPeriodic() {
    }
    
    public void teleopPeriodic() {
        final double driverX = -this.driver1.getRawAxis(1);
        final double driverY = this.driver2.getRawAxis(5);
        this.leftDrive1.set(this.leftStick);
        this.leftDrive2.set(this.leftStick);
        this.rightDrive1.set(this.rightStick);
        this.rightDrive2.set(this.rightStick);
    }
}
