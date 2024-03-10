import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressUpdaterGUI extends JFrame {

//   private AddressManager addressManager;
    private JTextField Name ,streetField, cityField, stateField, zipCodeField;
    private JTextArea addressArea;
     static String viewName="";
    static Address address;
    AddressManager addMan = new AddressManager();

    public AddressUpdaterGUI() {
        super("Address Updater");

//       addressManager = new AddressManager();

        // Create components
        Name =new JTextField(20);
        streetField = new JTextField(20);
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipCodeField = new JTextField(10);
        addressArea = new JTextArea(10, 30);
        addressArea.setEditable(false);
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton clear = new JButton("Clear");
        JButton view = new JButton("View Address");


        // Set layout
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(Name);
        inputPanel.add(new JLabel("Street Address:"));
        inputPanel.add(streetField);
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateField);
        inputPanel.add(new JLabel("Zip Code:"));
        inputPanel.add(zipCodeField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clear);
        buttonPanel.add(view);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(addressArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Name.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                int zipCode = Integer.parseInt(zipCodeField.getText());
                if (!street.isEmpty() && !city.isEmpty() && !state.isEmpty() &&Address_Updater.val==true/*&& !zipCode*/) {
                     address = new Address(name,street, city, state, zipCode);
                    Address_Updater.addAddress(address);
                    addMan.addToAddressManager(address);
                    updateAddressArea();
                    clearFields();
                }
                else if (Address_Updater.val==false) {
                    JOptionPane.showMessageDialog(AddressUpdaterGUI.this, "Fields should not contain special characters other than Hyphen(-) and Comma(,)");
                }
                Address_Updater.val=true ;
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAddressArea();
                viewName= JOptionPane.showInputDialog("Enter the Name to delete their Addresses");
                clearAddressArea();
                DB.deleteAddress(viewName);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement edit functionality
                clearFields();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAddressArea();
               viewName= JOptionPane.showInputDialog("Enter the Name to view their Addresses");
                updateAddressArea(Address_Updater.getAllAddresses());
                int index= Integer.parseInt(JOptionPane.showInputDialog("Select the index to be edited"));
                clearAddressArea();
                updateAddressArea(Address_Updater.getAllAddresses());
                DB.deleteAddress(index);
                updateAddressArea(Address_Updater.getAllAddresses());
                DB.updateDB(address,index);
                Name.setText(address.getName());
                streetField.setText(address.getStreetAddress());
                cityField.setText(address.getCity());
                stateField.setText(address.getState());
                zipCodeField.setText(Integer.toString(address.getZipCode()));
                clearAddressArea();
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAddressArea();
                viewName= JOptionPane.showInputDialog("Enter the Name to view their Addresses");
                updateAddressArea(Address_Updater.getAllAddresses());
                DB.viewAddress(viewName);
                Name.setText(address.getName());
                streetField.setText(address.getStreetAddress());
                cityField.setText(address.getCity());
                stateField.setText(address.getState());
                zipCodeField.setText(Integer.toString(address.getZipCode()));
            }
        });
        // Set frame
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        ImageIcon image =new ImageIcon("download.jpeg");
        setIconImage(image.getImage());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateAddressArea() {
        List<Address> addresses =AddressManager.getAddresses();
        StringBuilder sb = new StringBuilder();
        for (Address address : addresses) {
            sb.append(address.getName()).append(": ").append(address.getStreetAddress()).append(", ").append(address.getCity())
                    .append(", ").append(address.getState()).append(" ").append(address.getZipCode())
                    .append("\n");
       }
        addressArea.setText(sb.toString());
    }
    private void updateAddressArea(Address address) {
       List<Address> addresses =AddressManager.getAddresses();
       StringBuilder sb = new StringBuilder();
       for (Address addresss : addresses) {
            sb.append(addresss.getName()).append(": ").append(addresss.getStreetAddress()).append(", ").append(addresss.getCity())
                    .append(", ").append(addresss.getState()).append(" ").append(addresss.getZipCode())
                    .append("\n");
       }
        addressArea.setText(sb.toString());
    }

    private void clearFields() {
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipCodeField.setText("");
        Name.setText("");
    }
    private void clearAddressArea() {
        addressArea.setText("");
    }

//    public static void main(String[] args) {
//
//    }
}
