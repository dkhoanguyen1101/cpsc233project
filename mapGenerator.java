import java.util.Random; 

public class mapGenerator {
	
map toReturn;
private int[][] map1 = {{0, 13, 1, 14, 2, 15}, {13, 0, 14, 1, 15,2}, {4,11}};

public mapGenerator() {
	generate();
	}

public map generate() {
	Random numberGen = new Random();
	int mapNum = numberGen.nextInt(3) +1;
	switch(mapNum) {
	case( 1):{
		map toReturn = new map(map1[0], map1[1], map1[2]);
		return toReturn;
	}
	default:{
		map toReturn = new map(map1[0], map1[1], map1[2]);
		return toReturn;}
	}
	
}
}
