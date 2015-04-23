import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;
import java.security.NoSuchAlgorithmException;

/**
 * This class runs all the methods from the other clasess which are required to help
 * the user find their lost password of a very specific format.
 * 
 * @Joe Deahr
 * @4/23/2015
 */
public class Main
{
    /**
     * This method runs all the necessary methods from the Search, Algorithm, and Rules
     * classes to generate a password list, a hash list, and return the password.
     * 
     * @pre Search, Rules, and Algorithm classes all work as they are supposed to
     * 
     * @post The unhashed form of the user's password is printed to the console
     */
    public static void main(String[] args)
    throws FileNotFoundException
    {
        //Writes the possible passwords in the format the user specifies to unhashed.txt
        Rules r = new Rules();
        r.rulesMain();
        
        //Writes the hashes for the possible passwords to hashes.txt
        Algorithm a = new Algorithm();
        a.hash();
        
        //Searches for the password of the hash which the user has specified and prints
        //the password to the console
        Search s = new Search();
        String str = s.passFinder();
        System.out.println(str);
    }
}
