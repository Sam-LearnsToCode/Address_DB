import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Address class representing a single address
public class Address_Updater{
    public static boolean val=false;
    public static int size;
    public static Address add;
    public static boolean addAddress(Address address){
                // Regular expression pattern to only allow certain values for certain fields
                String regex = "^[a-zA-Z0-9,-]+$"; //RegEx for street address
                String alphaRegex = "^[a-zA-Z]+$"; //RegEx for State and City
                String numRegex = "^[0-9]+$"; // //RegEx for Pincode
                Pattern pattern = Pattern.compile(regex);
                Pattern pattern2 = Pattern.compile(alphaRegex);
                Pattern pattern3 = Pattern.compile(numRegex);
                Matcher matcher = pattern.matcher(address.getStreetAddress());
                Matcher matcher2 = pattern2.matcher(address.getState());
                Matcher matcher3 = pattern2.matcher(address.getCity());
                Matcher matcher4=pattern3.matcher(Integer.toString(address.getZipCode()));
        System.out.println(matcher.matches()+" "+matcher2.matches()+" "+matcher3.matches()+" "+matcher4.matches());
                // Check if the input string matches the pattern
        System.out.println("\n"+(val) );
       return val = matcher.matches()&&matcher2.matches()&&matcher3.matches()&&matcher4.matches();
    }
    //Method to retrieve addresses matching a criteria
    public static void getAllAddresses(ResultSet rs) {
        //
        try {
            if(!DB.rs.next()){
                JOptionPane.showMessageDialog(null,"User doesn't exist");
            }
            else {
                rs.beforeFirst();
                while (DB.rs.next()) {
                    AddressUpdaterGUI.rowData1 = new Object[]{rs.getInt("Id"), rs.getString("Name"), rs.getString("Street"),
                            rs.getString("City"), rs.getString("State"), rs.getInt("zipcode")};
                    AddressUpdaterGUI.updateAddressArea();
                }
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
class Address {
    private int Id;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    public String Name;

    // Constructors
    public Address(String Name,String streetAddress, String city, String state, int zipCode) {
        this.Name=Name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public Address(int id ,String Name,String streetAddress, String city, String state, int zipCode) {
        this.Id=Id;
        this.Name=Name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    // Getters and setters
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return Name;
    }
    public void setName(String city) {
        this.Name = Name;
    }

    public int getId() {return Id;}
    public void setId(int id) {this.Id = id;}
}




