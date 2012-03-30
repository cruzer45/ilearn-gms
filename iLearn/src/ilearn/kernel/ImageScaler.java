package ilearn.kernel;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Blob;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author mrogers
 */
public class ImageScaler
{

    static final Logger logger = Logger.getLogger(ImageScaler.class.getName());

    public static File getScaledImage(Blob blob, int height, int width, File output, JLabel label)
    {
        try
        {
            if (height == 0 || width == 0)
            {
                height = 200;
                width = 200;
            }
            else
            {
                height = (int) (height * .95);
                width = (int) (width * .95);
            }
            ImageIcon ii = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            ImageIcon original = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            if (ii.getIconHeight() > height || ii.getIconWidth() > width)
            {
                Image img = ii.getImage();
                try
                {
                    double thumbRatio = (double) width / (double) height;
                    int imageWidth = img.getWidth(null);
                    int imageHeight = img.getHeight(null);
                    double aspectRatio = (double) imageWidth / (double) imageHeight;
                    if (thumbRatio < aspectRatio)
                    {
                        height = (int) (width / aspectRatio);
                    }
                    else
                    {
                        width = (int) (height * aspectRatio);
                    }
                    // Draw the scaled tempImage
                    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics2D = newImage.createGraphics();
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    graphics2D.drawImage(img, 0, 0, width, height, null);
                    ii = new ImageIcon(newImage);
                    label.setIcon(ii);
                }
                catch (Exception ex)
                {
                    String message = "An error occurred while trying to save the image.";
                    logger.log(Level.SEVERE, message, ex);
                }
            }
            //draw original image
            Image img = original.getImage();
            BufferedImage originalImage = new BufferedImage(original.getIconWidth(), original.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = originalImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(img, 0, 0, original.getIconWidth(), original.getIconHeight(), null);
            UUID randomUUID = java.util.UUID.randomUUID();
            output = new File(System.getProperty("java.io.tmpdir") + "/" + randomUUID.toString() + ".PNG");
            ImageIO.write(originalImage, "PNG", output);
        }
        catch (Exception ex)
        {
            String message = "An error occurred while trying to save the image.";
            logger.log(Level.SEVERE, message, ex);
        }
        return output;
    }
}
