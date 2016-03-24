/**
 * Persfriend.java
 * Class with functionalities of Personal Friends
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

class Persfriend extends Acquaintance implements Serializable
{
    String persfriend_tmp_met;
    Date persfriend_met;
    String persfriend_context;
    String persfriend_specific_event;

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
     * Function to create new personal friend and input values
     */
    public void create_persfriend ()
    {
        // Input from console
        Scanner scan = new Scanner (System.in);
        System.out.println ("Enter the following details:");

        System.out.format ("%-35s: ", "Personal Friend's Name");
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

        // Input and validate date of meeting
        do
        {
            System.out.format ("%-35s: ", "Date of Meeting (dd/MM/yyyy)");
            persfriend_tmp_met = scan.nextLine ();
        }
        while (date_validation (persfriend_tmp_met) == false);

        DateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        try
        {
            persfriend_met = formatter.parse (persfriend_tmp_met);
        }
        catch (Exception e)
        {
            System.out.println ("Error: Could not parse date");
            scan.next ();
        }

        do
        {
            System.out.format ("%-35s: ", "Context of Meeting (max 100 chars)");
            persfriend_context = scan.nextLine ();
        }
        while (persfriend_context.length () > 100);

        do
        {
            System.out.format ("%-35s: ", "Specific Event (max 100 chars)");
            persfriend_specific_event = scan.nextLine ();
        }
        while (persfriend_specific_event.length () > 100);
    }

    /**
     * Function to show personal friend Names
     */
    public void show_persfriend_name ()
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
     * Function to show personal friend Details
     */
    public void show_persfriend ()
    {
        System.out.format ("\n%-20s: %25s\n", "Personal Friend Name", acq_name);
        System.out.format ("%-20s: %25s\n", "Mobile Number", acq_mobile);
        System.out.format ("%-20s: %25s\n", "Email Address", acq_email);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
        System.out.format ("%-20s: %25s\n", "Date of Meeting", formatter.format (persfriend_met));
        System.out.format ("%-20s: %25s\n", "Context of Meeting", persfriend_context);
        System.out.format ("%-20s: %25s\n", "Specific Event", persfriend_specific_event);
    }
}
