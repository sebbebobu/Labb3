import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    public ArrayList<VehicleImage> VehicleImages = new ArrayList<>(); //, saabImage, scaniaImage;
    // To keep track of a single cars position
    /*
    Point volvoPoint = new Point();
    Point saabPoint = new Point();
    Point scaniaPoint = new Point();
    */
    // TODO: Make this general for all cars
    public void addImage(String vehicleName, Point position){
        System.out.println(VehicleImages.size());
        // Print an error message in case file is not found with a try/catch block
        try {
            String pathName = "src\\pics\\" + vehicleName +".jpg";
            BufferedImage image = ImageIO.read(new File(pathName));
            VehicleImage vImage = new VehicleImage(image, position);
            VehicleImages.add(vImage);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public void updateImages(ArrayList<String> vehicleNames, ArrayList<Point> carPoints){
        VehicleImages.clear();
        for (int i = 0; i <= vehicleNames.size()-1; i++)
            addImage(vehicleNames.get(i), carPoints.get(i));
    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }
    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y,null); // see javadoc for more info on the parameters
        VehicleImages.forEach(VehicleImage -> g.drawImage(VehicleImage.Image, VehicleImage.point.x, VehicleImage.point.y, null));
    }
}