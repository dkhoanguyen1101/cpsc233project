import java.util.Random; 

public class mapGenerator {
	
map toReturn;
private int[][] map1 = {{0, 13, 1, 14, 2, 15}, {13, 0, 14, 1, 15,2}, {4,11}};
private int[][] map2 = { {13, 0, 14, 1, 15,2},{0, 13, 1, 14, 2, 15}, {11,4}};
private int[][] map3 = { {6, 11, 7, 11, 8,11},{6, 4, 7, 4, 8,4}, {11,4}};
public mapGenerator() {
	generate();
	}
/*
 * using the Random library, generate a number and return a random map to Game
 * Usage: generate()
 * return map toReturn
 */
public map generate() {
	Random numberGen = new Random();
	int mapNum = numberGen.nextInt(3) +1;
	switch(mapNum) {
	case( 1):{
		map toReturn = new map(map1[0], map1[1], map1[2]);
		return toReturn;
	}
	case( 2):{
		map toReturn = new map(map2[0], map2[1], map2[2]);
		return toReturn;
	}
	case( 3):{
		map toReturn = new map(map3[0], map3[1], map3[2]);
		return toReturn;
	}
	default:{
		map toReturn = new map(map1[0], map1[1], map1[2]);
		return toReturn;}
	}
	
}
}
