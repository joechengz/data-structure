import java.util.Arrays;

public class ClosestPairDC {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;

    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "DC (x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    public static XYPoint[] bruteForce(XYPoint P[],int n)
    {
    	XYPoint[] bestd = new XYPoint[2];
	    if ( n <= 1)
	    {
	    return bestd;
	    }
	    else if (n == 2){
	    bestd[0] = P[0];
	    bestd[1] = P[1];
	    return bestd;
	    }
	    else if (n == 3){
	      double delta =  Math.min(Math.min(P[0].dist(P[1]), 
	    P[2].dist(P[1])), 
	    P[2].dist(P[0]));
	    if(delta == P[0].dist(P[1])){
	    bestd[0] = P[0];
	    bestd[1] = P[1];
	    }
	    else if(delta == P[0].dist(P[2])){
	    bestd[0] = P[0];
	    bestd[1] = P[2];
	    }
	    else{
	    bestd[0] = P[1];
	    bestd[1] = P[2];
	    }
	    }
	    return bestd;
    }
    // divide and conquer
    public static void findClosestPair(XYPoint pointsByX[], 
				       XYPoint pointsByY[],
				       boolean print)
    {
    	// Your code goes here!
    	//
    	       	int nPoints = pointsByX.length;
    	       
    	    double bestDistance = INF;
    	 
    	    XYPoint[] best = closest(pointsByX, pointsByY, nPoints);
    	    if(print)
    	    {
    	    System.out.println("DC " + best[0].toString() + " " + best[1].toString() + 
    	    " " + best[0].dist(best[1]));
    	    }
    }
    	    
    	    
     public static XYPoint[] closest(XYPoint pointsByX[], XYPoint pointsByY[],int nPoints)
     {
    	 XYPoint[] bestd = new XYPoint[2];
    	    if(nPoints<=3)
    	    {
    	    	bestd=bruteForce(pointsByX,nPoints);
    	    }
    	    else
    	    {
    	    //deal with situation when number of points is bigger than 3
    	   
    	    //set median point
    	    int mid = (nPoints) / 2;
    	    XYPoint midPoint = pointsByX[mid];
    	   
    	    //Separate sorted X Y to XL XR and YL YR
    	    XYPoint XL[]=new XYPoint [mid];
        	XYPoint XR[]=new XYPoint [nPoints-mid];
        	XYPoint YL[]=new XYPoint [mid];
        	XYPoint YR[]=new XYPoint [nPoints-mid];
        	// put the first half of pointsByX into XL
        	// put the second half of pointsByX into XR
        	int xli=0;
        	int xrk=0;
        	for(int i=0;i<nPoints;i++)
        	{
        		if(pointsByX[i].isLeftOf(midPoint))
        		{
        			XL[xli++]=pointsByX[i];
        		}
        		else
        		{
        			XR[xrk++]=pointsByX[i];
        		}
        	}
        	int ylk=0;
        	int yrk=0;
        	for (int i=0;i<nPoints;i++)
        	{
        		if(pointsByY[i].isLeftOf(midPoint))
        		{
        			YL[ylk]=pointsByY[i];
        			ylk++;
        		}
        		else
        		{
        			YR[yrk]=pointsByY[i];
        			yrk++;
        		}
        	}
    	    XYPoint[] distL = closest(XL, YL, XL.length);
    	    XYPoint[] distR = closest(XR, YR, XR.length); 
    	    double delta = Math.min(distL[0].dist(distL[1]), distR[0].dist(distR[1]));
    	    boolean big=distL[0].dist(distL[1])<distR[0].dist(distR[1]);
    	    if(big)
    	    {	
    	    	bestd = distL;
    	    }
    	    else
    	    {
    	    	bestd = distR;
    	    }
    	    XYPoint[] stripy = new XYPoint[nPoints];
    	    int a = 0;
    	    for(int i = 0; i < nPoints; i++){
    	    if(Math.abs(pointsByY[i].x - midPoint.x) < delta)
    	    {
    	    	stripy[a] = pointsByY[i];
    	    	a++;
    	    }
    	    }   
    	    for(int i = 0; i < a; i++){
    	    for(int l = i + 1; (l < a)&&(stripy[l].y - stripy[i].y) < delta; l++)
    	    {
    	    	double distance = stripy[i].dist(stripy[l]);
    	    	if(distance < delta)
    	    	{
    	    		delta = distance; 
    	    		bestd[0] = stripy[i];
    	    		bestd[1] = stripy[l];   
    	        }
    	    }
    	    }
    	    }
    	    return bestd;    
    	}
    	}