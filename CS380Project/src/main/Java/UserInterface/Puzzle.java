package UserInterface;
import java.util.*;
import java.io.*;

class Puzzle {

    private boolean puzzleStatus = false;                                   // Flag for successful puzzle completion
    private String wordToGuess;
    private StringBuilder wordHidden = new StringBuilder();

    public String getHiddenWord(){ return wordHidden.toString(); }
    public String getWordToGuess(){ return wordToGuess;}
    public boolean getPuzzleStatus(){ return puzzleStatus; }

    public void puzzleEvent() {
        System.out.println("Puzzle Started");
        String line;
        List<String> wordList = new ArrayList<String>();

        try(InputStream is = getClass().getResourceAsStream("/puzzle_wordbank.txt")){

            System.out.println("InputStream: " + is);

            BufferedReader fileBuff = new BufferedReader(new InputStreamReader(is));

            while((line = fileBuff.readLine()) !=null){
                wordList.add(line.trim());
            }

            System.out.println("wordList: " + wordList.size());
        }catch(IOException | NullPointerException e){
            System.out.println("File not found...");
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