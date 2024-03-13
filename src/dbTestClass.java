import java.sql.*;

public class dbTestClass {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create statement
            statement = connection.createStatement();

            // Execute query
            resultSet = statement.executeQuery("SELECT * FROM my_table");

            // Iterate through the ResultSet
            while (resultSet.next()) {
                // Retrieve data from the current row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Retrieve other columns as needed
                // Process the data, for example, print it
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of creation
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
