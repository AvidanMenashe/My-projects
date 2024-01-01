package BarberShopGUI;
import BarberShopComponents.Customer;
import BarberShop.BarberShop;
import BarberShopComponents.Event;
import BarberShopComponents.WomenEvent;
import BarberShopComponents.ManEvent;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------


public class CalendarPageGUI implements ActionListener 
{
    // members:
    private BarberShop _barberShop;
    private JFrame _frame = new JFrame("Calendar Page");
    private JPanel _panel;
    private JButton _backButton = new JButton("Back");

    //Customer object buttons
    private JButton _addEventButton = new JButton("Add event to the calendar");
    private JButton _removeEventButton = new JButton("Remove event from the calendar");
    private JButton _showAllEventsButton = new JButton("Print All Events");
    private JButton _showAllEventsByCustomerButton = new JButton("Print All Events By Customer");
    private JButton _showAllEventsByDateButton = new JButton("Print All Events By Date");
  //private JButton _editEventButton = new JButton("Edit event details");
    //private JButton _showEventButton = new JButton("Show event details");

    public static final int FEMALE = 0;
    public static final int MALE = 1;

    // constructor:
    public CalendarPageGUI(BarberShop barberShop) {
        // Set the barber shop
        this._barberShop = barberShop;
        // Create the frame
        this.setFrame();

        // Create a JPanel with a custom background color or image
        this._panel = new JPanel() {
            //@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                 ImageIcon imageIcon = new ImageIcon("src/BarberShop/CalendarBackGround.jpeg");
                 Image image = imageIcon.getImage();
                 g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel titleLabel = new JLabel("Calendar Page");

        // Set the title's font
        titleLabel.setFont(new Font("Brush Script", Font.BOLD, 60));

        // Set the title's color
        titleLabel.setForeground(new Color(0, 0, 0));

        // Set the title's position
        titleLabel.setBounds(295, 0, 500, 100);

        // Set the title's background
        titleLabel.setOpaque(false);

        // Set the back button's position
        this.setBackButton();

        // Set functionality buttons
        this.setAddEventButton();
        this.setRemoveEventButton();
        this.setShowAllEventsButton();
        this.setShowAllEventsByCustomerButton();
        this.setShowAllEventsByDateButton();
        

        // Add the title to the panel
        this._panel.add(titleLabel);
        // Add the back button to the panel
        this._panel.add(this._backButton);

        // set the panel's layout
        this._panel.setLayout(null);

        // add the panel to the frame
        this._frame.getContentPane().add(this._panel);
        // set visible
        this._frame.setVisible(true);

    }

    private void setAddEventButton() {
        this._addEventButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        this._addEventButton.setForeground(Color.black);
        this._addEventButton.setBackground(Color.black);
        this._addEventButton.setOpaque(false);
        this._addEventButton.setBorderPainted(false);
        this._addEventButton.setFocusable(true);
        // set button position
        this._addEventButton.setBounds(50, 100, 450, 50);
        // set the button's listener
        this._addEventButton.addActionListener(this);
        // add the button to the panel
        this._panel.add(this._addEventButton);
    }

    private void setRemoveEventButton() {
        this._removeEventButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        this._removeEventButton.setForeground(Color.black);
        this._removeEventButton.setBackground(Color.black);
        this._removeEventButton.setOpaque(false);
        this._removeEventButton.setBorderPainted(false);
        this._removeEventButton.setFocusable(true);
        // set button position
        this._removeEventButton.setBounds(50, 170, 500, 50);
        // set the button's listener
        this._removeEventButton.addActionListener(this);
        // add the button to the panel
        this._panel.add(this._removeEventButton);
    }

    private void setShowAllEventsByCustomerButton() {
        this._showAllEventsByCustomerButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        this._showAllEventsByCustomerButton.setForeground(Color.black);
        this._showAllEventsByCustomerButton.setBackground(Color.black);
        this._showAllEventsByCustomerButton.setOpaque(false);
        this._showAllEventsByCustomerButton.setBorderPainted(false);
        this._showAllEventsByCustomerButton.setFocusable(true);
        // set button position
        this._showAllEventsByCustomerButton.setBounds(50, 240, 450, 50);
        // set the button's listener
        this._showAllEventsByCustomerButton.addActionListener(this);
        // add the button to the panel
        this._panel.add(this._showAllEventsByCustomerButton);
    }

    private void setShowAllEventsButton() {
        this._showAllEventsButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        this._showAllEventsButton.setForeground(Color.black);
        this._showAllEventsButton.setBackground(Color.black);
        this._showAllEventsButton.setOpaque(false);
        this._showAllEventsButton.setBorderPainted(false);
        this._showAllEventsButton.setFocusable(true);
        // set button position
        this._showAllEventsButton.setBounds(50, 310, 450, 50);
        // set the button's listener
        this._showAllEventsButton.addActionListener(this);
        // add the button to the panel
        this._panel.add(this._showAllEventsButton);
    }

    private void setShowAllEventsByDateButton() {
        this._showAllEventsByDateButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        this._showAllEventsByDateButton.setForeground(Color.black);
        this._showAllEventsByDateButton.setBackground(Color.black);
        this._showAllEventsByDateButton.setOpaque(false);
        this._showAllEventsByDateButton.setBorderPainted(false);
        this._showAllEventsByDateButton.setFocusable(true);
        // set button position
        this._showAllEventsByDateButton.setBounds(50, 380, 450, 50);
        // set the button's listener
        this._showAllEventsByDateButton.addActionListener(this);
        // add the button to the panel
        this._panel.add(this._showAllEventsByDateButton);
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

    private void setBackButton() {
        this._backButton = new JButton("Back");
        this._backButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._backButton.setForeground(Color.black);
        this._backButton.setBackground(Color.black);
        this._backButton.setOpaque(false);
        this._backButton.setBorderPainted(false);
        this._backButton.setFocusable(true);
        // set button position
        this._backButton.setBounds(0, 550, 250, 50);
        // set the button's listener
        this._backButton.addActionListener(this);
    }

    public Date createDateFromUserInput(String userInput) throws ParseException 
    {
        // Define the date format according to your GUI
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.parse(userInput);
    }

    public Date createDateFromUserInputNoHours(String userInput) throws ParseException
    {
        // Define the date format according to your GUI
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(userInput);
    }
    
    //@Override
    public void actionPerformed(ActionEvent e) 
{

        if (e.getSource() == this._backButton) {
            this._frame.dispose();
            new BarberShopHomePageGUI(this._barberShop);
        }
        //--------------------------------------------------------------------------------------------------------------
        else if (e.getSource() == this._addEventButton) 
        {
            // each input will be checked for validity, and it will be asked again if it is invalid
        	Date date;
        	int eventTime;
        	String eventTimeStr;
        	Customer customer;
        	String customerName= "";
            String genderStr = "";
            String userInput= "";
            int gender;
            String isCurlyStr= "";
            boolean isCurly;
            boolean hasBeard;
            String hasBeardStr = "";
            
            
            while (true) 
            {
                // create a pop-up window to ask for the event's details
            	userInput = JOptionPane.showInputDialog(this._frame,
                        "Enter the date in this format: dd/MM/yyyy HH:mm " +
                                "\n years lower then 2023 wont be excepted",
                        "Add Event", JOptionPane.PLAIN_MESSAGE);
                // if user pressed cancel or closed the window
                if (userInput == null)
                {
                    date = null;
                    return;
                }
                try 
                {
                    date = createDateFromUserInput(userInput);
                    //date is valid, moving to the next input
                    if (date.getDay() > 0 &&
                            date.getDay() < 32 &&
                            date.getHours() >= 0 &&
                            date.getHours()<25 &&
                            date.getMinutes()>=0 &&
                            date.getMinutes()<60 &&
                            date.getMonth()>=0 &&
                            date.getMonth()<13 &&
                            date.getYear() >= 123)
                    //123 == 2023 - 1900
                    {
                        // eventTime is valid
                        break;
                    }
                    else {
                        // create a pop-up window to notify the user that the event time is invalid
                        JOptionPane.showMessageDialog(this._frame, "Invalid date and time, please try again",
                                "Add Event", JOptionPane.ERROR_MESSAGE);

                    }
                } 
                catch (ParseException err) 
                {
                	// create a pop-up window to notify the user that the name is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid date and time format, please try again: ",
                            "Add Event", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            while(true) 
            {
                // get the event time
            	eventTimeStr = JOptionPane.showInputDialog(this._frame, "Please enter the event time: "
                        , "Add Event", JOptionPane.PLAIN_MESSAGE);
                // validate the event time type
            	eventTime = Integer.parseInt(eventTimeStr);
                if (eventTime>=0) 
                {
                    // eventTime is valid
                    break;
                } 
                else 
                {
                    // create a pop-up window to notify the user that the event time is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid event time, please try again",
                            "Add Event", JOptionPane.ERROR_MESSAGE);
                }

            }
            

            while (true) 
            {
                // create a pop-up window to ask for the event's details
            	customerName = JOptionPane.showInputDialog(this._frame, "Enter the customer's name:",
                        "Add Event", JOptionPane.PLAIN_MESSAGE);
                // check if the name is valid
            	customer = this._barberShop.getCustomerByName(customerName);
                if (customer != null) 
                {
                    // name is valid, moving to the next input
                    break;
                }
                else 
                {
                    // create a pop-up window to notify the user that the name is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid name, please try again: ",
                            "Add Event", JOptionPane.ERROR_MESSAGE);
                }
            }
            

            if(customer.getGender() == 0)
            {
            	isCurlyStr = JOptionPane.showInputDialog(this._frame, "Please enter if the customer is curly: " +
                        "\n(1 - for yes, 0 for no)", "Add Event", JOptionPane.PLAIN_MESSAGE);
                // validate the type
                if (isCurlyStr.equals("1")) 
                {
                	isCurly = true;
                    // if flag is true, then make meeting duration plus 15 minutes longer because curly hair takes more time
                    eventTime += 15;
                }
                else if(isCurlyStr.equals("0"))
                {
                	isCurly = false;
                }

                else 
                {
                	isCurly = false;
                    // create a pop-up window to notify the user that the answer is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid answer, please try again",
                            "Add Event", JOptionPane.ERROR_MESSAGE);
                }
            	
            	this._barberShop.getCalendar().addEvent(new WomenEvent(date, eventTime,customer , isCurly));
            }
            //if it's a man
            else if(customer.getGender() == 1)
            {
                hasBeardStr = JOptionPane.showInputDialog(this._frame, "Please enter if the customer has beard: " +
                        "\n(1 - for yes, 0 for no)", "Add Event", JOptionPane.PLAIN_MESSAGE);
                // validate the type
                if (hasBeardStr.equals("1"))
                {
                    hasBeard = true;
                    // if flag is true, then make meeting duration plus 10 minutes longer because beard takes more time
                    eventTime += 10;
                    //break;
                }
                else if(hasBeardStr.equals("0"))
                {
                    hasBeard = false;
                    //break;
                }

                else
                {
                    hasBeard = false;
                    // create a pop-up window to notify the user that the answer is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid answer, please try again",
                            "Add Event", JOptionPane.ERROR_MESSAGE);
                }

                this._barberShop.getCalendar().addEvent(new ManEvent(date, eventTime,customer , hasBeard));

            }
            
         // Notify the user that the event was added successfully
	        JOptionPane.showMessageDialog(this._frame, "Event was added successfully.",
	                "Add Event", JOptionPane.INFORMATION_MESSAGE);
            	
	        }
        //--------------------------------------------------------------------------------------------------------------
        else if (e.getSource() == this._removeEventButton) 
        {
            // First, check if there are events in the barber shop
            if (this._barberShop.getCalendar().getCalendarSize() == 0) 
            {
                // create a pop-up window to notify the user that there are no customers
                JOptionPane.showMessageDialog(this._frame, "There are no events in the barber shop to remove.",
                        "Remove Event", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // create a pop-up window to ask for the customer's name
            String name = JOptionPane.showInputDialog(this._frame,
                    "Enter the customer's name you want to remove the events with him:",
                    "Remove Event", JOptionPane.PLAIN_MESSAGE);
            if (name != null) {
                // check if the name exists in the barber shop customers list
                if (!this._barberShop.isCustomerExists(name)) {
                    // create a pop-up window to notify the user that the customer does not exist
                    JOptionPane.showMessageDialog(this._frame, "Customer does not exist, please try again.",
                            "Remove Event", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            else
            {
                //user asked to cancel the operation
                return;
            }
            // get the customer object from the barber shop customers list
            Customer customerToRemove = this._barberShop.getCustomerByName(name);

            // remove customer appointments from the diary
            this._barberShop.getCalendar().deleteEventByCustomer(customerToRemove);

            //notify the user that the customer was removed
            JOptionPane.showMessageDialog(this._frame, "The event was removed successfully.",
                    "Remove Customer", JOptionPane.INFORMATION_MESSAGE);
        }

        //--------------------------------------------------------------------------------------------------------------
        else if (e.getSource() == this._showAllEventsButton) 
        {
            if (this._barberShop.getCalendar().getCalendarSize() != 0) 
            {
                // iterate over calendar list and call each customer toString method to build a string
                String allEvents = "";
                for (Event event : this._barberShop.getCalendar().getCalendar()) 
                {
                	allEvents += event.toString() + "\n ------------------------ \n";
                }

                // display all customers
                JOptionPane.showMessageDialog(this._frame, allEvents,
                        "Show All Events", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                // notify the user that the list is empty
                JOptionPane.showMessageDialog(this._frame, "The calendar is empty.",
                        "Show All Events", JOptionPane.ERROR_MESSAGE);
            }
        }

      //--------------------------------------------------------------------------------------------------------------
        else if (e.getSource() == this._showAllEventsByCustomerButton) 
        {
            if (this._barberShop.getCalendar().getCalendarSize() != 0) {

                // create a pop-up window to ask for the customer's name
                String name = JOptionPane.showInputDialog(this._frame, "Enter the customer's name:",
                        "Show Events By Customer", JOptionPane.PLAIN_MESSAGE);
                // if user asked to cancel the operation
                if (name != null)
                {
                    // check if the name exists in the barber shop customers list
                    if (!this._barberShop.isCustomerExists(name)) {
                        // create a pop-up window to notify the user that the customer does not exist
                        JOptionPane.showMessageDialog(this._frame, "Customer does not exist, please try again.",
                                "Show Events By Customer", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else
                {
                    //user asked to cancel the operation
                    return;
                }
                // get the customer object from the barber shop customers list
                Customer customer = this._barberShop.getCustomerByName(name);
                
                String allEvents = this._barberShop.getCalendar().EventsByCustomer(customer);
                
                // display all customers
                JOptionPane.showMessageDialog(this._frame, allEvents,
                        "Show Events By Customer", JOptionPane.INFORMATION_MESSAGE);
            }

            else
            {
                // notify the user that the list is empty
                JOptionPane.showMessageDialog(this._frame, "The calendar is empty.",
                        "Show Events By Customer", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
      //--------------------------------------------------------------------------------------------------------------
        else if (e.getSource() == this._showAllEventsByDateButton) 
        {
            if (this._barberShop.getCalendar().getCalendarSize() != 0) 
            {
                
            	// create a pop-up window to ask for the event's details
            	String userInput = JOptionPane.showInputDialog(this._frame, "Enter the date in this format: dd/MM/yyyy",
                        "Show Events By Date", JOptionPane.PLAIN_MESSAGE);
            	Date date2 = new Date();
            	try 
                {
                    date2 = createDateFromUserInputNoHours(userInput);
                    //date is valid, moving to the next input
                    //break;
                } 
                catch (ParseException err2) 
                {
                	// create a pop-up window to notify the user that the name is invalid
                    JOptionPane.showMessageDialog(this._frame, "Invalid date and time format, please try again: ",
                            "Show Events By Date", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                String allEvents = this._barberShop.getCalendar().EventsByDate(date2);
                
                // display all customers
                JOptionPane.showMessageDialog(this._frame, allEvents,
                        "Show Events By Date", JOptionPane.INFORMATION_MESSAGE);
            }
            
            else
            {
                // notify the user that the list is empty
                JOptionPane.showMessageDialog(this._frame, "The calendar is empty.",
                        "Show Events By Date", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}