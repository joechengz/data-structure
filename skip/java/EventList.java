//
// EVENTLIST.JAVA
// Skeleton code for your EventList collection type.
//
import java.util.*;

class EventList {
    
	 Integer INF = Integer.MAX_VALUE;
	 Integer MIN = Integer.MIN_VALUE;
	 Random randseq;
	 Event head = new Event(MIN,"");
	 Event tail = new Event(INF,"");
	 public int count = 1;
	    
	    ////////////////////////////////////////////////////////////////////
	    // Here's a suitable geometric random number generator for choosing
	    // pillar heights.  We use Java's ability to generate random booleans
	    // to simulate coin flips.
	    ////////////////////////////////////////////////////////////////////
	    
	    int randomHeight()
	    {
		int v = 1;
		while (randseq.nextBoolean()) { v++; }
		return v;
	    }
	    //
	    // Constructor
	    //
	    public EventList()
	    {
	    	randseq = new Random(58243); // You may seed the PRNG however you like.
	    	head.right = new Event[count];
	    	head.right[0] = tail;
	   // 	tail.left[0] = head;
	    	head.sameyearevent = null;
	    	tail.sameyearevent = null;
	    
	    }
    //
	    // Add an Event to the list.
	    //
	    public void insert(Event e)
	    {
	    	int t = randomHeight();
	    	e.right = new Event[t];
	    	Event[] oldright = head.right;
	    	while(head.right.length<t){
	    		count = count*2;
	    		head.right = new Event[count];
	    		tail.right = new Event[count];
	    	}
	    	for(int i=0; i<head.right.length;i++){
	    		if(i>=oldright.length){
	    			head.right[i] = tail;
	    			//tail.left[i] = head;
	    		}
	    		else{
	    			head.right[i] = oldright[i];
	    		}
	    	}
	    	
	    	int h = head.right.length-1;
	    	Event x = head;
	    	Event year = search(e.year);
	    	if(year!=null){
	    		e.sameyearevent = year.sameyearevent;
	    		year.sameyearevent = e;
	    	}
	    	else{
	    		while(h>=0){
	    			Event y = x.right[h];
	    			if(y.year<e.year){
	    				x=y;
	    			}
	    			else{
	    				if(h<t){
	    					x.right[h]=e;
	    					e.right[h]=y;
	    				}
	    				h=h-1;
	    			}
	    		}
	    	}
		
	    }
	    

	    public Event search (int year)
	    {
	    	int l = head.right.length-1;
	    	Event x = head;
	    	while(l >= 0){
	    		Event y = x.right[l];
	    		if(y.year == year){
	    			return y;
	    		}
	    		else 
	    			if(y.year < year){
	    				x = y;
	    			}
	    			else l--;
	    	}
	    	return null;
	    	
	    }

	    //
	    // Remove all Events in the list with the specified year.
	    //
	    public void remove(int year)
	    {
	    	Event y = search(year);
	    	Event x1 = head;
	    	int h = count-1;
	    	if(y!=null){
	    		while(h>=0){
	    			Event x2 = x1.right[h];
	    			if(x2.year<y.year){
	    				x1=x2;
	    			}
	    			else{
	    				if(x2.year==y.year){
	    					x1.right[h]=y.right[h];
	    				}
	    					h=h-1;
	    			}
	    		}
	    		
	    	}
	    	while(y!=null){
	    		Event yl = y.sameyearevent;
	    		y = null;
	    		y = yl;
	    	}
	    	
		
	    }
	    //
	    // Find all events with greatest year <= input year
	    //
	    public Event [] findMostRecent(int year)
	    {
	    	Event y = search(year);
	    	while(y==null){
	    		year--;
	    		y=search(year);
	    		if(year<=-100000){
	    			return null;
	    		}
	    	}
	    	int x=0;
	    	Event m =y;
	    	while(m!=null){
	    		x++;
	    		m=m.sameyearevent;
	    	}
	    	Event[] latestEvents = new Event[x];
	    	for(int i=0;i<x;i++){
	    		latestEvents[i]=y;
	    		y=y.sameyearevent;
	    	}
	    	return latestEvents;

	    }
	    //
	    // Find all Events within the specific range of years (inclusive).
	    //
	    public Event [] findRange(int first, int last)
	    {
	    	int x =0;
	    	Event f = search(first);
	    	while(f==null){
	    		first = first+1;
	    		f = search(first);
	    	}
	    	Event l = search(last);
	    	while(l==null){
	    		last = last-1;
	    		l = search(last);
	    	}
	    	Event f1 = f;
	    	Event l1 =l;
	    	while(f.year<=l.year){
	    		Event m = f;
	    		while(m!=null){
	    			x++;
	    			m=m.sameyearevent;
	    		}
	    		f=f.right[0];
	    	}
	    	
	    	if(x==0){
	    		return null;
	    	}
	    	Event[] result = new Event[x];
	    	int i=0;
	    	while(f1.year<=l1.year){
	    		Event q = f1;
	    		while(q!=null){
	    			result[i] = q;
	    			i++;
	    			q=q.sameyearevent;
	    		}
	    		f1=f1.right[0];
	    	}
	    	return result;    	
	    }
	}


