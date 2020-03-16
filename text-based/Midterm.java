	
	import java.util.ArrayList;
	import java.util.Scanner;
	public class Midterm extends Chara{
		Scanner userIn = new Scanner(System.in);
		
		public Midterm(String name, int id, int att, int health, int mana, 
				int maxAtt, int maxHealth, int maxMana, int move, int range) {
			super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
		}
		
		/*this special is a high-cost, but permanent-debuff on the targeted player
		*/
		public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
			
			return false;
		}
}
