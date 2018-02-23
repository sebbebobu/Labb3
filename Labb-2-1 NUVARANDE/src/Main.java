import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main extends JPanel{
    public Saab95 saab = new Saab95();
    public Volvo240 volvo = new Volvo240();
    public Scania scania = new Scania();

/*
    List<Car> cars = new ArrayList<>(); // car[0] = VOLVO car[1] = SAAB car[2] = SCANIA
    public Main(){
        saab.setPosition(new Point(50,50));
        volvo.setPosition(new Point(200,290));
        scania.setPosition(new Point(200,200));
        saab.startEngine();
        volvo.startEngine();
        scania.startEngine();
        saab.gas(1);
        saab.gas(1);
        volvo.gas(1);
        volvo.gas(1);
        scania.gas(1);
        scania.gas(1);
        //volvo.turnLeft();
        //volvo.turnLeft();
        //volvo.turnLeft();
        cars.add(scania);
        cars.add(volvo);
        cars.add(saab);


        // TODO WORK-AROUND
        scania.stopEngine();
        scania.lowerRamp();
        scania.startEngine();
        // TODO WORK-AROUND
    }

    public void paint(Graphics g){
        super.paint(g);
        cars.forEach(c -> c.paint(g));
    }

    public void Update(){
        //System.out.print(volvo.position+"  "+scania.position+"   "+saab.position);
        Scanner scanner = new Scanner(System.in);
        String choice;
        Car selectedCar = new Volvo240();
        while(!(selectedCar.equals(saab) || selectedCar.equals(volvo) || selectedCar.equals(scania))){
            System.out.println("Select vehicle [S] Saab, [V] Volvo or [B] Biltransport: ");
            choice = scanner.next();
            switch (choice){
                case "S":
                    selectedCar = saab;
                    break;
                case "V":
                    selectedCar = volvo;
                    break;
                case "B":
                    selectedCar = scania;
                    break;
            }
        }
        boolean done = false;
        while(!done){
            System.out.println("This vehicle's currentSpeed: "+selectedCar.getCurrentSpeed()+" pixels/move");
            System.out.println("Select action [E] Engine(On/Off), [M] Move, [L] Turn Left or [R] Turn Right: ");
            if (selectedCar.equals(scania)){
                System.out.println("Additional actions for Scania;");
                System.out.println("[O] Lower Ramp, [H] Lift Ramp, [U] Detach Vehicle, [A] Attach Vehicle: ");
            }
            choice = scanner.next();
            switch (choice){
                case "E":
                    if(selectedCar.engineOn)
                        selectedCar.stopEngine();
                    else
                        selectedCar.startEngine();
                    done = true;
                    break;
                case "M":
                    boolean moved = false;
                    double amount = 0;
                    while(!moved){
                        System.out.println("Select action [G] Gas, [B] Brake or [R] Roll: ");
                        choice = scanner.next();
                        System.out.println("Enter an amount between [0,1] : ");
                        amount = scanner.nextDouble();
                        switch (choice){
                            case "G":
                                selectedCar.gas(amount);
                                moved = true;
                                break;
                            case "B":
                                selectedCar.brake(amount);
                                moved = true;
                                break;
                            case "R":
                                moved = true;
                                break;
                        }
                    }
                    selectedCar.move();
                    done = true;
                    break;
                case "L":
                    selectedCar.turnLeft();
                    done = true;
                    break;
                case "R":
                    selectedCar.turnRight();
                    done = true;
                    break;
                case "O":
                    if(selectedCar instanceof Scania) {
                        scania.lowerRamp();
                        done = true;
                    }
                    break;
                case "H":
                    if(selectedCar instanceof Scania) {
                        scania.liftRamp();
                        done = true;
                    }
                    break;
                case "A":
                    if(selectedCar instanceof Scania) {
                        scania.attachVehicle(saab);
                        scania.attachVehicle(volvo);
                        done = true;
                    }
                    break;
                case "U":
                    if(selectedCar instanceof Scania) {
                        scania.detachVehicle();
                        done = true;
                    }
                    break;
            }
        }
    }
    public static void main(String[] args){
        Main objects = new Main();
        JFrame frame = new JFrame("Car Simulation Window Here");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,400,400);
        frame.getContentPane().add(objects);
        frame.setVisible(true);

        while(true){ // MAIN-LOOP
            objects.Update();
            frame.getContentPane().repaint();
        }

        //TODO IGNORE - disgusting testing method.
        /*
        List<Bil> biles = new ArrayList<>();
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Volvo240());
        biles.add(new Saab95());
        biles.add(new Saab95());
        biles.add(new Saab95());
        biles.add(new Saab95());
        for (Bil b : biles) {
            b.stopEngine();
            b.startEngine();
            b.getCurrentSpeed();
            b.getColor();
            b.getNrDoors();
            b.getEnginePower();
            b.move();
            b.turnLeft();
            b.turnRight();
            b.setColor(Color.BLUE);
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnRight();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            b.move();
            b.turnLeft();
            if (b instanceof Saab95){
                ((Saab95) b).gas(0);
                ((Saab95) b).brake(0);
                ((Saab95) b).gas(9000);
                ((Saab95) b).brake(9000);
                ((Saab95) b).setTurboOff();
                ((Saab95) b).setTurboOn();
                ((Saab95) b).speedFactor();
            }
            else if (b instanceof Volvo240){
                ((Volvo240) b).brake(0);
                ((Volvo240) b).gas(0);
                ((Volvo240) b).brake(9000);
                ((Volvo240) b).gas(9000);
            }
        }

    }
    */
}