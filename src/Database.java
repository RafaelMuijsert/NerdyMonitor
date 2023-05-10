import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    /**
     * Initialize a connection with Database
     */
    private static String database = "mydb";
    private static String url = "jdbc:mysql://localhost:3306/" + database;
    private static String username = "root";
    private static String password = "";

    private Connection connection;

    public Database () {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Component");

            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }

        } catch(Exception e){
            System.out.println(e);
        }

    }
}
