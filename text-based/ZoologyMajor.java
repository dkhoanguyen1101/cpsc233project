import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class ZoologyMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);
	private static int maxHealth = 180;
	private static int maxAttack = 20;
	private static int maxMana = 5;
	private int ID;
	private static String name = "Zoology Major";
	private Item[] inventory = { new HealthPotion(),  new EmptyItem(),  new EmptyItem()};	
		
	//constructor
	public ZoologyMajor(int id) {
		this.ID = id;
		this.setAttack(maxAttack);
		this.setHealth(maxHealth);
		this.setMana(2);
		this.setRange(1);
		this.setMove(8);
		}
		
	//methods
	
	//Special allows the ZoologyMajor Character increase it's speed by 1
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2) System.out.println("This special requires 2 mana");
		else {
			System.out.println("Boost your movement speed(Costs 2 mana)");
			System.out.println("Enter 1 to confirm");
			int choice = userIn.nextInt();
			if(choice == 1) {
				this.setMove(this.getMove() + 1);
				}
				setMana(getMana() - 2);
				didSomething = true;
		}
		return didSomething;
	}
}

