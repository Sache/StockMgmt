package stock; //name of the Project folder.

import java.awt.BorderLayout; //Enables the access of BorderLayout class by this class.
import java.awt.event.ActionEvent; //Provides access to ActionEvent class.
import java.awt.event.ActionListener; //activates the the use of ActionListner class which allows events to be listned. For example- detecting button being pressed
import java.text.DecimalFormat;////enables the conversion of a number written as text into decimal format.
import javax.swing.ImageIcon; // Imports the ImageIcon library
import javax.swing.JButton; // Enable the use of buttons within GUI.
import javax.swing.JFrame; // allows use of top level window that includes window with title and broder.
import javax.swing.JLabel; //Allows access of JLablel class that enables display of text within GUI.
import javax.swing.JPanel; // Allows JPanel class to be accessed by this class.
import javax.swing.JTextArea; // Access class which enables user input of text within GUI.
import javax.swing.JTextField; // Provides access to JTextField class which create a rectangular GUI box where the user can input text.

public class CheckStock extends JFrame implements ActionListener { // Enables the use of JFrame class and Action Listener.

    JTextField stockNo = new JTextField(7); // provides variable for JTextField
    JTextArea information = new JTextArea(7, 40);// allows JTextArea to be 
    JButton check = new JButton("Check Stock"); // create  a variable for a JButton
    DecimalFormat pounds = new DecimalFormat("£#,##0.00"); // The value of the variable 'pounds' will set to £.
    JLabel imgLabel; // JLabel assigned to a variable
    private ImageIcon image2;// variable assigned with ImageIcon
    

    public CheckStock() { 
        
        setLayout(new BorderLayout()); // Allows GUI to fit in five regions; notrh, south, east, west and center.
        setBounds(600, 300, 600, 200); // Specifies the size and position of the GUI.
        setTitle("Check Stock"); //sets the title "Check Stock" on the main frame.
        //close application when the use clicks on the exit button.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // It will close the GUI of 'CheckStock.java' only when the user clicks on exit(x) button.
        JPanel top = new JPanel(); // assigns a variable called 'top' for JPanel
        top.add(new JLabel("Enter Stock Number:")); //Adds 'Enter Stock Number' within the GUI.
        top.add(stockNo); // Adds text field on 'top' JPanel.
        top.add(check); // Adds JButton on 'top' JPanel.
        check.addActionListener(this); // Will detect when 'check' button is pressed.
        add("North", top); // Sets the region of 'top'  as north region of the BorderLayout used.
        JPanel west = new JPanel(); //Assigns variable called 'middle' for JPanel.
        west.add(information); //places text area 
        add("West", west); // Sets the region of the variable middle as center of region on the border layout.
        JPanel right = new JPanel(); // JPanel is created assigning "right" as variable.
        image2 = new ImageIcon(getClass().getResource("res/product.jpg"));// Looks for the jpg image file 
        imgLabel = new JLabel(image2); //makes ImageIcon an jLabel object
        right.add(imgLabel); //adds imgLabel on right JPanel
        getContentPane().add(imgLabel); // Adjusts the image location 
        add("East", right); // Places "right" JPanel onto the East side of the border layout.
        setResizable(false);//disallows the user to change the size of the frame.
        setVisible(true); //Makes the GUI appear on screen. 
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Executes the codes below when 'check' button is pressed.
        String key = stockNo.getText(); //Returns text value of 'stockNo' and save it to 'key' variable
        String name = StockData.getName(key); // Retuns 'StockData' class with key.
        //If "name" does match with null, then "information" JTextArea will display "No such item in stock"
        if (name == null) { //if name is equal to null then the code below this line will be executed.
            information.setText("No such item in stock"); //text within information variable will be set as 'No such item in stock'.
        } else { // if name is not equal to null then then 3 lines code below will be executed.
            information.setText(name); //sets the value of 'information' variable same as 'name' variable.
            information.append("\nPrice: " + pounds.format(StockData.getPrice(key)));//updates the value of 'information' variable.
            information.append("\nNumber in stock: " + StockData.getQuantity(key)); //updates the value of 'information' variable.
        }
                try{
        if (e.getSource() == check) {        
        image2 = new ImageIcon(getClass().getResource("res/" + key + ".jpg"));
        imgLabel.setIcon(image2);
        }
    }catch (Exception f){
    System.out.println("The imge file doesn't exist or the file extensin is wrong.");    
    }
    }
}
