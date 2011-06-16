package ilearn.kernel;

import java.io.File;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class ExcelFileFilter extends FileFilter
{

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String extension = ImageFilterUtils.getExtension(f);
        if (extension != null)
        {
            if (extension.equals("xls")
                    || extension.equals("xlsx"))
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
        return "Excel Files";
    }
}
