import java.util.ArrayList;

//
// PRIORITYQUEUE.JAVA
// A priority queue class supporting sundry operations needed for
// Dijkstra's algorithm.
//

class PriorityQueue<T> {
	
	
	public ArrayList<Node<T>> heap;
    
    // constructor
    //
    public PriorityQueue()
    {
    	heap = new ArrayList<Node<T>>();
    	heap.add(null);
    }
    
    // Return true iff the queue is empty.
    //
    public boolean isEmpty()
    {
    	if(heap.size()==1){
    		return true;
    	}
    	else{
    		return false;
    	}

    }
    
    // Insert a pair (key, value) into the queue, and return
    // a Handle to this pair so that we can find it later in
    // constant time.
    //
    

    public void swap (ArrayList<Node<T>> hp, int i, int j){
    	Node<T> child = hp.get(i);
    	Node<T> parent = hp.get(j);
    	heap.set(i, parent);
    	heap.get(i).getHandle().setIndex(i);
    	heap.set(j, child);
    	heap.get(j).getHandle().setIndex(j);
     }
    

    

       Handle insert(int key, T value)
    {
    	Node<T> newNode = new Node<T>(key,value);
    	heap.add(newNode);
    	int count = heap.size()-1;
    	newNode.getHandle().setIndex(count);
    	int parent;
    	while(count>1){
    		parent = (int)(count/2);
    		if(key<heap.get(parent).getKey()){
    			Node<T> c = heap.get(count);
    			Node<T> p = heap.get(parent);
    			heap.set(count, p);
    			heap.get(count).getHandle().setIndex(count);
    			heap.set(parent, c);
    			heap.get(parent).getHandle().setIndex(parent);
    		}
    		count=parent;
    	}
    	Handle ans = newNode.getHandle();
    	return ans;
    }
    

    
    // Return the smallest key in the queue.
    //
    public int min()
    {
    	if(this.isEmpty()){
    		return -1;
    	}
    	else{
    		return heap.get(1).getKey();
    	}
    }
    
    // Extract the (key, value) pair associated with the smallest
    // key in the queue and return its "value" object.
    //
    
      public void Heapify(ArrayList<Node<T>> h, int i){
    	int leftchild = i*2;
    	int rightchild = i*2+1;
    	//int leftkey;
    	//int rightkey;
    	
    	if(heap.size()==1){
    		return;
    	}
//    	
//    	if(leftchild<=heap.size()-1){
//    		leftkey = heap.get(leftchild).getKey();
//    	}
//    	
//    	if(rightchild<=heap.size()-1){
//    		rightkey = heap.get(rightchild).getKey();
//    	}
//    	int rightkey = heap.get(rightchild).getKey();
    	int key = heap.get(i).getKey();
    	int minIndex;
    	if(leftchild<=heap.size()-1&&heap.get(leftchild).getKey()<key){
    		minIndex = leftchild;
    	}
    	else{
    		minIndex = i;
    	}
    	if(rightchild<=heap.size()-1 && heap.get(rightchild).getKey()<heap.get(minIndex).getKey()){
    		minIndex = rightchild;
    	}
    	if(minIndex!=i){
    		Node<T> child = heap.get(i);
    		Node<T> parent = heap.get(minIndex);
    		heap.set(i, parent);
    		heap.get(i).getHandle().setIndex(i);
    		heap.set(minIndex, child);
    		heap.get(minIndex).getHandle().setIndex(minIndex);
    		Heapify(h,minIndex);
    	}	
    	
    }

    
    public T extractMin()
    {

    	if(heap.isEmpty()){
    		return null;
    	}
    	Node<T> root = heap.get(1);
    	root.getHandle().setIndex(-1);
    	int lastLeaf = heap.size()-1;
    	heap.set(1, heap.get(lastLeaf));
    	heap.remove(lastLeaf);
    	Heapify(heap,1);
    	return root.getValue();
    	
    	
    }
    
    
    // Look at the (key, value) pair referenced by Handle h.
    // If that pair is no longer in the queue, or its key
    // is <= newkey, do nothing and return false.  Otherwise,
    // replace "key" by "newkey", fixup the queue, and return
    // true.
    //
    public boolean decreaseKey(Handle h, int newkey)
    {
     	int hIndex = h.getIndex();
    	if(h.getIndex()<1){
    		return false;
    	}
    	if(handleGetKey(h)<newkey){
    		return false;
    	}
    	heap.get(hIndex).setKey(newkey);
    	while(hIndex>1&&heap.get((int)(hIndex/2)).getKey()>newkey){
    		Node<T> child = heap.get(hIndex);
    		Node<T> parent = heap.get((int)(hIndex/2));
    		heap.set(hIndex, parent);
    		heap.get(hIndex).getHandle().setIndex(hIndex);
    		heap.set((int)(hIndex/2), child);
    		heap.get((int)(hIndex/2)).getHandle().setIndex((int)(hIndex/2));
    		hIndex = (int)(hIndex/2);
    	}
    	return true;

    }
    


    
    
    // Get the key of the (key, value) pair associated with a 
    // given Handle. (This result is undefined if the handle no longer
    // refers to a pair in the queue.)
    //
    public int handleGetKey(Handle h)
    {
    	if(h.getIndex() <0) {
    		return 0;
    	}
    	else{
    		int i = h.getIndex();
    		if(i<=heap.size()-1) {
    			return heap.get(i).getKey();
    		}
    		else return 0;
    	}

    }

    // Get the value object of the (key, value) pair associated with a 
    // given Handle. (This result is undefined if the handle no longer
    // refers to a pair in the queue.)
    //
    public T handleGetValue(Handle h)
    {
    	if(h.getIndex() <0) {
    		return null;
    	}
    	else {
    		int i = h.getIndex();
    		return heap.get(i).getValue();
    	}

    }
    
    // Print every element of the queue in the order in which it appears
    // in the implementation (i.e. the array representing the heap).
    public String toString()
    {
    	String s = "";
    	for(int i=1; i<heap.size(); i++) {
    		s = s +": (" + heap.get(i).getKey() + " " +  heap.get(i).getValue()
    				+ ")" + "\n";
    	}
    	return s;

    }
}
