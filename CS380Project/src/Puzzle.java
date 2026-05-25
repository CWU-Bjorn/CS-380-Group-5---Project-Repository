import java.util.*;
import java.io.*;

class Puzzle {
    boolean puzzleStatus = false;                                   // Flag for successful puzzle completion

    public boolean puzzleEvent() {

        String fileName = "puzzle_wordbank.txt";
        String line;
        List<String> wordList = new ArrayList<String>();

        boolean success = false;

        Scanner input = new Scanner(System.in);

        try (BufferedReader fileBuffer = new BufferedReader (new FileReader(fileName))){ // Try-Catch Block to open file if exist

            while ((line = fileBuffer.readLine()) != null) { // While loop to skip header and EOF lines. Stores word in variable
                wordList.add(line.trim());
            }

        } catch (IOException e){
            System.out.println("File Not Found...");
        }

        Random randChoice = new Random();
        String wordToGuess = wordList.get(randChoice.nextInt(wordList.size()));

        StringBuilder wordHidden = new StringBuilder();
        for(int i = 0; i < wordToGuess.length(); i++){
            wordHidden.append("_");
        }

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