package Chara;
		
import java.util.ArrayList;

import main.map;
public class Midterm extends Chara {
		
	public Midterm(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range, "");
	}

	//this special is a high-cost, but permanent-debuff on all players
	
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		//check to make sure there is enough mana to use ability (10)
		if(getMana() >= 10)
			//reduce all player characters attack value by one
			for(Chara member : players) {		
					member.setAttack(member.getAttack() - 1);
				//lower mana value by ten
				setMana(getMana() - 10);
				}
				//mark that special was used
				didSomething = true;
		
		return didSomething;
	}
}
