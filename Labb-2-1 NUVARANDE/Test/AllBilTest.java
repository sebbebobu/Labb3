import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AllCarTest {

    Saab95 saab = new Saab95();
    Volvo240 volvo = new Volvo240();
    Scania scania = new Scania();
    Ferry ferry = new Ferry();

    @BeforeEach
    void setUp(){
        /*
        System.out.println("currentSpeed: "+saab.getCurrentSpeed() +" , isTurboOn: "
                +saab.isTurboOn()+" , Direction: "+saab.getDirection()+" , Position: "+saab.getPosition());
        // Då vet vi alla egenskaper efter varje testmetodsanrop!
        */
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        ferry = new Ferry();
    }

    @Test
    void isTurboOn() {
        assertFalse(saab.isTurboOn());
        // Turbo:n är avstängd vid skapandet av Saab:en.
    }
    @Test
    void setTurboOn() {
        saab.setTurboOn();
        assertTrue(saab.isTurboOn());
        // Turbo:n ska vara på efter metod-anropet.

        saab.setTurboOn();
        assertTrue(saab.isTurboOn());
        // Turbo:n ska vara på efter metod-anropet, även om vi kallade på metoden igen.
    }

    @Test
    void setTurboOff() {
        saab.setTurboOff();
        assertFalse(saab.isTurboOn());
        // Turbo:n ska vara av efter metod-anropet.

        saab.setTurboOff();
        assertFalse(saab.isTurboOn());
        // Turbo:n ska vara av efter metod-anropet, även om vi kallade på metoden igen.
    }

    @Test
    void speedFactor() {
        double innanTurbo = saab.speedFactor();
        saab.setTurboOn();
        double efterTurbo = saab.speedFactor();
        // Speedfactor:n ska ge mindre utan turbo.
        assertTrue(innanTurbo < efterTurbo);

        assertEquals(1.25,volvo.speedFactor());


        assertEquals(scania.enginePower/100,scania.speedFactor());
    }
/*
    @Test
    void incrementSpeed() {
        double innanInc = saab.getCurrentSpeed();
        saab.incrementSpeed(0);
        double efterInc = saab.getCurrentSpeed();
        assertTrue(innanInc == efterInc);
        // Ska vara lika.

        innanInc = saab.getCurrentSpeed();
        saab.incrementSpeed(10);
        efterInc = saab.getCurrentSpeed();
        assertTrue(innanInc < efterInc);
        // Ska öka currentspeed.

        // ------------- VOLVO -----------------------

        innanInc = volvo.getCurrentSpeed();
        volvo.incrementSpeed(0);
        efterInc = volvo.getCurrentSpeed();
        assertTrue(innanInc == efterInc);
        // Ska vara lika.

        innanInc = volvo.getCurrentSpeed();
        volvo.incrementSpeed(10);
        efterInc = volvo.getCurrentSpeed();
        assertTrue(innanInc < efterInc);
        // Ska öka currentspeed.
    }

    @Test
    void decrementSpeed() {
        double innanDec = saab.getCurrentSpeed();
        saab.decrementSpeed(0);
        double efterDec = saab.getCurrentSpeed();
        assertTrue(innanDec == efterDec);
        // Ska vara lika.

        innanDec = saab.getCurrentSpeed();
        saab.gas(10);
        efterDec = saab.getCurrentSpeed();
        assertTrue(innanDec > efterDec);
        // Ska vara mindre.

        //---------------- VOLVO -------------------

        innanDec = volvo.getCurrentSpeed();
        volvo.decrementSpeed(0);
        efterDec = volvo.getCurrentSpeed();
        assertTrue(innanDec == efterDec);
        // Ska vara lika.

        innanDec = volvo.getCurrentSpeed();
        volvo.decrementSpeed(10);
        efterDec = volvo.getCurrentSpeed();
        assertTrue(innanDec >= efterDec);
        // Ska vara mindre. OCH om båda är 0.
    }
*/
    @Test
    void gas() {
        double innanGas = saab.getCurrentSpeed();
        saab.gas(0.0);
        double efterGas = saab.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Gas utan gas är ingen gas...

        innanGas = saab.getCurrentSpeed();
        saab.gas(-1);
        efterGas = saab.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är negativ - ska inte currentSpeed ändras.

        innanGas = saab.getCurrentSpeed();
        saab.gas(9000);
        efterGas = saab.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är mer än 1 - ska inte currentSpeed ändras.

        innanGas = saab.getCurrentSpeed();
        saab.gas(1.0);
        efterGas = saab.getCurrentSpeed();
        assertTrue(innanGas < efterGas);
        // currentSpeed borde ha ökat.

        double speedFactor = saab.speedFactor();
        assertTrue(speedFactor + innanGas == efterGas);
        // Helt enkelt det metoden Gas gör. (UTAN turbo)

        // ------------------ VOLVO ------------------------------

        innanGas = volvo.getCurrentSpeed();
        volvo.gas(0.0);
        efterGas = volvo.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Gas utan gas är ingen gas...

        innanGas = volvo.getCurrentSpeed();
        volvo.gas(-1);
        efterGas = volvo.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är negativ - ska inte currentSpeed ändras.

        innanGas = volvo.getCurrentSpeed();
        volvo.gas(9000);
        efterGas = volvo.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är mer än 1 - ska inte currentSpeed ändras.

        innanGas = volvo.getCurrentSpeed();
        volvo.gas(1.0);
        efterGas = volvo.getCurrentSpeed();
        assertTrue(innanGas < efterGas);
        // currentSpeed borde ha ökat.

        speedFactor = volvo.speedFactor();
        assertTrue(speedFactor + innanGas == efterGas);
        // Helt enkelt det metoden Gas gör. (UTAN turbo)

        // ------------------ SCANIA -------------------------------
        innanGas = scania.getCurrentSpeed();
        scania.gas(0.0);
        efterGas = scania.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Gas utan gas är ingen gas...

        innanGas = scania.getCurrentSpeed();
        scania.gas(-1);
        efterGas = scania.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är negativ - ska inte currentSpeed ändras.

        innanGas = scania.getCurrentSpeed();
        scania.gas(9000);
        efterGas = scania.getCurrentSpeed();
        assertTrue(innanGas == efterGas);
        // Om gasen är mer än 1 - ska inte currentSpeed ändras.

        innanGas = scania.getCurrentSpeed();
        scania.gas(1.0);
        efterGas = scania.getCurrentSpeed();
        assertTrue(innanGas < efterGas);
        // currentSpeed borde ha ökat.

        speedFactor = scania.speedFactor();
        assertTrue(speedFactor + innanGas == efterGas);
    }

    @Test
    void brake() {
        double innanBroms = saab.getCurrentSpeed();
        saab.brake(0.0);
        double efterBroms = saab.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Broms utan broms är ingen broms...

        innanBroms = saab.getCurrentSpeed();
        saab.brake(-1);
        efterBroms = saab.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är negativ - ska inte currentSpeed ändras.

        innanBroms = saab.getCurrentSpeed();
        saab.brake(9000);
        efterBroms = saab.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är mer än 1 - ska inte currentSpeed ändras.

        innanBroms = saab.getCurrentSpeed();
        saab.brake(1.0);
        efterBroms = saab.getCurrentSpeed();
        assertTrue(innanBroms > efterBroms);
        // currentSpeed borde ha ökat.

        double speedFactor = saab.speedFactor();
        assertTrue(innanBroms - speedFactor == efterBroms);
        // Helt enkelt det metoden Brake gör. (med eller utan turbo)

        // ----------------------- VOLVO -------------------------------
        innanBroms = volvo.getCurrentSpeed();
        volvo.brake(0.0);
        efterBroms = volvo.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Broms utan broms är ingen broms...

        innanBroms = volvo.getCurrentSpeed();
        volvo.brake(-1);
        efterBroms = volvo.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är negativ - ska inte currentSpeed ändras.

        innanBroms = volvo.getCurrentSpeed();
        volvo.brake(9000);
        efterBroms = volvo.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är mer än 1 - ska inte currentSpeed ändras.

        innanBroms = volvo.getCurrentSpeed();
        volvo.brake(1.0);
        efterBroms = volvo.getCurrentSpeed();
        assertTrue(innanBroms >= efterBroms);
        // currentSpeed borde ha ökat. KAN vara lika om båda är 0

        speedFactor = volvo.speedFactor();
        assertTrue(innanBroms - speedFactor == efterBroms || efterBroms == 0);
        // Helt enkelt det metoden Brake gör. (med eller utan turbo)

        // ----------------------- SCANIA ----------------------------------------------

        innanBroms = scania.getCurrentSpeed();
        scania.brake(0.0);
        efterBroms = scania.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Broms utan broms är ingen broms...

        innanBroms = scania.getCurrentSpeed();
        scania.brake(-1);
        efterBroms = scania.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är negativ - ska inte currentSpeed ändras.

        innanBroms = scania.getCurrentSpeed();
        scania.brake(9000);
        efterBroms = scania.getCurrentSpeed();
        assertTrue(innanBroms == efterBroms);
        // Om Bromsen är mer än 1 - ska inte currentSpeed ändras.

        innanBroms = scania.getCurrentSpeed();
        scania.brake(1.0);
        efterBroms = scania.getCurrentSpeed();
        assertTrue(innanBroms >= efterBroms);
        // currentSpeed borde ha ökat. KAN vara lika om båda är 0

        speedFactor = scania.speedFactor();
        assertTrue(innanBroms - speedFactor == efterBroms || efterBroms == 0);
        // Helt enkelt det metoden Brake gör. (med eller utan turbo)
    }

    @Test
    void startEngine() {
        assertFalse(saab.isEngineOn());


        assertEquals(0.0, saab.getCurrentSpeed());
        // I Cars c-tor anropas stopEngine, vilket sätter currentSpeed till 0.
        saab.startEngine();
        assertEquals(0.1,saab.getCurrentSpeed());
        saab.gas(1);
        double nySpeed = saab.getCurrentSpeed();
        saab.startEngine();
        assertTrue(nySpeed > saab.getCurrentSpeed());

        // ------------------ VOLVO ---------------------------------------
        assertEquals(0.0, volvo.getCurrentSpeed());
        // I Cars c-tor anropas stopEngine, vilket sätter currentSpeed till 0.
        volvo.startEngine();
        assertEquals(0.1,volvo.getCurrentSpeed());
        volvo.gas(1);
        nySpeed = volvo.getCurrentSpeed();
        volvo.startEngine();
        assertTrue(nySpeed > volvo.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        assertEquals(0.0, saab.getCurrentSpeed());
        // I Cars c-tor anropas stopEngine, vilket sätter currentSpeed till 0.
        saab.stopEngine();
        assertEquals(0.0, saab.getCurrentSpeed());
        // Borde bevara värdet.
        saab.startEngine();
        saab.gas(1);
        double oldSpeed = saab.getCurrentSpeed();
        saab.stopEngine();
        assertTrue(oldSpeed > saab.getCurrentSpeed());

        //----------------- VOLVO -----------------------------------

        assertEquals(0.0, volvo.getCurrentSpeed());
        // I Cars c-tor anropas stopEngine, vilket sätter currentSpeed till 0.
        volvo.stopEngine();
        assertEquals(0.0, volvo.getCurrentSpeed());
        // Borde bevara värdet.

        volvo.startEngine();
        volvo.gas(1);
        oldSpeed = volvo.getCurrentSpeed();
        volvo.stopEngine();
        assertTrue(oldSpeed > volvo.getCurrentSpeed());
    }

    @Test
    void getNrDoors() {
        assertEquals(2,saab.getNrDoors());
        // Det anges i c-tor:n.
        assertEquals(4,volvo.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(125,saab.getEnginePower());
        assertEquals(100,volvo.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        assertEquals(saab.currentSpeed, saab.getCurrentSpeed());
        assertEquals(volvo.currentSpeed, volvo.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.red, saab.getColor());
        // Färgen anges i c-tor för Saab!
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    void setColor() {
        // Färgen anges i c-tor för Saab!
        saab.setColor(Color.black);
        assertEquals(Color.black, saab.getColor());

        //--------------- VOLVO ---------------------

        volvo.setColor(Color.red);
        assertEquals(Color.red, volvo.getColor());
    }

    @Test // GEMENSAM TESTMETOD FÖR ALLT GÄLLANDE DIRECTION!
    void changeDirection(){
        // Notera att alla Car är vända åt NORTH efter skapandet.
/*
        assertTrue(saab.getDirection() == Car.dir.NORTH);
        saab.turnLeft();
        assertTrue(saab.getDirection() == Car.dir.WEST);
        saab.turnLeft();
        assertTrue(saab.getDirection() == Car.dir.SOUTH);
        saab.turnLeft();
        assertTrue(saab.getDirection() == Car.dir.EAST);
        saab.turnLeft();
        assertTrue(saab.getDirection() == Car.dir.NORTH);
        // SVÄNG VÄNSTER

        // SVÄNG HÖGER
        saab.turnRight();
        assertTrue(saab.getDirection() == Car.dir.EAST);
        saab.turnRight();
        assertTrue(saab.getDirection() == Car.dir.SOUTH);
        saab.turnRight();
        assertTrue(saab.getDirection() == Car.dir.WEST);
        saab.turnRight();
        assertTrue(saab.getDirection() == Car.dir.NORTH);
*/
    }

    @Test // GEMENSAM TESTMETOD FÖR ALLT GÄLLANDE POSITION!
    void changePosition(){
        assertTrue(saab.getPosition().x == 150 && saab.getPosition().y == 150);
        assertTrue(saab.getDirection() == Directions.NORTH);
        // Notera att Positionen sätts till [0,0] och direction till NORTH vid skapandet.
        /*
        0,0 1,0 2,0 3,0 4,0      | -2,-2 -1,-2  0,-2  1,-2  2,-2 |
        0,1 1,1 2,1 3,1 4,1      | -2,-1 -1,-1  0,-1  1,-1  2,-1 |
        0,2 1,2 2,2 3,2 4,2 ==>  | -2,0  -1,0   0,0   1,0   2,0  | Visualisering av Positionsplanet.
        0,3 1,3 2,3 3,3 4,3      | -2,1  -1,1   0,1   1,1   2,1  |
        0,4 1,4 2,4 3,4 4,4      | -2,2  -1,2   0,2   1,2   2,2  |
         */
        saab.startEngine(); // Motorn måste vara på (duh) annars är currentSpeed alltid 0.
        saab.currentSpeed = 1; // INTE TILLÅTET men who cares... (Då ändras postionen med jämna steg (int))
        int innanFlyttX;
        int innanFlyttY;

        int efterFlyttX;
        int efterFlyttY;
        int i = 0;
        while(i < 4){ // Vrid och kör 4 gånger.
            innanFlyttX = saab.getPosition().x;
            innanFlyttY = saab.getPosition().y;
            saab.move();
            efterFlyttX = saab.getPosition().x;
            efterFlyttY = saab.getPosition().y;
/*
            if(saab.getDirection() == Car.dir.NORTH)
                assertTrue(innanFlyttX == efterFlyttX && innanFlyttY > efterFlyttY);
            else if(saab.getDirection() == Car.dir.WEST)
                assertTrue(innanFlyttX > efterFlyttX && innanFlyttY == efterFlyttY);
            else if(saab.getDirection() == Car.dir.SOUTH)
                assertTrue(innanFlyttX == efterFlyttX && innanFlyttY < efterFlyttY);
            else if(saab.getDirection() == Car.dir.EAST)
                assertTrue(innanFlyttX < efterFlyttX && innanFlyttY == efterFlyttY);
            else
                System.out.println("FEL!");
            saab.turnLeft();
            i++; // Denna är rolig att glömma!
            */
        }
    }
    @Test
    void getModelName() {
        assertEquals("Volvo240",volvo.getModelName());
        assertEquals("Saab95",saab.getModelName());
    }

    @Test
    void Position() {
        Point innan = saab.getPosition();
        saab.setPosition(new Point(0,0));
        Point efter = saab.getPosition();
        assertTrue(innan != efter);
    }

    @Test
    void testRamp(){
        scania.liftRamp();
        assertEquals(true,scania.isRampUp());
        scania.lowerRamp();
        assertEquals(false,scania.isRampUp());
    }

    @Test
    void testAttach(){
        saab.setPosition(new Point(50,50));
        scania.setPosition(new Point(50,49));
        Saab95 saab2 = new Saab95();
        saab2.setPosition(saab.getPosition());

        scania.lowerRamp(); // Will NOT work if ramp is not lowered!
        scania.attachVehicle(saab);
        scania.attachVehicle(saab2);
        assertTrue(scania.cars.size() == 1);
        System.out.println(scania.cars.size());
        assertEquals(scania.getPosition(),saab.getPosition());
        scania.move();
        assertEquals(scania.getPosition(),saab.getPosition());


        scania.detachVehicle();
        scania.detachVehicle();
        //assertFalse(scania.getPosition() == saab.getPosition());



        // Vehicle has been detached and moved! NORTH

        scania.turnLeft();
        saab.turnLeft();
        saab.setPosition(new Point(50,50));
        scania.setPosition(new Point(49,50));
        scania.lowerRamp();
        scania.attachVehicle(saab);
        assertEquals(scania.getPosition(),saab.getPosition());
        scania.detachVehicle();
        assertFalse(scania.getPosition() == saab.getPosition());
        // WEST



        scania.turnLeft();
        saab.turnLeft();
        saab.setPosition(new Point(50,49));
        scania.setPosition(new Point(50,50));
        scania.lowerRamp();
        scania.attachVehicle(saab);
        assertEquals(scania.getPosition(),saab.getPosition());
        scania.detachVehicle();
        assertFalse(scania.getPosition() == saab.getPosition());
        // SOUTH


        scania.turnLeft();
        saab.turnLeft();
        saab.setPosition(new Point(49,50));
        scania.setPosition(new Point(50,50));
        scania.lowerRamp();
        scania.attachVehicle(saab);
        assertEquals(scania.getPosition(),saab.getPosition());
        scania.detachVehicle();
        assertFalse(scania.getPosition() == saab.getPosition());
        // EAST

        scania.attachVehicle(saab);
        scania.turnRight();
        scania.turnLeft();
        scania.liftRamp();
        scania.startEngine();
        Point innan = scania.getPosition();
        scania.gas(1);
        scania.gas(1);
        scania.gas(1);
        scania.move();
        Point efter = scania.getPosition();
        assertTrue(innan.x == efter.x);


        scania.detachVehicle(volvo);
    }



    @Test
    public void paintTest(){
        Main objects = new Main();
        JFrame frame = new JFrame("Car Simulation Window Here");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,400,400);
        frame.getContentPane().add(objects);
        frame.setVisible(true);
        frame.getContentPane().repaint();
        //paint(frame.getGraphics());
    }
    /*
    public void paint(Graphics g)
    {
        saab.paint(g);
        saab.turnLeft();
        saab.paint(g);
        saab.turnLeft();
        saab.paint(g);
        saab.turnLeft();
        saab.paint(g);

        volvo.paint(g);
        volvo.turnLeft();
        volvo.paint(g);
        volvo.turnLeft();
        volvo.paint(g);
        volvo.turnLeft();
        volvo.paint(g);

        scania.paint(g);
        scania.turnLeft();
        scania.paint(g);
        scania.turnLeft();
        scania.paint(g);
        scania.turnLeft();
        scania.paint(g);


        ferry.paint(g);
    }
       */
    // FERRIES HERE!

    @Test
    public void testFerry(){
        Volvo240 volvo2 = new Volvo240();
        Volvo240 volvo3 = new Volvo240();

        Saab95 saab2 = new Saab95();
        Saab95 saab3 = new Saab95();

        ferry.detachVehicle();

        ferry.attachVehicle(scania);
        ferry.attachVehicle(scania);
        ferry.attachVehicle(ferry);
        ferry.attachVehicle(volvo);
        ferry.attachVehicle(saab);

        /*
        for (int i = 0; i<ferry.cars.size(); i++)
            System.out.println(ferry.cars.get(i));
        */


        ferry.cars.forEach(c -> ferry.detachVehicle(c));
        ferry.cars.forEach(System.out::println);


        ferry.attachVehicle(volvo);
        assertEquals(volvo,ferry.cars.get(0));
        ferry.attachVehicle(saab);
        assertEquals(saab,ferry.cars.get(1));
        ferry.attachVehicle(volvo2);
        assertEquals(volvo2,ferry.cars.get(2));
        ferry.attachVehicle(saab2);
        assertEquals(saab2,ferry.cars.get(3));
        ferry.attachVehicle(volvo3);
        assertEquals(volvo3,ferry.cars.get(4));
        ferry.attachVehicle(saab3);
        assertEquals(saab3,ferry.cars.get(5));


        /*
            Only vehicles able to move/detach are:
                volvo at Index 0
                saab2 at Index 3
            Tests for this:
         */
        ferry.detachVehicle(saab);      // Index 1
        ferry.detachVehicle(volvo2);    // Index 2
        ferry.detachVehicle(volvo3);    // Index 4
        ferry.detachVehicle(saab3);     // Index 5
        // ^ These should remain on the ferry!
        assertNotNull(ferry.cars.get(1));
        assertNotNull(ferry.cars.get(2));
        assertNotNull(ferry.cars.get(4));
        assertNotNull(ferry.cars.get(5));
        // IF passed THEN all these vehicles remained.

        // Let's detach all attached vehicles! (WITHOUT specifying which vehicle to detach)
        ferry.cars.forEach(c -> ferry.detachVehicle());

        ferry.attachVehicle(volvo);
        ferry.attachVehicle(volvo2);
        ferry.attachVehicle(volvo3);
        ferry.attachVehicle(saab);
        ferry.attachVehicle(saab2);
        ferry.attachVehicle(saab3);

        // Let's detach all attached vehicles! (BUT specifying which vehicle to detach)
        ferry.cars.forEach(c -> ferry.detachVehicle(c));

        assertTrue(ferry.canAttach(volvo));
        assertFalse(ferry.canAttach(null));
        assertFalse(ferry.canAttach(ferry));
        assertTrue(ferry.canAttach(scania));
        assertTrue(ferry.canAttach(saab));


    }
}