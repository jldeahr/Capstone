import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search
{
    /**
     * Searches for the password belonging to the hash the user enters
     * 
     * @pre     unhashed.txt already contains unhashed passwords
     *          hashes.txt already contains hashed passwords
     *          
     * @post    the password is found and returned
     * 
     * @return  pass    the password belonging to the hash the user enters
     *          "Password not found"    if the password is not found
     */
    public String find()
    throws FileNotFoundException
    {
        //initializes the hash and unhashed counters for parsing through
        //hashes.txt and unhashed.txt
        int hCount = 0;
        int uhCount = 0;
        
        //initializes the hash the user enters as String userHash,
        //the hash from parsing hashes.txt as String hash,
        //and the password to be returned as String pass
        String pass;
        String userHash;
        String hash;
        
        //initializes Scanner in to read user input,
        //Scanner hIn to read from hashes.txt,
        //and Scanner wIn to read from unhashed.txt
        Scanner in = new Scanner(System.in);
        Scanner hIn = new Scanner(new File("hashes.txt"));
        Scanner wIn = new Scanner(new File("unhashed.txt"));
        
        //requests user to enter the hash they want to find the password for
        //and sets userHash to that value
        System.out.println("Enter the hash: ");
        userHash = in.nextLine();
        
        //finds the location of the hash the user entered in hashes.txt
        while (hIn.hasNextLine())
        {
            hash = hIn.nextLine();
            if (userHash.compareTo(hash) != 0)
            {
                hCount++;
            }
        }
        
        //finds the location of the password unhashed.txt,
        //for the hash the user entered and returns it
        while (wIn.hasNextLine())
        {
            pass = wIn.nextLine();
            if (uhCount == hCount)
            {  
                pass = "Password: " + pass;
                return pass;
            }
            else
            {
                uhCount++;
            }
        }
        
        //returns if the password is not found for the specified hash
        return "Password not found.";
    }
}
