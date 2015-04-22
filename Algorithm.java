import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This class will encrypt the given string into the hash of the given algorithm.
 * It will also include get methods to get the information from the object.
 * 
 * @Joe Deahr
 * @4/13/215
 */
public class Algorithm
{    
    /**
     * Writes newly hashed passwords from unhashed.txt to hashes.txt.
     * 
     * @pre     unhashed.txt already contains unhashed passwords
     * @post    a list of hashes will be written to hashes.txt
     */
    public void hash()
    throws FileNotFoundException
    {
        //initializes the printer for printing to hashes.txt
        PrintWriter out = new PrintWriter("hashes.txt");
        
        //initializes the scanner for scanning from unhashed.txt
        Scanner in = new Scanner(new File("unhashed.txt"));
        
        //initializes the unhashed string
        String uh;
        
        //initializes the hashed string
        String hash;
        
        //checks to see if unhashed.txt has more passwords to hash
        while (in.hasNextLine())
        {
            uh = in.nextLine();
            
            //hashes the password and writes the hash to hashes.txt
            hash = hashPassword(uh);
            out.println(hash);
        }
        
        //closes the hashes.txt PrintWriter
        out.close();
    }

    /**
     * Hashes the given string into a SHA1 hash.
     *
     * @pre        password is a string
     * @post    password will be returned as a hashed string
     * @param    password   the password to be encrypted
     * @return    sha1 as an encrypted string
     */    
    private String hashPassword(String password)
    {
        //initializes the hash as a string
        String sha1 = "";
        try
        {
            //initializes the SHA-1 crypter
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            
            //resets crypt for further use
            crypt.reset();
            
            //updates crypt to use UTF-8 of password
            crypt.update(password.getBytes("UTF-8"));
            
            //finalizes the hash computation
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        //returns the newly hashed password
        return sha1;
    }

    /**
     * Changes the byte value to a hex value
     *
     * @pre        hash is an array
     * @post    result will be returned as a string
     * @param    hash   the byte value to be changed to hex
     * @return    result as a hex string
     */
    private String byteToHex(final byte[] hash)
    {
        //initializes formatter 
        Formatter formatter = new Formatter();
        
        //writes the formatted string to its destination in hash
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        
        //initializes result as the formatter as a string
        String result = formatter.toString();
        
        //closes the formatter
        formatter.close();
        
        //returns result as a string
        return result;
    }
}
