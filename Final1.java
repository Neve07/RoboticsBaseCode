// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import java.util.HashMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Final1 extends TimedRobot
{
    Joystick controller;
    Victor LeftDrive;
    Victor RightDrive;
    Victor Upper_Hippo;
    Victor Lower_Hippo;
    Victor ArmDrive;
    VictorSP Hatch;
    DoubleSolenoid kobe;
    boolean startButton;
    boolean backButton;
    double LeftStick;
    double RightStick;
    double speedLimit;
    boolean upperLeftTrigger;
    boolean upperRightTrigger;
    boolean aButton;
    boolean yButton;
    HashMap<String, Integer> elevation;
    HashMap<String, Boolean> map2;
    
    public Final1() {
        this.controller = new Joystick(0);
        this.LeftDrive = new Victor(3);
        this.RightDrive = new Victor(4);
        this.Upper_Hippo = new Victor(1);
        this.Lower_Hippo = new Victor(2);
        this.ArmDrive = new Victor(5);
        this.Hatch = new VictorSP(2);
        this.kobe = new DoubleSolenoid(0, 1);
        this.elevation = new HashMap<String, Integer>();
        this.map2 = new HashMap<String, Boolean>();
    }
    
    public void robotInit() {
        this.elevation.put("Level", 0);
        this.map2.put("counter", true);
    }
    
    public void teleopPeriodic() {
        this.speedLimit = 0.5;
        this.LeftStick = this.speedLimit * this.controller.getRawAxis(1);
        this.RightStick = this.speedLimit * this.controller.getRawAxis(5);
        this.LeftDrive.set(-this.LeftStick);
        this.RightDrive.set(this.RightStick);
        this.aButton = this.controller.getRawButton(1);
        this.yButton = this.controller.getRawButton(4);
        final long revTime = 8400L;
        final double revPower = 0.7;
        final int level = this.elevation.get("Level");
        if (this.yButton) {
            if (level == 0) {
                this.elevation.replace("Level", 1);
                final long start = System.currentTimeMillis();
                long finish = System.currentTimeMillis();
                for (int i = 0; i < 6000; ++i) {
                    this.ArmDrive.set(i / 100.0);
                }
                while (finish - start < 2100L) {
                    this.ArmDrive.set(60.0);
                    finish = System.currentTimeMillis();
                }
                this.ArmDrive.set(0.0);
            }
            else if (level == 1) {
                this.elevation.replace("Level", 2);
                final long start = System.currentTimeMillis();
                long finish = System.currentTimeMillis();
                for (int i = 0; i < 6000; ++i) {
                    this.ArmDrive.set(i / 100.0);
                }
                while (finish - start < 2100L) {
                    this.ArmDrive.set(60.0);
                    finish = System.currentTimeMillis();
                }
                this.ArmDrive.set(0.0);
            }
            this.ArmDrive.set(0.44);
        }
    }
}
