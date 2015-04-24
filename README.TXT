------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Capstone
PURPOSE OF PROJECT: To generate a specific list of passwords and their hashes.
VERSION or DATE: 4/23/2015
DEPENDENCIES: None
HOW TO START THIS PROJECT: Run the main method in the Main class.
AUTHORS: Joe Deahr
USER INSTRUCTIONS:
In order to not take up excessive space on the computer for grading, please only enter
a maximum length of 5, with a maximum character count of 5. The length must also be less than or equal to
the character count.

After each setting change, make sure to type "done" or "Done". Also, follow all the
onscreen directions.

When the program asks for a hash, go to http://www.sha1-online.com/ and enter a password which follows the format/settings you entered.

example input:

    random
    date
    done
    a
    b
    c
    done
    3
    mm-dd-yyyy
    e16dbcf91fda51f27f98a962f24935a9264ad0c7
    
example output:
    password: acb04-23-2015
    

    
This project involves ideas we have discussed this year, including:
    Usage of Class design - generally used in order to design this program
    Usage of Scanner - to read from the console, hashes.txt, and unhashed.txt
    Usage of ArrayLists - for the user entered options, the user entered characters, and the strings generated from the user input characters
    Usage of Arrays - for the date format options and the options for password format
    Reading and writing to files - to write the hashed passwords to hashes.txt and the unhashed passwords to unhashed.txt, and reading from unhashed.txt to generate the hashes to write to hashes.txt
                                   and to search for the given hash in hashes.txt and the password it belongs to in unhashed.txt
    
This project also involves some ideas we skimmed the surface of or did not discuss at all, including:
    Usage of GregorianCalendar - to generate the current year, month, and day for the date option
    Usage of MessageDigest - to generate the hashes of the passwords in unhashed.txt
    Usage of Formatter - to format the newly generated hash which will later be added to hashes.txt