package UserInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.util.Pair;

public class NPC {
    private String npcName = "";
    private String fileName = "";
    private String line = "";

    public NPC(String npcName) {
        this.npcName = npcName;
    }

    public void setName(String name){
        npcName = name;
    }

    public void shop(){
        record stock(String itemName, int price){}

        ArrayList<stock> shop = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        String itemBought = " ";

        shop.add(new stock("Bread",2));
        shop.add(new stock("Cheese",2));
        shop.add(new stock("Coffee",3));
        shop.add(new stock("Dinner",5));
        shop.add(new stock("Flour",1));

        int exitFlag = 0;
        while(exitFlag == 0){
            System.out.println("Welcome to THE SHOP\n");
            System.out.println("====Shop Stock====");
            for(stock item : shop){
                System.out.println("[" + item.itemName() + "] || Price: " + item.price());
            }
            System.out.println("====================");
            System.out.print("\nFancy Anything?: ");
            String userChoice = scan.nextLine();

            for(stock purchase : shop){
                if(purchase.itemName().equals(userChoice)){
                    shop.remove(purchase);
                }
            }
            if(userChoice.equalsIgnoreCase("exit")){
                exitFlag = 1;
                break;
            }

            System.out.print("Item Bought!: " + itemBought);
        }

    }

    public void npcEvent() {

        switch(npcName) {
            case "bard":
                fileName = "bardSong.txt";
                break;
            case "local":
                fileName = "villagerTalk1.txt";
                break;
            case "traveller":
                fileName = "travellerTalk.txt";
            case "shopkeeper":
                shop();
                break;
        }

        if(!npcName.equals("shopkeeper")){
            try (BufferedReader fileBuffer = new BufferedReader (new FileReader(fileName))){ // Try-Catch Block to open file if exist

                while ((line = fileBuffer.readLine()) != null) { // While loop to skip header and EOF lines. Stores word in variable
                    System.out.println(line);
                }

            } catch (IOException e){
                System.out.println("File Not Found...");
            }
        }


    }
}
