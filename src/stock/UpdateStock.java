/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.*;


/**
 *
 * @author sk3998h
 */
public class UpdateStock extends 
        JFrame implements ActionListener{
    JButton addItem = new JButton("Update Stock");
    JTextField stockNo = new JTextField(7);
    JTextField inputStock = new JTextField(7);
    JTextArea info = new JTextArea(7,40);
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    JCheckBox keyOkay = new JCheckBox();
    JLabel imgLabel ;
    private ImageIcon image2;
    
    
    public static void main(String[] args) {
    UpdateStock updateStock = new UpdateStock();

    }
    
    public UpdateStock(){
            JPanel pwdPanel = new JPanel();
            pwdPanel.setLayout(new GridLayout(4,4));
            JPasswordField pwdField = new JPasswordField();
            JLabel enterPass = new JLabel("Enter Password:");
            pwdPanel.add(enterPass);
            pwdPanel.add(pwdField);
            int input = JOptionPane.showConfirmDialog(null, pwdPanel, "Enter your password:" 
                    ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            char[] password = pwdField.getPassword();
            char[] storedPwd = {'u', 's','e'};
            boolean isCorrect = Arrays.equals(storedPwd, password);
            if (password.length == storedPwd.length){
                System.out.print("Welcome Authorised User\n");
                Arrays.fill(password, '0');
 //Codes above this line ^^ within the constructer are for password field.  
        setLayout(new BorderLayout());
        setBounds(600, 300, 600, 199);
        setTitle("Update Stock");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Product Key:"));
        top.add(stockNo,top);
        top.add(keyOkay);
        keyOkay.addActionListener(this);
        
        add("North", top);
        JPanel west = new JPanel();
        west.add(info);
        add("West", west);
        JPanel south = new JPanel();
        south.add(new JLabel("Add Stock:" ));
        south.add(inputStock);
        inputStock.setText("0");
        south.add(addItem);
        addItem.addActionListener(this);
        add("South", south);
        
        JPanel right = new JPanel();
        image2 = new ImageIcon(getClass().getResource("res/product.jpg"));
        imgLabel = new JLabel(image2);
        right.add(imgLabel);
        getContentPane().add(imgLabel);
        add("East", right);
        setResizable(false);
        setVisible (true);
    } else {
       JOptionPane.showMessageDialog(new JFrame(),"The password you have entered is incorrect.",
               "",JOptionPane.PLAIN_MESSAGE);
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         String key = stockNo.getText();
      String name = StockData.getName(key);

      if (name == null) {
            info.setText("No such item in stock");
        } else {
            info.setText(name);
            info.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
            info.append("\nNumber in stock: " + (StockData.getQuantity(key)));
      } 

      if (e.getSource()== addItem){
          int newStockInput = Integer.parseInt(inputStock.getText());
          StockData.update(key, newStockInput);
          info.append("\nNumber in stock: " + (StockData.getQuantity(key)));
          JOptionPane.showMessageDialog(null, "The updated value of stock for "+ StockData.getName(key)+" is "+StockData.getQuantity(key)+".");
      }
              try{
        if (e.getSource() == keyOkay) {        
        image2 = new ImageIcon(getClass().getResource("res/" + key + ".jpg"));
        imgLabel.setIcon(image2);
        }
    }catch (Exception f){
    System.out.println("The imge file doesn't exist or the file extensin is wrong.");    
    }
    }
    
}

