import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

/**
 * This class will encrypt the given string into the hash of the given algorithm.
 * It will also include get methods to get the information from the object.
 * 
 * @Joe Deahr
 * @4/13/215
 */
public class Algorithm
{
    //The string to be hashed
    String h;
    
    /**
     * Initializes String h as the string to be hashed.
     * When other algorithms are implemented, there will be another parameter for the algorithm
     */
    public Algorithm(String toHash)
    {
        // the string to be hashed
        h = toHash;
    }

    /**
     * Encrypts the string into a SHA1 hash.
     *
     * @pre        password is a string
     * @post    password will be returned as a hashed string
     * @param    password   the password to be encrypted
     * @return    sha1 as an encrypted string
     */    
    private String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
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
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * Gets the new hash value of the string
     * 
     * @post    the newly hashed password will be returned as a string
     * @return  hash    the hash of the encrypted string
     */
    private String getHash()
    {
        return encryptPassword(h);
    }
}
