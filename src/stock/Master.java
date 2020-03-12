package stock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Master extends JFrame implements ActionListener {

    JButton check = new JButton("Check Stock");
    JButton purchase = new JButton("Purchase Item");
    JButton stock = new JButton("Update Stock");
    JButton quit = new JButton("Exit");
    ImageIcon image1;

    public static void main(String[] args) {
        Master master = new Master();
    }

    public Master() {
        setLayout(new BorderLayout());
        setSize(450, 100);
        setBounds(600, 300, 600, 199);
        setTitle("Master");

        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      try{  
      JPanel top = new JPanel();
       image1 =  new ImageIcon(getClass().getResource("res/hello.jpg"));
       top.add(new JLabel(image1));
       top.add(new JLabel("\n"));
       add("North", top);
      } catch (Exception f){
          System.out.println("The imge file doesn't exist or the file extensin is wrong.");
      }

        JPanel center = new JPanel();
        center.add(new JLabel("\nSelect an option by clicking one of the buttons below"));
        add("Center", center);

        JPanel bottom = new JPanel();
        bottom.add(check);
        check.addActionListener(this);
        bottom.add(purchase);
        purchase.addActionListener(this);
        bottom.add(stock);
        stock.addActionListener(this);
        bottom.add(quit);
        quit.addActionListener(this);
        add("South", bottom);

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            CheckStock checkStock = new CheckStock();
        } else if (e.getSource() == quit) {
            StockData.close();
            System.exit(0);
        }
        if (e.getSource() == purchase){
            PurchaseItem purchaseItem = new PurchaseItem();
        } else if (e.getSource()== stock){
            UpdateStock updateStock = new UpdateStock();
        }
        
    }
}
