
class DepthFirstSearchEngine extends AbstractSearchEngine {
 	   public DepthFirstSearchEngine(int width, int height){
        super(width, height);
        iterateSearch(startLoc,1);
      }
  
  //the search algorithm, uses recursive searching get
  // the first node and search under it
  private void iterateSearch(Location loc, int depth){ //depth ??
    if(isSearching == false) return; //best practise to check first!
    maze.setValue(loc.x, loc.y, (short)depth); //?? set the start point
    
    //get all possibleSolution and then iterate on it
    //and under this iterate for everyone (recursion on that location!)
    Location[] moves = getPossibleSolutions(loc);
    for (int i=0; i<4; i++){
      if(moves[i] == null) break; //out of scope 
      
      //otherwise
      //check if goal item or not
      //1. if goalLoc ok success
      //2. if not keep looking and get possible solution for this
      searchPath[depth] = moves[i]; //the forgotten line !!!!
      if(isEqual(moves[i],goalLoc)){
        System.out.println("Found the goal at " + moves[i].x +
                                   ", " + moves[i].y);
        isSearching = false;
        maxDepth = depth; //??
        return;
      } else {
        iterateSearch(moves[i], depth + 1);
        if (isSearching == false) return;
      }
      }
      return;
  }
}