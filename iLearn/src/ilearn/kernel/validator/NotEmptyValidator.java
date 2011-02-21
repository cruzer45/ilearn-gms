package ilearn.kernel.validator;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A class for performing basic validation on text fields. All it does is make
 * sure that they are not null.
 *
 * @author Michael Urban
 */
public class NotEmptyValidator extends AbstractValidator
{

    public NotEmptyValidator(JComponent parent, JTextField c, String message)
    {
        super(parent, c, message);
    }

    public NotEmptyValidator(JComponent parent, JTextArea c, String message)
    {
        super(parent, c, message);
    }

    protected boolean validationCriteria(JComponent c)
    {
        if (((JTextField) c).getText().equals(""))
        {
            return false;
        }
        return true;
    }
}
