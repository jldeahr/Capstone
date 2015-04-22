import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;
import java.security.NoSuchAlgorithmException;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    public void main(String[] args)
    throws FileNotFoundException
    {
        Rules r = new Rules();
        r.rulesMain();
        
        Algorithm a = new Algorithm();
        a.hash();
        
        Search s = new Search
    }
}
