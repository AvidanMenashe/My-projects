package BarberShopComponents;
// For using strings in Java

import java.lang.String;
import java.util.Scanner;

// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class Customer
{
	private String _name;
	private String _phoneNumber;
	private CreditCard _customerCreditCard;
	private int _gender;
	private Service _currService;

	// FEMALE =0 MALE =1 לשאול לגבי זה
	//what about full name?
	public static final int FEMALE = 0;
	public static final int MALE = 1;

	//this function is used to check if the name of the customer contain only letters
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

	//this function is used to check if the phone number of the customer contain only letters
	private boolean isValidPhoneNumber(String phoneNumber)
	{
		return phoneNumber.matches("\\d+") && phoneNumber.length()==10;
	}


	//gets
	public String getName()
	{
		return this._name;
	}

	public String getPhoneNumber()
	{
		return this._phoneNumber;
	}

	public CreditCard getCreditCard()
	{
		return this._customerCreditCard;
	}

	public int getGender(){return this._gender;}

	//get current service
	public Service getCurrentService() {return this._currService;}

	//get customer
	public Customer getCustomer() {return this;}


	//sets
	public void setName(String name)
	{
		//if the name is legal update the variable of the name
		if(isValidName(name))
		{
			this._name=name;
		}

		// if the name is illegal print an error and ask for the use to enter new name
		else
		{
			 //System.out.println("Invalid name. Please try again.");
			 Scanner scanner = new Scanner(System.in);
			while(true)
			{
				 //System.out.print("Enter the name of customer: ");
				 String input = scanner.nextLine();
				 if (isValidName(input))
				 {
						this._name = input;
						break;
				 }
//				 else
//				 {
//					 System.out.println("Invalid name. Please try again.");
//				 }
			}
		}
	}

	public void setPhone(String phoneNumber)
	{
		//if the phone number is legal update the variable of the name
			if(isValidPhoneNumber(phoneNumber)) {
				this._phoneNumber = phoneNumber;
			}

			// if the phone number is illegal print an error and ask for the use to enter new name
			else
			{
				 //System.out.println("Invalid phone number. Please try again.");
				 Scanner scanner = new Scanner(System.in);
				while(true)
				{
					 //System.out.print("Enter the phone number of customer: ");
					 String input = scanner.nextLine();
					 if (isValidName(input))
					 {
							this._phoneNumber = input;
							break;
					 }
//					 else
//					 {
//						 System.out.println("Invalid phone number. Please try again.");
//					 }
				}
			}
	}

	public void set_currService(Service service)
	{
		if (service != null)
			this._currService = new Service(service);
		else
			this._currService = null;
	}

	//constructor
	public Customer(String name, String phoneNumber, CreditCard customerCreditCard, int gender, Service service)
	{
		this._customerCreditCard=new CreditCard(customerCreditCard);

		//if the phone number is legal update the variable of the name
		this.setPhone(phoneNumber);

		//set gender - check if gender is valid:
		if(gender != MALE && gender != FEMALE)
		{

			Scanner scanner = new Scanner(System.in);
			while (true)
			{
				//System.out.println("Wrong gender, please try again :");
				gender = scanner.nextInt();
				if (gender == MALE || gender == FEMALE)
				{
					break;
				}
			}
		}
		this._gender = gender;

		//set name:
		this.setName(name);

		//set current service:
		this._currService = new Service(service);
	}


	//constructor without service
	public Customer(String name, String phoneNumber, CreditCard customerCreditCard, int gender)
	{
		this._customerCreditCard=new CreditCard(customerCreditCard);

		//if the phone number is legal update the variable of the name
		this.setPhone(phoneNumber);

		//set gender - check if gender is valid:
		if(gender != MALE && gender != FEMALE)
		{

			Scanner scanner = new Scanner(System.in);
			while (true)
			{
				//System.out.println("Wrong gender, please try again :");
				gender = scanner.nextInt();
				if (gender == MALE || gender == FEMALE)
				{
					break;
				}
			}
		}
		this._gender = gender;

		//set name:
		this.setName(name);
	}

	//copy constructor
	public Customer(Customer other)
	{
		this._name = other._name;
		this._phoneNumber = other._phoneNumber;
		this._customerCreditCard = new CreditCard(other._customerCreditCard);
		this._gender = other._gender;
	}

	@Override
	public String toString()
	{
		String strToReturn =
				"\nCustomer details: " +
				"\nName: " + this._name +
				"\nPhone number: " + this._phoneNumber +
				"\n" + this._customerCreditCard.toString();

		//check the gender:
		if (this.getGender() == MALE) {
			strToReturn += "\nGender: Male";
		} else {
			strToReturn += "\nGender: Female";
		}

		// add service details
		if (this._currService != null)
			strToReturn += "\nCurrent Service: " + this._currService.getServiceName();
		else
			strToReturn += "\nCurrent Service: None";

		return strToReturn;
	}

//	// test
//	public static void main(String[] args)
//	{
//		Customer chen =
//				new Customer("avidan", "0502003245", new CreditCard("0545220041417885", "012"), 55);
//		System.out.println(chen);
//	}
}
