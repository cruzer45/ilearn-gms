package ilearn.kernel;

import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter
{

    //Accept all directories and all gif, jpg or png files.
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String extension = ImageFilterUtils.getExtension(f);
        if (extension != null)
        {
            if (extension.equals(ImageFilterUtils.gif)
                    || extension.equals(ImageFilterUtils.jpeg)
                    || extension.equals(ImageFilterUtils.jpg)
                    || extension.equals(ImageFilterUtils.png))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    //The description of this filter
    public String getDescription()
    {
        return "Images";
    }
}
