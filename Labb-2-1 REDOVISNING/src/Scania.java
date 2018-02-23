import java.awt.*;

public class Scania extends BilTransport { // implements Transportable
    /**
     * Constructor for Scania. Creates Scania-object with the given parameters.
     * Sets maxCapacity to 1.
     */
    public Scania() {
        super(1, 125,  Color.blue, "Scania");
    }
    /**
     * Checks if it's possible to attach the given vehicle based on location (but also direction!).
     * @param car The given vehicle to load onto the truck-bed.
     */
    @Override
    public void attachVehicle(Car car){
        if(canAttach(car) && cars.size() < maxCapacity && !isRampUp() && !(cars.contains(car))){ // A Scania can't transport another Scania.
            Enum dirScania = this.getDirection();
            Enum dirCar = car.getDirection();
            int xScania = this.getPosition().x;
            int yScania = this.getPosition().y;
            int xCar = car.getPosition().x;
            int yCar = car.getPosition().y;
            if (dirScania.equals(Directions.NORTH) && (dirCar.equals(Directions.NORTH) || dirCar.equals(Directions.SOUTH))){
                if (yScania < yCar && (xScania + 15 > xCar && xScania - 15 < xCar)) {
                    car.setPosition(this.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Directions.SOUTH) && (dirCar.equals(Directions.NORTH) || dirCar.equals(Directions.SOUTH))){
                if (yScania > yCar && (xScania + 15 > xCar && xScania - 15 < xCar)) {
                    car.setPosition(this.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Directions.WEST) && (dirCar.equals(Directions.WEST) || dirCar.equals(Directions.EAST))){
                if (xScania < xCar && (yScania + 15 > yCar && yScania - 15 < yCar)) {
                    car.setPosition(this.getPosition());
                    cars.add(car);
                }
            }
            else if (dirScania.equals(Directions.EAST) && (dirCar.equals(Directions.WEST) || dirCar.equals(Directions.EAST))){
                if (xScania > xCar && (yScania + 15 > yCar && yScania - 15 < yCar)) {
                    car.setPosition(this.getPosition());
                    cars.add(car);
                }
            }
        }
    }

    @Override
    public void detachVehicle(Car type) {
        if(type != null) detachVehicle();
    }

    /**
     * Detaches the last vehicle in the list (most recently added)
     * As long as there is a vehicle to unload and the ramp is lowered.
     */
    @Override
    public void detachVehicle(){
        if(cars.size() >= 1 && !isRampUp()) {
            Car car = cars.get(cars.size() - 1);
            switch (this.getDirection()){
                case NORTH:
                    car.setPosition(new Point(this.getPosition().x, this.getPosition().y+50));
                    break;
                case SOUTH:
                    car.setPosition(new Point(this.getPosition().x, this.getPosition().y-50));
                    break;
                case WEST:
                    car.setPosition(new Point(this.getPosition().x+50, this.getPosition().y));
                    break;
                case EAST:
                    car.setPosition(new Point(this.getPosition().x-50, this.getPosition().y));
                    break;
            }
            cars.remove(car);
        }
    }

    /**
     * Overridden paint-method for drawing the Scania.
     * @param g is a Graphics-component given automatically from swing.
     */
    /*
    @Override
    public void paint(Graphics g) {
        if (this.getDirection().equals(Directions.NORTH)){
            g.setColor(this.getColor());
            g.fillRect(this.position.x-13,this.position.y-45,26,15);
            g.setColor(Color.gray);
            g.fillRect(this.position.x-15,this.position.y-30,30,60);
            if(!this.isRampUp())
                g.fillRect(this.position.x-12,this.position.y+30,24,25);
            g.setColor(Color.black);
            g.drawString(this.getModelName(),this.position.x-20,this.position.y-50);
        }
        else if (this.getDirection().equals(Directions.SOUTH)){
            g.setColor(this.getColor());
            g.fillRect(this.position.x-13,this.position.y+30,26,15);
            g.setColor(Color.gray);
            g.fillRect(this.position.x-15,this.position.y-30,30,60);
            if(!this.isRampUp())
                g.fillRect(this.position.x-12,this.position.y-55,24,25);
            g.setColor(Color.black);
            g.drawString(this.getModelName(),this.position.x-20,this.position.y-50);
        }
        else if (this.getDirection().equals(Directions.WEST)){
            g.setColor(this.getColor());
            g.fillRect(this.position.x-45,this.position.y-13,15,26);
            g.setColor(Color.gray);
            g.fillRect(this.position.x-30,this.position.y-15,60,30);
            if(!this.isRampUp())
                g.fillRect(this.position.x+30,this.position.y-12,24,25);
            g.setColor(Color.black);
            g.drawString(this.getModelName(),this.position.x-20,this.position.y-20);
        }
        else if (this.getDirection().equals(Directions.EAST)){
            g.setColor(this.getColor());
            g.fillRect(this.position.x+30,this.position.y-13,15,26);
            g.setColor(Color.gray);
            g.fillRect(this.position.x-30,this.position.y-15,60,30);
            if(!this.isRampUp())
                g.fillRect(this.position.x-54,this.position.y-12,24,25);
            g.setColor(Color.black);
            g.drawString(this.getModelName(),this.position.x-20,this.position.y-20);
        }
    }
    */
}
