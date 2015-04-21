import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search
{
    public void Search()
    {
        int hcount = 0;
        int wcount = -1;
        int count = 0;
        String str;
        String hash;
        Scanner in = new Scanner(System.in);
        Scanner hIn = new Scanner("hashes.txt");
        Scanner wIn = new Scanner("words.txt");
        System.out.println("Enter the hash: ");
        hash = in.nextLine();
        while (hIn.hasNextLine() && wcount == -1)
        {
            str = hIn.nextLine();
            if (str.compareTo(hash) == 0)
            {
                wcount = hcount;
            }
            else
            {
                wcount++;
            }
        }
        
        while (wIn.hasNextLine())
        {
            str = wIn.nextLine();
            if (count == wcount)
            {
                System.out.println("Your result: " + str);
            }
            else
            {
                count++;
            }
        }
    }
    
}
