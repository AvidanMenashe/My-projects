package BarberShopComponents;

import java.util.Date; //using the Date library of java
import java.lang.String;//using strings in Java


// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------
public abstract class Event {
 //this class is abstract because we don't use his objects.
	
	//define members
	private Date _date;
	private int _eventTime;
	private Customer _customer;
	
	//constructor:
	public Event(Date date ,int eventTime, Customer customer)
	{
		this._date = date;
		this._eventTime = eventTime;
		this._customer = customer;
	}
	
	// Copy Constructor
	public Event(Event other)
	{
		this._date = other.getDate();
		this._eventTime = other.getEventTime();
		this._customer = other.getCustomer();
	}
	
	//gets:
	public Date getDate()
	{
		return this._date;
	}
	public int getEventTime()
	{
		return this._eventTime;
	}
	
	public Customer getCustomer()
	{
		return this._customer;
	}
	
	//sets:
	public void setDate(Date date)
	{
		this._date = date;
	}
	public void setEventTime(int eventTime)
	{
		this._eventTime = eventTime;
	}
	public void setCustomer(Customer customer)
	{
		this._customer = customer;
	}
	
	//this function is for checking if there is a collision between two events.
	//this function is abstract because we don't need to use him in this class
	 public abstract boolean collidesWith(Event otherEvent);
	 
	 //overRide on the toString function of Object class.
	 //this function is abstract because we don't need to use him in this class
	 @Override
	 public abstract String toString();


}
