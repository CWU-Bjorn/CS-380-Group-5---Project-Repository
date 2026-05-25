package UserInterface;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

class Puzzle {

    Random puzzleID = new Random();                                     //Use Random for puzzle selection
    public int puzzleNumber = puzzleID.nextInt(3 - 1 + 1) + 1; // Puzzle Selection Generation
    boolean puzzleStatus = false;                                   // Flag for successful puzzle completion

    public boolean puzzleEvent() {

        String fileName = "";      //Stores file name
        String line = "";         // Stores File Lines
        String wordToGuess = "";
        boolean success = false;

        Scanner input = new Scanner(System.in);

        switch (puzzleNumber) { // File Selector using switch
            case 1:
                fileName = "puzzle1.txt";
                break;
            case 2:
                fileName = "puzzle2.txt";
                break;
            case 3:
                fileName = "puzzle3.txt";
                break;
        }

        try (BufferedReader fileBuffer = new BufferedReader (new FileReader(fileName))){ // Try-Catch Block to open file if exist

            while ((line = fileBuffer.readLine()) != null) { // While loop to skip header and EOF lines. Stores word in variable
                if (line.contains("=")) {
                    continue;
                }
                wordToGuess = line.trim();
            }

        } catch (IOException e){
            System.out.println("File Not Found...");
        }

        StringBuilder wordHidden = new StringBuilder(); // WordBuilder for string mutation
        wordHidden.append("_".repeat(wordToGuess.length()));  // Create a "Hidden" version of word to guess

        while(!success) {
            System.out.print("Enter a letter to guess the clue: ");
            char letter = input.next().charAt(0);
            for(int i = 0; i < wordToGuess.length(); i++){
                if(wordToGuess.charAt(i) == letter){
                    wordHidden.setCharAt(i, letter);
                }
            }

            System.out.println(wordHidden);

            if(wordHidden.toString().equals(wordToGuess)) {
                success = true;
            }
        }


        System.out.println(wordHidden);
        return puzzleStatus;
    }

}