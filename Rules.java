import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * This class will allow the user to define the specific rules of the password list.
 * 
 * @Joe Deahr
 * @4/13/2015
 */
public class Rules
{
    final static String[] options = {"Random", "Date", "Password Length"};

    public static void main(String[] args)
    throws FileNotFoundException
    {
        userInput();
    }

    /**
     * The user inputs what the password contains.
     *
     * @post    postconditions for the method
     *            (what the method guarantees upon completion)
     * @param    y    description of parameter y
     * @return    description of the return value
     */
    @SuppressWarnings("unchecked")
    private static ArrayList<String> userInput()
    {
        ArrayList<String> input = new ArrayList();

        String next;

        Scanner in = new Scanner(System.in);
        System.out.println("Please select one of the options from the list:");
        for (int i = 0; i < options.length; i++)
        {
            System.out.println("\t" + (i+1) + ". " + options[i]);
        }

        if (in.hasNext())
        {
            for (int i = 0; i < options.length; i++)
            {
                next = in.next();
                {
                    input.add(next);
                }
            }
        }
        
        return input;
    }

}