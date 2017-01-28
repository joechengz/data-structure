import java.util.ArrayList;

//
// SHORTESTPATHS.JAVA
// Compute shortest paths in a graph.
//
// Your constructor should compute the actual shortest paths and
// maintain all the information needed to reconstruct them.  The
// returnPath() function should use this information to return the
// appropriate path of edge ID's from the start to the given end.
//
// Note that the start and end ID's should be mapped to vertices using
// the graph's get() function.
//
// You can ignore the input and startTime arguments to the constructor
// unless you are doing the extra credit.
//
class ShortestPaths {
    
    //
    // constructor
    //
	int INF = Integer.MAX_VALUE;
	
	class Content{
		int dist;
		int edge;
		Vertex vertex;
		Handle h = new Handle();
		Content parent;
		public Content(Vertex v){
			this.vertex = v;
			this.dist = INF;
			this.parent = null;
		}
	}

	ArrayList<Content> arrayList = new ArrayList<Content>(); 
	PriorityQueue<Content> prioqueue = new PriorityQueue<Content>();

	
    public ShortestPaths(Multigraph G, int startId, 
			 Input input, int startTime) 
    {
    	for(int i=0;i<G.nVertices();i++){
    		arrayList.add(new Content(null));
    	}
    	for(int i=0;i<G.nVertices();i++){
    		Vertex v = G.get(i);
    		Content c1 = new Content(v);
    		int id = G.get(i).id();
    		if(id==startId){
    			c1.dist=0;
    			c1.h = prioqueue.insert(0, c1);
    			arrayList.set(id, c1);
    		}
    		else{
    			c1.h = prioqueue.insert(INF, c1);
    			arrayList.set(id, c1);
    		}
    	}
    	while(!prioqueue.isEmpty()){
    		Content spoint = prioqueue.extractMin();
    		if(spoint.dist!=INF){
    			Vertex.EdgeIterator adjedges = spoint.vertex.adj();
    			while(adjedges.hasNext()){
    				Edge e = adjedges.next();
    				Content c2 = arrayList.get(e.to().id());
    				if(prioqueue.decreaseKey(c2.h, spoint.dist+e.weight())){
    					c2.dist = spoint.dist+e.weight();
    					c2.edge = e.id();
    					c2.parent = spoint;
    				}
    			}
    		}
    	}
    }
    
    //
    // returnPath()
    // Return an array containing a list of edge ID's forming
    // a shortest path from the start vertex to the specified
    // end vertex.
    //
    public int [] returnPath(int endId) 
    {
    	int count = 0;
    	ArrayList<Integer> info = new ArrayList<Integer>();
    	Content epoint = arrayList.get(endId);
    	while(epoint.parent!=null){
    		info.add(epoint.edge);
    		epoint = epoint.parent;
    		count=count+1;
    	}
    	int[] ans = new int[count];
    	for(int i=count;i>0;i--){
    		ans[i-1] = info.get(count-i);
    	}
    	return ans;
    }

}
