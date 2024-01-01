package BarberShopComponents;// For using strings in Java
import java.lang.String;
import java.util.Scanner;

// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class CreditCard implements Payment
{
	private String _cardNumber;
	private String _cvv;

	//this function is used to check if the cvv is legal
	private boolean isValidCvv(String cvv)
	{
		//return true if the length of the cvv is 3 and contains only number
		return cvv.length()==3 && cvv.matches("\\d+");
	}

	//this function is used to check if the card number is leag
	private boolean isValidCreditCard(String creditCard)
	{
		//return true if the lenght of the cvv is 16 and contains only numbers
		return creditCard.length()==16 && creditCard.matches("\\d+");
	}

	//this function is used to add "-" after all 4 digit except the last group of the card number
	private String addHyphens(String cardNumber)
	{
		//we don't use multi threads, so we are using stringBulider to edit the string of card number
		StringBuilder formattedNumber = new StringBuilder();
		//iterate over the string of card number
		for (int i = 0; i < cardNumber.length(); i++)
		{
			formattedNumber.append(cardNumber.charAt(i));

			// Insert hyphen after every four digits, except for the last group
			if ((i + 1) % 4 == 0 && (i + 1) < cardNumber.length()) {
				formattedNumber.append("-");
			}

		}
		//convert to stirng
		return formattedNumber.toString();
	}

	//getters
	public String getCardNumber() {return _cardNumber;}
	public String getCvv() {return _cvv;}

	//setters
	public void setCardNumber(String cardNumber) {this._cardNumber = cardNumber;}
	public void setCvv(String cvv) {this._cvv = cvv;}


	//this function is return a string that reprsent to the costumer how much he need to pay and from which card he paid
	public String amountToPay(int totalPayment)
	{
		 String lastFourDigit=this._cardNumber.substring(15);
		 return "amount payment : " + totalPayment+ "from credit card number : ****-****-****-" +lastFourDigit;
	}

	@Override
	public String toString()
	{
		//print the credit card details
		return "Credit Card Number: " + _cardNumber + "\nCredit Card CVV number: " + _cvv;
	}

	//constructors
	public CreditCard(String cardNumber, String cvv)
	{
		//if the cvv is legal update the cvv variable
		if (isValidCvv(cvv))
		{
			this._cvv=cvv;
		}
		//if the cvv is illegal print error and ask for another cvv until we get legal cvv
		else
		{
			 //System.out.println("Invalid CVV. Please try again.");
			 Scanner scanner = new Scanner(System.in);
			while(true)
			{
				 System.out.print("Enter CVV: ");
				 String input = scanner.nextLine();
				 if (isValidCvv(input))
				 {
						this._cvv = input;
						break;
				 }
//				 else
//				 {
//					 System.out.println("Invalid CVV. Please try again.");
//				 }
			}
		}

		if (isValidCreditCard(cardNumber))
		{
			this._cardNumber= addHyphens(cardNumber);
		}

		else
		{
			 //System.out.println("Invalid credit number. Please try again.");
			 Scanner scanner = new Scanner(System.in);
			while(true)
			{
				 //System.out.print("Enter credit number : ");
				 String input = scanner.nextLine();
				 if (isValidCreditCard(input))
				 {
					 this._cardNumber = addHyphens(input);
					 break;
				 }
//				 else
//				 {
//					 System.out.println("Invalid credit number. Please try again.");
//				 }
			}
		}
	}

	//copy constructor
	public CreditCard(CreditCard other)
	{
		this._cardNumber = other._cardNumber;
		this._cvv = other._cvv;
	}

	//only for testing!
	//public static void main(String[] args)
	//{
		//CreditCard test=new CreditCard("1234567891234567","j99");
		//System.out.println(test);

	//}
}
