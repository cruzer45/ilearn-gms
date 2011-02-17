/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

/**
 *
 * @author mrogers
 */
import org.apache.commons.codec.binary.Base64;
import java.security.*;

public class EncryptionHandler
{

    public static String encrypt(String clearText)
    {
        return new String(Base64.encodeBase64(clearText.getBytes()));
    }

    public static String decrypt(String encodedText)
    {
        return new String(Base64.decodeBase64(encodedText.getBytes()));
    }

    public static String encryptPassword(String message)
    {
        MessageDigest md;
        String encrypted = "";
        try
        {
            md = MessageDigest.getInstance("SHA-512");

            md.update(message.getBytes());
            byte[] mb = md.digest();
            String out = "";
            for (int i = 0; i < mb.length; i++)
            {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2)
                {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
           // System.out.println(out.length());
            //System.out.println("CRYPTO: " + out);
            encrypted = out;
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("ERROR: " + e.getMessage());
        }
        return encrypted;
    }
}
