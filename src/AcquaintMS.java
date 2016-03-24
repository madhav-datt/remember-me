/**
 *
 * Acquaintance Management System
 * Software Engineering Lab - Assignment 1, Question 2
 *
 * Contains main method
 * Displays welcome screen and lets user select from various functionalities
 *
 * write_to_file - writes objects to file to save session data
 * read_from_file - reads objects from file to start from last session
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

public class AcquaintMS
{
    // Save location file names
    static final String RELATIVE_SAVE_FILE = "relative_file.ser";
    static final String PERSFRIEND_SAVE_FILE = "persfriend_file.ser";
    static final String PROFFRIEND_SAVE_FILE = "proffriend_file.ser";
    static final String CASUAL_SAVE_FILE = "casual_file.ser";

    // Array lists for each category of acquaintances
    static ArrayList<Relative> relative_list;
    static ArrayList<Persfriend> persfriend_list;
    static ArrayList<Proffriend> proffriend_list;
    static ArrayList<Casual> casual_list;

    public static void main (String[] args) throws java.io.IOException
    {
        // Input from console
        Scanner scan = new Scanner (System.in);

        relative_list = new ArrayList<Relative> ();
        persfriend_list = new ArrayList<Persfriend> ();
        proffriend_list = new ArrayList<Proffriend> ();
        casual_list = new ArrayList<Casual> ();

        // Welcome screen menu
        while (true)
        {
            System.out.println ("\n*********************************************************");
            System.out.println ("             Acquaintance Management System              ");
            System.out.println ("*********************************************************");
            System.out.println ("Press 1: Load saved data from file");
            System.out.println ("Press 2: Create a new Acquaintance");
            System.out.println ("Press 3: Display all Acquaintance Names");
            System.out.println ("Press 4: Display all Acquaintance Details");
            System.out.println ("Press 5: Display all Acquaintances of specific category");
            System.out.println ("Press 6: Search for Acquaintance");
            System.out.println ("Press 7: Delete Acquaintance");
            System.out.println ("Press 0: Exit");
            System.out.println ("*********************************************************\n");

            int choice = 0;
            boolean flag;
            do
            {
                flag = false;
                try
                {
                    choice = scan.nextInt ();
                }
                catch (Exception e)
                {
                    flag = true;
                    System.out.println ("Incorrect option selected\nTry again:");
                    scan.next ();
                }
            }
            while (flag == true);

            if (choice == 0)
                break;

            switch (choice)
            {
                // Load saved course data from files
                case 1:
                    try
                    {
                        relative_read_from_file (RELATIVE_SAVE_FILE);
                        persfriend_read_from_file (PERSFRIEND_SAVE_FILE);
                        proffriend_read_from_file (PROFFRIEND_SAVE_FILE);
                        casual_read_from_file (CASUAL_SAVE_FILE);
                        System.out.println ("Loading from file successful");
                    }
                    catch (Exception e)
                    { // No action needed
                    }
                    break;

                // Add new acquaintance
                case 2:
                    System.out.println ("Select type of Acquaintance");
                    System.out.println ("Press 1: Relative\nPress 2: Personal Friend\nPress 3: Professional Friend\nPress 4: Casual Friend");
                    int category = 0;
                    do
                    {
                        flag = false;
                        try
                        {
                            category = scan.nextInt ();
                        }
                        catch (Exception e)
                        {
                            flag = true;
                            System.out.println ("Incorrect option selected\nTry again:");
                            scan.next ();
                        }
                    }
                    while (flag == true);

                    // Add type of Acquaintance
                    if (category == 1)
                    {
                        Relative tmp_relative = new Relative ();
                        tmp_relative.create_relative ();
                        relative_list.add (tmp_relative);
                        System.out.println ("Acquaintance adding successful\n");
                    }
                    else if (category == 2)
                    {
                        Persfriend tmp_persfriend = new Persfriend ();
                        tmp_persfriend.create_persfriend ();
                        persfriend_list.add (tmp_persfriend);
                        System.out.println ("Acquaintance adding successful\n");
                    }
                    else if (category == 3)
                    {
                        Proffriend tmp_proffriend = new Proffriend ();
                        tmp_proffriend.create_proffriend ();
                        proffriend_list.add (tmp_proffriend);
                        System.out.println ("Acquaintance adding successful\n");
                    }
                    else if (category == 4)
                    {
                        Casual tmp_casual = new Casual ();
                        tmp_casual.create_casual ();
                        casual_list.add (tmp_casual);
                        System.out.println ("Acquaintance adding successful\n");
                    }
                    else
                    {
                        System.out.println ("Incorrect choice entered\n");
                    }

                    // Write data to file
                    relative_write_to_file (RELATIVE_SAVE_FILE);
                    persfriend_write_to_file (PERSFRIEND_SAVE_FILE);
                    proffriend_write_to_file (PROFFRIEND_SAVE_FILE);
                    casual_write_to_file (CASUAL_SAVE_FILE);
                    break;

                // Show all acquaintance names
                case 3:
                    // Handle no courses added
                    if (relative_list.size () == 0 && persfriend_list.size () == 0 && proffriend_list.size () == 0 && casual_list.size () == 0)
                    {
                        System.out.println ("No Acquaintances found\n");
                        break;
                    }

                    // Print all acquaintances
                    for (int i = 0; i < relative_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1) + ": ");
                        relative_list.get (i).show_relative_name ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < persfriend_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1) + ": ");
                        persfriend_list.get (i).show_persfriend_name ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < proffriend_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1) + ": ");
                        proffriend_list.get (i).show_proffriend_name ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < casual_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1) + ": ");
                        casual_list.get (i).show_casual_name ();
                    }
                    System.out.println ("");
                    break;

                // Show all acquaintance details
                case 4:
                    // Handle no courses added
                    if (relative_list.size () == 0 && persfriend_list.size () == 0 && proffriend_list.size () == 0 && casual_list.size () == 0)
                    {
                        System.out.println ("No Acquaintances found\n");
                        break;
                    }

                    for (int i = 0; i < relative_list.size (); i++)
                    {
                        System.out.println ("\n\nRelative Number: " + Integer.toString (i + 1));
                        relative_list.get (i).show_relative ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < persfriend_list.size (); i++)
                    {
                        System.out.println ("\n\nPersonal Friend Number: " + Integer.toString (i + 1));
                        persfriend_list.get (i).show_persfriend ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < proffriend_list.size (); i++)
                    {
                        System.out.println ("\n\nProfessional Friend Number: " + Integer.toString (i + 1));
                        proffriend_list.get (i).show_proffriend ();
                    }
                    System.out.println ("");

                    for (int i = 0; i < casual_list.size (); i++)
                    {
                        System.out.println ("\n\nCasual Acquaintance Number: " + Integer.toString (i + 1));
                        casual_list.get (i).show_casual ();
                    }
                    System.out.println ("");
                    break;

                // Show Acquaintance of specific category
                case 5:
                    System.out.println ("Select type of Acquaintance");
                    System.out.println ("Press 1: Relative\nPress 2: Personal Friend\nPress 3: Professional Friend\nPress 4: Casual Friend");
                    category = 0;
                    do
                    {
                        flag = false;
                        try
                        {
                            category = scan.nextInt ();
                        }
                        catch (Exception e)
                        {
                            flag = true;
                            System.out.println ("Incorrect option selected\nTry again:");
                            scan.next ();
                        }
                    }
                    while (flag == true);

                    // Print category specific acquaintances
                    if (category == 1)
                    {
                        // Handle no courses added
                        if (relative_list.size () == 0)
                        {
                            System.out.println ("No Acquaintances found\n");
                            break;
                        }

                        for (int i = 0; i < relative_list.size (); i++)
                        {
                            System.out.println ("\n\nRelative Number: " + Integer.toString (i + 1));
                            relative_list.get (i).show_relative ();
                        }
                        System.out.println ("");
                    }
                    else if (category == 2)
                    {
                        // Handle no courses added
                        if (persfriend_list.size () == 0)
                        {
                            System.out.println ("No Acquaintances found\n");
                            break;
                        }

                        for (int i = 0; i < persfriend_list.size (); i++)
                        {
                            System.out.println ("\n\nPersonal Friend Number: " + Integer.toString (i + 1));
                            persfriend_list.get (i).show_persfriend ();
                        }
                        System.out.println ("");
                    }
                    else if (category == 3)
                    {
                        // Handle no courses added
                        if (proffriend_list.size () == 0)
                        {
                            System.out.println ("No Acquaintances found\n");
                            break;
                        }

                        for (int i = 0; i < proffriend_list.size (); i++)
                        {
                            System.out.println ("\n\nProfessional Friend Number: " + Integer.toString (i + 1));
                            proffriend_list.get (i).show_proffriend ();
                        }
                        System.out.println ("");
                    }
                    else if (category == 4)
                    {
                        // Handle no courses added
                        if (casual_list.size () == 0)
                        {
                            System.out.println ("No Acquaintances found\n");
                            break;
                        }

                        for (int i = 0; i < casual_list.size (); i++)
                        {
                            System.out.println ("\n\nCasual Acquaintance Number: " + Integer.toString (i + 1));
                            casual_list.get (i).show_casual ();
                        }
                        System.out.println ("");
                    }
                    else
                    {
                        System.out.println ("Incorrect choice entered\n");
                    }
                    break;

                // Search for acquaintance using given name
                case 6:
                    System.out.println ("Enter name of person to search for");
                    scan.nextLine ();
                    String search_name = scan.nextLine ();
                    System.out.println ("\nMacthes found:");
                    for (int i = 0; i < relative_list.size (); i++)
                    {
                        // Case insensitive comparison
                        if (Objects.equals (relative_list.get (i).give_acq_name ().toLowerCase (), search_name.toLowerCase ()) == true)
                            relative_list.get (i).show_relative ();
                    }

                    for (int i = 0; i < persfriend_list.size (); i++)
                    {
                        // Case insensitive comparison
                        if (Objects.equals (persfriend_list.get (i).give_acq_name ().toLowerCase (), search_name.toLowerCase ()) == true)
                            persfriend_list.get (i).show_persfriend ();
                    }

                    for (int i = 0; i < proffriend_list.size (); i++)
                    {
                        // Case insensitive comparison
                        if (Objects.equals (proffriend_list.get (i).give_acq_name ().toLowerCase (), search_name.toLowerCase ()) == true)
                            proffriend_list.get (i).show_proffriend ();
                    }

                    for (int i = 0; i < casual_list.size (); i++)
                    {
                        // Case insensitive comparison
                        if (Objects.equals (casual_list.get (i).give_acq_name ().toLowerCase (), search_name.toLowerCase ()) == true)
                            casual_list.get (i).show_casual ();
                    }
                    break;

                // Delete acquaintance
                case 7:
                	System.out.println ("Select Acquaintance number to delete");
                    // Handle no courses added
                    if (relative_list.size () == 0 && persfriend_list.size () == 0 && proffriend_list.size () == 0 && casual_list.size () == 0)
                    {
                        System.out.println ("No Acquaintances found\n");
                        break;
                    }

                    // Print all acquaintances
                    for (int i = 0; i < relative_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1) + ": ");
                        relative_list.get (i).show_relative_name ();
                    }

                    for (int i = 0; i < persfriend_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1 + relative_list.size ()) + ": ");
                        persfriend_list.get (i).show_persfriend_name ();
                    }

                    for (int i = 0; i < proffriend_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1 + relative_list.size () + persfriend_list.size ()) + ": ");
                        proffriend_list.get (i).show_proffriend_name ();
                    }

                    for (int i = 0; i < casual_list.size (); i++)
                    {
                        System.out.print (Integer.toString (i + 1 + relative_list.size () + persfriend_list.size () + proffriend_list.size ()) + ": ");
                        casual_list.get (i).show_casual_name ();
                    }

                    // Input index for acquaintance
                    category = 0;
                    do
                    {
                        flag = false;
                        try
                        {
                            category = scan.nextInt ();
                        }
                        catch (Exception e)
                        {
                            flag = true;
                            System.out.println ("Incorrect option selected\nTry again:");
                            scan.next ();
                        }
                    }
                    while (flag == true);

                    // Delete acquaintance
                    if (category - 1 < relative_list.size ())
                    {
                        relative_list.remove (category - 1);
                        System.out.println ("Deletion successful\n");
                    }
                    else if (category - 1 < relative_list.size () + persfriend_list.size ())
                    {
                        persfriend_list.remove (category - 1 - relative_list.size ());
                        System.out.println ("Deletion successful\n");
                    }
                    else if (category - 1 < relative_list.size () + persfriend_list.size () + proffriend_list.size ())
                    {
                        proffriend_list.remove (category - 1 - relative_list.size () - persfriend_list.size ());
                        System.out.println ("Deletion successful\n");
                    }
                    else if (category - 1 < relative_list.size () + persfriend_list.size () + proffriend_list.size () + casual_list.size ())
                    {
                        casual_list.remove (category - 1 - relative_list.size () - persfriend_list.size () - proffriend_list.size ());
                        System.out.println ("Deletion successful\n");
                    }
                    else
                    {
                        System.out.println ("Incorrect option selected\n");
                    }

                    // Write data to file
                    relative_write_to_file (RELATIVE_SAVE_FILE);
                    persfriend_write_to_file (PERSFRIEND_SAVE_FILE);
                    proffriend_write_to_file (PROFFRIEND_SAVE_FILE);
                    casual_write_to_file (CASUAL_SAVE_FILE);
                    break;
            }
        }
    }

    /**
     * Function to read relative course details from pre-saved file
     * Reads from text file SAVE_FILE from same directory
     */
    @SuppressWarnings("unchecked")
    static public void relative_read_from_file (String file_name) throws java.io.IOException
    {
        try
        {
            File save_file = new File(file_name);
            FileInputStream inputstream_file = new FileInputStream (file_name);
            ObjectInputStream inputstream_object = null;

            // File is not empty
            if (save_file.length () >= 10)
            {
                inputstream_object = new ObjectInputStream (inputstream_file);
                relative_list = (ArrayList<Relative>) inputstream_object.readObject();
            }

            if(inputstream_object != null)
                inputstream_object.close();
            inputstream_file.close();
        }
        catch (Exception e)
        {
            System.out.println ("Could not read saved data\nFile might not exist\n");
        }
    }

    /**
     * Function to write relative course details to file
     * Saves objects in text file SAVE_FILE from same directory
     */
    static public void relative_write_to_file (String file_name) throws java.io.IOException
    {
        try
        {
            // Create new file. Overwrite if necessary
            FileOutputStream outputstream_file = new FileOutputStream (file_name);
            ObjectOutputStream outputstream_object = new ObjectOutputStream (outputstream_file);

            outputstream_object.writeObject (relative_list);
            outputstream_object.close ();
            outputstream_file.close ();
        }
        catch (Exception e)
        {
            System.out.println ("Could not save data\n");
        }
    }

    /**
     * Function to read persfriend course details from pre-saved file
     * Reads from text file SAVE_FILE from same directory
     */
    @SuppressWarnings("unchecked")
    static public void persfriend_read_from_file (String file_name) throws java.io.IOException
    {
        try
        {
            File save_file = new File(file_name);
            FileInputStream inputstream_file = new FileInputStream (file_name);
            ObjectInputStream inputstream_object = null;

            // File is not empty
            if (save_file.length () >= 10)
            {
                inputstream_object = new ObjectInputStream (inputstream_file);
                persfriend_list = (ArrayList<Persfriend>) inputstream_object.readObject();
            }

            if(inputstream_object != null)
                inputstream_object.close();
            inputstream_file.close();
        }
        catch (Exception e)
        {
            System.out.println ("Could not read saved data\nFile might not exist\n");
        }
    }

    /**
     * Function to write persfriend course details to file
     * Saves objects in text file SAVE_FILE from same directory
     */
    static public void persfriend_write_to_file (String file_name) throws java.io.IOException
    {
        try
        {
            // Create new file. Overwrite if necessary
            FileOutputStream outputstream_file = new FileOutputStream (file_name);
            ObjectOutputStream outputstream_object = new ObjectOutputStream (outputstream_file);

            outputstream_object.writeObject (persfriend_list);
            outputstream_object.close ();
            outputstream_file.close ();
        }
        catch (Exception e)
        {
            System.out.println ("Could not save data\n");
        }
    }

    /**
     * Function to read proffriend course details from pre-saved file
     * Reads from text file SAVE_FILE from same directory
     */
    @SuppressWarnings("unchecked")
    static public void proffriend_read_from_file (String file_name) throws java.io.IOException
    {
        try
        {
            File save_file = new File(file_name);
            FileInputStream inputstream_file = new FileInputStream (file_name);
            ObjectInputStream inputstream_object = null;

            // File is not empty
            if (save_file.length () >= 10)
            {
                inputstream_object = new ObjectInputStream (inputstream_file);
                proffriend_list = (ArrayList<Proffriend>) inputstream_object.readObject();
            }

            if(inputstream_object != null)
                inputstream_object.close();
            inputstream_file.close();
        }
        catch (Exception e)
        {
            System.out.println ("Could not read saved data\nFile might not exist\n");
        }
    }

    /**
     * Function to write proffriend course details to file
     * Saves objects in text file SAVE_FILE from same directory
     */
    static public void proffriend_write_to_file (String file_name) throws java.io.IOException
    {
        try
        {
            // Create new file. Overwrite if necessary
            FileOutputStream outputstream_file = new FileOutputStream (file_name);
            ObjectOutputStream outputstream_object = new ObjectOutputStream (outputstream_file);

            outputstream_object.writeObject (proffriend_list);
            outputstream_object.close ();
            outputstream_file.close ();
        }
        catch (Exception e)
        {
            System.out.println ("Could not save data\n");
        }
    }

    /**
     * Function to read casual course details from pre-saved file
     * Reads from text file SAVE_FILE from same directory
     */
    @SuppressWarnings("unchecked")
    static public void casual_read_from_file (String file_name) throws java.io.IOException
    {
        try
        {
            File save_file = new File(file_name);
            FileInputStream inputstream_file = new FileInputStream (file_name);
            ObjectInputStream inputstream_object = null;

            // File is not empty
            if (save_file.length () >= 10)
            {
                inputstream_object = new ObjectInputStream (inputstream_file);
                casual_list = (ArrayList<Casual>) inputstream_object.readObject();
            }

            if(inputstream_object != null)
                inputstream_object.close();
            inputstream_file.close();
        }
        catch (Exception e)
        {
            System.out.println ("Could not read saved data\nFile might not exist\n");
        }
    }

    /**
     * Function to write casual course details to file
     * Saves objects in text file SAVE_FILE from same directory
     */
    static public void casual_write_to_file (String file_name) throws java.io.IOException
    {
        try
        {
            // Create new file. Overwrite if necessary
            FileOutputStream outputstream_file = new FileOutputStream (file_name);
            ObjectOutputStream outputstream_object = new ObjectOutputStream (outputstream_file);

            outputstream_object.writeObject (casual_list);
            outputstream_object.close ();
            outputstream_file.close ();
        }
        catch (Exception e)
        {
            System.out.println ("Could not save data\n");
        }
    }
}
