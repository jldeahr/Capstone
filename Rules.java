import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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

    //List of the possible random passwords the user specified
    static ArrayList<String> passes = new ArrayList<String>();

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
    public static void rulesMain()
    throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);

        PrintWriter out = new PrintWriter("unhashed.txt");

        String curDate;

        userInput();

        int count;

        boolean check = true;

        if (input.size() == 2)
        {
            if (input.get(0).compareTo("random") == 0 && input.get(1).compareTo("date") == 0)
            {
                while (check)
                {
                    charChoice();
                    System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                    if (in.hasNextInt())
                    {
                        check = false;
                        count = in.nextInt();
                        gen("", count, count, chars); 
                        in.close();
                    }
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }

                    curDate = date();

                    for (int i = 0; i < passes.size(); i++)
                    {
                        out.println(passes.get(i) + curDate);
                    }
                    out.close();
                }
            }
            else if (input.get(0).compareTo("date") == 0 && input.get(1).compareTo("random") == 0)
            {
                while (check)
                {
                    charChoice();
                    System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                    if (in.hasNextInt())
                    {
                        check = false;
                        count = in.nextInt();
                        gen("", count, count, chars); 
                        in.close();
                    }
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }

                    curDate = date();

                    for (int i = 0; i < passes.size(); i++)
                    {
                        out.println(curDate + passes.get(i));
                    }
                    out.close();
                }
            }
            else
            {
                System.out.println("One or more of your options entered was not valid.\nPlease try again.");
                rulesMain();
            }
        }
        else if (input.size() == 1)
        {
            if (input.get(0).compareTo("date") == 0)
            {
                curDate = date();
                out.println(curDate);
                out.close();
            }
            else if (input.get(0).compareTo("random") == 0)
            {
                while (check)
                {
                    charChoice();
                    System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                    if (in.hasNextInt())
                    {
                        check = false;
                        count = in.nextInt();
                        gen("", count, count, chars); 
                        in.close();

                        for (int i = 0; i < passes.size(); i++)
                        {
                            out.println(passes.get(i));
                        }
                        out.close();
                    }
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }
                }
            }
            else
            {
                System.out.println("One or more of your options entered was not valid.");
                rulesMain();
            }
        }
        else
        {
            System.out.println("You either entered no values or too many values.");
            rulesMain();
        }

    }

    /**
     * The user inputs what the password contains.
     * 
     * @pre      the user will choose either 1 or 2 of the given options
     */
    private static void userInput()
    {
        String next;

        boolean close = true;

        int count = 0;

        while (input.size() != 0)
        {
            System.out.println("removed: " + input.get(0));
            input.remove(0);
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Please select either one or two of the options from the list\n(separate each option with the enter key).\nIf you use multiple options, enter them in the order they would appear in the password.\nWhen you have finished, type 'Done' (CaSe sEnsItiVe):");
        for (int i = 0; i < options.length; i++)
        {
            System.out.println("\t" + options[i]);
        }

        while (close)
        {
            next = in.next();
            next.toLowerCase();
            if (next.compareTo("done") == 0)
            {
                in.close();
                close = false;
                System.out.println(input.size());
            }
            else
            {
                input.add(count,next);
                count++;
            }
        }
    }

    /**
     * Ge}nerates a list of strings in a not so random order.
     *
     * @pre      The characters entered are individual characters.
     * @post    the year is returned as a string
     * @return    sYear  a string of the current year
     */
    private static void gen(String str, int count, int count2, ArrayList<String> chars)
    {
        String pwd = str;
        int counter = count;
        int len = count2;
        if (counter == 0)
        {
            if (pwd.length() == len)
            {   
                passes.add(pwd);
            }
        }
        else
        {
            for (int i = 0; i < chars.size(); i++)
            {
                String character = pwd + chars.get(i);
                gen(character,counter-1,len,chars);
            }
        }

        if (pwd.length() == len)
        {
            System.out.println(pwd);
        }
    }

    /**
     * Adds the specified characters of a part of the password to an ArrayList.
     *
     * @post    ArrayList chars contains the potential characters of the random password.
     */
    private static void charChoice()
    {
        boolean close = true;
        String next;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the possible characters (separated by enter)\n(separate each character with the enter key).\nWhen you have finished, type 'Done' (CaSe sEnsItiVe):");
        while (close)
        {
            next = in.nextLine();
            next.toLowerCase();
            if (next.compareTo("done") == 0)
            {
                in.close();
                close = false;
            }
            else
            {
                chars.add(next);
            }
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
        if ((choice.compareTo(dateOptions[0]) == 0) || (choice.compareTo("1") == 0))
        {
            date = (getDay() + "/" + getMonth() + "/" + getYear());
        }
        else if ((choice.compareTo(dateOptions[1]) == 0) || (choice.compareTo("2") == 0))
        {
            date = (getMonth() + "/" + getDay() + "/" + getYear());
        }
        else if ((choice.compareTo(dateOptions[2]) == 0) || (choice.compareTo("3") == 0))
        {
            date = (getDay() + "-" + getMonth() + "-" + getYear());
        }
        else if ((choice.compareTo(dateOptions[3]) == 0) || (choice.compareTo("4") == 0))
        {
            date = (getMonth() + "-" + getDay() + "-" + getYear());
        }
        if (date == "")
        {
            System.out.println("That option was not valid. Please try again.");
            date();
        }
        else
        {
            return date;
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
