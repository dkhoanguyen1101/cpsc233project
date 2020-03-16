	
	import java.util.ArrayList;
	import java.util.Scanner;
	public class Enemy extends Chara{
		Scanner userIn = new Scanner(System.in);
		
		public Enemy(String name, int id, int att, int health, int mana, 
				int maxAtt, int maxHealth, int maxMana, int move, int range) {
			super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
		}
		
		public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
			return false;
		}
}
