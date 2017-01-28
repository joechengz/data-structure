public class ClosestPairNaive {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "NAIVE (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair(XYPoint points[], boolean print)
    {
    	int nPoints=points.length;
    	double min = INF;
    	int minx1=0;
    	int miny1=0;
    	int minx2=0;
    	int miny2=0;
    	for(int i=0;i<nPoints;++i)
    	{
    		for(int j=i+1;j<nPoints;++j)
    		{
    			if(points[i].dist(points[j])<min)
    			{
    				min=points[i].dist(points[j]);
    				minx1=points[i].x;
    				miny1=points[i].y;
    				minx2=points[j].x;
    				miny2=points[j].y;
    			}
    		}
    	}
    	if(print)
    	{
    	     System.out.println("NAIVE ("+minx1+","+miny1+") ("+minx2+","+miny2+") "+min);
    	}

	
	//
	// Your code goes here!
	//
	
	// if (print)
	//   System.out.println("NAIVE " + ...);
    }
}
