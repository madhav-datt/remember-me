/**
 * Casual.java
 * Class with functionalities of Casual Acquaintances
 *
 * Copyright (C)   2016    Madhav Datt
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 */

package acquaintance;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;

class Casual extends Acquaintance implements Serializable
{
    String casual_tmp_met;
    Date casual_met;
    String casual_where_met;
    String casual_circumstance;
    String casual_other_info;

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
     * Function to create new casual acquaintance and input values
     */
    public void create_casual ()
    {
        // Input from console
        Scanner scan = new Scanner (System.in);
        System.out.println ("Enter the following details:");

        System.out.format ("%-45s: ", "Personal Friend's Name");
        acq_name = scan.nextLine ();

        do
        {
            System.out.format ("%-45s: ", "Email Address");
            acq_email = scan.nextLine ();
        }
        while (email_validation (acq_email) == false);

        do
        {
            System.out.format ("%-45s: ", "Mobile Number");
            acq_mobile = scan.nextLine ();
        }
        while (mobile_validation (acq_mobile) == false);

        // Input and validate date of meeting
        do
        {
            System.out.format ("%-45s: ", "Date of Meeting (dd/MM/yyyy)");
            casual_tmp_met = scan.nextLine ();
        }
        while (date_validation (casual_tmp_met) == false);

        DateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        try
        {
            casual_met = formatter.parse (casual_tmp_met);
        }
        catch (Exception e)
        {
            System.out.println ("Error: Could not parse date");
            scan.next ();
        }

        do
        {
            System.out.format ("%-45s: ", "Location of Meeting (max 100 chars)");
            casual_where_met = scan.nextLine ();
        }
        while (casual_where_met.length () > 100);

        do
        {
            System.out.format ("%-45s: ", "Circumstance of Meeting (max 100 chars)");
            casual_circumstance = scan.nextLine ();
        }
        while (casual_circumstance.length () > 100);

        do
        {
            System.out.format ("%-45s: ", "Other Information (max 100 chars)");
            casual_other_info = scan.nextLine ();
        }
        while (casual_other_info.length () > 100);
    }

    /**
     * Function to show casual acquaintance Names
     */
    public void show_casual_name ()
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
     * Function to show casual acquaintance Details
     */
    public void show_casual ()
    {
        System.out.format ("\n%-30s: %25s\n", "Casual Acquaintance Name", acq_name);
        System.out.format ("%-30s: %25s\n", "Mobile Number", acq_mobile);
        System.out.format ("%-30s: %25s\n", "Email Address", acq_email);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        System.out.format ("%-30s: %25s\n", "Date of Meeting", formatter.format (casual_met));
        System.out.format ("%-30s: %25s\n", "Location of Meeting", casual_where_met);
        System.out.format ("%-30s: %25s\n", "Circumstance of Meeting", casual_circumstance);
        System.out.format ("%-30s: %25s\n", "Other Information", casual_other_info);
    }
}
