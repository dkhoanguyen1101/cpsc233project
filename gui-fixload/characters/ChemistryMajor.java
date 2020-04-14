package characters;

import java.util.ArrayList;

import main.map;

public class ChemistryMajor extends Chara {

	//instance variables	
	
	//constructor
	public ChemistryMajor(int id) {
		super("Chemistry Major", id, 15, 200, 2, 15, 200, 5, 5, 1, "This special boosts the attack \n of a chosen ally (costs 3 mana). \n Select the ally you want to boost \n on the map", "BioChem.png");
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
					teammate.setAttack(teammate.getAttack() + 5);
					setMana(getMana() - 3);
					didSomething = true;
				}		
			}
		}
		return didSomething;
	}
}
