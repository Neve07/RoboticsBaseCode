// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import java.util.HashMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class TankStyle extends TimedRobot
{
    Joystick controller;
    Victor LeftDrive;
    Victor RightDrive;
    Victor Upper_Hippo;
    Victor Lower_Hippo;
    Victor ArmDrive;
    Victor Pulley;
    LEDLight light;
    DoubleSolenoid kobe;
    HashMap<String, Integer> elevation;
    HashMap<String, Integer> robotMode;
    HashMap<String, Boolean> camera;
    private Object armCamera;
    private Object hatchCamera;
    private Object Server;
    private Object armLight;
    double LeftStick;
    double RightStick;
    boolean aButton;
    boolean bButton;
    boolean yButton;
    boolean xButton;
    boolean startButton;
    boolean backButton;
    boolean MicroAdj_Up;
    boolean MicroAdj_Down;
    boolean upperLeftTrigger;
    boolean upperRightTrigger;
    double lowerLeftTrigger;
    double lowerRightTrigger;
    double speedLimit;
    
    public TankStyle() {
        this.controller = new Joystick(0);
        this.LeftDrive = new Victor(3);
        this.RightDrive = new Victor(4);
        this.Upper_Hippo = new Victor(1);
        this.Lower_Hippo = new Victor(2);
        this.ArmDrive = new Victor(5);
        this.Pulley = new Victor(6);
        this.light = new LEDLight(7);
        this.kobe = new DoubleSolenoid(0, 1);
        this.elevation = new HashMap<String, Integer>();
        this.robotMode = new HashMap<String, Integer>();
        this.camera = new HashMap<String, Boolean>();
    }
    
    public void robotInit() {
        this.armCamera = CameraServer.getInstance().startAutomaticCapture(0);
        this.hatchCamera = CameraServer.getInstance().startAutomaticCapture(1);
        this.robotMode.put("Hatch", 0);
        this.robotMode.put("Hippo", 1);
        this.elevation.put("armLevel", 0);
        this.elevation.put("hatchLevel", 1);
    }
    
    public void autonomousInit() {
    }
    
    public void autonomousPeriodic() {
    }
    
    public void teleopPeriodic() {
        this.speedLimit = 0.8;
        this.aButton = this.controller.getRawButton(1);
        this.xButton = this.controller.getRawButton(3);
        this.bButton = this.controller.getRawButton(2);
        this.yButton = this.controller.getRawButton(4);
        this.upperLeftTrigger = this.controller.getRawButton(5);
        this.upperRightTrigger = this.controller.getRawButton(6);
        this.lowerLeftTrigger = this.controller.getRawAxis(2);
        this.lowerRightTrigger = this.controller.getRawAxis(3);
        this.backButton = this.controller.getRawButton(7);
        this.startButton = this.controller.getRawButton(8);
        this.MicroAdj_Up = this.controller.getRawButton(9);
        this.MicroAdj_Down = this.controller.getRawButton(10);
        this.LeftStick = 0.85 * this.controller.getRawAxis(1);
        this.RightStick = this.speedLimit * this.controller.getRawAxis(5);
        if (this.startButton) {
            final long start = System.currentTimeMillis();
            while (this.startButton) {
                final long finish = System.currentTimeMillis();
                if (finish - start > 300L) {
                    if (this.robotMode.get("Hatch") == 0) {
                        this.robotMode.replace("Hatch", 1);
                        break;
                    }
                    if (this.robotMode.get("Hatch") == 0) {
                        this.robotMode.replace("Hatch", 0);
                        break;
                    }
                    this.robotMode.replace("Hatch", 0);
                    break;
                }
            }
        }
        if (this.robotMode.get("Hatch") == 0) {
            System.out.println("The Hatch Side is true");
            this.LeftStick = 0.85 * this.controller.getRawAxis(5);
            this.RightStick = this.speedLimit * this.controller.getRawAxis(1);
            this.LeftDrive.set(this.LeftStick);
            this.RightDrive.set(-this.RightStick);
            final long revTime = 5200L;
            final double revPower = 0.5;
            final double stayPower = 0.44;
            final int level = this.elevation.get("hatchLevel");
            if (this.yButton && level == 1) {
                this.elevation.replace("hatchLevel", 2);
                final long start = System.currentTimeMillis();
                long finish = System.currentTimeMillis();
                while (finish - start < 3000L) {
                    this.Pulley.set(revPower);
                    finish = System.currentTimeMillis();
                    this.LeftStick = 0.45 * this.controller.getRawAxis(5);
                    this.RightStick = 0.4 * this.controller.getRawAxis(1);
                    this.LeftDrive.set(this.LeftStick);
                    this.RightDrive.set(-this.RightStick);
                }
                this.Pulley.set(0.0);
            }
            if (this.aButton && level == 2) {
                this.elevation.replace("hatchLevel", 1);
                final long start = System.currentTimeMillis();
                long finish = System.currentTimeMillis();
                while (finish - start < 2400L) {
                    this.Pulley.set(-0.45);
                    finish = System.currentTimeMillis();
                    this.LeftStick = 0.45 * this.controller.getRawAxis(5);
                    this.RightStick = 0.4 * this.controller.getRawAxis(1);
                    this.LeftDrive.set(this.LeftStick);
                    this.RightDrive.set(-this.RightStick);
                }
                this.Pulley.set(0.0);
            }
            if (this.xButton) {
                this.kobe.set(DoubleSolenoid.Value.kForward);
            }
            if (this.bButton) {
                this.kobe.set(DoubleSolenoid.Value.kReverse);
            }
            if (this.lowerRightTrigger > 0.0) {
                for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 100L; finish = System.currentTimeMillis()) {
                    this.Pulley.set(0.3);
                }
                for (int i = 0; i < 100; ++i) {
                    this.Pulley.set(0.0);
                }
                this.Pulley.set(0.0755);
            }
            if (this.lowerLeftTrigger > 0.0) {
                for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 25L; finish = System.currentTimeMillis()) {
                    this.Pulley.set(-0.15);
                }
                this.Pulley.set(0.0);
            }
        }
        else if (this.robotMode.get("Hatch") != 0) {
            System.out.println("The arm Side is true");
            this.armLight = true;
            this.LeftStick = this.speedLimit * this.controller.getRawAxis(1);
            this.RightStick = 0.85 * this.controller.getRawAxis(5);
            this.LeftDrive.set(-this.LeftStick);
            this.RightDrive.set(this.RightStick);
            final long revTime = 8400L;
            final double revPower = 20.0;
            final double stayPower = 0.22;
            final int level = this.elevation.get("armLevel");
            if (this.yButton) {
                if (level == 0) {
                    this.elevation.replace("armLevel", 1);
                    for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 750L; finish = System.currentTimeMillis()) {
                        this.ArmDrive.set(0.5);
                    }
                    this.ArmDrive.set(stayPower);
                }
                else if (level == 1) {
                    this.elevation.replace("armLevel", 2);
                    for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 1000L; finish = System.currentTimeMillis()) {
                        this.ArmDrive.set(0.6);
                    }
                    this.ArmDrive.set(stayPower);
                }
            }
            if (this.aButton) {
                if (level == 2) {
                    this.elevation.replace("armLevel", 1);
                    for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 200L; finish = System.currentTimeMillis()) {
                        this.ArmDrive.set(0.0);
                    }
                    this.ArmDrive.set(0.22);
                }
                else if (level == 1) {
                    this.elevation.replace("armLevel", 0);
                    for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 100L; finish = System.currentTimeMillis()) {
                        this.ArmDrive.set(0.05);
                    }
                    this.ArmDrive.set(0.0);
                }
            }
            if (this.lowerRightTrigger > 0.0) {
                for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 100L; finish = System.currentTimeMillis()) {
                    this.ArmDrive.set(0.5);
                }
                this.ArmDrive.set(0.22);
            }
            if (this.lowerLeftTrigger > 0.0) {
                for (long start = System.currentTimeMillis(), finish = System.currentTimeMillis(); finish - start < 35L; finish = System.currentTimeMillis()) {
                    this.ArmDrive.set(0.1);
                }
                this.ArmDrive.set(0.22);
            }
            if (this.upperLeftTrigger) {
                this.Lower_Hippo.set(-0.5);
                this.Upper_Hippo.set(0.5);
            }
            else if (this.upperRightTrigger) {
                this.Lower_Hippo.set(0.6);
                this.Upper_Hippo.set(-0.6);
            }
            else if (!this.upperLeftTrigger || !this.upperRightTrigger) {
                this.Lower_Hippo.set(0.0);
                this.Upper_Hippo.set(0.0);
            }
        }
    }
}
