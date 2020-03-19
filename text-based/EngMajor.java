
import java.util.ArrayList;
import java.util.Scanner;

public class EngMajor extends Chara {

	
	//instance variables
		Scanner userIn = new Scanner(System.in);
		private static int maxHealth = 200;
		private static int maxAttack = 20;
		private static int maxMana = 5;
		private int ID;
		private static String name = "Engineering Major";
		private Item[] inventory = { new HealthPotion(),  new EmptyItem(),  new EmptyItem()};	
		
		//constructor
		public EngMajor(int id) {
			this.ID = id;
			this.setAttack(maxAttack);
			this.setHealth(maxHealth);
			this.setMana(3);
			this.setRange(4);
			this.setMove(4);
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
