import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.cars = VehicleFactory.standardVehicles();
        /*
        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        cc.cars.get(0).setPosition(new Point(100,100));
        cc.cars.get(1).setPosition(new Point(100,300));
        cc.cars.get(2).setPosition(new Point(100,500));

        //cc.cars.get(1).startEngine();


        // Making them face EAST
        //cc.cars.get(0).turnRight();
        //cc.cars.get(1).turnRight();
        //cc.cars.get(2).turnRight();

        cc.cars.get(1).turnRight();

        cc.cars.get(2).turnRight();
        cc.cars.get(2).turnRight();
        */
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }




    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> classNames = new ArrayList<>();
            ArrayList<Point> points = new ArrayList<>();
            for (Car car : cars) {
                checkCollision(car);
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                System.out.println(car.getDirection() +" typ "+car.getClass().getSimpleName()+": "+x+"  "+y);
                car.move();

                //System.out.println(x +"  "+ y);
                // TODO

                classNames.add(car.getClass().getSimpleName());
                points.add(car.getPosition());



                /*
                if(car instanceof Saab95)
                    frame.drawPanel.moveSaab(car.getPosition());
                else if(car instanceof Volvo240)
                    frame.drawPanel.moveVolvo(car.getPosition());
                else if(car instanceof Scania)
                    frame.drawPanel.moveScania(car.getPosition());
                */
                // repaint() calls the paintComponent method of the panel

                frame.drawPanel.repaint();

            }
            frame.drawPanel.updateImages(classNames, points);
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;

        for (Car car : cars) {
            car.gas(gas);
        }
    }
    // Calls the brake method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;

        for (Car car : cars) {
            car.brake(brake);
        }
    }
    // Calls stopEngine for each car once
    void stopEngines() {
        cars.forEach(Car::stopEngine);
    }

    // Calls startEngine for each car once
    void startEngines() {
        cars.forEach(Car::startEngine);
    }
    // Sets all vehicles with turbos ON
    void turbosOn() {
        for (Car car : cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOn();
        }
    }
    // Sets all vehicles with turbos OFF
    void turbosOff() {
        for (Car car : cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }
    // Lowers all ramps of all vehicles with a ramp
    void lowerRamps(){
        for (Car car : cars){
            if (car instanceof BilTransport)
                ((BilTransport) car).lowerRamp();
        }
    }
    // Lifts all ramps of all vehicles with a ramp
    void liftRamps(){
        for (Car car : cars){
            if (car instanceof BilTransport)
                ((BilTransport) car).liftRamp();
        }
    }

    public void checkCollision(Car car){
        int carX = (int) Math.round(car.getPosition().getX());
        int carY = (int) Math.round(car.getPosition().getY());
        if(carY == 0)
            car.setDirection(Directions.SOUTH);
        else if(carY+60 >= 560)
            car.setDirection(Directions.NORTH);
        else if(carX == 0)
            car.setDirection(Directions.EAST);
        else if(carX+100 >= 800)
            car.setDirection(Directions.WEST);
        else if (car.getCurrentSpeed() > 0){
            for (Car car2 : cars){
                int car2X = (int) Math.round(car2.getPosition().getX());
                int car2Y = (int) Math.round(car2.getPosition().getY());

                if(!car2.equals(car) && (carX <= car2X+100 && carX+100 >= car2X)){

                    if(car.getDirection() == Directions.NORTH && (carY >= car2Y && carY <= car2Y+60)){
                        car.setDirection(Directions.SOUTH);
                        return;
                    }
                    else if (car.getDirection() == Directions.SOUTH && (carY+60 >= car2Y && carY+60 <= car2Y+60)){
                        car.setDirection(Directions.NORTH);
                        return;
                    }
                    else if((car.getDirection() == Directions.WEST && carX == car2X+100 && (carY <= car2Y+60 && carY+60 >= car2Y)) || car.getDirection() == Directions.EAST && carX+100 == car2X && (carY <= car2Y+60 && carY+60 >= car2Y)) {
                        if (car.getDirection() == Directions.EAST) {
                            car.setDirection(Directions.WEST);
                            return;
                        }
                        else {
                            car.setDirection(Directions.EAST);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Method for removing a vehicle in the list of vehicles randomly.
     * IF empty THEN throws IllegalStateException.
     */
    public void removeVehicle() throws IllegalStateException{
        if (cars.size() == 0) throw new IllegalStateException("Vehicle-list is empty!");
        int randomIndex = ThreadLocalRandom.current().nextInt(0, cars.size() + 1);
        cars.remove(randomIndex);
    }

}
