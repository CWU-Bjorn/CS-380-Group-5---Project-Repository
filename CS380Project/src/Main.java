/**
 * Imports, as with the other files this could probably be cleaned up
 */
import java.util.*;
import java.sql.*;
import java.io.*;
/**
 * Declaration of main argument
 */
public class Main {
    public static void main(String[] args) {
/**
 *This prompts the user to enter a save slot so they can start the game
 */
        int choosenSlot = 0;
        Scanner userVar = new Scanner(System.in);
        System.out.print("Please enter the save slot you wish to use: ");
        choosenSlot = userVar.nextInt();
        userVar.nextLine();
        /**
         * Uses what the player enters to connect to the database and run code in the DatabaseConnection.java file
         */
        Player clientSideUser = DatabaseConnection.loadPlayer(choosenSlot);
        /**
         * Same as the user prompt above except it wants the password the is unique to each save. Even if two saves have the same password the save must first be
         * designated and because of that there will never be conflict as the save file number is unique for each save slot and thus the password will also be unique.
         */
        String userPassword = "";
        System.out.println("Please enter your password for this save: ");
        userPassword = userVar.nextLine();

        /**
         * This uses the method in the player.java file to call and check it the String entered is actually the password that is being looked for.
         */
        if(clientSideUser.isPasswordCorrect(userPassword)){
            System.out.println("Welcome back! You are now logged in!");
            System.out.println(clientSideUser);
        }else{
            System.out.println("Password does not match the correct password");
        }
    }
}
