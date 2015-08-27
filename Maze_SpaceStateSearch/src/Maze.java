
public class Maze {
	//Constants Configurations
	public static short OBSTACLE = -1;
	public static short START_LOC_VALUE = -2;
	public static short GOAL_LOC_VALUE = -3; //will be used as constants
	
	//Width and height
	private int width;
	private int height;
	
	//Computer starting and goal points
	public Location startLoc = new Location();
	public Location goalLoc = new Location();
	
	//The maze matrix
	private short[][] maze;
	
	public Maze(int width, int height){
		this.width = width;
		this.height = height;
		
		//Create the maze matrix according to width and height
		maze = new short[width+2][height+2];
		//initiate the maze objects
		for(int i = 0; i<width+2; i++){
			for(int j=0;j<height+2; j++){
				maze[i][j] = 0;
			}
		}
		
		//Make the walls
		for(int i = 0; i < height+2; i++){
			maze[0][i] = maze[width+1][i] = OBSTACLE;
		}
		
		for(int i = 0; i < width+2; i++){
			maze[i][0] = maze[i][height+1] = OBSTACLE;
		}
		
		//Randomize the obstacles -> building the maze obstacles
        int max_obstacles = (width * height) / 3;
        for (int i=0; i<max_obstacles; i++) {
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            setValue(x, y, OBSTACLE);
        }
        
        // Specify the starting location
        
        startLoc.x = 0;
        startLoc.y = 0;
        setValue(0, 0, (short)0);
       
       // Specify the goal location
       // its a fucntion of width and height
       goalLoc.x = width - 1;
       goalLoc.y = height - 1;
       setValue(width - 1, height - 1, GOAL_LOC_VALUE);
	}

	//SET and GET value
    synchronized public short getValue(int x, int y) { return maze[x+1][y+1]; }
    synchronized public void setValue(int x, int y, short value) { maze[x+1][y+1] = value; }
	
	//GET width and height
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
}
