// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot;

public class LEDLight
{
    private boolean on;
    private int portNum;
    
    public LEDLight(final int num) {
        this.on = false;
        this.portNum = num;
    }
    
    public void swapLight() {
        this.on = !this.on;
    }
}
