import java.awt.*;

public abstract class Car implements Movable{

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car

    protected boolean engineOn;

    public boolean isEngineOn() {
        return engineOn;
    }

    public String getModelName() {
        return modelName;
    }

    protected String modelName; // The car model name

    public Directions getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    protected Directions direction = Directions.NORTH;

    public void setPosition(Point position) {
        this.position = position;
    }

    protected Point position = new Point(150,150); // 0,0


    /**
     * Constructor for Bil. Creates Bil-object with the given parameters.
     * @param nrDoors Number of doors.
     * @param enginePower The Power of the Engine.
     * @param currentSpeed The cars speed currently.
     * @param color The color of the car.
     * @param modelName Name of the car model.
     */
    public Car(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    /**
     * Method which sets currentSpeed to 0.1.
     */
    public void startEngine(){
        engineOn = true;
        currentSpeed = 0.1;
    }

    /**
     * Method which stop the engine and sets currentSpeed to 0.
     */
    public void stopEngine(){
        engineOn = false;
        currentSpeed = 0;
    }


    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Relocates the car in question depending on it's current direction and position.
     */
    @Override
    public void move() {
        if(engineOn){
            switch (direction){
                case NORTH:
                    position.y = Math.max((int) (position.y - currentSpeed),0);
                    break;
                case WEST:
                    position.x = Math.max((int) (position.x - currentSpeed),0);
                    break;
                case EAST:
                    position.x = Math.max((int) (position.x + currentSpeed),0);
                    break;
                case SOUTH:
                    position.y = Math.max((int) (position.y + currentSpeed),0);
                    break;
            }
        }
    }

    /**
     * "Turns" the car to the left. Changes it's current direction by -90 degrees.
     */
    @Override
    public void turnLeft() {
        switch (direction){
            case NORTH:
                direction = Directions.WEST;
                break;
            case WEST:
                direction = Directions.SOUTH;
                break;
            case EAST:
                direction = Directions.NORTH;
                break;
            case SOUTH:
                direction = Directions.EAST;
                break;
        }
    }
    /**
     * "Turns" the car to the right. Changes it's current direction by +90 degrees.
     */
    @Override
    public void turnRight() {
        switch (direction){
            case NORTH:
                direction = Directions.EAST;
                break;
            case WEST:
                direction = Directions.NORTH;
                break;
            case EAST:
                direction = Directions.SOUTH;
                break;
            case SOUTH:
                direction = Directions.WEST;
                break;
        }
    }

    public abstract double speedFactor();

    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);

    /**
     * Method-calls incrementSpeed if gas amount is between 0 and 1.
     * @param amount Amount of applied gas.
     */
    public void gas(double amount){
        if(amount >= 0 && amount <= 1)
            incrementSpeed(amount);
    }

    /**
     * Method-calls incrementSpeed if brake amount is between 0 and 1.
     * @param amount Amount of applied brakes.
     */
    public void brake(double amount){
        if(amount >= 0 && amount <= 1)
            decrementSpeed(amount);
    }


    /**
     * Draws the vehicle in question. If the vehicle requires another appearance - simply Override this method.
     * @param g is a Graphics-component given automatically from swing.
     */
    /*
    public void paint(Graphics g){
        if(this.getDirection() == Car.dir.NORTH ||this.getDirection() == Car.dir.SOUTH) {
            g.setColor(this.getColor());
            g.fillRect(this.position.x-10,this.position.y-20,20,40);
            g.setColor(Color.black);
            g.drawString(this.getModelName(),this.position.x-20,this.position.y-25);
            g.setColor(Color.white);
            g.drawLine(this.position.x,this.position.y-20,this.position.x,this.position.y+20);
            if(this.getDirection() == Car.dir.NORTH){ // UP
                g.drawLine(this.position.x,this.position.y-20,this.position.x-10,this.position.y);
                g.drawLine(this.position.x,this.position.y-20,this.position.x+10,this.position.y);
            }
            else { // DOWN
                g.drawLine(this.position.x, this.position.y + 20, this.position.x - 10, this.position.y);
                g.drawLine(this.position.x, this.position.y + 20, this.position.x + 10, this.position.y);
            }
        }
        else if (this.getDirection() == Car.dir.WEST || this.getDirection() == Car.dir.EAST){
            g.setColor(this.getColor());
            g.fillRect(this.position.x - 20, this.position.y - 10, 40, 20);
            g.setColor(Color.black);
            g.drawString(this.getModelName(), this.position.x - 25, this.position.y - 15);
            g.setColor(Color.white);
            g.drawLine(this.position.x-20,this.position.y,this.position.x+20,this.position.y);
            if(this.getDirection() == Car.dir.WEST){ // LEFT
                g.drawLine(this.position.x-20,this.position.y,this.position.x,this.position.y-10);
                g.drawLine(this.position.x-20,this.position.y,this.position.x,this.position.y+10);
            }
            else{ // RIGHT
                g.drawLine(this.position.x+20,this.position.y,this.position.x,this.position.y-10);
                g.drawLine(this.position.x+20,this.position.y,this.position.x,this.position.y+10);
            }
        }
    }
    */
}
