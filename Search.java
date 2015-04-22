import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search
{
    public void Search()
    {
        int hcount = 0;
        int wcount = -1;
        String str;
        String hash;
        Scanner in = new Scanner(System.in);
        Scanner hIn = new Scanner("hashes.txt");
        Scanner wIn = new Scanner("unhashed.txt");
        System.out.println("Enter the hash: ");
        hash = in.nextLine();

        while (hash.compareTo(hIn.nextLine()) != 0)
        {
            hcount++;
        }
        hcount++;
        
        while (wIn.hasNextLine())
        {
            str = wIn.nextLine();
            if (wcount == hcount)
            {  
                System.out.println("Your password is: " + str);
            }
            else
            {
                wcount++;
            }
        }
    }
}
