// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.TimedRobot;

public class TankAutonomous extends TimedRobot
{
    Victor LeftDrive;
    Victor RightDrive;
    Joystick controller;
    double LeftStick;
    double RightStick;
    Timer timer;
    
    public TankAutonomous() {
        this.LeftDrive = new Victor(1);
        this.RightDrive = new Victor(5);
        this.controller = new Joystick(0);
    }
    
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        this.timer = new Timer();
    }
    
    public void autonomousInit() {
        this.timer.reset();
        this.timer.start();
    }
    
    public void autonomousPeriodic() {
        if (this.timer.get() < 1.0) {
            this.LeftDrive.set(-0.5);
            this.RightDrive.set(0.5);
        }
        else if (1.0 < this.timer.get() && this.timer.get() < 2.0) {
            this.LeftDrive.set(-0.5);
        }
        else if (2.0 < this.timer.get() && this.timer.get() < 3.0) {
            this.RightDrive.set(0.5);
        }
        else if (3.0 < this.timer.get() && this.timer.get() < 4.0) {
            this.LeftDrive.set(-0.5);
        }
        else if (4.0 < this.timer.get() && this.timer.get() < 5.0) {
            this.RightDrive.set(0.5);
        }
        else {
            this.LeftDrive.set(0.0);
            this.RightDrive.set(0.0);
        }
    }
    
    public void teleopPeriodic() {
        this.LeftStick = this.controller.getRawAxis(1);
        this.RightStick = this.controller.getRawAxis(5);
        this.LeftDrive.set(-this.LeftStick);
        this.RightDrive.set(this.RightStick);
    }
}
