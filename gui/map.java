import java.util.ArrayList;


public class map {

  private int row = 16;
  private int column= 16;
  private int[] charPos = new int[6];
  private int[] enemyPos = new int[6];
  private int[] itemPos = new int[2];
  
  

 
  private ArrayList<ArrayList<Integer>> twoDList = new ArrayList<ArrayList<Integer>>();

  /**
 * Take in arguments for row and column to generate twoDList each elements in is 0
 * Usage -> map(int r, int cl)
 * Parameters: r, cl
 *
 * r: number of rows
 * cl: number of columns
 *
 * Return: Nothing
 */

  public map(int[] acharPos, int[] aenemy, int[] aitem){
    
    for(int countRow = 0; countRow < row; countRow++) {
    	twoDList.add(new ArrayList<Integer>());
    	for(int countColumn = 0; countColumn < column; countColumn++) {
    		twoDList.get(countRow).add(0);
    		}
    	}
    charPos = acharPos.clone();
    enemyPos = aenemy.clone();
    itemPos = aitem.clone();
    }


/**
* Take in arguments as id to find position and return position as string ( column, row)
* Usage -> getPos(int id)
* Parameters: id
*
* id: each object should be assigned with an unique map id to be located on the map
*
* Return: int[] position: position of the object
* position[0] : column
* position[1] : row
* [-1,-1] is returned incase object is not on the map
*/

  public int[] getPos(int id) {
	  int[] position = new int[2];
	  position[0] = -1;
	  position[1] = -1;
	  for(int countRow = 0; countRow < row; countRow++) {
		  for(int countCol = 0; countCol < column; countCol++) {
			  if(twoDList.get(countRow).get(countCol).equals(id)) {
				  position[0]=countCol;
				  position[1]=countRow;
				  return position;
		  }
	  }}
	  return position;
  }

  public int[] getCharPos() {
	  return charPos.clone();
  }
  
  public int[] getEnemyPos() {
	  return enemyPos.clone();
  }
  
  public int[] getItemPos() {
	  return itemPos.clone();
  }

  
  public int getID(int xCoord, int yCoord) {
	  return twoDList.get(yCoord).get(xCoord);
  }
  /**
   * Take in arguments for id, rowCoord, colCoord to set the position of the object
   * Usage -> setPos(int id, int rowCoord, int colCoord)
   * Parameters: id, rowCoord, colCoord
   *
   * id: each object should be assigned with an unique map id to be located on the map
   * rowCoord: row where the object will be assigned
   * rowCoord: row where the object will be assigned
   *
   * Return: nothing
   */
  public void setPos(int id, int colCoord, int rowCoord) {
	  twoDList.get(rowCoord).set(colCoord, id);
  }

  /**
   * Take in arguments for row and column, return boolean value if the slot is empty
   * Usage -> isEmpty(int rowCoord, int colCoord)
   * Parameters: rowCoord, colCoord
   *
   * rowCoord
   * colCoord
   *
   * Return: boolean: true if the slot is empty, false if there is an object in the slot
   */
  public boolean isEmpty(int rowCoord, int colCoord) {
	  return twoDList.get(rowCoord).get(colCoord).equals(0);
  }
 

  /**
   * Take in arguments for id, return value if the object is on the map
   * Usage ->  isOnMap(int id)
   * Parameters: id
   *
   *  id: each object should be assigned with an unique map id to be located on the map
   * Return: boolean: true if the position is not[-1,-1], false if it is
   */
  public boolean isOnMap(int id) {
	  int[] position = getPos(id);
	  if(position[0] != -1 && position[1] != -1) {
		  return true;
	  }
	  return false;
  }

  /**
   * Take in arguments for id, rowCoord, colCoord, movement of the object, calculate if the movement is legal
  (less than or equals the limit)
   * Usage ->  isLegalMove	(int id, int rowCoord, int colCoord, int limit)
   * Parameters: id rowCoord, colCoord, limit
   *
   *  id: each object should be assigned with an unique map id to be located on the map
   *  rowCoord, colCoord: location of the destination
   *  limit: how many step can the object move
   * Return: boolean: true if the attemped move is less than or equal the limit, false if the object not on map or
   the movement is more than the limit
   */
  public boolean isLegalMove(int id, int rowCoord, int colCoord, int limit) {
		  int[] position = getPos(id);
		  if (isOnMap(id)) {
		  int originalCol = position[0];
		  int originalRow = position[1];
		  int distance = shortestWay(colCoord, rowCoord, originalCol, originalRow);
		  return (distance <= limit);}
		  return false;
  }


  /**
   * Take in arguments for id, rowCoord, colCoord, movement of the object, calculate if the movement is legal
  (less than or equals the limit), the destination is empty, the object is on the map > then move the object and turn
  the starting point to zero(0)
   * Usage ->  isLegalMove(int id, int rowCoord, int colCoord, int limit)
   * Parameters: id, rowCoord, colCoord, limit
   *
   *  id: each object should be assigned with an unique map id to be located on the map
   *  rowCoord, colCoord: location of the destination
   *  limit: how many step can the object move
   * Return: nothing
   */
  public boolean move(int id, int rowCoord, int colCoord, int limit) {
	  if (isOnMap(id)) {
	  if (isEmpty(rowCoord, colCoord)) {
	  if (isLegalMove( id, rowCoord, colCoord, limit) == true) {
		 int[] position = getPos(id);
		 int originalCol = position[0];
		 int originalRow = position[1];
		 setPos(0, originalCol, originalRow);
		 setPos(id, colCoord, rowCoord);
	  	return true;}
	  else {
		  System.out.println("out of range move");
		  return false;
	  }
	 } else {
		 System.out.println("place is not empty");
		  return false;
	 	}
	}else {
		System.out.println("character is not on map");
		  return false;
	}
  }
  
  public int calDist(int col1, int row1,int col2,int row2) {
	  int dist = Math.abs(row1 - row2) + Math.abs(col1 - col2); 
	  return dist;
  }
  
  
  /**
   * return string reresent the map in textbase
   * Usage -> toString()
   * 
   *
   * Return: String representing map
   */
  
  public String toString() {
	  String toReturn="";
	  for(int countRow = 0; countRow < row ; countRow++) {
		  toReturn += "[";
		  for(int countCol = 0; countCol < column; countCol++) {
			  if (countCol < column -1) {
				  toReturn +=  twoDList.get(countRow).get(countCol);
				  toReturn += ";";
			  } else {
				  toReturn +=  twoDList.get(countRow).get(countCol);
			  }
			
		  }
		  toReturn += "] \n";  
	  }
	 return toReturn;
  }
  
  
  /**
   * this support shortestWay
   * Usage -> getNeighbours(int x, int y)
   
   * Return: ArrayList<ArrayList<Integer>> every neighbor of the passed in block
   */
  public ArrayList<ArrayList<Integer>> getNeighbours(int x, int y) {
	  ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>();
	  
	  ArrayList<Integer> up = new ArrayList<Integer>();
	  if(x >=0 && x < column && (y - 1) >=0 && (y -1)  < row) {
	  up.add(x);
	  up.add(y-1);
	  toReturn.add(up);
	  }
	  
	  ArrayList<Integer> down = new ArrayList<Integer>();
	  if((x >=0) && (x < column) && (y + 1) >=0 && (y + 1)  < row) {
	  down.add(x);
	  down.add(y+1);
	  toReturn.add(down);
	  }
	  
	  ArrayList<Integer> right = new ArrayList<Integer>();
	  if((x + 1) >=0 && (x + 1) < column && (y  >=0) && (y  < row)) {
	  right.add(x+1);
	  right.add(y);
	  toReturn.add(right);
	  }
	  
	  ArrayList<Integer> left = new ArrayList<Integer>();
	  if((x - 1) >=0 && (x - 1) < column && (y  >=0) && (y  < row)) {
	  left.add(x-1);
	  left.add(y);
	  toReturn.add(left);
	  }
	  
	  return toReturn;
  }
  
  public boolean trapped(int x, int y) {
	  boolean toReturn = true;
	  for(ArrayList<Integer> j : getNeighbours(x, y)) {
		  if (isEmpty(j.get(1), j.get(0))) {
			  toReturn = false;
		  }
	  }
	  return toReturn;
  }
  
  
  /**
   * take argument and calculate shortest way
   * Usage -> shortestWay(int destX, int destY, int xStart, int yStart, 'i')
   * Use Dijkstra's algorithm
   * Return: int ( the steps take to travel from startpoint to end point)
   */
  
  public int shortestWay(int endX,int endY,int startX,int startY) {
	  int steps = 0;
	  
	  ArrayList<Integer> end = new ArrayList<Integer>();
	  end.add(endX);
	  end.add(endY);
	  ArrayList<Integer> start = new ArrayList<Integer>();
	  start.add(startX);
	  start.add(startY);
	  
	 
	  ArrayList<ArrayList<Integer>> location = new ArrayList<ArrayList<Integer>>();
	  ArrayList<ArrayList<Integer>> passed =  new ArrayList<ArrayList<Integer>>();
	  
	  ArrayList<ArrayList<Integer>> toAdd = new ArrayList<ArrayList<Integer>>();
	  ArrayList<ArrayList<Integer>> toRemove =  new ArrayList<ArrayList<Integer>>();
	 
	  location.add(start);
	  passed.add(start);
	  
	  while(!passed.contains(end)) {
		  if(steps > 8) {
			  return steps;
			  
		  }
		  
		  steps ++;
		  for(ArrayList<Integer> i : location) {
			  for (ArrayList<Integer> j : getNeighbours(i.get(0), i.get(1))) {
				  if(isEmpty(j.get(1), j.get(0)) && !passed.contains(j)){
					  toAdd.add(j);
				  }
			  }
			  toRemove.add(i);
		  }
		  for(ArrayList<Integer> i : toRemove) {
			  location.remove(i);
		  }
		  for(ArrayList<Integer> j : toAdd) {
			  location.add(j);
			  passed.add(j);
		  }
		  
	  }
	 
	  return steps;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * OLD CODE
   */
  /**
   * take argument and calculate shortest way
   * Usage -> shortestWay(int destX, int destY, int xStart, int yStart, 'i')
   *this is a recursing fuction which loop the way from start point to end point, if the path move forward once, it wont go backward right after, same with up/down.
   *
   * Return: int ( the steps take to travel from startpoint to end point)
   */
  
//  public int shortestWay(int destX, int destY, int xStart, int yStart, char step) {
//	  if ((destX == xStart + 1 && destY == yStart) || (destX == xStart - 1 && destY == yStart) || (destX == xStart  && destY == yStart + 1) || (destX == xStart && destY == yStart -1 )) {
//		  return 1;
//		  }
//	  if ((calDist(destX , destY, xStart -1, yStart) < calDist(destX , destY, xStart, yStart)) && step != 'r' && isEmpty( yStart, xStart-1)) {
//		  return (1 + shortestWay( destX,  destY,  xStart-1,  yStart, 'l'));
//	  }else if(calDist(destX , destY, xStart +1, yStart) < calDist(destX , destY, xStart , yStart) && step != 'l'&& isEmpty( yStart, xStart+1)) {
//		  return (1 + shortestWay( destX,  destY,  xStart+1,  yStart, 'r'));
//	  }
//	  else if(calDist(destX , destY, xStart , yStart+1) < calDist(destX , destY, xStart , yStart) && step != 'd'&& isEmpty( yStart+1, xStart)) {
//		  return (1 + shortestWay( destX,  destY,  xStart,  yStart+1, 'u'));
//	  }
//	  else if(calDist(destX , destY, xStart , yStart-1) < calDist(destX , destY, xStart , yStart) && step != 'u'&& isEmpty( yStart-1, xStart)){
//		  return (1+shortestWay( destX,  destY,  xStart,  yStart-1, 'd'));
//	  }
//	  return 1;
//  }
// 
//  public boolean pathXClear(int xValue, int yHead, int yTail) {
//	  int block = 0;
//		 if (yHead > yTail) {
//			 for ( int y = yTail; y < yHead; y++) {
//				 if(!isEmpty(y, xValue)) {
//					 block ++;
//				 }
//			 }
//		 }else {
//			 for ( int y = yTail; y > yHead; y--) {
//				 if(!isEmpty(y, xValue)) {
//					 block ++;
//				 }
//			 }
//		 }
//		
//		 if (block > 0) {
//			 return false;
//		 }else {
//			 return true;
//		 }
//  }
//  
//  public boolean pathYClear(int yValue, int xHead, int xTail) {
//	  int block = 0;
//		 if (xHead > xTail) {
//			 for ( int x = xTail; x < xHead; x++) {
//				 if(!isEmpty(yValue, x)) {
//					 block ++;
//				 }
//			 }
//		 }else {
//			 for ( int x = xTail; x > xHead; x--) {
//				 if(!isEmpty(yValue, x)) {
//					 block ++;
//				 }
//			 }
//		 }
//		
//		 if (block > 0) {
//			 return false;
//		 }else {
//			 return true;
//		 }
//  }
//  /**
//   * take argument and passed into shortestWay(int destX, int destY, int xStart, int yStart, char step) to generate number of steps
//   * Usage -> shortestWay(int destX, int destY, int xStart, int yStart)
//   * 
//   *
//   * Return: shortestWay (destX,destY, xStart,  yStart, 'i')
//   */
//	 public int shortestWay(int destX, int destY, int xStart, int yStart) {
//		 if ((pathXClear(yStart, destX, xStart) && pathYClear(destX, destY, yStart)) || (pathYClear(xStart, destY, yStart) && pathXClear(destY, destX, xStart) )) {
//			 return calDist(destX, destY,xStart,yStart);
//		 }
//		 
//			 
//		
//		 return shortestWay (destX,destY, xStart,  yStart, 'i');
//	  }
//  
  }
