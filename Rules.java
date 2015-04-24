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
 * @4/23/2015
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
     * @pre      the user will choose either 1, or 2 options
     * @post     the possible passwords will be written to unhashed.txt on separate lines
     */
    public static void rulesMain()
    throws FileNotFoundException
    {
        //Initializes Scanner in for requesting the character count of the "randomly generated" strings
        Scanner in = new Scanner(System.in);

        //Initializes PrintWriter out for printing the passwords to unhashed.txt
        PrintWriter out = new PrintWriter("unhashed.txt");

        //Initializes the String for the current date as a potential password option
        String curDate;

        //Requests the user's input for the massive chunk of code below
        userInput();

        //Initializes count for the total count of characters to be used in the not so random random generator
        int count;

        //Initializes check for checking if the user entered an integer for the number of characters 
        boolean check = true;

        //if the user selected two options
        if (input.size() == 2)
        {
            //if the options were in the order of random then date
            if (input.get(0).compareTo("random") == 0 && input.get(1).compareTo("date") == 0)
            {
                //gets the user's character choices
                charChoice();
                System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                while (check)
                {
                    //if user enters an integer, the random generator starts
                    //else the user is asked to enter an int
                    if (in.hasNextInt())
                    {
                        //changes check to false to end the checker while loop
                        check = false;
                        count = in.nextInt();
                        gen("", count, count, chars);
                        
                        //closes user input
                        in.close();
                    }
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }
                }

                //sets curDate to the date in the user specified format
                curDate = date();

                //adds the passwords to unhashed.txt
                for (int i = 0; i < passes.size(); i++)
                {
                    out.println(passes.get(i) + curDate);
                }

                //closes the PrintWriter
                out.close();
            }
            //if the options were in the order of date then random
            else if (input.get(0).compareTo("date") == 0 && input.get(1).compareTo("random") == 0)
            {
                //gets the user's character choices
                charChoice();
                System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                while (check)
                {
                    //if user enters an integer, the random generator starts
                    //else the user is asked to enter an int
                    if (in.hasNextInt())
                    {
                        //changes check to false to end the checker while loop
                        check = false;
                        count = in.nextInt();
                        gen("", count, count, chars); 
                        
                        //closes user input
                        in.close();
                    }
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }
                }
                
                //sets curDate to the date in the user specified format
                curDate = date();

                //adds the passwords to unhashed.txt
                for (int i = 0; i < passes.size(); i++)
                {
                    out.println(curDate + passes.get(i));
                }
                
                //closes the PrintWriter
                out.close();
            }
            //if one of the options was not entered correctly
            else
            {
                System.out.println("One or more of your options entered was not valid.\nPlease try again.");
                rulesMain();
            }
        }
        //if the user entered only one option
        else if (input.size() == 1)
        {
            //if the option was date
            if (input.get(0).compareTo("date") == 0)
            {
                //sets curDate to the current date, and prints it to unhashed.txt
                curDate = date();
                out.println(curDate);
                
                //closes the PrintWriter
                out.close();
            }
            //if the option was random
            else if (input.get(0).compareTo("random") == 0)
            {
                while (check)
                {
                    //gets the user's character choices
                    charChoice();
                    System.out.println("Enter the amount of random characters (cannot be greater than " + chars.size() + "): ");
                    //if user enters an integer, the random generator starts
                    //else the user is asked to enter an int
                    if (in.hasNextInt())
                    {
                        //changes check to false to end the checker while loop
                        check = false;
                        
                        //sets count and generates
                        count = in.nextInt();
                        gen("", count, count, chars); 
                        
                        //closes user input
                        in.close();

                        for (int i = 0; i < passes.size(); i++)
                        {
                            out.println(passes.get(i));
                        }
                        
                        //closes the PrintWriter
                        out.close();
                    }
                    //the user did not enter an integer number
                    else
                    {
                        System.out.println("Please enter an integer number: ");
                    }
                }
            }
            //if one of the options was not entered correctly
            else
            {
                System.out.println("One or more of your options entered was not valid.\nPlease try again.");
                rulesMain();
            }
        }
        //either no values were entered, or too many values were entered
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
     * @post     user input is added to ArrayList input
     */
    private static void userInput()
    {
        //Initializes next for the next input
        String next;

        //initializes close for checking if in has been closed
        boolean close = true;

        //initializes count for counting the user inputs
        int count = 0;

        //resets input to an empty ArrayList
        while (input.size() != 0)
        {
            input.remove(0);
        }

        //initializes in to ask the user for which options they would like to choose
        Scanner in = new Scanner(System.in);
        System.out.println("Please select either one or two of the options from the list\n(separate each option with the enter key).\nIf you use multiple options,enter them in the order they would appear in the password.\nWhen you have finished, type 'Done' (CaSe sEnsItiVe):");
        for (int i = 0; i < options.length; i++)
        {
            System.out.println("\t" + options[i]);
        }

        //if in has not been closed, then the next line is added to ArrayList input
        //otherwise, the while loop ends
        while (close)
        {
            //sets next to the next user input and sets it to lowercase
            next = in.nextLine();
            next.toLowerCase();
            
            //if the user enters done, then in is closed and close is set to false
            if (next.compareTo("done") == 0)
            {
                //user input is closed and close is set to false
                in.close();
                close = false;
            }
            else
            //user did not enter done, thus the option is added to ArrayList input at count and count is increased by one
            {
                input.add(count,next);
                count++;
            }
        }
    }

    /**
     * Generates a list of strings in a not so random order.
     *
     * @pre      The characters entered are individual characters.
     * @post    passes contains all possible variants of the characters from ArrayList chars
     */
    private static void gen(String str, int count, int count2, ArrayList<String> chars)
    {
        //initializes pwd as str, counter as count, and len as count2
        String pwd = str;
        int counter = count;
        int len = count2;
        
        //if the password has reached its required length
        if (counter == 0)
        {
            //ensures the password is of the proper length and adds it to ArrayList passes
            if (pwd.length() == len)
            {   
                passes.add(pwd);
            }
        }
        //if the password has not reached its required length
        else
        {
            //adds a character to the password
            for (int i = 0; i < chars.size(); i++)
            {
                String character = pwd + chars.get(i);
                gen(character,counter-1,len,chars);
            }
        }
    }

    /**
     * Adds the specified characters of a part of the password to an ArrayList.
     *
     * @post    ArrayList chars contains the potential characters of the random password.
     */
    private static void charChoice()
    {
        //initializes close for checking if in has been closed
        boolean close = true;
        
        //initializes next for the next option and scanner to scan for the next option
        String next;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the possible characters (separated by enter)\n(separate each character with the enter key).\nWhen you have finished, type 'Done' (CaSe sEnsItiVe):");
        
        //if in has not been closed, then the next line is added to ArrayList chars
        //otherwise, the while loop ends
        while (close)
        {
            //sets next to the entered option and sets it to lowercase
            next = in.nextLine();
            next.toLowerCase();
            
            //if next is 'done' then in is closed and close is set to false to end the while loop
            if (next.compareTo("done") == 0)
            {
                in.close();
                close = false;
            }
            //if next is not 'done' then it is added to ArrayList chars
            else
            {
                chars.add(next);
            }
        }
        
        //user input is closed
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
        int day = cal.get(cal.DAY_OF_MONTH);
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
