/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

/**
 *
 * @author mrogers
 */
public class EncryptionTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String plain = "jdbc:mysql://localhost:3306/iLearn";
        plain = "2099-12-31";
        String encrypted = EncryptionHandler.encrypt(plain);
        System.out.println(plain);
        System.out.println(encrypted);
        //System.out.println(EncryptionHandler.decrypt(encrypted));
        //System.out.println(EncryptionHandler.encryptPassword(plain));
        System.exit(0);
    }
}