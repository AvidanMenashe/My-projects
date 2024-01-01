package BarberShopComponents;

import java.util.Calendar;
import java.util.Date; //using the Date library of java
import java.lang.String; //using strings in Java


// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------
public class ManEvent extends Event
{
	//this class is extends from Event class
	
	//define members
	private boolean _hasBeard;
	
	//constructor:
    public ManEvent(Date date, int eventTime, Customer customer , boolean hasBeard) {
        super(date, eventTime,customer); //making 'this' to be an event by super()
        this._hasBeard = hasBeard;
    }
    
    //Copy Constructor
    public ManEvent(ManEvent otherManEvent)
    {
    	//making 'this' to be an event by super()
    	super(otherManEvent.getDate(), otherManEvent.getEventTime() , otherManEvent.getCustomer()); 
    	this._hasBeard = otherManEvent.getHasBeard();
    }
    
    //get:
    public boolean getHasBeard() 
    {
        return this._hasBeard;
    }
    
    //set:
    public void setHasBeard(boolean hasBeard)
    {
    	this._hasBeard = hasBeard;
    }
    
    //this function is for checking if there is a collision between two events.
    @Override
    public boolean collidesWith(Event otherEvent) {
    	//checking if otherEvent is in the type of MeetingEvent
        if (otherEvent instanceof ManEvent) {
        	//down casting to MeetingEvent:
        	ManEvent otherMeeting = (ManEvent) otherEvent;
            //check if the meetings is in the same time:
        	Calendar cal1 = Calendar.getInstance();
            cal1.setTime(otherMeeting.getDate());

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(this.getDate());

            int year1 = cal1.get(Calendar.YEAR);
            int month1 = cal1.get(Calendar.MONTH);
            int day1 = cal1.get(Calendar.DAY_OF_MONTH);
            int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
            int minutes1 = cal1.get(Calendar.MINUTE);
            
            int year2 = cal2.get(Calendar.YEAR);
            int month2 = cal2.get(Calendar.MONTH);
            int day2 = cal2.get(Calendar.DAY_OF_MONTH);
            int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
            int minutes2 = cal2.get(Calendar.MINUTE);
        	
        	int eventTime1 = otherMeeting.getEventTime();
        	int eventTime2 = this.getEventTime();
        	if(year1 == year2 && month1 == month2 && day1 == day2 && hour1 == hour2)
        	{
        		if(minutes1 == minutes2 ||
                        (minutes1<minutes2 && minutes2< (minutes1 + eventTime1)) ||
                        (minutes2<minutes2 && minutes2< (minutes2 + eventTime2)))
        		{
        			return true;
        		}
        	}
        }
        return false;
    }

    @Override
    public String toString() 
    {
        return "Event details: [date=" + this.getDate() +
                ", event time=" + this.getEventTime() +
                ", customer=" + this.getCustomer().toString()+
                ", has beard=" + this.getHasBeard() + "]";
    }

}
