import java.util.ArrayList;

public class Enemy extends Chara{
		
	public Enemy(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range, "none");
	}
		
	/* basic enemies do not have specials.
	 * instead, they simply return that the special 
	 * cannot be used at all times	*/
		
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		return false;
	}
}
