import Utils.StringUtils;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final static String database = "mydb";
    private final static String url = "jdbc:mysql://localhost:3306/" + database;
    private final static String username = "root";
    private final static String password = "";

    private static Connection connection;

    /**
     * Establish a connection with the Database
     */
    public Database () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            System.out.println(e);
        }
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
            String SQL = "SELECT * FROM";
            SQL += " ";
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
                SQL += "ORDER BY DESC";
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
            ArrayList<ResultSet> results = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            return resultSet;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }



}
