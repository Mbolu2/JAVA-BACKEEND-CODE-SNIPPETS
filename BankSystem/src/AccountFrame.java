import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AccountFrame extends JFrame {
    JLabel accnNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;

    JTextField accNoTXT, ownerTXT, balanceTXTn, amountTXT;

    JComboBox<City> citiesCMB;

    JButton newBTN,saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;

    JRadioButton maleRDB, fenaleRDB;

    ButtonGroup genderBTNGRP;

    JList<Account> accountsLST;

    JPanel p1,p2,p3,p4,p5;

    Set<Account> accountSet = new TreeSet<>();
    Account acc, x;

    boolean newRec = true;

    // Combobox Data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;

    //Table Data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    public AccountFrame()  {
        super("Account Operations");
        setLayout(null);
        setSize(600,400);

    // Adding Components to the Frame

        // 1- Labels
        accnNoLBL = new JLabel("Account No.");
        ownerLBL =new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");


        // 2- Text Fields
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();

        balanceTXTn = new JTextField(); balanceTXTn.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));


        // ComboBox
        citiesCMBMDL = new DefaultComboBoxModel<City>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement((new City("Brooklen", "New York")));
        citiesCMBMDL.addElement(new City("Dothan", "Alabama"));
        citiesCMBMDL.addElement(new City("Atlanta", "Vergina"));
        citiesCMBMDL.addElement(new City("Panama", "Florida"));
        citiesCMBMDL.addElement(new City("Miami", "Florida"));

        // Adding data to JCOMBOBOX
        citiesCMB = new JComboBox<City>(citiesCMBMDL);


        // 4- Radio Buttons
        maleRDB = new JRadioButton("Male", true);
        fenaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(fenaleRDB);


        // 5- Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        // 6 - Table
        accountsLSTMDL = new DefaultListModel<>();
        accountsLST = new JList<>(accountsLSTMDL);

        // 7- Panels
        p1 = new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout (new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel();  p4.setBounds(305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());



        // Adding Components to Panel
        p1.add(accnNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXTn);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(fenaleRDB);


        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);


        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountsLST);

        // Adding Panels to Frame

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);



        // table creation
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("trsNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);


/****************************  Functionality *********************/
newBTN.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       accNoTXT.setText("");
       ownerTXT.setText("");
       citiesCMB.setSelectedIndex(0);
       maleRDB.setSelected(true);
       balanceTXTn.setText("");
       amountTXT.setText("");
       newRec = true;
    }
});

saveBTN.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(newRec){
            // Insertion
            if(ownerTXT.getText().length() !=0){
                acc = new Account(
                        ownerTXT.getText(),
                        (City) citiesCMB.getSelectedItem(),
                        maleRDB.isSelected()? 'M' : 'F');

                accNoTXT.setText(String.valueOf(acc.accNo));
                accountSet.add(acc);
                accountsLSTMDL.addElement(acc);
                newRec = false;
            } else {
                JOptionPane.showMessageDialog(null, "Fill All Fields Please");
            }
        } else {

            // Updating
            accountSet.remove(acc);

            int a = Integer.parseInt(accNoTXT.getText());
            String o = ownerTXT.getText();
            City c = (City) citiesCMB.getSelectedItem();

            char g = maleRDB.isSelected()? 'M' : 'F';
            double b = Double.parseDouble(balanceTXTn.getText());
            acc = new Account(a,o,c,g,b);
            accountSet.add(acc);
            accountsLSTMDL.setElementAt(acc, accountsLST.getSelectedIndex());
            newRec = false;


        }


    }
});

showBTN.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = "";
        Iterator<Account> it = accountSet.iterator();
        while(it.hasNext()){
            s += it.next().toString()+"\n";
            JOptionPane.showMessageDialog(null, s);

        }
    }
});

depositBTN.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!newRec && amountTXT.getText().length() !=0){

            // Adding Transaction to table
            Transaction t = new Transaction(acc, LocalDate.now(),
                    'D', Double.parseDouble(amountTXT.getText()));
            DisplayTransactionsIntable(t);

            // Perform deposit from account
            acc.deposit(Double.parseDouble(amountTXT.getText()));
            balanceTXTn.setText(String.valueOf(acc.balance));
        }
    }
});

withdrawBTN.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!newRec && amountTXT.getText().length() != 0){

            // Adding transaction to table
            Transaction t = new Transaction(
                    acc, LocalDate.now(),
                    'W',
                    Double.parseDouble(amountTXT.getText()));

                    DisplayTransactionsIntable(t);


                    // Perform withdrawal on account
            acc.withdraw(Double.parseDouble(amountTXT.getText()));
            balanceTXTn.setText(String.valueOf(acc.balance));

        }
    }
});

accountsLST.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        acc = x = accountsLST.getSelectedValue();

        accNoTXT.setText(String.valueOf(acc.accNo));
        ownerTXT.setText(acc.owner);
        citiesCMB.setSelectedItem(acc.city);

        if (acc.gender == 'M') maleRDB.setSelected(true);
        else fenaleRDB.setSelected(true);


        balanceTXTn.setText(String.valueOf(acc.balance));
        amountTXT.setEnabled(true);
        depositBTN.setEnabled(true);
        newRec = false;



        // clear Table:
        for (int i = tableModel.getRowCount() -1; i>= 0; i-- ){
            tableModel.removeRow(i);
        }

        // Get Transactions to selected account
        SearchTransactionList(acc.accNo);
    }
});

    }

    private void SearchTransactionList(int accNo) {

        List<Transaction> filteredList = new ArrayList<>();

        // iterate through the list
        for (Transaction t : translist) {

            // filter value that contains trasNo
            if (t.getAcc().accNo == accNo) {
                filteredList.add(t);
            }
        }

        // Display the filtered List
        for (int i = 0; i < filteredList.size(); i++){

            // Displaying Data into table

            tableModel.addRow(new Object[]{
                    filteredList.get(i).getTrsNo(),
                    filteredList.get(i).getDate(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getAmount()
            });
        }

    }

    private void DisplayTransactionsIntable(Transaction t) {

   // Displaying data into table
        tableModel.addRow(new Object[]{
                t.getTrsNo(),
                t.getDate(),
                t.getOperation(),
                t.getAmount()
        });

        // Adding object to arrayList
        translist.add(t);


    }

    public static void main(String[] args){
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
