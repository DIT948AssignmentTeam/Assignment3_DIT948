import becker.robots.icons.Icon;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


/**
 *Extending becker's Icon in order to modify it
 */
public class CustomIcon extends Icon {
    private Image image;

    public CustomIcon(){
        super();

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/icon/customPrizeSmall.png"));
        }catch(IOException e){
            image = null;
        }
    }

    /**
     * returning the image with the size x, y
     */
    public Image getImage(int x, int y, double rotation){
        return image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }
}
