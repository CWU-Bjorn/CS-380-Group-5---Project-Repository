package UserInterface;
/**
 * Import of the necessary stuff, I just imported all of the libraries so this would probably need to change later for efficiency
 */
import java.sql.*;
public class DatabaseConnection {
    /**
     *Method that connects to the database by passing the url of the file into it
     */
       private static Connection connect() throws SQLException{
           return DriverManager.getConnection("jdbc:sqlite:380DatabaseSQLiteFinal.db");
        }


    /**
     * Finds the table within the database
     */
    private static String findsTable(int saveslotRotation){
           if(saveslotRotation == 1){
               return "SaveFile1";
           } else if (saveslotRotation == 2) {
               return "SaveFile2";
           } else if (saveslotRotation == 3) {
               return "SaveFile3";
           } else {
               throw new IllegalArgumentException("There is no saveslot with this designation, enter 1 , 2 , 3 to choose a save.");
           }
}
    /**
     *Finds the primary key of the table
     */
    private static String findsPrimaryKey(int saveslotRotation){
        if(saveslotRotation == 1){
            return "Save1";
        } else if (saveslotRotation == 2) {
            return "Save2";
        } else if (saveslotRotation == 3) {
            return "Save3";
        } else {
            throw new IllegalArgumentException("There is no saveslot with this designation, enter 1 , 2 , 3 to choose a save.");
        }
    }
    /**
     *Runs SQL query to fetch everything from the located save slot. It does this by mathcing the table with the values in the if statement
     * wrapped in a try/catch statement. In the future this section will likely look a lot different.
     */
    public static Player loadPlayer(int saveslotRotation){
           String foundTable = findsTable(saveslotRotation);
           String foundPrimaryKey = findsPrimaryKey(saveslotRotation);
           String executableSQL = "SELECT * FROM " + foundTable + " WHERE " +foundPrimaryKey + " = ?";

            try(
                    Connection connection = connect();
                    PreparedStatement statement = connection.prepareStatement(executableSQL);
                    ){

                statement.setInt(1, saveslotRotation);

                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()){
                    return new Player(
                            saveslotRotation,
                            resultSet.getString("Pname"),
                            resultSet.getInt("playerHealth"),
                            resultSet.getString("PWord"),
                            resultSet.getInt("Sword") == 1,
                            resultSet.getInt("keyInGame") == 1,
                            resultSet.getInt("Shield") == 1,
                            resultSet.getInt("food") == 1,
                            resultSet.getInt("Currency"),
                            resultSet.getInt("NumberObstaclesDone")
                    );
                }
            }catch (SQLException e){
                System.out.println("Trouble with the database, unable to load it");
                e.printStackTrace();
            }
            return null;
    }

    /**
     * Update for the items within the game. This method allows for the user to flip the boolean values from true to false and back again. It also is what
     * ties together the currency attribute to be able to give each item a currency value that on selling or buying the item the currency amount is added or
     * subtracted from the currency variable
     */
    public static boolean updateMethodForItems(int saveslotRotation, String saveslotName, boolean itemDesignation, int currencyUpdate){

        String foundTable = findsTable(saveslotRotation);
        String foundPrimaryKey = findsPrimaryKey(saveslotRotation);
        String columnName = grabbingInfo(saveslotName);
        int checkIfUserAlreadyHadItem;

        if(itemDesignation){
            checkIfUserAlreadyHadItem = 0;
        } else{
            checkIfUserAlreadyHadItem = 1;
        }

        String sql = "UPDATE " + foundTable + " SET " + columnName + "= ?, Currency = Currency + ?" + " WHERE " + foundPrimaryKey + "= ?" + " AND " + columnName + "= ?" + " AND Currency + ? >= 0";

        try(
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){

            statement.setInt(1, itemDesignation ? 1 : 0);
            statement.setInt(2, currencyUpdate);
            statement.setInt(3, saveslotRotation);
            statement.setInt(4, checkIfUserAlreadyHadItem);
            statement.setInt(5, currencyUpdate);

            int result = statement.executeUpdate();

            return (result > 0);

        } catch (SQLException e){
            System.out.println("Update not sucessful");
            e.printStackTrace();
            return false;
        }
    }
    //Helper method for the updateMethofForitems
    private static String grabbingInfo(String item){
        switch(item){
            case "Sword":
                return "Sword";
            case "Key":
                return "keyInGame";
            case "Shield":
                return "Shield";
            case "Food":
                return "food";
            default:
                throw new IllegalArgumentException("None existant item: " + item);

        }
    }

    /**
     * This specifically works with the playerHealth and what happens when you eat the food. Before the food would just be added to the currency but that made no sense.
     * To fix that this method adds to the player hp for the food eaten rather than just selling it back.
     */
    public static boolean updateToFood(int saveslotRotation, int playerHP){
        String foundTable = findsTable(saveslotRotation);
        String foundPrimaryKey = findsPrimaryKey(saveslotRotation);
        String columnName = grabbingInfo("Food");

        String sql = "UPDATE " + foundTable + " SET " + columnName + "= 0, playerHealth = playerHealth + ?"+ " WHERE " + foundPrimaryKey + "= ?" + " AND " + columnName + "= 1";

        try( Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ){
            statement.setInt(1,playerHP);
            statement.setInt(2,saveslotRotation);
            int result = statement.executeUpdate();

            return (result > 0);

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the state of the "NumberObstaclesDone" in the database. This is called in GameplayController.java.
     */
    public static boolean obstacleUpdates(int saveslotRotation){
        String foundTable = findsTable(saveslotRotation);
        String foundPrimaryKey = findsPrimaryKey(saveslotRotation);

        String sql = "UPDATE " + foundTable + " SET NumberObstaclesDone = NumberObstaclesDone + 1 WHERE " + foundPrimaryKey + "= ?";

        try(
                Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ){
            statement.setInt(1, saveslotRotation);

            int result = statement.executeUpdate();
            return (result > 0);

        }catch(SQLException e){
            e.printStackTrace();
            return false;
    }
}

    /**
     *While the updateMethodForItems also deals with currency I made this to specifically only deal with currency on enemy defeat.
     * Because I don't want to have to modify the parameters of how the items are updated I whipped up this similar update method.
     */
    public static boolean currencyOnEnemyDefeat(int saveslotRotation, int rewardVar){
    String foundTable = findsTable(saveslotRotation);
    String foundPrimaryKey = findsPrimaryKey(saveslotRotation);

    String sql = "UPDATE " + foundTable + " SET Currency = Currency + ? " + " WHERE " + foundPrimaryKey + "= ?";

    try(
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);

            ){
        statement.setInt(1, rewardVar);
        statement.setInt(2, saveslotRotation);

        int result = statement.executeUpdate();
        return (result > 0);
    }catch(SQLException e){
        e.printStackTrace();
        return false;
    }
}

    /**
     *This method is what handles to check is a save slot has or does not have a password yet. If it does then nothing
     * happens and the player can log it, if there is no password then it will link with methods in Mapcontroller to display that
     */
    public static boolean emptySave(int saveslotRotation, String newPasswordVar){
        String foundTable = findsTable(saveslotRotation);
        String foundPrimaryKey = findsPrimaryKey(saveslotRotation);

    String sql = "UPDATE " + foundTable + " SET  Pname = ?, " + " PWord = ?, " + " Sword = 0, " + " keyInGame = 0, "+ " Shield = 0, "+ " food = 0, " + " Currency = 0, "  + " NumberObstaclesDone = 0, " + "PlayerHealth = 25 " + " WHERE " + foundPrimaryKey + "= ?";

    try(
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
        statement.setString(1, "Character " + saveslotRotation);
        statement.setString(2, newPasswordVar);
        statement.setInt(3, saveslotRotation);

        int result = statement.executeUpdate();
        return (result > 0);
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
}
        }
