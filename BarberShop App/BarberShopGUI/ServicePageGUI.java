package BarberShopGUI;
import BarberShop.BarberShop;
import BarberShopComponents.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Dimension;

// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------


public class ServicePageGUI implements ActionListener {
    // members:
    private BarberShop _barberShop;
    private JFrame _frame = new JFrame("Sevices Page");
    private JPanel _panel;
    private JButton _addServiceButton = new JButton("Add Service");
    private JButton _deleteServiceButton = new JButton("Delete Service");
    private JButton _showServiceButton = new JButton("Show Service");
    private JButton _showAllServiceButton = new JButton("Show All Services");
    private JButton _backButton = new JButton("Back");
    private JButton _sortServicesButton = new JButton("Sort Services By Name");
    private JButton _editServiceButton = new JButton("Edit Service");

    // constructor:
    public ServicePageGUI(BarberShop barberShop) {
        // Set the barber shop
        this._barberShop = barberShop;
        // Create the frame
        this.setFrame();

        // Create a JPanel with a custom background color or image
        this._panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                 ImageIcon imageIcon = new ImageIcon("src/BarberShop/BarberBackround.jpeg");
                 Image image = imageIcon.getImage();
                 g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel titleLabel = new JLabel("Services Page");

        // Set the title's font
        titleLabel.setFont(new Font("Brush Script", Font.BOLD, 60));

        // Set the title's color
        titleLabel.setForeground(new Color(255, 255, 255));

        // Set the title's position
        titleLabel.setBounds(295, 0, 500, 100);

        // Set the title's background
        titleLabel.setOpaque(false);

        // Set the buttons position
        this.setBackButton();
        this.setAddServiceButton();
        this.setDeleteServiceButton();
        this.setShowServiceButton();
        this.setShowAllServiceButton();
        this.setSortServicesButton();
        this.setEditServiceButton();
        

        // Add the title to the panel
        this._panel.add(titleLabel);
        
        // Add the buttons to the panel
        this._panel.add(this._backButton);
        this._panel.add(this._addServiceButton);
        this._panel.add(this._showServiceButton);
        this._panel.add(this._showAllServiceButton);
        this._panel.add(this._sortServicesButton);
        this._panel.add(this._editServiceButton);
        this._panel.add(this._deleteServiceButton);



        // set the panel's layout
        this._panel.setLayout(null);

        // add the panel to the frame
        this._frame.getContentPane().add(this._panel);
        // set visible
        this._frame.setVisible(true);
            
    }


    private void setFrame()
    {
        // Set the frame's default close operation to exit on close
        this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame's size to 1000x667
        this._frame.setSize(1000, 667);
        // Set the frame to be not resizable
        this._frame.setResizable(false);
    }
    
    // Button functions
    
    //ADD
    private void setAddServiceButton() {
        this._addServiceButton = new JButton("Add Service");
        this._addServiceButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._addServiceButton.setForeground(Color.white);
        this._addServiceButton.setBackground(Color.white);
        this._addServiceButton.setOpaque(false);
        this._addServiceButton.setBorderPainted(false);
        this._addServiceButton.setFocusable(true);
        // set button position
        this._addServiceButton.setBounds(350, 230, 350, 50);
        // set the button's listener
        this._addServiceButton.addActionListener(this);
    }
    
    //DELETE
    private void setDeleteServiceButton() {
        // create the button
        this._deleteServiceButton = new JButton("Delete Service");
        // set button font
        this._deleteServiceButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._deleteServiceButton.setForeground(Color.white);
        this._deleteServiceButton.setBackground(Color.white);
        this._deleteServiceButton.setOpaque(false);
        this._deleteServiceButton.setBorderPainted(false);
        this._deleteServiceButton.setFocusable(true);
        // set button position
        this._deleteServiceButton.setBounds(350, 300, 350, 50);
        // set the button's listener
        this._deleteServiceButton.addActionListener(this);
    }
    
    //EDIT
    private void setEditServiceButton() {
        // create the button
        this._editServiceButton = new JButton("Edit Service");
        // set button font
        this._editServiceButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._editServiceButton.setForeground(Color.white);
        this._editServiceButton.setBackground(Color.white);
        this._editServiceButton.setOpaque(false);
        this._editServiceButton.setBorderPainted(false);
        this._editServiceButton.setFocusable(true);
        // set button position
        this._editServiceButton.setBounds(350, 370, 350, 50);
        // set the button's listener
        this._editServiceButton.addActionListener(this);
    }
    
    //SHOW SERVICES
    private void setShowServiceButton() {
        this._showServiceButton = new JButton("Show Service");
        this._showServiceButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._showServiceButton.setForeground(Color.white);
        this._showServiceButton.setBackground(Color.white);
        this._showServiceButton.setOpaque(false);
        this._showServiceButton.setBorderPainted(false);
        this._showServiceButton.setFocusable(true);
        // set button position
        this._showServiceButton.setBounds(350, 440, 350, 50);
        // set the button's listener
        this._showServiceButton.addActionListener(this);
    }
    
    //SHOW ALL SERVICES
    private void setShowAllServiceButton() {
        this._showAllServiceButton = new JButton("Show All Services");
        this._showAllServiceButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._showAllServiceButton.setForeground(Color.white);
        this._showAllServiceButton.setBackground(Color.white);
        this._showAllServiceButton.setOpaque(false);
        this._showAllServiceButton.setBorderPainted(false);
        this._showAllServiceButton.setFocusable(true);
        // set button position
        this._showAllServiceButton.setBounds(350, 510, 350, 50);
        // set the button's listener
        this._showAllServiceButton.addActionListener(this);
    }
    
    //SORT 
    private void setSortServicesButton() {
        this._sortServicesButton = new JButton("Sort Services");
        this._sortServicesButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._sortServicesButton.setForeground(Color.white);
        this._sortServicesButton.setBackground(Color.white);
        this._sortServicesButton.setOpaque(false);
        this._sortServicesButton.setBorderPainted(false);
        this._sortServicesButton.setFocusable(true);
        // set button position
        this._sortServicesButton.setBounds(350, 580, 350, 50);
        // set the button's listener
        this._sortServicesButton.addActionListener(this);
    }
    
    //BACK
    private void setBackButton() {
        this._backButton = new JButton("Back");
        this._backButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._backButton.setForeground(Color.white);
        this._backButton.setBackground(Color.white);
        this._backButton.setOpaque(false);
        this._backButton.setBorderPainted(false);
        this._backButton.setFocusable(true);
        // set button position
        this._backButton.setBounds(0, 550, 250, 50);
        // set the button's listener
        this._backButton.addActionListener(this);
    }
    

    private boolean isValidName(String name)
    {
        int comperName=name.compareTo(" ");
        boolean isSpace = false;
        if(comperName==0)
        {
            isSpace = true;
        }
        //return true only if the name contains only letters
        return name.matches("[a-zA-Z\\s]+") && !isSpace;
    }

    private boolean isValidPrice(String price)
    {
    	
    	if(!price.matches("\\d+") ) {
    		return false;
    	}
    	
    	int IntPrice = Integer.parseInt(price);
        if(IntPrice < 0)
        {
            return false;
        }
        // return false if price is null or empty
        if (price == null || price.isEmpty()) {
            return false;
        }

        // return true only if the price contains only digits
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this._backButton)
        {
            this._frame.dispose();
            new BarberShopHomePageGUI(this._barberShop);
        }
        
        //Add service
        else if( this._addServiceButton == e.getSource()) {
        	//this._frame.dispose();
        	String serviceName; 
        	String servicePrice;
        	String serviceExpenses;
        			
        	while (true) {
                // create a pop-up window to ask for the customer's details
        		// get service's name:
        		serviceName = JOptionPane.showInputDialog(this._frame, "Enter the service's name:",
                        "Add Service", JOptionPane.PLAIN_MESSAGE);
                //if user press cancel or close the window:
                if (serviceName == null) {
                    return;
                }
                // check if the name is valid
                if (this.isValidName(serviceName)) {
                    // name is valid, moving to the next input
                    break;
                }
                else {
                    // create a pop-up window to notify the user that the name is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid name, please try again: ",
                            "Add Service", JOptionPane.ERROR_MESSAGE);
                }
            }
        	
        	while (true) {
        		//get service's price:
        		servicePrice = JOptionPane.showInputDialog(this._frame, "Enter the service's price:",
                        "Add Service", JOptionPane.PLAIN_MESSAGE);
                //if user press cancel or close the window:
                if (servicePrice == null) {
                    return;
                }
                // check if the name is valid
                if (this.isValidPrice(servicePrice)) {
                    // name is valid, moving to the next input
                    break;
                }
                else {
                    // create a pop-up window to notify the user that the price is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid price, please try again: ",
                            "Add Service", JOptionPane.ERROR_MESSAGE);
                }
            }
        	
        	while (true) {
        		//get service's expenses:
        		serviceExpenses = JOptionPane.showInputDialog(this._frame, "Enter the service's expenses:",
                        "Add Service", JOptionPane.PLAIN_MESSAGE);
                //if user press cancel or close the window:
                if (serviceExpenses == null) {
                    return;
                }
                // check if the input expenses is valid
                if (this.isValidPrice(serviceExpenses)) {
                    // name is valid, moving to the next input
                    break;
                }
                else {
                    // create a pop-up window to notify the user that the name is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid expenses cost, please try again: ",
                            "Add Service", JOptionPane.ERROR_MESSAGE);
                }
            }
        	
        	 int servicePriceInt =  Integer.parseInt(servicePrice);
        	 int serviceExpensesInt = Integer.parseInt(serviceExpenses);
            this._barberShop.addService(new Service(serviceName, servicePriceInt, serviceExpensesInt));
            
            // Notify the user that the service was added successfully
            JOptionPane.showMessageDialog(this._frame, "Service was added successfully.",
                    "Add Service", JOptionPane.INFORMATION_MESSAGE);
            // check
            System.out.println(this._barberShop.getServiceList());
        	
        }
        
        //delete Service
        else if( this._deleteServiceButton == e.getSource()) {
        	if (this._barberShop.getServiceList().size() != 0) {
        		// create a pop-up window to ask for the service's name
                String name = JOptionPane.showInputDialog(this._frame, "Enter the service's name:",
                        "Remove Service", JOptionPane.PLAIN_MESSAGE);

                // check if the user pressed cancel or closed the window
                if (name == null) {
                    return;
                }
                
                // check if the name exists in the barber shop services list
                if (!this._barberShop.isServiceExists(name)) {
                    // create a pop-up window to notify the user that the service does not exist
                    JOptionPane.showMessageDialog(this._frame, "Service does not exist, please try again.",
                            "Remove Service", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // get the customer object from the barber shop services list
                Service serviceToRemove = this._barberShop.getServiceByName(name);
                
                // remove the customer from the barber shop
                this._barberShop.removeService(serviceToRemove);
                
                // remove the service from the customer list 
                this._barberShop.deleteServiceFromCustomerList(serviceToRemove);

                //notify the user that the customer was removed
                JOptionPane.showMessageDialog(this._frame, "The Service was removed successfully.",
                        "Remove Service", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else {
        		 // notify the user that the list is empty
                JOptionPane.showMessageDialog(this._frame, "Services list is empty.",
                        "Empty List", JOptionPane.ERROR_MESSAGE);
        	}
        	
        }
        
        //Show service:
        else if( this._showServiceButton == e.getSource()) {
        	//get the service's name
            String serviceName = JOptionPane.showInputDialog(this._frame, "Enter the service's name:",
                    "Show Service", JOptionPane.PLAIN_MESSAGE);
            // check if the user pressed cancel or closed the window
            if (serviceName == null) {
                return;
            }
            
            // check if the service exists
            Service serviceToShow = this._barberShop.getServiceByName(serviceName);
            if (serviceToShow != null) {
                // display the customer's details
                JOptionPane.showMessageDialog(this._frame, serviceToShow.toString(),
                        "Show Service", JOptionPane.INFORMATION_MESSAGE);
            }
            // if the customer was not found, notify the user
            else {
                JOptionPane.showMessageDialog(this._frame, "Service was not found.",
                        "Show Service", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Show all services:
        else if( this._showAllServiceButton == e.getSource()) {
        	if (this._barberShop.getServiceList().size() != 0) {
                // iterate over customers list and call each customer toString method to build a string
                String allServices = "";
                for (Service service : this._barberShop.getServiceList()) {
                	allServices += service.toString() + "\n ------------------------ \n";
                }

                // display all customers
                JOptionPane.showMessageDialog(this._frame, allServices,
                        "Show All Services", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // notify the user that the list is empty
                JOptionPane.showMessageDialog(this._frame, "Services list is empty.",
                        "Show All Services", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Sort Services List:
        else if (e.getSource() == this._sortServicesButton) {
            // sort the customers list by lexicographic order
            int result = this._barberShop.sortByServiceName();
            // check result flag
            if (result == 0) {
                // notify the user that the list was sorted
                JOptionPane.showMessageDialog(this._frame, "Services list was sorted successfully.",
                        "Sort Services", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // notify the user that the list was already sorted
                JOptionPane.showMessageDialog(this._frame, "Services list is empty.",
                        "Sort Services", JOptionPane.ERROR_MESSAGE);
            }
            
            // check
            System.out.println(this._barberShop.getServiceList());
        }
        
        
      //Edit Service:
        else if (e.getSource() == this._editServiceButton) {
            // create a pop-up window to ask for the customer's name
            String name = JOptionPane.showInputDialog(this._frame, "Enter the service's name:",
                    "Edit Service", JOptionPane.PLAIN_MESSAGE);
            // check if the user pressed cancel or closed the window
            if (name == null) {
                return;
            }

            // check if the name exists in the barber shop services list
            if(this._barberShop.isServiceExists(name))
            {
                // get the customer object from the barber shop customers list
                Service serviceToEdit = this._barberShop.getServiceByName(name);

                // check if the service exsits in the barber shop services list
                if (serviceToEdit == null) {
                    // create a pop-up window to notify the user that the customer does not exist
                    JOptionPane.showMessageDialog(this._frame, "Service does not exist, please try again.",
                            "Edit Service", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ask for the service detail to edit
                // Declare instance variables
                JList<String> ServicesList;

                String[] ServiceDetails = {"Name", "Price", "Expenses"};

                // Create a JList with the array of strings as the data
                ServicesList = new JList<>(ServiceDetails);

                // Create a JScrollPane and add the JList to it
                JScrollPane scrollPane = new JScrollPane(ServicesList);
                scrollPane.setPreferredSize(new Dimension(350, 200));

                // Show the JOptionPane with the JScrollPane as the message and get the user's choice
                JOptionPane.showOptionDialog(this._frame, scrollPane,
                        "Choose what you want to edit:",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);

                // get the user's choice
                String selectedService = ServicesList.getSelectedValue();

                // Check the user's choice
                // ----------------------------Name:
                if (selectedService.equals("Name"))
                {

                    //validate the name
                    while (true)
                    {
                        // create a pop-up window to ask for the customer's name
                        String newName = JOptionPane.showInputDialog(this._frame, "Enter the service's new name:",
                                "Edit Service", JOptionPane.PLAIN_MESSAGE);

                        // check if the name is valid
                        if (this.isValidName(newName)) {
                            // update the customer's name
                            serviceToEdit.setName(newName);
                            break;
                        } else
                            JOptionPane.showMessageDialog(this._frame, "Invalid name, please try again.",
                                    "Edit Service", JOptionPane.ERROR_MESSAGE);
                    }

//                    // check
//                    System.out.println(this._barberShop.getCustomersList());
        }
                
                // ----------------------Price:
                else if (selectedService.equals("Price"))
                {
                    //validate the phone number
                    while (true)
                    {
                        // create a pop-up window to ask for the customer's name
                        String newPrice = JOptionPane.showInputDialog(this._frame, "Enter the service's price:",
                                "Edit Service", JOptionPane.PLAIN_MESSAGE);

                        int newPriceInt = Integer.parseInt(newPrice);
                        
                        if (this.isValidPrice(newPrice)) {
                            // change the customer's phone number
                            serviceToEdit.setPrice(newPriceInt);
                            break;
                        } 
                        else
                            JOptionPane.showMessageDialog(this._frame, "Invalid price, please try again.",
                                    "Edit Service", JOptionPane.ERROR_MESSAGE);
                    }

                    // check
                    System.out.println(this._barberShop.getCustomersList());
                }
                
                // ------------------------Expenses:
                else if (selectedService.equals("Expenses")) {
                    //validate the expenses
                    while (true)
                    {
                        //create a pop-up window to ask for the service's
                        String newExpenses = JOptionPane.showInputDialog(this._frame, "Enter the service's new expenses number:",
                                "Edit Service", JOptionPane.PLAIN_MESSAGE);
                        
                        int newExpensesInt = Integer.parseInt(newExpenses);

                        // check if the card number is valid
                        if (this.isValidPrice(newExpenses)) {
                            // change the customer's phone number
                        	serviceToEdit.setExspenesFromService(newExpensesInt);
                            break;
                        } else
                            JOptionPane.showMessageDialog(this._frame, "Invalid expenses number, please try again.",
                                    "Edit Service", JOptionPane.ERROR_MESSAGE);
                    }
//                    // check
//                    System.out.println(this._barberShop.getCustomersList());
                }
            }
            //if the service to edit dosent exist in the list:
            else {
            	JOptionPane.showMessageDialog(this._frame, "The service doesn't exist, please try again.",
                        "Edit Service", JOptionPane.ERROR_MESSAGE);
            }
            
         // Notify the user that the customer was editted successfully
            JOptionPane.showMessageDialog(this._frame, "Service was editted successfully.",
                    "Edit Service", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}  
        
        

	

