import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BilTransport extends Car implements Transporter<Car>{
    protected List<Car> cars;
    protected int maxCapacity; // Not something usually changeable...
    protected boolean rampUp;


    /**
     * Getter for rampUp - which checks the this-object current ramp-angle.
     * @return Getter for rampUp.
     */
    public boolean isRampUp() {
        return rampUp;
    }

    /**
     * Constructor for the abstract class.
     * Can not add cars at init. Needs to be called by attachVehicle for obvious reasons.
     * @param maxCapacity Maximum number of attachable vehicles.
     * @param enginePower EnginePower - only useful when moving.
     * @param color Color of this vehicle.
     * @param modelName Some string for the model-name. Why? I dunno.
     */
    public BilTransport(int maxCapacity, int enginePower, Color color, String modelName) {
        super(0,enginePower,0,color,modelName);
        this.cars = (maxCapacity > 0) ? new ArrayList<>(maxCapacity): new ArrayList<>(1);
        this.maxCapacity = maxCapacity;
        rampUp = false;
    }
    /*
    public abstract void attachVehicle(Car car);

    public abstract void detachVehicle();
    */

    @Override
    public void turnLeft() {
        super.turnLeft();
        cars.forEach(Car::turnLeft);
    }
    @Override
    public void turnRight() {
        super.turnRight();
        cars.forEach(Car::turnRight);
    }

    /**
     * Absolute maximum acceleration based on engine-power.
     * @return EnginePower / 100.
     */
    public double speedFactor(){
        return enginePower * 0.01;
    }

    /**
     * Changes (sets) the cars currentSpeed. The new speed is dependent on the speedfactor and the given amount of gas.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    /**
     * Changes (sets) the (Scania's or Ferry's) currentSpeed. The new speed is dependent on the speedfactor and the given amount of brake.
     * @param amount Double where amount is between 0 and 1. Describes how much of the speedFactor will be used.
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Lifts the ramp to 0 deg.
     */
    public void liftRamp(){
        rampUp = true;
    }

    /**
     * Lowers the ramp to 70 deg.
     */
    public void lowerRamp(){
        if(!isEngineOn())
            rampUp = false;
    }

    public void move(){
        if(isRampUp() && isEngineOn()){
            super.move();
            cars.forEach(c -> c.setPosition(this.getPosition()));
        }
    }

    /**
     * Checks vehicle if transportable.
     * @param car vehicle to check.
     * @return True or false.
     */
    @Override
    public boolean canAttach(Car car) {
        return (car instanceof Transportable);
    }
}
