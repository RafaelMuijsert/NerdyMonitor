package Utils;

import javax.swing.*;
import java.awt.*;

public class ImageUtils {
    public static ImageIcon getImageIcon(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(
                width,
                height,
                java.awt.Image.SCALE_SMOOTH
        ); // scale it the smooth way
        return new ImageIcon(newImg);  // transform it back
    }
}
