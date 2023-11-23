package com.example.cms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class main {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {


    }

    /**
     * Checks if UserID and Password is valid
     * @param UserID This user's keyed in UserID
     * @param pw This user's keyed in password
     * @param choice Determines which csv to look through
     * @return True/False depending if login details are valid
     * @throws IOException When the I/O operation is interrupted
     */

    //for Excel sheet function
    private static boolean check(String UserID, String pw, int choice) throws IOException
    {
        FilePath f = new FilePath();
        String filename = null;
        switch (choice)
        {
            case 1: //Student login
            {
                filename = f.getSTUDFILENAME();
                break;
            }
            case 2: //Staff login
            {

                filename = f.getSTAFFFILENAME();
                break;

            }
        }
        try(BufferedReader r = new BufferedReader(new FileReader(filename)))
        {
            String csvSplitBy = ",";
            String line = r.readLine();
            line = r.readLine();
            while(line!=null)
            {
                // Add a new row to the bottom of the file
                String[] parts = line.split(csvSplitBy);
                String password = parts[2];
                String email[][] = new String[parts.length][];
                for (int x = 0; x < parts.length; x++)
                {
                    email[x] = parts[x].split("@");

                }
                if (email[1][0].equals(UserID))
                {
                    if (password.equals(pw))
                    {
                        return true;
                    }
                    System.out.println("Incorrect password!");
                    return false;
                }
                line = r.readLine();
            }
            System.out.println("UserID does not exist!");
            return false;


        }
    }
}
