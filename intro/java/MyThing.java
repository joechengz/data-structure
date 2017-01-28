public class MyThing {
    
    public void count(int x) 
    {
	// Print every odd number between 1 and x (inclusive) in the form
	// 1...
        // 3...
	// <etc>
	// x!
    	int i=1;
        int k=1;
    	if(x%2==0)
    	{
    		
            while(i<(x/2))
    		{
    		System.out.println(k+"...");
    		i++;
    		k=k+2;
    		}
    		while(i==x/2)
    		{
    			System.out.println(k+"!");
    			i++;
    		}	
    	}
    	else
    	{
    		while(i<=(x/2))
    		{
    			System.out.println(k+"...");
    			i++;
    			k=k+2;
    		}
    		while(i==(x/2)+1)
    		{
    			System.out.println(k+"!");
    			i++;
    		}
    			
    
    	}
    	}
    			
    }

