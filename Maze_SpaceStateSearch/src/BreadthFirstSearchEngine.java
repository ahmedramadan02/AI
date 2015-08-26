//This class inherites from AbstractSearchEngine class
//so it can use all its protected methods and vars
//the differnet thing here is that it is using LocationQueue class
//for implementing the search tree  
class BreadthFirstSearchEngine 
  extends AbstractSearchEngine {
  
  //no vars here. only local vars availble
  
  //Constructors
  public BreadthFirstSearchEngine(int width, int height){
    super(width, height); //to init the maze
    doSearchOn2DGrid();    
  }
  
  private void doSearchOn2DGrid() {
    int width  = maze.getWidth();
    int height = maze.getHeight();
    
    boolean alreadyVisitedFlag[][] = new boolean[width][height];
    Location predecessor[][] = new Location[width][height];
    LocationQueue queue = new LocationQueue();
    
    //initiate all these vars
	 for(int i = 0; i<width;i++){
      for(int j = 0;j<height;j++){
        alreadyVisitedFlag[i][j] = false;
        predecessor[i][j] = 0;
      }
    }
    
    //Put the initial conditions
    alreadyVisitedFlag[0][0] = true;
    queue.addToBackOfQueue(startLoc);
    boolean success = false;
    
    //start the algorthim
    //
    while(queue.isEmpty() == false){ //while -> not understand the condition
      Location head = queue.peekAtFrontOfQueue();
      if(head == null) break; //nothing to do
      
      //Get possible Moves
      Location[] connected = getPossibleMoves(head);
      for(int i=0; i<4; i++){
        if(connected[i] == null) break; //?
        int w = connected[i].x;
        int h = connected[i].y;
        
        if(alreadyVisitedFlag[w][h] == false) {
          alreadyVisitedFlag[w][h] = true;
          queue.addToBackOfQueue(connected[i]);
          if(equals(connected[i],goalLoc)){
            success = true;
            break outer; //?
          }
        }
      }
      
      queue.removeFromFrontOfQueue(); // ignore return
    }
    
    //Calculte the shortest path from the predecessor
    //fill the successPath
    maxDepth = 0;
    if (success) { //best practices to check on the flag even if you know it's true! 
      searchPath[maxDepth++] = goalLoc;
    	for (int i=0; i<100; i++) {
    		searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].x][searchPath[maxDepth - 1].y];
    		maxDepth++;
   		if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
    }
      
  }
    
}


//This class reimplments the Queue class to deal with our location object
//we are using static arrays to increase efficiency (no pointer!)
class LocationQueue {
  private Location[] queue;
  private int tail, head, len;
  
  //Constructors
  public LocationQueue(int num){
    queue = new Location[num];
    tail = head = 0;
    len = num;
  }
  
  public LocationQueue(){
    this(400);
  }
  
  //Functions
  public void addToBackOfQueue(Location loc){
    queue[tail] = loc;
    if(tail >= (len -1)){ //reach the lenght of the queue, then no more
      tail = 0;
    }else{
      tail++;
    }
  } 
  
  public Location peekAtFrontOfQueue(){
  		Location ret = queue[head];
    	if(head >= (len -1)){
        head = 0; //if the user get much items, must not exceeds the len [out of bounds]
        head++;
      }
  }
  
  public boolean isEmpty(){
    return head == (tail -1); //the head is the tail, -1 because at least two nodes
  }
  public Location removeFromFrontOfQueue(){ return queue[head]; }
} 