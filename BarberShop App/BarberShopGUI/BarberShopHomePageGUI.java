package BarberShopGUI;
import BarberShop.BarberShop;

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
public class BarberShopHomePageGUI implements ActionListener {
    //members:
    private BarberShop _barberShop;
    private JFrame _frame;
    private JPanel _panel;
    private JButton _cashRegisterButton;
    private JButton _CustomersMenuButton;
    private JButton _ServicesMenuButton;
    private JButton _CalendarMenuButton;


    //constructor:
    public BarberShopHomePageGUI() {

        this._barberShop = new BarberShop();
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

        this._panel.setLayout(null);

        // Create the buttons
        this.setCashRegisterButton();
        this.setCustomersMenuButton();
        this.setServicesMenuButton();
        this.setCalendarMenuButton();

        // Set the title of the panel
        JLabel titleLabel = new JLabel("Barber's Shop");
        // Set small title
        JLabel smallTitleLabel = new JLabel("By: Chen, Avidan, Shoam & Hadas");

        // Set the title's font
        titleLabel.setFont(new Font("Brush Script", Font.BOLD, 70));
        smallTitleLabel.setFont(new Font("Brush Script", Font.BOLD, 30));

        // Set the title's color
        titleLabel.setForeground(new Color(255, 255, 255));
        smallTitleLabel.setForeground(new Color(255, 255, 255));

        // Set the title's position
        titleLabel.setBounds(295, 0, 500, 100);
        smallTitleLabel.setBounds(280, 55, 600, 100);

        // Set the title's background
        titleLabel.setOpaque(false);
        smallTitleLabel.setOpaque(false);

        // Add the title to the panel
        this._panel.add(titleLabel);
        this._panel.add(smallTitleLabel);
        // add buttons to the panel
        this._panel.add(this._cashRegisterButton);
        this._panel.add(this._CustomersMenuButton);
        this._panel.add(this._ServicesMenuButton);
        this._panel.add(this._CalendarMenuButton);

        this._frame.getContentPane().add(this._panel);

        // Make the frame visible
        this._frame.setVisible(true);
    }

    public BarberShopHomePageGUI(BarberShop barberShop) {

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

        this._panel.setLayout(null);

        // Create the buttons
        this.setCashRegisterButton();
        this.setCustomersMenuButton();
        this.setServicesMenuButton();
        this.setCalendarMenuButton();

        // Set the title of the panel
        JLabel titleLabel = new JLabel("Barber's Shop");
        // Set small title
        JLabel smallTitleLabel = new JLabel("By: Chen, Avidan, Shoam & Hadas");

        // Set the title's font
        titleLabel.setFont(new Font("Brush Script", Font.BOLD, 70));
        smallTitleLabel.setFont(new Font("Brush Script", Font.BOLD, 30));

        // Set the title's color
        titleLabel.setForeground(new Color(255, 255, 255));
        smallTitleLabel.setForeground(new Color(255, 255, 255));

        // Set the title's position
        titleLabel.setBounds(295, 0, 500, 100);
        smallTitleLabel.setBounds(280, 55, 600, 100);

        // Set the title's background
        titleLabel.setOpaque(false);
        smallTitleLabel.setOpaque(false);

        // Add the title to the panel
        this._panel.add(titleLabel);
        this._panel.add(smallTitleLabel);
        // add buttons to the panel
        this._panel.add(this._cashRegisterButton);
        this._panel.add(this._CustomersMenuButton);
        this._panel.add(this._ServicesMenuButton);
        this._panel.add(this._CalendarMenuButton);

        this._frame.getContentPane().add(this._panel);

        // Make the frame visible
        this._frame.setVisible(true);
    }

    // Button functions
    private void setCustomersMenuButton() {
        // create the button
        this._CustomersMenuButton = new JButton("Customers Menu");
        // set button font
        this._CustomersMenuButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._CustomersMenuButton.setForeground(Color.white);
        this._CustomersMenuButton.setBackground(Color.white);
        this._CustomersMenuButton.setOpaque(false);
        this._CustomersMenuButton.setBorderPainted(false);
        this._CustomersMenuButton.setFocusable(true);
        // set button position
        this._CustomersMenuButton.setBounds(400, 360, 350, 50);
        // set the button's listener
        this._CustomersMenuButton.addActionListener(this);
    }

    private void setCashRegisterButton() {
        this._cashRegisterButton = new JButton("Cash Register");
        this._cashRegisterButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._cashRegisterButton.setForeground(Color.white);
        this._cashRegisterButton.setBackground(Color.white);
        this._cashRegisterButton.setOpaque(false);
        this._cashRegisterButton.setBorderPainted(false);
        this._cashRegisterButton.setFocusable(true);
        // set button position
        this._cashRegisterButton.setBounds(400, 430, 350, 50);
        // set the button's listener
        this._cashRegisterButton.addActionListener(this);
    }

    private void setServicesMenuButton() {
        this._ServicesMenuButton = new JButton("Services Menu");
        this._ServicesMenuButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._ServicesMenuButton.setForeground(Color.white);
        this._ServicesMenuButton.setBackground(Color.white);
        this._ServicesMenuButton.setOpaque(false);
        this._ServicesMenuButton.setBorderPainted(false);
        this._ServicesMenuButton.setFocusable(true);
        // set button position
        this._ServicesMenuButton.setBounds(400, 500, 350, 50);
        // set the button's listener
        this._ServicesMenuButton.addActionListener(this);
    }

    private void setCalendarMenuButton() {
        this._CalendarMenuButton = new JButton("Calendar Menu");
        this._CalendarMenuButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        this._CalendarMenuButton.setForeground(Color.white);
        this._CalendarMenuButton.setBackground(Color.white);
        this._CalendarMenuButton.setOpaque(false);
        this._CalendarMenuButton.setBorderPainted(false);
        this._CalendarMenuButton.setFocusable(true);
        // set button position
        this._CalendarMenuButton.setBounds(400, 570, 350, 50);
        // set the button's listener
        this._CalendarMenuButton.addActionListener(this);
    }


    private void setFrame()
    {
        this._frame = new JFrame("Barber Shop");
        // Set the frame's default close operation to exit on close
        this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame's size to 1000x667
        this._frame.setSize(1000, 667);
        // Set the frame's layout to border layout
        this._frame.setLayout(new BorderLayout());
        // Set the frame to be not resizable
        this._frame.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //check which button was pressed
        if (this._cashRegisterButton == e.getSource())
        {
            // close the current window
            this._frame.dispose();
            // create new cash register gui object
            CashRegisterPageGUI cashRegisterGUI = new CashRegisterPageGUI(this._barberShop);
        }
        else if (this._CustomersMenuButton == e.getSource())
        {
            // close the current window
            this._frame.dispose();
            // create new customers menu gui object
            CustomerPageGUI customersMenuGUI = new CustomerPageGUI(this._barberShop);
        }
        else if (this._ServicesMenuButton == e.getSource())
        {
            // close the current window
            this._frame.dispose();
            // create new services menu gui object
            ServicePageGUI servicesMenuGUI = new ServicePageGUI(this._barberShop);
        }
        else if (this._CalendarMenuButton == e.getSource())
        {
            // close the current window
            this._frame.dispose();
            // create new calendar menu gui object
            CalendarPageGUI calendarMenuGUI = new CalendarPageGUI(this._barberShop);
        }
    }

    //main function:
    public static void main(String[] args) {
        BarberShop barberShop = new BarberShop();

        BarberShopHomePageGUI gui = new BarberShopHomePageGUI(barberShop);
    }
}
