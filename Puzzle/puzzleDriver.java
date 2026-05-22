package Puzzle;

public class puzzleDriver {
    public static void main(String[] args) {
        Puzzle puzzleGen = new Puzzle();
        boolean success = puzzleGen.puzzleEvent();
        System.out.println(success);
    }
}
