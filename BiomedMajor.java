import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class BiomedMajor extends Chara {
	
	Scanner userIn = new Scanner(System.in);
	
	public BiomedMajor(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
	
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
			for(int i = 0; i < enemies.size(); i++) {
				if(!isPlayer) {
					playerID = enemies.get(i).getID();
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
