package Chara;
import java.util.ArrayList;

import main.map;

public class Quiz extends Chara {

	public Quiz(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range,"none");
	}
	@Override
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		// TODO Auto-generated method stub
		return false;
	}

}
