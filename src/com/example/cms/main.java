package com.example.cms;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.example.cms.Staff.*;
import com.example.cms.Student.*;
public class main {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {

        int userInput;

        int menuChoice;

        boolean access = false;

        String password;

        String UserID=null;

        System.out.println("Enter User type (-1 to quit) ");
        System.out.println("1. Student");
        System.out.println("2. Staff");

        Scanner sc = new Scanner(System.in);
        userInput = sc.nextInt();
        sc.nextLine();  				//input buffer


        while (userInput>0) {

            switch(userInput)
            {
                case 1:
                {
                    while (access == false)
                    {
                        System.out.println("Please input Student UserID (-1 to quit) : ");
                        UserID=sc.nextLine();
                        if (UserID.equals("-1")) {
                            break;
                        }

                        System.out.println("Please input password : ");
                        password=sc.nextLine();
                        if (check(UserID, password, 1) == true)
                        {
                            access = true;
                            //User user = new User(UserID,password,);
                            menuChoice=Student.display(); //depending on type of student menu display differs

                            while (menuChoice != 8)
                            {
                                StudentMenu.execution(menuChoice,s);
                                if (menuChoice == 1) {
                                    access=false;
                                    break;
                                }
                                menuChoice=StudentMenu.display();
                            }
                        }
                    }
                    access=false;
                    break;
                }
                case 2: {
                    access = false;
                    while (access == false) {
                        System.out.println("Please input Staff UserID (-1 to quit): ");
                        UserID = sc.nextLine();
                        if (UserID.equals("-1")) {
                            break;
                        }

                        System.out.println("Please input password : ");
                        password = sc.nextLine();
                        if (check(UserID, password, 2) == true) {
                            access = true;
                            StaffAcc sp = new StaffAcc(UserID, password);
                            menuChoice = StaffMenu.display();

                            while (menuChoice != 5) {
                                StaffMenu.execution(menuChoice, sp);
                                if (menuChoice == 1) {
                                    access = false;
                                    break;
                                }
                                menuChoice = StaffMenu.display();
                            }
                        }
                    }
                    access = false;
                    break;
                }
                default:
                    System.out.println("Invalid Choice");
            }
            System.out.println("Enter User type (-1 to quit) ");
            System.out.println("1. Student");
            System.out.println("2. Supervisor");
            System.out.println("3. FYP Coordinator");
            userInput = sc.nextInt();
            sc.nextLine();   //input buffer
        }
        System.out.println("Thank you for using the program!");
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
                filename = f.getUSERFILENAME();
                break;
            }
            case 2: //Supervisor login
            {

                filename = f.getStaffFILENAME();
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
