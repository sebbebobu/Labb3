import java.awt.*;

public class Ferry extends BilTransport {


    /*
        The Ferry's parking space-layout when pointing NORTH:
        cars[0]  cars[3]
        cars[1]  cars[4]
        cars[2]  cars[5]
        The logic behind attach/detach will be adapting to this concept/layout.
     */
    /**
     * Constructor for Ferry. Creates Ferry-object with the given parameters.
     * Sets maxCapacity to 6. (3 spaces * 2 lanes)
     */
    public Ferry() {
        super(6, 150,  Color.orange, "Ferry_Deluxe");
        for (int i = 0; i < maxCapacity; i++)
            cars.add(i,null);
    }

    /**
     * @param car The specific vehicle to be attached to this ferry.
     * This method will attach the given vehicle under these following conditions:
     *            The given vehicle is not a type of BilTransport. (Neither Ferry or Scania)
     *            The given vehicle is not already attached/boarded on this ferry already.
     *            There is an empty spot for the given vehicle.
     *            There is no vehicles blocking it's "parking-spot".
     *            The given vehicle is parallel to the ferry.
     *            REMOVED: The given vehicle is positioned in front of an empty lane/space.
     */
    @Override
    public void attachVehicle(Car car) {
        if ((car != null) && !cars.contains(car) && canAttach(car)){
            for (int i = 0;i < cars.size(); i++) {
                if(cars.get(i) == null){
                    if(i % 3 == 2) { // The first row of vehicles in each lane.
                        cars.set(i, car);
                        return;
                    }
                    else if(i % 3 == 1 && cars.get(i+1) == null) { // Second Row of each lane.
                        cars.set(i, car);
                        return;
                    }
                    else if(i % 3 == 0 && cars.get(i+1) == null && cars.get(i+2) == null) { // Last row.
                        cars.set(i, car);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Method for detaching vehicles from this ferry.
     * This method will detach the first vehicle from the list of attached vehicles.
     */
    @Override
    public void detachVehicle() {
        for (int i = 0;i < cars.size(); i++) {
            if(cars.get(i) != null){
                if(i % 3 == 0) { // The first row of vehicles in each lane.
                    cars.set(i, null);
                    return;
                }
                else if(i % 3 == 1 && cars.get(i-1) == null) { // Second Row of each lane.
                    cars.set(i, null);
                    return;
                }
                else if(i % 3 == 2 && cars.get(i-1) == null && cars.get(i-2) == null) { // Last row.
                    cars.set(i, null);
                    return;
                }
            }
        }
    }

    /**
     * @param car The specific vehicle to detach from the ferry.
     * Detaches the given vehicle from the ferry under following conditions:
     *            If the given vehicle is in fact attached to this ferry.
     *            If no vehicle is blocking this vehicle from detaching/deboarding.
     */
    public void detachVehicle(Car car){
        if(car != null) {
            if (!car.equals(this) && cars.contains(car)) {
                int index = cars.indexOf(car);
                if (index % 3 == 0) { // The first row of vehicles in each lane.
                    cars.set(index, null);
                } else if (index % 3 == 1 && cars.get(index - 1) == null) { // Second Row of each lane.
                    cars.set(index, null);
                } else if (index % 3 == 2 && cars.get(index - 1) == null && cars.get(index - 2) == null) { // Last row.
                    cars.set(index, null);
                }

            }
        }
    }

    /**
     * This is the paint-method specifically made for the Ferry. Although the ferry will never be displayed.
     * This method does nothing. Only overridden to not be displayed as a car (Saab or Volvo).
     *
     * @param g is a Graphics-component given automatically from swing.
     */
    /*
    @Override
    public void paint(Graphics g) {

    }
    */
}
