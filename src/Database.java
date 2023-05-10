import Utils.StringUtils;
import com.mysql.cj.protocol.Resultset;

import java.lang.reflect.Array;
import java.sql.*;

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

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public ResultSet find (String[] select, String fromTable, boolean desc, int limit) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Convert Array into String select
            String selectedCols = select.length > 1 ? StringUtils.implode(",", select) : select[0];
            String SQL = "SELECT " + selectedCols + " FROM " + fromTable;

            if(limit > 0) {
                SQL += " LIMIT =" + limit;
            }
            if(desc) {
                SQL += " ORDER BY DESC";
            }
            
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

            return statement.getResultSet();

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
