// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.RobotBase;

public final class Main
{
    private Main() {
    }
    
    public static void main(final String... args) {
        RobotBase.startRobot((Supplier)TankStyle::new);
    }
}
