
import Utils.StringUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.jfree.data.json.JSONUtils;
import org.jfree.data.json.impl.JSONArray;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static String url;
    private static String username;
    private static String password;

    private static Connection connection;

    /**
     * Establish a connection with the Database
     */
    public Database () {
        // A connection is already established.
        if(isConnected(connection)) {
            return;
        }

        try {
            // Initialize Env file
            Dotenv dotenv = Dotenv.load();

            this.url = "jdbc:mysql://" + dotenv.get("DATABASE");
            this.username = dotenv.get("DB_USERNAME");
            this.password = dotenv.get("DB_PASSWORD");

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Check if connection a connection has been established and isn't closed.
     * @return
     */
    public boolean isConnected(Connection con) {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException ignored) {
            System.out.println(ignored);
        }

        return false;
    }

    /**
     * Execute query and return a ResultSet Object, which can be used to loop through all the records found.
     * @param select
     * @param fromTable
     * @param where
     * @param desc
     * @param limit
     * @return
     */
    public ResultSet find(String[] select, String fromTable, String[][] where, boolean desc, int limit) {
        try {
            // Convert Array into String select
            String selectedCols = (select.length > 1) ? StringUtils.implode(",", select) : select[0];
            String SQL = "SELECT ";
            SQL += selectedCols;

            SQL += "FROM ";
            SQL += StringUtils.sanitize(fromTable);
            SQL += " ";

            // Concat Where statements
            if(where.length > 0) {
                int i= 0;
                for(String[] col : where) {
                    if(i == 0) {
                        SQL += "WHERE";
                    } else {
                        SQL += "AND";
                    }
                    SQL += " ";

                    SQL += col[0]; // Column
                    SQL += col[1]; // Operator
                    SQL += " ? "; // prepared statement placeholder

                    i++;
                }
            }

            if(desc) {
                SQL += " ";
                SQL += "ORDER BY id DESC";
            }

            if(limit > 0) {
                SQL += "LIMIT =" + StringUtils.sanitize(String.valueOf(limit));
            }

            PreparedStatement statement = connection.prepareStatement(SQL);

            int i = 1;
            for(String[] col : where) {
                // Replace placeholders with values.
                statement.setString(i, StringUtils.sanitize(col[2]));
                i++;
            }

            ResultSet resultSet = statement.executeQuery();

            return resultSet;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * <pre>Beware when using!</pre>
     *
     * Executes a raw SQL select query. Always manually escape content using the sanitize function.
     * @param SQL
     * @return
     */
    public ResultSet findRaw(String SQL) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            return resultSet;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }



}
