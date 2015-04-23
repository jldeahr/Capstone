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
    public String passFinder()
    throws FileNotFoundException
    {
        //initializes the count of the location of the hash in hashes.txt as an int
        int hCount = hashSearch();
        
        //initializes the unhashed counter for parsing through unhashed.txt
        int uhCount = 0;
        
        //initializes the password to be returned as String pass
        String pass;

        //initializes Scanner wIn to read from unhashed.txt
        Scanner wIn = new Scanner(new File("unhashed.txt"));
        
        //finds the location of the password in unhashed.txt,
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
    
    public int hashSearch()
    throws FileNotFoundException
    {
        //initializes the hash counter for parsing through hashes.txt
        int hCount = 0;
        
        //initializes the hash from parsing hashes.txt as String hash
        //and the hash the user is to enter as String userHash
        String userHash;
        String hash;
        
        //initializes Scanner in to read user input,
        //Scanner hIn to read from hashes.txt,
        Scanner in = new Scanner(System.in);
        Scanner hIn = new Scanner(new File("hashes.txt"));
        
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
            else
            {
                return hCount;
            }
        }
        return hCount;
    }
}
