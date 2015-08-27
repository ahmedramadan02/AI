//Here the search problem will be specified by a set of nodes
//and links
//this class is the abstarct class to deal with nodes and connecting links
abstract public class AbstractGraphSearch {
  // Vars
  // this really a good way to define these values, i apprecite that
  // the limited nomber of elments
  // and static arrays 
  // good for embedded systems
  
  final public static int MAX = 50;
  
  //Path
  protected int[] path = new int[AbstractGraphSearch.MAX];
  protected int num_path = 0;
  
  //Nodes
  protected String[] nodeNames = new String[MAX];
  protected int[] node_x = new int[MAX]; 
  protected int[] node_y = new int[MAX];
  
  //Links or edges
  protected int[] link_1 = new int[MAX]; //for every two nodes we have to links
  protected int[] link_2 = new int[MAX];
  protected int[] lengths = new int[MAX]; //to calculate the distance from start
  
  protected int numNodes = 0;
  protected int numLinks = 0;
  
  protected int goalNodeIndex = -1, startNodeIndex = -1;
  
  //Constructors
  
  
  //Functions
  public void addNode(String name, int x, int y) {
    nodeNames[numNodes] = name;
    node_x[numNodes] = x;
    node_y[numNodes] = y;
    numNodes++;
  }
  
  //Add links by nodes
  //or by string name by searching on the related nodes
  public void addLink(int node1, int node2) {
	link_1[numLinks] = node1;
	link_2[numLinks] = node2;
    //setting the distance
	int dist_squared =
	    (node_x[node1] - node_x[node2]) * (node_x[node1] - node_x[node2]) +
	    (node_y[node1] - node_y[node2]) * (node_y[node1] - node_y[node2]);
	lengths[numLinks] = (int)Math.sqrt(dist_squared);
	numLinks++;
    }

    public void addLink(String name1, String name2) {
	int index1 = -1, index2 = -1;
	for (int i=0; i<numNodes; i++) {
	    if (name1.equals(nodeNames[i])) index1 = i;
	    if (name2.equals(nodeNames[i])) index2 = i;
	}
	if (index1 != -1 && index2 != -1)  addLink(index1, index2);
    }

  
  
  //Getters and setters
  public int getNumNodes() { return numNodes; }
  public int getNumLinks() { return numLinks; }
  
  public String getNodeName(int index) {
    try {
      return nodeNames[index];
    }catch (Exception e){
      System.out.println("Error in get Node Name: " + e);
    }
    return "no name";
  }
  
  public int getNodeX(int index) {
		try {
	    return node_x[index];
		} catch (Exception e) {
	    System.out.println("Error in getNodePosition: " + e);
		}
		return 0;  // error condition
   }


   public int getNodeY(int index) {
		try {
	    return node_y[index]; //array index!
		} catch (Exception e) {
	    System.out.println("Error in getNodePosition: " + e);
		}
		return 0;  // error condition
   }
  
  public int getLink1(int index){ return link_1[index]; }
  public int getLink2(int index){ return link_1[index]; }
  
   //Get node index by its name
   protected int getNodeIndex(String name) {
	for (int i=0; i<numNodes; i++) {
	    if (name.equals(nodeNames[i])) return i;
	}
	return -1; // error condition
    }
  
    /** findPath - abstract method that is defined in subclasses */
    abstract public int [] findPath(int start_node, int goal_node); // return an array of node indices
}