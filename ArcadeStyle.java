// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TimedRobot;

public class ArcadeStyle extends TimedRobot
{
    RobotDrive myRobot;
    Victor LeftDrive;
    Victor RightDrive;
    Joystick controller;
    
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        this.LeftDrive = new Victor(1);
        this.RightDrive = new Victor(5);
        this.myRobot = new RobotDrive((SpeedController)this.LeftDrive, (SpeedController)this.RightDrive);
        this.LeftDrive.setInverted(true);
        this.RightDrive.setInverted(true);
        this.controller = new Joystick(0);
    }
    
    public void autonomousInit() {
    }
    
    public void autonomousPeriodic() {
    }
    
    public void teleopPeriodic() {
        this.myRobot.arcadeDrive((GenericHID)this.controller);
    }
}
