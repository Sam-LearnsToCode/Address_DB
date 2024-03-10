import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DB.createConnection();
        SwingUtilities.invokeLater(AddressUpdaterGUI::new);
//        System.out.println(Address_Updater.val);
//        System.out.println(Arrays.toString(AddressManager.addresses.toArray()));
    }
}