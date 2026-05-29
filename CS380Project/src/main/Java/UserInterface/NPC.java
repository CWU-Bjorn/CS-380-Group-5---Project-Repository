package UserInterface;

import java.io.*;
import java.util.ArrayList;

public class NPC {
    String npcName = "";
    String fileName = "";
    String line = "";

    public void shop(){
        ArrayList<String> shop = new ArrayList<>();
    }

    public void npcEvent(String npc) {
        npc = npc.toLowerCase();
        switch(npc) {
            case "bard":
                fileName = "Dialogue2.txt";
                break;
            case "local":
                fileName = "Dialogue1.txt";
                break;
            case "shopkeeper":
                break;
        }

        try (BufferedReader fileBuffer = new BufferedReader (new FileReader(fileName))){ // Try-Catch Block to open file if exist

            while ((line = fileBuffer.readLine()) != null) { // While loop to skip header and EOF lines. Stores word in variable
                System.out.println(line);
            }

        } catch (IOException e){
            System.out.println("File Not Found...");
        }

    }
}
