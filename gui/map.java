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

protected map(int[] acharPos, int[] aenemy, int[] aitem){
    
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

  protected int[] getPos(int id) {
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

  protected int[] getCharPos() {
	  return charPos.clone();
  }
  
  protected int[] getEnemyPos() {
	  return enemyPos.clone();
  }
  
  protected int[] getItemPos() {
	  return itemPos.clone();
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
  protected void setPos(int id, int colCoord, int rowCoord) {
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
  private boolean isOnMap(int id) {
	  int[] position = getPos(id);
	  if(position[0] != -1 && position[1] != -1) {
		  return true;
	  }
	  return false;
  }

  /**
   * Take in arguments for id, rowCoord, colCoord, movement of the object, calculate if the movement is legal
  (less than or equals the limit)
   * Usage ->  isLegalMove(int id, int rowCoord, int colCoord, int limit)
   * Parameters: id rowCoord, colCoord, limit
   *
   *  id: each object should be assigned with an unique map id to be located on the map
   *  rowCoord, colCoord: location of the destination
   *  limit: how many step can the object move
   * Return: boolean: true if the attemped move is less than or equal the limit, false if the object not on map or
   the movement is more than the limit
   */
  protected boolean isLegalMove(int id, int rowCoord, int colCoord, int limit) {
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
  protected boolean move(int id, int rowCoord, int colCoord, int limit) {
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
  
  protected double calDist(int col1, int row1,int col2,int row2) {
	  double dist = Math.abs(row1 - row2) + Math.abs(col1 - col2); 
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
   * take argument and calculate shortest way
   * Usage -> shortestWay(int destX, int destY, int xStart, int yStart, 'i')
   *this is a recursing fuction which loop the way from start point to end point, if the path move forward once, it wont go backward right after, same with up/down.
   *
   * Return: int ( the steps take to travel from startpoint to end point)
   */
  
  public int shortestWay(int destX, int destY, int xStart, int yStart, char step) {
	  if ((destX == xStart + 1 && destY == yStart) || (destX == xStart - 1 && destY == yStart) || (destX == xStart  && destY == yStart + 1) || (destX == xStart && destY == yStart -1 )) {
		  return 1;
		  }
	  if ((calDist(destX , destY, xStart -1, yStart) < calDist(destX , destY, xStart, yStart)) && step != 'r' && isEmpty( yStart, xStart-1)) {
		  return (1 + shortestWay( destX,  destY,  xStart-1,  yStart, 'l'));
	  }else if(calDist(destX , destY, xStart +1, yStart) < calDist(destX , destY, xStart , yStart) && step != 'l'&& isEmpty( yStart, xStart+1)) {
		  return (1 + shortestWay( destX,  destY,  xStart+1,  yStart, 'r'));
	  }
	  else if(calDist(destX , destY, xStart , yStart+1) < calDist(destX , destY, xStart , yStart) && step != 'd'&& isEmpty( yStart+1, xStart)) {
		  return (1 + shortestWay( destX,  destY,  xStart,  yStart+1, 'u'));
	  }
	  else if(calDist(destX , destY, xStart , yStart-1) < calDist(destX , destY, xStart , yStart) && step != 'u'&& isEmpty( yStart-1, xStart)){
		  return (1+shortestWay( destX,  destY,  xStart,  yStart-1, 'd'));
	  }
	  return 1;
  }
  /**
   * take argument and passed into shortestWay(int destX, int destY, int xStart, int yStart, char step) to generate number of steps
   * Usage -> shortestWay(int destX, int destY, int xStart, int yStart)
   * 
   *
   * Return: shortestWay (destX,destY, xStart,  yStart, 'i')
   */
	 public int shortestWay(int destX, int destY, int xStart, int yStart) {
		 return shortestWay (destX,destY, xStart,  yStart, 'i');
	  }
  
  }