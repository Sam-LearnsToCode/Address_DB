import java.sql.*;
public class DB {

        // JDBC URL, username, and password
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Address_Updater";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "sameer";

        private static String query="";
    public static ResultSet rs;


        public static void createConnection() {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("connection Successful");
//                Statement view = connection.createStatement();
//                rs= view.executeQuery("select * from address ");
//                while (rs.next()) {
//                    Address add = new Address(rs.getString("Name"),rs.getString("street"),
//                            rs.getString("city"),rs.getString("state"),rs.getInt("zipcode"));
//                    AddressManager.addToAddressManager(add);
//                }
//            connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        public static void addDB(Address address){
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("connection Successful");
                query="insert into address (Name, street, city, state,zipcode) VALUES ('"+address.Name+"','"+address.getStreetAddress()+"','"
                        +address.getCity()+"','"+address.getState()+"',"+address.getZipCode()+")";
                Statement statement = connection.createStatement();
                statement.execute(query);
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    public static void viewAddress(String Name){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            Statement view = connection.createStatement();
            rs= view.executeQuery("select * from address where name='"+Name+"'");
//            connection.close();
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
    public static void deleteAddress(int index){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connection Successful");
            query="delete from address where Id ='"+index+"'";
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
            query="update address SET Name ='"+address.Name+"',street='"+address.getStreetAddress()+"',city='"+address.getCity()+"',state='"+address.getState()+"',zipcode="+address.getZipCode();
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }