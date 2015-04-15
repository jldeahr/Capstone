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
    //List of the possible options for the user to choose from for parts of the password
    final static String[] options = {"Date", "Random"};
    
    //List of the options the user has chosen for parts of the password
    static ArrayList<String> input = new ArrayList<String>();
    
    //List of the possible characters to be used in the random password
    static ArrayList<String> chars = new ArrayList<String>();
    
    //List of the possible formats for the user to choose from for the date option
    final static String[] dateOptions = {"dd/mm/yyyy", "mm/dd/yyyy", "dd-mm-yyyy", "mm-dd-yyyy"};
    
    //The calendar used to find the current day, month, and year
    static GregorianCalendar cal = new GregorianCalendar();

    /**
    * The main method of the Rules class.
    * Runs the necessary methods to write the possible passwords to a file.
    * 
    * @pre      the user will choose either 0, 1, or 2 options
    */
    public static void main(String[] args)
    throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        
        String curDate;

        userInput();

        for (int i = 0; i < input.size(); i++)
        {
            if (input.get(i).compareTo("Date") == 0)
            {
                curDate = date();
            }
            else if (input.get(i).compareTo("Random") == 0)
            {
                System.out.println("Enter the amount of random characters: ");
                if (in.hasNextInt())
                {
                    rgen("", in.nextInt(), chars); 
                    in.close();
                }
                else
                {
                    System.out.println("Please enter an integer number: ");
                }
            }
        }
    }

    /**
    * The user inputs what the password contains.
    * 
    * @pre      the user will choose either 0, 1, or 2 options
    */
    private static void userInput()
    {
        String next;

        int close = 1;

        Scanner in = new Scanner(System.in);
        System.out.println("Please select one to three of the options from the list\n(separate each option with the enter key).\nWhen you have finished, type Done (CaSe sEnsItiVe):");
        for (int i = 0; i < options.length; i++)
        {
            System.out.println("\t" + options[i]);
        }

        for (int i = 0; i < options.length; i++)
        {
            if (close != 0)
            {
                next = in.next();
                if (next.compareTo("Done") != 0)
                {
                    input.add(next);
                }
                else
                {
                    in.close();
                    close = 0;
                }
            }
        }

    }

    /**
    * Calculates the current year.
    *
    * @pre      The year in the hash is the current year.
    * @post    the year is returned as a string
    * @return    sYear  a string of the current year
    */
    private static String rgen(String str, int count, ArrayList<String> chars)
    {
        if (count == 0)
        {
            return str;
        }
        else
        {
            for (int i = 0; i < chars.size(); i++)
            {
                str +=  (String)chars.get(i);
                count--;
                rgen(str,count,chars);
                System.out.println(str);
            }
        }
        return str;
    }

    /**
    * Adds the specified characters of a part of the password to an ArrayList.
    *
    * @post    ArrayList chars contains the potential characters of the random password.
    */
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

    /**
    * Uses the day, month, and year to build the date to user's specifications.
    *
    * @pre      The format of the date is one of the four options
    *           (mm/dd/yyyy , dd/mm/yyyy , mm-dd-yyyy , dd-mm-yyyy)
    * @post    the current date is constructed in the format which the user specified
    * @return   the current date in the format the user specified
    */
    private static String date()
    {
        Scanner in = new Scanner(System.in);
        
        String date = "";
        
        System.out.println("Please enter which date option you would like to use (CaSe sEnsItiVe):");
        for (int i = 0; i < dateOptions.length; i++)
        {
            System.out.println("\t" + (i+1) + ". " + dateOptions[i]);
        }
        String choice = in.next();
        if (choice.compareTo(dateOptions[0]) == 0)
        {
            date = ((String)getDay() + "/" + (String)getMonth() + "/" + (String)getYear());
        }
        else if (choice.compareTo(dateOptions[1]) == 0)
        {
            date = ((String)getMonth() + "/" + (String)getDay() + "/" + (String)getYear());
        }
        else if (choice.compareTo(dateOptions[2]) == 0)
        {
            date = ((String)getDay() + "-" + (String)getMonth() + "-" + (String)getYear());
        }
        else if (choice.compareTo(dateOptions[3]) == 0)
        {
            date = ((String)getMonth() + "-" + (String)getDay() + "-" + (String)getYear());
        }
        if (date == "")
        {
            System.out.println("That option was not valid. Please try again.");
            date();
        }
        else
        {
            System.out.println("The date is: " + date);
        }
        return date;
    }

    /**
    * Calculates the current day.
    *
    * @pre      The day in the hash is the current day
    * @post    the day is returned as an integer, as a string
    * @return    sDay  a string of the current day
    */
    private static String getDay()
    {
        int day = cal.get(cal.DAY_OF_MONTH) + 1;
        String sDay;
        if (day > 9)
        {
            sDay = Integer.toString(day);
        }
        else
        {
            sDay = "0" + Integer.toString(day);
        }
        return sDay;
    }

    /**
    * Calculates the current month.
    *
    * @pre      The month in the hash is the current month
    * @post    the month is returned as an integer, as a string
    * @return    sMonth  a string of the current month
    */
    private static String getMonth()
    {
        int month = cal.get(cal.MONTH) + 1;
        String sMonth = "0" + Integer.toString(month);
        return sMonth;
    }

    /**
    * Calculates the current year.
    *
    * @pre      The year in the hash is the current year
    * @post    the year is returned as a string
    * @return    sYear  a string of the current year
    */
    private static String getYear()
    {
        int year = cal.get(cal.YEAR);
        String sYear = Integer.toString(year);
        return sYear;
    }
}
