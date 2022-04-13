// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TimedRobot;

public class ArcadeAutonomous extends TimedRobot
{
    RobotDrive myRobot;
    Victor LeftDrive;
    Victor RightDrive;
    Joystick controller;
    Timer timer;
    
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        this.LeftDrive = new Victor(1);
        this.RightDrive = new Victor(5);
        this.myRobot = new RobotDrive((SpeedController)this.LeftDrive, (SpeedController)this.RightDrive);
        this.LeftDrive.setInverted(true);
        this.RightDrive.setInverted(true);
        this.controller = new Joystick(0);
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
        else if (5.0 < this.timer.get() && this.timer.get() < 6.0) {
            this.LeftDrive.set(-0.5);
        }
        else if (6.0 < this.timer.get() && this.timer.get() < 7.0) {
            this.RightDrive.set(0.5);
        }
        else {
            this.myRobot.drive(0.0, 0.0);
        }
    }
    
    public void teleopPeriodic() {
        this.myRobot.arcadeDrive((GenericHID)this.controller);
    }
}
