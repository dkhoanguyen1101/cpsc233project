import java.util.ArrayList;


public class map {

  private int row;
  private int collumn;
  private ArrayList<ArrayList<Integer>> twoDList = new ArrayList<ArrayList<Integer>>();

  protected map(int r, int cl){
    row = r;
    collumn = cl;
    for(int countRow = 0; countRow <= row; countRow++) {
    	twoDList.add(new ArrayList<Integer>());
    	for(int countCollumn = 0; countCollumn <= collumn; countCollumn++) {
    		twoDList.get(countRow).add(0);
    		}
    	}
    }

  protected int[] getPos(int id) {
	  int[] position = new int[2];
	  position[0] = -1;
	  position[1] = -1;
	  for(int countRow = 0; countRow < row; countRow++) {
		  for(int countCol = 0; countCol < collumn; countCol++) {
			  if(twoDList.get(countRow).lastIndexOf(id) != -1) {
				  int copyCol = countCol;
				  position[0]=copyCol;
				  int copyRow = countRow;
				  position[1]=copyRow;
				  return position;
		  }
	  }}
	  return position;
  }

  protected void setPos(int id, int rowCoord, int colCoord) {
	  twoDList.get(rowCoord).set(colCoord, id);
  }

  protected boolean isEmpty(int rowCoord, int colCoord) {
	  return twoDList.get(rowCoord).get(colCoord).equals(0);
  }

  protected boolean onMap(int id) {
	  int[] position = getPos(id);
	  if(position[0] != -1 && position[1] != -1) {
		  return true;
	  }
	  return false;
  }

  protected boolean legalMove(int id, int rowCoord, int colCoord, int availableMove) {
		  int[] position = getPos(id);
		  if (onMap(id)) {
		  int originalCol = position[0];
		  int originalRow = position[1];
		  int distance = Math.abs(rowCoord - originalRow) + Math.abs(colCoord - originalCol);
		  return (distance < availableMove);}
		  return false;
  }

  protected void move(int id, int rowCoord, int colCoord, int availableMove) {
	  if (onMap(id)) {
	  if (isEmpty(rowCoord, colCoord)) {
	  if (legalMove( id, rowCoord, colCoord, availableMove) == true) {
		 setPos(id, rowCoord, colCoord);}
	  else {
		  System.out.println("out of range move");
	  }
	 } else {
		 System.out.println("place is not empty");
	 	}
	}else {
		System.out.println("character is not on map");
	}
  }
}
