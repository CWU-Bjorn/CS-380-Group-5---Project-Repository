package UserInterface;

import java.io.*;

public class NPC {
    String npcName = "";
    String fileName = "";
    String line = "";

    public void npcEvent(String npc) {
        switch(npc) {
            case "bard":
                fileName = "Dialogue2.txt";
                break;
            case "local":
                fileName = "Dialogue1.txt";
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
