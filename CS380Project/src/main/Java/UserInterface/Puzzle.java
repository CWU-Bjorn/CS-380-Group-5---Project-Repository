package UserInterface;
import java.util.*;
import java.io.*;

/*Puzzle Class
  Contains attributes for creating a puzzle
  as well as manages attributes of a puzzle
 */
class Puzzle {

    /*Puzzle Attributes
      Checks for puzzle completion as well as
      Stores word to guess and a hidden version of the word
      to manipulate
     */
    private boolean puzzleStatus = false;                                   // Flag for successful puzzle completion
    private String wordToGuess;
    private StringBuilder wordHidden = new StringBuilder();

    //Getter Methods to retrieve attributes
    public String getHiddenWord(){ return wordHidden.toString(); }
    public String getWordToGuess(){ return wordToGuess;}
    public boolean getPuzzleStatus(){ return puzzleStatus; }

    //Puzzle Event method to place the player in a player event
    public void puzzleEvent() {

        //Statement to confirm initiation
        System.out.println("Puzzle Started");

        //String variable line. Used to store words extracted from the wordbank file
        String line;

        //Stores the various words from the wordbank file onto the arraylist.
        List<String> wordList = new ArrayList<String>();

        /*Try-Catch block. Determines existence of file to extract lines
          If not found, throw exception
          If found, place words into line variable, then transfer to arrayList
         */
        try(InputStream is = getClass().getResourceAsStream("/puzzle_wordbank.txt")){

            System.out.println("InputStream: " + is);

            //File Reader using BufferedReader
            BufferedReader fileBuff = new BufferedReader(new InputStreamReader(is));

            //While contents exist in file, extract onto line variable, then trim and place on arrayList
            while((line = fileBuff.readLine()) !=null){
                wordList.add(line.trim());
            }

            //Count list size
            System.out.println("wordList: " + wordList.size());
        }catch(IOException | NullPointerException e){
            System.out.println("File not found...");
        }
        if (wordList.isEmpty()) {
            return;
        }

        //Random randChoice. Selects random word to make the player guess
        Random randChoice = new Random();

        //Word guess variable grabbed from arrayList, then set on wordToGuess variable
        wordToGuess = wordList.get(randChoice.nextInt(wordList.size()));

        /*Based on the size of the wordToGuess variable, build the hidden word variable
          Using StringBuilder, successful letter guesses replace the "hidden" portions
          to the correct letter
         */
        wordHidden = new StringBuilder("_".repeat(wordToGuess.length()));

    }

    /*GuessLetter method. Allows for the player to guess
      on a letter-by-letter case. Will update on successful guesses
     */
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