
import java.util.ArrayList;
import java.util.Scanner;

public class EngMajor extends Chara {

	
	//instance variables
		Scanner userIn = new Scanner(System.in);	
		
		//constructor
		public EngMajor(int id) {
			super("Engineering Major", id, 20, 200, 3, 20, 200, 5, 4, 4, "This special move attacks any enemies in the same chosen column or row as the Engineering Major (costs 3 mana)");
		}
	
	//this special attacks any enemies in the same row as the engineer
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2) System.out.println("This special requires 2 mana");
		else {
			System.out.println("Attack all enemies in the same row as you (Costs 2 mana");
			System.out.println("Enter 1 to confirm");
			int choice = userIn.nextInt();
			if(choice == 1) {
				int row = theMap.getPos(getID())[1];
				for(int i = 0; i < enemies.size(); i++) {
					if(theMap.getPos(enemies.get(i).getID())[1] == row) {
						attack(enemies.get(i));
					}
				}
				setMana(getMana() - 2);
				didSomething = true;
			}
		}
		return didSomething;
	}
}