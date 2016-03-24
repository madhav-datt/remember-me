/**
 *
 * Class with functionalities of Professional Friends
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

class Proffriend extends Acquaintance implements Serializable
{
    String proffriend_common_interest;

    /**
     * Function to create new Professional friend and input values
     */
    public void create_proffriend ()
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

        do
        {
            System.out.format ("%-35s: ", "Common Professional Interest (max 100 chars)");
            proffriend_common_interest = scan.nextLine ();
        }
        while (proffriend_common_interest.length () > 100);
    }

    /**
     * Function to show Professional friend Names
     */
    public void show_proffriend_name ()
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
     * Function to show Professional friend Details
     */
    public void show_proffriend ()
    {
        System.out.format ("\n%-20s: %25s\n", "Professional Friend Name", acq_name);
        System.out.format ("%-20s: %25s\n", "Mobile Number", acq_mobile);
        System.out.format ("%-20s: %25s\n", "Email Address", acq_email);
        System.out.format ("%-20s: %25s\n", "Common Professional Interest", proffriend_common_interest);
    }
}
