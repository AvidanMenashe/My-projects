package BarberShopComponents;


// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class CashRegister {

    private int _totalBalance = 0;
    private int _totalExpenses = 0;
    private int _netProfit=0;


    // Constructor
    public CashRegister() {


    }

    // Getters
    // get the current total balance of the cash register
    public int getTotalBalance() {
        return _totalBalance;
    }
    //get the current total expenses of the barber shop
    public int getTotalExpenses() {
        return _totalExpenses;
    }
    
    public int getTotalNetProfit()
    {
    	//calculate the net profit
    	this._netProfit=this._totalBalance-this._totalExpenses;
    	
    	return this._netProfit;
    	
    	
    }



    public void addPayment(Customer customer) {
        //find the payment amount from the customer
        Service currentService = customer.getCurrentService();
        int paymentAmount = currentService.getServicePrice();
        // get also the expenses of the barber shop for the service
        int expenses = currentService.getExspenesFromService();

         //update the total balance of the cash register
         _totalBalance += paymentAmount;
        // update the total expenses of the cash register
         _totalExpenses += expenses;

       
    }

    public void RefoundCustomer(Service currentService) {
        //find the payment amount from the customer
        //Service currentService = customer.getCurrentService();
        int paymentAmount = currentService.getServicePrice();
        // get also the expenses of the barber shop for the service
        //int expenses = currentService.getExspenesFromService();

        // update the total balance of the cash register
        _totalBalance -= paymentAmount;
        // update the total expenses of the cash register,
        // we add the expenses to the total expenses because we are refounding the customer
        //_totalExpenses += expenses;

       
    }

    @Override
    public String toString() {
        // get the current date and time
        java.util.Date date = new java.util.Date();
        // return the current date and time, the total balance and the total expenses of the cash register till now
        return "Date :" +  date.toString() + "\nTotal Balance: " + _totalBalance + "\nTotal Expenses: " + _totalExpenses;
    }
}
