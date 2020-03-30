import java.util.Random; 

public class mapGenerator {
	
map toReturn;
private int[][] map1 = {{2, 5, 3, 5, 4, 5, 5, 5, 5, 4, 5, 3}, {11, 10, 12, 10, 13, 10, 10, 10, 10 , 11, 10, 12}, {7,7}};
private int[][] map2 = { {4, 5, 4, 6, 4, 7, 4,8, 4,9, 4, 10},{11, 5,11, 6, 11, 7,11, 8,  11, 9, 11, 10}, {7,7}};
private int[][] map3 = { {5, 5,6,5 7, 5,8,5 9,5,10,5},{5, 10,6, 10 7, 10,8,10 9,10, 10,10}, {7,7}};
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
		
		map toReturn = new map(map1[0], map1[1], map1[2], "Map1.png");
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 11; i < 16; i++) {
			for(int j = 11; j < 16; j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		return toReturn;
	}
	case( 2):{
		
		map toReturn = new map(map2[0], map2[1], map2[2], "Map2.png");
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j < 5-i;j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		for(int i = 0; i<5; i++) {
			for(int j = 15;j  > 10 + i;j--) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 0; i<5; i++) {
			for(int j = 15; j > 10 + i;j--) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 15; i>10; i--) {
			for(int j = 0; j < i - 10;j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 15; i>10; i--) {
			for(int j = 15; j > 25-i;j--) {
				toReturn.setPos(-1, j, i);
			}
		}

		return toReturn;
	}
	case( 3):{
		System.out.print(3);
		map toReturn = new map(map3[0], map3[1], map3[2], "Map3.png");
		for(int i = 0; i < 5 ; i++) {
			for (int j = i; j<16 - i; j ++) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 15; i > 10 ; i--) {
			for (int j = i; j >= 15-i; j --) {
				toReturn.setPos(-1, j, i);
			}
		}
		return toReturn;
	}
	default:{
		map toReturn = new map(map1[0], map1[1], map1[2], "Map1.png");
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		
		for(int i = 11; i < 16; i++) {
			for(int j = 11; j < 16; j++) {
				toReturn.setPos(-1, j, i);
			}
		}
		return toReturn;}
	}
	
}
}

