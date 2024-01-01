package BarberShopGUI;
import BarberShop.BarberShop;
import BarberShopComponents.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class CashRegisterPageGUI implements ActionListener {

	// members:
	private BarberShop _barberShop;
	private JFrame _frame = new JFrame("Customers Page");
	private JPanel _panel;
	private JButton _backButton = new JButton("Back");
	private JButton _expenseButton = new JButton("Show Expenses");
	private JButton _incomeButton = new JButton("Show Incomes");
	private JButton _netProfitButton = new JButton("Show Net Profit");
	private JButton _addPaymentButton = new JButton("Add Paymnet");
	private JButton _refoundCustomerButton = new JButton("Refound Customer");

	// constructor:
	public CashRegisterPageGUI(BarberShop barberShop) {
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

		JLabel titleLabel = new JLabel("CashRegister Page");

		// Set the title's font
		titleLabel.setFont(new Font("Brush Script", Font.BOLD, 60));

		// Set the title's color
		titleLabel.setForeground(new Color(255, 255, 255));

		// Set the title's position
		titleLabel.setBounds(200, 0, 650, 100);

		// Set the title's background
		titleLabel.setOpaque(false);

		// Create the buttons
		this.setBackButton();
		this.setExpenseButton();
		this.setIncomeButton();
		this.setNetProfitButton();
		this.setAddPaymentButton();
		this.setRefoundCustomerButton();

		// Add the title to the panel
		this._panel.add(titleLabel);
		// Add the back button to the panel
		this._panel.add(this._backButton);
		this._panel.add(this._expenseButton);
		this._panel.add(this._incomeButton);
		this._panel.add(this._netProfitButton);
		this._panel.add(this._addPaymentButton);
		this._panel.add(this._refoundCustomerButton);

		// set the panel's layout
		this._panel.setLayout(null);

		// add the panel to the frame
		this._frame.getContentPane().add(this._panel);
		// set visible
		this._frame.setVisible(true);

	}

	// this function is used to create the frame
	private void setFrame() {
		// Set the frame's default close operation to exit on close
		this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the frame's size to 1000x667
		this._frame.setSize(1000, 667);
		// Set the frame to be not resizable
		this._frame.setResizable(false);
	}

	// this function is used to create the button back
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

	// this function is used to create the buttom of expenses
	private void setExpenseButton() {
		// set button font
		this._expenseButton.setFont(new Font("MV Boli", Font.BOLD, 30));
		this._expenseButton.setForeground(Color.white);
		this._expenseButton.setBackground(Color.white);
		this._expenseButton.setOpaque(false);
		this._expenseButton.setBorderPainted(false);
		this._expenseButton.setFocusable(true);
		// set button position
		this._expenseButton.setBounds(330, 260, 350, 50);
		// set the button's listener
		this._expenseButton.addActionListener(this);
	}

	// this function is used to create the buttom of incomes
	private void setIncomeButton() {
		// set button font
		this._incomeButton.setFont(new Font("MV Boli", Font.BOLD, 30));
		this._incomeButton.setForeground(Color.white);
		this._incomeButton.setBackground(Color.white);
		this._incomeButton.setOpaque(false);
		this._incomeButton.setBorderPainted(false);
		this._incomeButton.setFocusable(true);
		// set button position
		this._incomeButton.setBounds(330, 320, 350, 50);
		// set the button's listener
		this._incomeButton.addActionListener(this);
	}

	// this function is used to create the buttom of net profit
	private void setNetProfitButton() {
		// set button font
		this._netProfitButton.setFont(new Font("MV Boli", Font.BOLD, 30));
		this._netProfitButton.setForeground(Color.white);
		this._netProfitButton.setBackground(Color.white);
		this._netProfitButton.setOpaque(false);
		this._netProfitButton.setBorderPainted(false);
		this._netProfitButton.setFocusable(true);
		// set button position
		this._netProfitButton.setBounds(330, 390, 350, 50);
		// set the button's listener
		this._netProfitButton.addActionListener(this);
	}

	// this function is used to create the buttom of add payment
	private void setAddPaymentButton() {
		// set button font
		this._addPaymentButton.setFont(new Font("MV Boli", Font.BOLD, 30));
		this._addPaymentButton.setForeground(Color.white);
		this._addPaymentButton.setBackground(Color.white);
		this._addPaymentButton.setOpaque(false);
		this._addPaymentButton.setBorderPainted(false);
		this._addPaymentButton.setFocusable(true);
		// set button position
		this._addPaymentButton.setBounds(330, 460, 350, 50);
		// set the button's listener
		this._addPaymentButton.addActionListener(this);
	}

	// this function is used to create the buttom of refound customer
	private void setRefoundCustomerButton() {
		// set button font
		this._refoundCustomerButton.setFont(new Font("MV Boli", Font.BOLD, 30));
		this._refoundCustomerButton.setForeground(Color.white);
		this._refoundCustomerButton.setBackground(Color.white);
		this._refoundCustomerButton.setOpaque(false);
		this._refoundCustomerButton.setBorderPainted(false);
		this._refoundCustomerButton.setFocusable(true);
		// set button position
		this._refoundCustomerButton.setBounds(330, 530, 350, 50);
		// set the button's listener
		this._refoundCustomerButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this._backButton) {
			this._frame.dispose();
			new BarberShopHomePageGUI(this._barberShop);
		}

		else if (e.getSource() == this._expenseButton) {
			// get the expenses and save it in string
			int totalExpenses = this._barberShop.getCashRegister().getTotalExpenses();
			String expenses = String.valueOf(totalExpenses);
			// show the expenses
			JOptionPane.showMessageDialog(this._frame, "Your expenses are : " + expenses, "Your expenses",
					JOptionPane.INFORMATION_MESSAGE);

		}

       else if(e.getSource()==this._addPaymentButton)
		{
        	//get the customer's name
    	   String CustomerName=JOptionPane.showInputDialog(this._frame, "Please enter the name of the customer", "Customer name", JOptionPane.PLAIN_MESSAGE);
    	   //if the user press cancel
			if(CustomerName==null) {
				return;
			}
		   //check if the customer name exists
    	   Customer currCustomer=this._barberShop.getCustomerByName(CustomerName);
    	   //customer doesn't exists
    	   if(currCustomer==null)
    	   {
    		   JOptionPane.showMessageDialog(this._frame, "Sorry,the customer name doesn't exsit", "error-customer doesn't found",
   					JOptionPane.ERROR_MESSAGE);
    		   return;
    	   }
    	   else
    	   {
    		   //check if the customer has a service
    		   Service currService=currCustomer.getCurrentService();
    		   if(currService==null)
    		   {
    			   JOptionPane.showMessageDialog(this._frame, "Sorry,the customer  donest has a service", "error-customer doesn't has service",
    	   					JOptionPane.ERROR_MESSAGE);
    			   return;
    		   }
    		   else
    		   {
    			   this._barberShop.getCashRegister().addPayment(currCustomer);
    			   //get the price of the current service
    			   int paymentAmount=currService.getServicePrice();
    			   
    			   // notify the user of the cash register that a payment has been made successfully
    			   JOptionPane.showMessageDialog(this._frame, "Payment of " + paymentAmount + " has been made successfully by "+currCustomer.getName(), "successfully payment",
      	   					JOptionPane.INFORMATION_MESSAGE);
    		       
    		        // thank to the customer for the payment
    		        JOptionPane.showMessageDialog(this._frame, "Thank you for your payment  " + currCustomer.getName() + " and see you again soon!", "thank you!",
      	   					JOptionPane.INFORMATION_MESSAGE);
    		        
    		        //after the payment put null in the current service
    		       this._barberShop.getCustomerByName(CustomerName).set_currService(null);
    		   }
    	   }
        }

		else if (e.getSource() == this._incomeButton) {
			// get the incomes and save it in string
			int totalIncomes = this._barberShop.getCashRegister().getTotalBalance();
			String incomes = String.valueOf(totalIncomes);
			// show the expenses
			JOptionPane.showMessageDialog(this._frame, "Your incomes are : " + incomes, "Your incomes",
					JOptionPane.INFORMATION_MESSAGE);
		}
		  else if(e.getSource()==this._netProfitButton) {
		  
			  //get the net profit
			  int netProfit=this._barberShop.getCashRegister().getTotalNetProfit();
			//check if we are in minus
		    	if(netProfit<0)
		    	{
		    		 //show warnig message since the net profit is negative
		    		JOptionPane.showMessageDialog(this._frame, "Be careful you are in  minus of : " +String.valueOf(netProfit), "net Profit", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else
		    	{
		    		//show the net profit
		    		JOptionPane.showMessageDialog(this._frame, "Your net profit is : " +String.valueOf(netProfit), "net Profit", JOptionPane.INFORMATION_MESSAGE);
		    	}
		  }
		
		  else if (e.getSource()==this._refoundCustomerButton)
		  {
			//get the customer's name
	    	   String CustomerName=JOptionPane.showInputDialog(this._frame, "Please enter the name of the customer", "Customer name", JOptionPane.PLAIN_MESSAGE);
	    	   if (CustomerName==null)
	    	   {
	    		   return;
	    	   }

			   //check if the customer name is exsit
	    	   Customer currCustomer=this._barberShop.getCustomerByName(CustomerName);
	    	   //customer doesn't exsit
	    	   if(currCustomer==null)
	    	   {
	    		   JOptionPane.showMessageDialog(this._frame, "Sorry,the customer name doesn't exsit", "error-customer doesn't found",
	   					JOptionPane.ERROR_MESSAGE);
	    		   return;
	    	   }
	    	   else
	    	   {
	    		   //ask from the user to choice the treatment for refound
	    		   JOptionPane.showMessageDialog(this._frame, "Please choose a treatment for refound","refound", JOptionPane.INFORMATION_MESSAGE);

	               // Declare instance variables
	               JList<String> ServicesList;

	               // Retrieve the list of services names from the barber shop
	               String[] servicesNames = this._barberShop.getServicesNames();
	               // append a string to the end of each service name
	               // Create a new array with a larger size
	               String[] servicesNamesWithNone = new String[servicesNames.length + 1];

	               // Copy elements from the original array to the new array
	               System.arraycopy(servicesNames, 0, servicesNamesWithNone, 0, servicesNames.length);

	               // Append the new string to the end of the new array
	               servicesNamesWithNone[servicesNamesWithNone.length - 1] = "Cancel";

	               // Create a JList with the array of strings as the data
	               ServicesList = new JList<>(servicesNamesWithNone);

	               // Create a JScrollPane and add the JList to it
	               JScrollPane scrollPane = new JScrollPane(ServicesList);
	               scrollPane.setPreferredSize(new Dimension(250, 300));

	               // Show the JOptionPane with the JScrollPane as the message and get the user's choice
	               int choice = JOptionPane.showOptionDialog(this._frame, scrollPane,
	                       "Choose the services for the customer:",
	                       JOptionPane.DEFAULT_OPTION,
	                       JOptionPane.PLAIN_MESSAGE,
	                       null,
	                       null,
	                       null);

	               // get the user's choice
	               String selectedService = ServicesList.getSelectedValue();
	               
	               if(selectedService.equals("Cancel"))
	               {
	            	   return;
	               }
	               
	               else 
	               {
	            	   //refound the customer by his choice
	            	   Service currentRefound=this._barberShop.getServiceByName(selectedService);
	            	   this._barberShop.getCashRegister().RefoundCustomer(currentRefound);
	                   
	            	   //get the price of the Refound service
	    			   int paymentAmount=currentRefound.getServicePrice();

	    			   //tell to the customer that the refound successfully executed
	    			   JOptionPane.showMessageDialog(this._frame, "Refound of " + paymentAmount + " has been made successfully for " +currCustomer,"refound successfully executed", JOptionPane.INFORMATION_MESSAGE);
	                   
	                   // thanks to the customer
	    			   JOptionPane.showMessageDialog(this._frame,"Thanks " + currCustomer.getName() + " and see you again soon!","thank you", JOptionPane.INFORMATION_MESSAGE);
	               }
	    	   }
		  }
	}
}
