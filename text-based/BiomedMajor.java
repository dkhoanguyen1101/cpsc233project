import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class BiomedMajor extends Chara {
	
	//instance variables
	Scanner userIn = new Scanner(System.in);	
	
	
	//constructor
	public BiomedMajor(int id) {
super("Biomedical Major", id, 10, 250, 3, 10, 250, 7, 4, 1, "This special boosts the health of a chosen nearby ally (costs 3 mana)");
	}
	
	//methods
	
	//this special boosts the health of a near-by ally
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 3) System.out.println("This special requires 3 mana");
		else {
			System.out.println("Heal another teammate for 40 health - within 3 spaces (costs 3 mana)");
			System.out.println("Enter the ID of a teammate to heal");
			Integer choice = userIn.nextInt();
			boolean isPlayer = false;
			int playerID = 0;
			int playerIndex = 0;
			for(int i = 0; i < players.size(); i++) {
				if(!isPlayer) {
					playerID = players.get(i).getID();
					isPlayer = playerID == choice;
					playerIndex = i;
				}
			}
				
			if(isPlayer) {
				int[] playerPos = theMap.getPos(playerID);
				int[] healerPos = theMap.getPos(getID());
				int range = Math.abs(healerPos[0] - playerPos[0]) + Math.abs(healerPos[1] - playerPos[1]);
				if(range <= 3) {
					Chara healed = players.get(playerIndex);
					healed.setHealth(healed.getHealth() + 40);
					setMana(getMana() - 3);
					didSomething = true;
				} else {
					System.out.println("Teammate is not in range");
				}
			} else {
				System.out.println("That teammate does not exist");
			}
		}
		return didSomething;
	}
}

