import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
	static ArrayList<String> chars = new ArrayList();
	final static String[] dateOptions = {“dd/mm/yyyy”, “mm/dd/yyyy, “dd-mm-yyyy”, “mm-dd-yyyy”};
	static ArrayList<String> dates = new ArrayList();
	GregorianCalendar cal = new Gregorian Calendar();


    public static void main(String[] args)
    throws FileNotFoundException
    {
        ArrayList<String> input = new ArrayList<String>(userInput());
		for (int i = 0; i < input.size(); i++)
		{
			if (input.get(i).compareTo(“Date”))
			{
				date();
			}
		}
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
        scanner.close();
        return input;
    }
    
    private static String rgen(String str, int count, ArrayList<String> chars)
    {
        if (count == 0)
        {
            return str;
        }
        else
        {
            for (int i = o; i < chars.size(); i++)
            {
                str +=  (String)chars.get(i);
                count--;
                rgen(str,count,chars);
                System.out.println(str);
            }
        }
    }

    private static void charChoice()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the possible characters (separated by enter): ");
        while (in.hasNext())
        {
            chars.add(in.next());
        }
		in.close();
    }

	private static void date()
	{
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < dateOptions.length; i++)
        {
            System.out.println("\t" + (i+1) + ". " + dateOptions[i]);
        }

		if (in.next().compareTo(dateOptions[0]))
		{
			dates.add((String)day() + “/” + (String)month() + “/” + (String)year());
		}
     if (in.next().compareTo(dateOptions[1]))
		{
			dates.add((String)month() + “/” + (String)day() + “/” + (String)year());
		}
		if (in.next().compareTo(dateOptions[2]))
		{
			dates.add((String)day() + “-” + (String)month() + “-” + (String)year());
		}
		if (in.next().compareTo(dateOptions[3]))
		{
			dates.add((String)month() + “-” + (String)day() + “-” + (String)year());
		}
		in.close();
	}
	
	private static String year()
	{
		return (String)cal.get(Calendar.YEAR);
	}

	private static String day()
	{
		return (String)cal.get(Calendar.DAY_OF_MONTH);
	}
	
	private static String month()
	{
		return (String)cal.get(Calendar.Month);
	}
}
