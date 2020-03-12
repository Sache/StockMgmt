package stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import java.text.*;

/**
 *
 * @author Sandesh
 */
public class PurchaseItem extends
        JFrame implements ActionListener {

    JTextField keyNo = new JTextField(7);
    JButton bsktButton = new JButton("Add to Basket");
    JTextArea info = new JTextArea(7, 40);
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    String[] quantityNO = {"1", "2", "3", "4", "5"};
    JComboBox listBox = new JComboBox(quantityNO);
    JTextField total = new JTextField(7);
    private ImageIcon image2;
    JButton checkOut = new JButton("Checkout");
    JLabel imgLabel ;

    public static void main(String[] args) {
        PurchaseItem purchaseItem = new PurchaseItem();
    }

    public PurchaseItem() {
        setLayout(new BorderLayout());
        setBounds(600, 300, 600, 199);
        setTitle("Purchase item");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel top = new JPanel();
        top.add(new JLabel("Enter key: "));
        top.add(keyNo);
        add("North", top);
        top.add(new JLabel("Input Quantity: "));
        top.add(listBox);
        top.add(bsktButton);
        bsktButton.addActionListener(this);
        JPanel left = new JPanel();
        left.add(info);
        add("West", left);
        JPanel south = new JPanel();
        south.add(new JLabel("Total: "));
        south.add(total);
        south.add(checkOut);
        checkOut.addActionListener(this);
        add("South", south);
        JPanel right = new JPanel();
        image2 = new ImageIcon(getClass().getResource("res/product.jpg"));
        imgLabel = new JLabel(image2);
        right.add(imgLabel);
        getContentPane().add(imgLabel);
        add("East", right);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = keyNo.getText();
        String name = StockData.getName(key);
        int quantity = Integer.parseInt(listBox.getSelectedItem().toString());
        if (name == null) {
            info.setText("No such item in stock");
        } else {
            info.setText(name);
            info.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
            info.append("\nNumber in stock: " + StockData.getQuantity(key));
            if (StockData.getQuantity(key) < 1) {
                info.append("\nThere is no stock available for this product at the moment.");
            }

            double totalV = StockData.getPrice(key);

            double totalValue = (double) (totalV * quantity);
            total.setText(pounds.format(totalValue));

            if (e.getSource() == checkOut) {
                int conformation = JOptionPane.showConfirmDialog(null, "Would you like to proceed the transaction?", "Proceed?", JOptionPane.YES_NO_OPTION);
                if (conformation == 0) {
                    int qup = 0 - quantity;
                    StockData.update(key, qup);
                }

            }
        }
        try{
        if (e.getSource() == bsktButton) {        
        image2 = new ImageIcon(getClass().getResource("res/" + key + ".jpg"));
        imgLabel.setIcon(image2);
        }
    }catch (Exception f){
    System.out.println("The imge file doesn't exist or the file extensin is wrong.");    
    }


  }
}