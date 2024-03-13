import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddressUpdaterGUI extends JFrame {
    private JTextField Name ,streetField, cityField, stateField, zipCodeField;
     static String viewId=" ";
    static Address address;
    static Object[] rowData1 ={"","","","","",""};
    static Object[] rowData2 ={45,"das","sad","das","sad","das"};

    public static DefaultTableModel model =new DefaultTableModel();

    public AddressUpdaterGUI() {
        super("Address Updater");
//       addressManager = new AddressManager();

        // Create components
        Name =new JTextField(20);
        streetField = new JTextField(20);
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipCodeField = new JTextField(10);
//        addressArea = new JTextArea(10, 30);
//        addressArea.setEditable(false);
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

         //Set buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(view);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clear);

         // Declare Table
        String[] colHeadings = {"ID","Name","Street","City","State","ZipCode"};
        model.setColumnIdentifiers(colHeadings);
//        DefaultTableModel model = new DefaultTableModel(new Object[0][colHeadings.length], colHeadings.length) ;
        model.setColumnIdentifiers(colHeadings);
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setEnabled(false);

        // Set Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
//       mainPanel.add(table.getTableHeader(), BorderLayout.EAST);
//       mainPanel.add(new JScrollPane(addressArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Name.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zipCode = zipCodeField.getText();
                 if (name.isEmpty()||street.isEmpty() || city.isEmpty() || state.isEmpty() ||zipCode.isEmpty()) {
                    JOptionPane.showMessageDialog(AddressUpdaterGUI.this,"No Fields should be Empty");
                    return;
                }
                address = new Address(name,street, city, state, Integer.parseInt(zipCode));
                 if (!Address_Updater.addAddress(address)) {
                     JOptionPane.showMessageDialog(AddressUpdaterGUI.this, "Fields should not contain special characters other than Hyphen(-) and Comma(,)");
                   return;
                 }
                DB.addDB(address);
                updateAddressArea(address,address.getId());
                clearFields();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(AddressUpdaterGUI.this,"Please view Addresses before deleting");
                    return;
                }
                viewId= JOptionPane.showInputDialog("Enter the Id to delete the Address");
                String confirm=JOptionPane.showInputDialog("Type Yes to permanently delete User with Id"+viewId);
                if(viewId.isEmpty()||confirm!="Yes"){
                    return;
                }
                DB.deleteAddress(Integer.parseInt(viewId));
                for (int i = 0; i < model.getRowCount(); i++) {
                    if ((model.getValueAt(i, 0))==viewId) {
                        model.removeRow(Integer.parseInt(viewId));

                    }
              }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index ;
//                if (model.getRowCount() == 0) {
                    viewId = JOptionPane.showInputDialog("Enter the Name to view their Addresses");
                    clearAddressArea();
                    DB.viewAddress(viewId);
//                } else if (model.getRowCount() != 0) {
                    index = Integer.parseInt(JOptionPane.showInputDialog("Select the index to be edited"));
//                    DB.viewAddress(viewId);
//                }
                Name.setText((String)rowData1[1]);
                streetField.setText((String)rowData1[2]);
                cityField.setText((String)rowData1[3]);
                stateField.setText((String)rowData1[4]);
                zipCodeField.setText(Integer.toString((Integer) rowData1[5]));

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Address add =new Address (Name.getText(),streetField.getText(),cityField.getText(),
                                stateField.getText(),Integer.parseInt(zipCodeField.getText()));
                        DB.updateDB(add, index);
                        DB.deleteAddress(index);
                        clearAddressArea();
                        DB.viewAddress(Name.getText());
                    }
                });
           }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAddressArea();
                viewId= JOptionPane.showInputDialog("Enter the Name to view their Addresses");
                 DB.viewAddress(viewId);
//                updateAddressArea();
//                Address_Updater.getAllAddresses();
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
    // External Methods
    protected static void updateAddressArea() {
        model.addRow(rowData1);
    }
    private void updateAddressArea(Address address) {
      rowData1=new Object[]{address.getName(),address.getStreetAddress(),address.getCity(),address.getState(),address.getZipCode()};
      model.addRow(rowData1);
    }
    private void updateAddressArea(Address address,int id) {
        rowData1=new Object[]{address.getId(),address.getName(),address.getStreetAddress(),address.getCity(),address.getState(),address.getZipCode()};
        model.addRow(rowData1);
    }

    private void clearFields() {
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipCodeField.setText("");
        Name.setText("");
    }
    private void clearAddressArea() {
        model.setRowCount(0);
    }
}
