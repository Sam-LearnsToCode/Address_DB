import java.sql.*;
public class DB {

        // JDBC URL, username, and password
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Address_Updater";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "sameer";

        private static String query="";
        private static String query2="";
    public static ResultSet rs;
    //Method to add values to database after pattern checks
        public static void addDB(Address address){
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("connection Successful");
                query="insert into address (Name, street, city, state,zipcode) VALUES ('"+address.Name+"','"+address.getStreetAddress()+"','"
                        +address.getCity()+"','"+address.getState()+"',"+address.getZipCode()+")";
                Statement statement = connection.createStatement();
                statement.execute(query);
                 query2 = "SELECT Id FROM address WHERE name='" + address.getName() + "' AND street='" + address.getStreetAddress()
                        + "' AND city='" + address.getCity() + "' AND state='" + address.getState()
                        + "' AND zipcode=" + address.getZipCode() + ";";
                rs=statement.executeQuery(query2);
                while(rs.next()) {
                    address.setId(rs.getInt("Id"));
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // Method to view addresses
    public static void viewAddress(String Name){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            Statement view = connection.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_READ_ONLY);
            rs= view.executeQuery("select * from address where name='"+Name+"'");
            Address_Updater.getAllAddresses(rs);
          connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void deleteAddress(String Name){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            query="delete from address where name ='"+Name+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void deleteAddress(int index) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            query = "delete from address where Id ='"+ index +"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateDB(Address address,int index){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            String query = "UPDATE address SET Name = '" + address.getName() + "', street = '" + address.getStreetAddress()
                    + "', city = '" + address.getCity() + "', state = '" + address.getState()
                    + "', zipcode = " + address.getZipCode() + " WHERE Id = " + index + ";";
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }