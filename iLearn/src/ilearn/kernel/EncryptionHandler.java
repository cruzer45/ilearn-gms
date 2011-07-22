package ilearn.kernel;

/**
 *
 * @author mrogers
 */
import org.apache.commons.codec.binary.Base64;
import java.security.*;

public class EncryptionHandler
{

    /**
     * This function encrypts the given string with the Base64 algorithm.
     * @param clearText to be encoded.
     * @return the Base64 encoded text.
     */
    public static String encrypt(String clearText)
    {
        return new String(Base64.encodeBase64(clearText.getBytes()));
    }

    /**
     * This function decrypts text encoded using the Base64 algorithm.
     * @param encodedText
     * @return the plain decrypted text.
     */
    public static String decrypt(String encodedText)
    {
        return new String(Base64.decodeBase64(encodedText.getBytes()));
    }

    /**
     * This function encrypts a plain text string with a SHA 512 hash.
     * @param message
     * @return the SHA-512 has of the given string.
     */
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
