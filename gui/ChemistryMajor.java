
import java.util.ArrayList;

public class ChemistryMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);	
	
	//constructor
	public ChemistryMajor(int id) {
		super("Chemistry Major", id, 15, 200, 2, 15, 200, 5, 5, 1, "This special boosts the attack of a chosen ally (costs 3 mana). Select the ally you want to boost on the map");
	}

	//methods
	
	//this special boosts the attack of your allies
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		if(getMana() < 3)
			{System.out.println("This special requires 3 mana");}
		else {
			int choice = theMap.getID(xPos, yPos);
			for(Chara teammate: players) {
				if (choice == teammate.getID()) {
					players.get(choice).setAttack(players.get(choice).getAttack() + 5);
					setMana(getMana() - 3);
					didSomething = true;
				}		
			}
		}
		return didSomething;
	}
}
