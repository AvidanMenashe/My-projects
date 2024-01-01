package BarberShopComponents;
import java.util.Date; //using the Date library of java
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


// ------------------------ Team Members -------------------
//Members:
// 1. Chen Cohen Gershon
// 2. Shoham Galili
// 3. Hadas Yosef-Zada
// 4. Avidan Menashe
//---------------------------------------------------------

public class Calander 
{
	//define members
	private List<Event> _diaryevents;
	
	//constructor:
    public Calander() 
    {
    	this._diaryevents = new ArrayList<>();
    }
    
    //get:
    public List<Event> getCalendar()
    {
    	return this._diaryevents;
    }
    //this function adds a new event to the diary:
    public void addEvent(Event event) 
    {
    	this._diaryevents.add(event);
        //System.out.println("Event added successfully!");
    }
    public int getCalendarSize()
    {
    	return _diaryevents.size();
    }
    
    //this function deletes an event in the index 'index' in the diary:
    public void deleteEvent(int index) 
    {
    	//checking if the index is legal:
        if (index >= 0 && index <= this._diaryevents.size())
        {
        	this._diaryevents.remove(index - 1);
            //System.out.println("Event deleted successfully.");
        } 
//        else
//        {
//            System.out.println("Invalid event index.");
//        }
    }
    
    public void deleteEventByCustomer(Customer customer)
    {
    	int j = 0;
    	for (Event event : this._diaryevents) 
        {
            if(customer.getGender()==0)
            {
                //checking if otherEvent is in the type of WomenEvent
                //and if the event has this customer
                if (event.getCustomer().getName() == customer.getName())
                {
                    //down casting to WomenEvent:
                    //WomenEvent otherMeeting = (WomenEvent) event;
                    this.deleteEvent(j + 1);
                }
            }

            if(customer.getGender()==1) {
                if (event.getCustomer().getName().equals(customer.getName())) {
                    //down casting to WomenEvent:
                    //ManEvent otherMeeting = (ManEvent) event;
                    this.deleteEvent(j + 1);
                }
            }
            // check  if diary is empty
            if(this._diaryevents.size() == 0)
            {
                break;
            }
            j++;
        }
    }

    //this function prints the events in a date by order:
    public void printEventsByDate(Date date) 
    {
        System.out.println("Events on " + date.toString() + ":");
        //for loop on all the events in the diary:
        for (Event event : this._diaryevents) 
        {
        	Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(event.getDate());

            int year1 = cal1.get(Calendar.YEAR);
            int month1 = cal1.get(Calendar.MONTH);
            int day1 = cal1.get(Calendar.DAY_OF_MONTH);

            int year2 = cal2.get(Calendar.YEAR);
            int month2 = cal2.get(Calendar.MONTH);
            int day2 = cal2.get(Calendar.DAY_OF_MONTH);

            if(year1 == year2 && month1 == month2 && day1 == day2)
            {
            	if (event instanceof WomenEvent)
            	{
                	//down casting to WomenEvent:
            		WomenEvent otherMeeting = (WomenEvent) event;
                	System.out.println("Event details: [date=" + otherMeeting.getDate() +
                            ", event time=" + otherMeeting.getEventTime() +", customer=" +
                            otherMeeting.getCustomer().toString()+ ", is curly=" + otherMeeting.getIsCurly() + "]");
            	}
            	else if (event instanceof ManEvent)
            	{
                	//down casting to ManEvent:
            		ManEvent otherMeeting = (ManEvent) event;
                	System.out.println("Event details: [date=" + otherMeeting.getDate() +
                            ", event time=" + otherMeeting.getEventTime() +", customer=" +
                            otherMeeting.getCustomer().toString()+ ", has beard=" + otherMeeting.getHasBeard() + "]");
            	}

            }
        }
    }
    
    public String EventsByDate(Date date) 
    {
    	String str ="";
    	str += "Events on " + date.toString() + ":";
        //for loop on all the events in the diary:
        for (Event event : this._diaryevents) 
        {
        	Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(event.getDate());

            int year1 = cal1.get(Calendar.YEAR);
            int month1 = cal1.get(Calendar.MONTH);
            int day1 = cal1.get(Calendar.DAY_OF_MONTH);

            int year2 = cal2.get(Calendar.YEAR);
            int month2 = cal2.get(Calendar.MONTH);
            int day2 = cal2.get(Calendar.DAY_OF_MONTH);

            if(year1 == year2 && month1 == month2 && day1 == day2)
            {
            	if (event instanceof WomenEvent) 
            	{
                	//down casting to WomenEvent:
            		WomenEvent otherMeeting = (WomenEvent) event;
            		str += "Event details: [date=" + otherMeeting.getDate() +
                            ", event time=" + otherMeeting.getEventTime() +
                            ", customer=" + otherMeeting.getCustomer().toString()+
                            ", is curly=" + otherMeeting.getIsCurly() + "]";
            	}
            	else if (event instanceof ManEvent) 
            	{
                	//down casting to ManEvent:
            		ManEvent otherMeeting = (ManEvent) event;
            		str += "Event details: [date=" + otherMeeting.getDate() +
                            ", event time=" + otherMeeting.getEventTime() +
                            ", customer=" + otherMeeting.getCustomer().toString()+
                            ", has beard=" + otherMeeting.getHasBeard() + "]";
            	}

            }
        }
        return str;
    }

    //this function prints the events that has the customer by order in dates:
    public void printMeetingsByCustomer(Customer customer) 
    {
        System.out.println("Meetings with " + customer.toString());
      //for loop on all the events in the diary:
        for (Event event : this._diaryevents) 
        {	
        	//checking if otherEvent is in the type of WomenEvent 
        	//and if the event has this customer
            if (((WomenEvent) event).getCustomer().getName() == customer.getName()) 
            {
            	//down casting to WomenEvent:
            	WomenEvent otherMeeting = (WomenEvent) event;
            	System.out.println("Event details: [date=" + otherMeeting.getDate() +
                        ", event time=" + otherMeeting.getEventTime() +", customer=" +
                        otherMeeting.getCustomer().toString()+ ", is curly=" +
                        otherMeeting.getIsCurly() + "]");
            }
            else if(((ManEvent) event).getCustomer().getName() == customer.getName()) 
            {
            	//down casting to WomenEvent:
            	ManEvent otherMeeting = (ManEvent) event;
            	System.out.println("Event details: [date=" + otherMeeting.getDate() +
                        ", event time=" + otherMeeting.getEventTime() +", customer=" +
                        otherMeeting.getCustomer().toString()+ ", has beard=" +
                        otherMeeting.getHasBeard() + "]");
            }
        }
    }

  //this function prints the events that has the customer by order in dates:
    public String EventsByCustomer(Customer customer) 
    {
    	String str ="";
    	str += "Meetings with " + customer.getName();
      //for loop on all the events in the diary:
        for (Event event : this._diaryevents) 
        {	
        	//checking if otherEvent is in the type of WomenEvent 
        	//and if the event has this customer
            if(customer.getGender() == 0)
            {
                if (event.getCustomer().getName() == customer.getName())
                {
                    //down casting to WomenEvent:
                    WomenEvent otherMeeting = (WomenEvent) event;
                    str += "\nEvent details: [date=" + otherMeeting.getDate() +
                            ", event time=" + otherMeeting.getEventTime() +
                            ", customer=" + otherMeeting.getCustomer().toString()+
                            ", is curly=" + otherMeeting.getIsCurly() + "]";
                }
            }
            else if(customer.getGender() == 1)
            {
              if( event.getCustomer().getName() == customer.getName())
                {
                //down casting to WomenEvent:
                ManEvent otherMeeting = (ManEvent) event;
                str += "\nEvent details: [date=" + otherMeeting.getDate() +
                        ", event time=" + otherMeeting.getEventTime() +
                        ", customer=" + otherMeeting.getCustomer().toString()+
                        ", has beard=" + otherMeeting.getHasBeard() + "]";
                }
            }

        }
        return str;
    }
    
    //this function is for checking if there is a collision between two events.
    //if there is, it deletes the latest event
    public void checkCollisions() 
    {
    	//going for all events and checking if there is a collision
        for (int i = 0; i < this._diaryevents.size(); i++) 
        {
            for (int j = i + 1; j < this._diaryevents.size(); j++) 
            {
                Event event1 = this._diaryevents.get(i);
                Event event2 = this._diaryevents.get(j);
                if (event1.collidesWith(event2)) 
                {
                	int compare_dates = event1.getDate().compareTo(event2.getDate());
                	if(compare_dates > 0)
                	{
                		this._diaryevents.remove(j);
                        System.out.println("Event at index " + j + " collided with event at index " + i + " and was deleted!");
                	}
                	else if(compare_dates < 0)
                	{
                		this._diaryevents.remove(i);
                        System.out.println("Event at index " + i + " collided with event at index " + j + " and was deleted!");
                	}
                }
            }
        }
    }


    //this function is printing all the events in the diary:
    public void printAllEvents() 
    {
        System.out.println("All Events:");
        //for loop on all the events in the diary:
        for (Event event : this._diaryevents) 
        {
        	if (event instanceof WomenEvent) 
        	{
            	//down casting to WomenEvent:
        		WomenEvent otherMeeting = (WomenEvent) event;
        		System.out.println("Event details: [date=" + otherMeeting.getDate() +
                        ", event time=" + otherMeeting.getEventTime() +", customer=" +
                        otherMeeting.getCustomer().toString()+ ", is curly=" + otherMeeting.getIsCurly() + "]");
        	}
        	else if (event instanceof ManEvent) 
        	{
            	//down casting to ManEvent:
        		ManEvent otherMeeting = (ManEvent) event;
        		System.out.println("Event details: [date=" + otherMeeting.getDate() +
                        ", event time=" + otherMeeting.getEventTime() +", customer=" +
                        otherMeeting.getCustomer().toString()+ ", has beard=" + otherMeeting.getHasBeard() + "]");
        	}
        }
    }
}
