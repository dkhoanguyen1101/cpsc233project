

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

  protected int[] getPos(int id) {
	  int[] position = new int[2];
	  for(int countRow = 0; countRow <= row; countRow++) {
		  for(int countCol = 0; countCol <= collumn; countCol++) {
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

  protected boolean legalMove(int id, int rowCoord, int colCoord, int availableMove) {
	  if (isEmpty(rowCoord, colCoord) == true) {
		  int[] position = getPos(id);
		  int originalCol = position[0];
		  int originalRow = position[1];
		  int distance = Math.abs(rowCoord - originalRow) + Math.abs(colCoord - originalCol);
		  return (distance < availableMove);
	  }
	  return false;
  }

  protected void move(int id, int rowCoord, int colCoord, int availableMove) {
	 if (legalMove( id, rowCoord, colCoord, availableMove) == true) {
		 setPos(id, rowCoord, colCoord);
	 }
  }
  
