package UserInterface;
import java.util.*;
import java.io.*;

class Puzzle {

    private boolean puzzleStatus = false;                                   // Flag for successful puzzle completion
    private String wordToGuess;
    private StringBuilder wordHidden;

    public String getHiddenWord(){ return wordHidden.toString(); }
    public String getWordToGuess(){ return wordToGuess;}
    public boolean getPuzzleStatus(){ return puzzleStatus; }

    public void puzzleEvent() {

        String fileName = "puzzle_wordbank.txt";
        String line;
        List<String> wordList = new ArrayList<String>();

        boolean success = false;

        Scanner input = new Scanner(System.in);

        try (BufferedReader fileBuffer = new BufferedReader(new FileReader(fileName))) { // Try-Catch Block to open file if exist

            while ((line = fileBuffer.readLine()) != null) { // While loop to skip header and EOF lines. Stores word in variable
                wordList.add(line.trim());
            }

        } catch (IOException e) {
            System.out.println("File Not Found...");
        }

        if (wordList.isEmpty()) {
            return;
        }

        Random randChoice = new Random();
        wordToGuess = wordList.get(randChoice.nextInt(wordList.size()));
        wordHidden = new StringBuilder("_".repeat(wordToGuess.length()));

    }

    public boolean guessLetter(char letter) {
        letter = Character.toLowerCase(letter);

        for(int i = 0; i < wordToGuess.length(); i++) {
            if(wordToGuess.charAt(i) == letter) {
                wordHidden.setCharAt(i, letter);
            }

        }

        if (wordHidden.toString().equals(wordToGuess)) {
            puzzleStatus = true;
        }
        return puzzleStatus;
    }
}