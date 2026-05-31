package UserInterface;
/**
 * Import of the necessary stuff, I just imported all of the libraries so this would probably need to change later for efficiency
 */
import java.nio.file.Paths;
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

    public static boolean updateMethodForItems(int saveslotRotation, String saveslotName, boolean itemDesignation){

        String foundTable = findsTable(saveslotRotation);
        String foundPrimaryKey = findsPrimaryKey(saveslotRotation);
        String columnName = grabbingInfo(saveslotName);

        String sql = "UPDATE " + foundTable + " SET " + columnName + "= ?" + " WHERE " + foundPrimaryKey + "= ?";

        try(
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setInt(1, itemDesignation ? 1 : 0);

            statement.setInt(2, saveslotRotation);

            int result = statement.executeUpdate();

            return (result > 0);

        } catch (SQLException e){
            System.out.println("Update not sucessful");
            e.printStackTrace();
            return false;
        }
    }

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

        }
