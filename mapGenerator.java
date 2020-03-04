
public class mapGenerator {
	
map toReturn = new map();
private int[] map1 = {0, 15, 15, 0, 0, 8, 3, 15, 7, 12, 11, 15, 3, 0, 8, 3, 12, 0, 8, 3};

public mapGenerator(String stage, int firstChar, int secondChar, int thirdChar) {
	if (stage.contentEquals("Quiz")){
		
		toReturn.setPos(firstChar, map1[6], map1[7]);
		toReturn.setPos(secondChar, map1[8], map1[9]);
		toReturn.setPos(thirdChar, map1[10], map1[1]);
		
	}
}

public map generate() {
	return toReturn;
}
}