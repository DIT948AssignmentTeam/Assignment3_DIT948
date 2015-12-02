import becker.robots.icons.Icon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Shafiq_Saloum on 12/2/2015.
 */
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


