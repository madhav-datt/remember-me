/**
 *
 * Class with functionalities of Relatives
 *
 * Acquaintance Management System
 * Software Engineering Lab - Assignment 1, Question 2
 *
 * Madhav Datt - 14CS30015
 *
 */

package acquaintance;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;

class Relative extends Acquaintance implements Serializable
{
    String rel_tmp_dob;
    String rel_tmp_lastment;
    Date relative_dob;
    Date relative_lastmet;

    /**
     * RegEx to check course start date validity
     * Considers leap years & accepts all dates in formats: dd.mm.yyyy dd/MM/yyyy dd-mm-yyyy
     */
    static final Pattern VALID_DATE_REGEX = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");

    /**
     * Function to check validity of mobile number based on RegEx
     * Returns true if valid, else false
     */
    static boolean date_validation (String dates)
    {
        Matcher matcher = VALID_DATE_REGEX.matcher (dates);
        if (matcher.find () == false)
        {
            System.out.println ("Invalid date entered!\nPlease enter a valid date in dd/MM/yyyy format");
            return false;
        }

        return true;
    }

    /**
     * Function to create new relatives and input values
     */
    public void create_relative ()
    {
        // Input from console
        Scanner scan = new Scanner (System.in);
        System.out.println ("Enter the following details:");

        System.out.format ("%-35s: ", "Relative's Name");
        acq_name = scan.nextLine ();

        do
        {
            System.out.format ("%-35s: ", "Email Address");
            acq_email = scan.nextLine ();
        }
        while (email_validation (acq_email) == false);

        do
        {
            System.out.format ("%-35s: ", "Mobile Number");
            acq_mobile = scan.nextLine ();
        }
        while (mobile_validation (acq_mobile) == false);

        // Input and validate date of birth
        do
        {
            System.out.format ("%-35s: ", "Date of Birth (dd/MM/yyyy)");
            rel_tmp_dob = scan.nextLine ();
        }
        while (date_validation (rel_tmp_dob) == false);

        DateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        try
        {
            relative_dob = formatter.parse (rel_tmp_dob);
        }
        catch (Exception e)
        {
            System.out.println ("Error: Could not parse date");
            scan.next ();
        }

        // Input and validate date of last meeting
        do
        {
            System.out.format ("%-35s: ", "Date of last meeting (dd/MM/yyyy)");
            rel_tmp_lastment = scan.nextLine ();
        }
        while (date_validation (rel_tmp_lastment) == false);

        try
        {
            relative_lastmet = formatter.parse (rel_tmp_lastment);
        }
        catch (Exception e)
        {
            System.out.println ("Error: Could not parse date");
            scan.next ();
        }
    }

    /**
     * Function to show relative Names
     */
    public void show_relative_name ()
    {
        System.out.println (acq_name);
    }

    /**
     * Function to return acquaintance name as string
     */
    public String give_acq_name ()
    {
        return acq_name;
    }

    /**
     * Function to show relative Details
     */
    public void show_relative ()
    {
        System.out.format ("\n%-20s: %25s\n", "Relative Name", acq_name);
        System.out.format ("%-20s: %25s\n", "Mobile Number", acq_mobile);
        System.out.format ("%-20s: %25s\n", "Email Address", acq_email);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        System.out.format ("%-20s: %25s\n", "Date of Birth", formatter.format (relative_dob));
        System.out.format ("%-20s: %25s\n", "Date of last Meeting", formatter.format (relative_lastmet));
    }
}
