package ilearn.kernel;

import ilearn.subject.Subject;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class AddTCCWeightings
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Creating connection");
            Environment.createConnection();
            String sql = "INSERT INTO `Subject_Weightings` (`subID`, `assmentTypeID`, `weighting`) VALUES (?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            System.out.println("Getting subject listing");
            //get list of 4th form subjects
            ArrayList<HashMap> subjects = Subject.getSubjectDetailList();
            System.out.println("Testing subjects");
            for (HashMap subject : subjects)
            {
                if (subject.get("subCode").toString().startsWith("4"))
                {
                    if (subject.get("subCode").toString().contains("PE")
                            || subject.get("subCode").toString().contains("COUN"))
                    {
                        continue;
                    }
                    System.out.println("Adding Weighting for: " + subject.get("subCode").toString());
                    prep.setString(1, subject.get("subID").toString());
                    prep.setInt(2, 13);
                    prep.setInt(3, 65);
                    prep.addBatch();
                    prep.setString(1, subject.get("subID").toString());
                    prep.setInt(2, 5);
                    prep.setInt(3, 35);
                    prep.addBatch();
                }
            }
            System.out.println("Saving changes");
            prep.executeBatch();
            System.out.println("Process Finished");
            System.exit(0);
        }
        catch (Exception ex)
        {
            Logger.getLogger(AddTCCWeightings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
