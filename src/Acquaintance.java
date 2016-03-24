/**
 * Acquaintance.java
 * Class with Acquaintance common fields and their validations
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

class Acquaintance implements Serializable
{
    protected String acq_name;
    protected String acq_mobile;
    protected String acq_email;

    // RegEx to check email address validity
    protected static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //RegEx to check mobile number validity (assumed 10 digits, starting with 7, 8 or 9)
    protected static final Pattern VALID_MOBILE_REGEX = Pattern.compile("^[789][0-9]{9}$");

    /**
     * Function to check validity of email id based on RegEx
     * Returns true if valid, else false
     */
    protected static boolean email_validation (String email_id)
    {
        Matcher matcher = VALID_EMAIL_REGEX.matcher (email_id);
        if (matcher.find () == false)
        {
            System.out.println ("Invalid email address entered!\nPlease try again");
            return false;
        }

        return true;
    }

    /**
     * Function to check validity of mobile number based on RegEx
     * Returns true if valid, else false
     */
    protected static boolean mobile_validation (String mobile_num)
    {
        Matcher matcher = VALID_MOBILE_REGEX.matcher (mobile_num);
        if (matcher.find () == false)
        {
            System.out.println ("Invalid mobile number entered!\nPlease enter a valid mobile number (10 digits, starting with a 7, 8 or 9)");
            return false;
        }

        return true;
    }
}
