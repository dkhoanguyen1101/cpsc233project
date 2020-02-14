import java.util.ArrayList;


public class map {
  private int row;
  private int collumn;
  private ArrayList<ArrayList<Integer>> twoDList = new ArrayList<ArrayList<Integer>>();

  protected map(int r, int cl){
    int copyr = r;
    row = copyr;
    int copycl = r;
    collumn = copycl;
    for(int countRow = 0; countRow <= row; countRow++) {
    	twoDList.add(new ArrayList<Integer>());
    	for(int countCollumn = 0; countCollumn <= collumn; countCollumn++) {
    		twoDList.get(countRow).add(0);
    		}
    	}
    }

  protected int[] getPos(int a) {
	  int[] position = new int[2];
	  for(int countRow = 0; countRow <= row; countRow++) {
		  if(twoDList.get(countRow).lastIndexOf(a) != -1) {
		  position[0]=(twoDList.get(countRow).lastIndexOf(a));
		  position[1]=countRow;
		  return position;
		  }
	  }
	  return position;
  }
  protected void setPos(int id, int rowCoord, int colCoord) {
	  for(int countRow = 0; countRow <= row; countRow++) {
		 for(int countCol = 0; countCol <= collumn; countCol++) {
			 if(twoDList.get(countRow).get(countCol) == id) {
				 twoDList.get(countRow).set(countCol, 0);
			 }
		 }
	  }
	  twoDList.get(rowCoord).set(colCoord, id);
  }
  }
