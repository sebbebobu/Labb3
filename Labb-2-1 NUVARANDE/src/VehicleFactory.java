import java.awt.*;
import java.util.ArrayList;

public class VehicleFactory {
    public static Volvo240 createVolvo(){
        return new Volvo240();
    }
    public static Saab95 createSaab(){
        return new Saab95();
    }
    public static Scania createScania(){
        return new Scania();
    }

    public static Car createDesiredVehicle(String requestedVehicle) throws IllegalArgumentException{
        requestedVehicle = requestedVehicle.toUpperCase();
        if(requestedVehicle.isEmpty()) throw new IllegalArgumentException("Empty input String.");
        switch (requestedVehicle){
            case "SCANIA":
                return new Scania();
            case "SAAB95":
                return new Saab95();
            case "VOLVO240":
                return new Volvo240();
            default:
                throw new IllegalArgumentException("Non-defined Vehicle-type.");
        }

    }
    public static ArrayList<Car> standardVehicles(){
        ArrayList<Car> vehicles = new ArrayList<>();
        Car volvo = new Volvo240();
        Car saab = new Saab95();
        Car scania = new Scania();

        volvo.setPosition(new Point(100,100));
        saab.setPosition(new Point(100,300));
        scania.setPosition(new Point(100,500));

        //saab.turnRight();
        volvo.turnRight();
        volvo.turnRight();

        vehicles.add(volvo);
        vehicles.add(saab);
        vehicles.add(scania);

        return vehicles;
    }
}
