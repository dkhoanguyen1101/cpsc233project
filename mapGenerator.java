import java.util.Random; 

public class mapGenerator {
	
map toReturn;
private int map1 = { {13, 0, 14, 1, 15,2}};

public mapGenerator() {
	generate();
	}

public map generate() {
	Random numberGen = new Random();
	int mapNum = numberGen.nextInt(3) +1;
	return toReturn;
}
}