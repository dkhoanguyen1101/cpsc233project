import java.util.ArrayList;
import java.util.Scanner;

public class ZoologyMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);
	
	//constructor
	public ZoologyMajor(int id) {
		super("Zoology Major", id, 20, 180, 2, 20, 180, 5, 8, 1, "This special allows the Zoology Major to move a second time (Costs 2 mana). Select the location you would like to move to");
		}
		
	//methods
	
	//Special allows the ZoologyMajor Character to take a second movement action
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		if (getMana() < 2) {
			System.out.println("This special requires 2 mana");
		} else {
			didSomething = theMap.move(this.getID(), xPos, yPos, this.getRange());
		}
		if (didSomething == true) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
