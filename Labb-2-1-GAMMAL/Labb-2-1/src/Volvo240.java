import java.awt.*;

public class Volvo240 extends Car implements Transportable{

    protected final static double trimFactor = 1.25;

    public Volvo240(){
        super(4,100,0, Color.black,"Volvo240");
    }


    /**
     * Absolute maximum acceleration based on trim-factor and engine-power.
     * @return EnginePower * Trim-factor / 100.
     */
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of gas.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of brake.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}
