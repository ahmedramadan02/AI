//This is the parent class for all search methodology
//it has the base fucntions to deal with the "maze" object
//elements; like getPath, getPossibleSolutions (to check the top, down, left, right)
public class AbstractSearchEngine {
	//Maze object
	protected Maze maze;
	
	//Path
	//This is protected because we will inherite it so that
	//we use with inherited once
    protected Location [] searchPath = null;
    protected int pathCount;
    protected int maxDepth;
    protected Location startLoc, goalLoc, currentLoc; //the curren location is related directly to this class
    protected boolean isSearching = true;
	
	//Constructor
	public AbstractSearchEngine(int width, int height){
		maze = new Maze(width,height);
		initSearch();
	}
	
	//Getters and setters
	public Maze getMaze(){ return maze; }
	public Location[] getPath() {
		//We made that temp because of the access modifier of searchPath is protected
		//1. but we made this function for public purposes!
		//2. to cut form the searchPath as MAX_DEPTH
		Location[] tempPath = new Location[maxDepth];
		for(int i = 0; i < maxDepth; i++){
			tempPath[i] = searchPath[i];
		}
		
		return tempPath;
	}
	
	//init all varaibles here
	protected void initSearch(){
		if(searchPath == null){
			searchPath = new Location[1000];
			for(int i = 0;i<1000;i++){
				searchPath[i] = new Location();
			}
			
			//Set the start location
	        pathCount = 0;
	        startLoc = maze.startLoc;
	        currentLoc = startLoc;
	        goalLoc = maze.goalLoc;
	        searchPath[pathCount++] = currentLoc;
		}
	}
	
	//isEqual
	protected boolean isEqual(Location x1, Location x2){
		return x1.x == x2.x && x1.y == x2.y; 
	}
	
	//Look of how to write best practise algorthim
	//Look at possible solution
	//Top, Bottom, Left, Right looking for obstacles
	protected Location[] getPossibleSolutions(Location loc){
		Location[] temp = new Location[4];
		temp[0] = temp[1] = temp[2] = temp[3] = null; // why putting null ?? i dont know -> but i i think best practise
		//and to check on it after that!
        int x = loc.x; //always get copy from the based values
        int y = loc.y;
        int num = 0;

        //Check North
		if(maze.getValue(currentLoc.x, currentLoc.y-1) == 0 || maze.getValue(currentLoc.x, currentLoc.y-1) == Maze.GOAL_LOC_VALUE){
			temp[num++] = new Location(x,y-1);
		}
		
        //Check South
		if(maze.getValue(currentLoc.x, currentLoc.y+1) == 0 || maze.getValue(currentLoc.x, currentLoc.y+1) == Maze.GOAL_LOC_VALUE){
			temp[num++] = new Location(x,y+1);
		}
		
        //Check West
		if(maze.getValue(currentLoc.x-1, currentLoc.y) == 0 || maze.getValue(currentLoc.x-1, currentLoc.y) == Maze.GOAL_LOC_VALUE){
			temp[num++] = new Location(x-1,y);
		}
		
        //Check East
		if(maze.getValue(currentLoc.x+1, currentLoc.y) == 0 || maze.getValue(currentLoc.x+1, currentLoc.y) == Maze.GOAL_LOC_VALUE){
			temp[num++] = new Location(x+1,y);
		}
		
		return temp;
	}
	
	
	
}
