
import java.util.ArrayList;
import java.util.Scanner;

public class ChemistryMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);
	private static int maxHealth = 200;
	private static int maxAttack = 20;
	private static int maxMana = 5;
	private int ID;
	private static String name = "Chemistry Major";
	private Item[] inventory = { new HealthPotion(),  new EmptyItem(),  new EmptyItem()};	
	
	//constructor
	public ChemistryMajor(int id) {
		this.ID = id;
		this.setAttack(maxAttack);
		this.setHealth(maxHealth);
		this.setMana(2);
		this.setRange(1);
		this.setMove(5);
	}

	//methods
	
	//this special boosts the attack of your allies
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2)
			{System.out.println("This special requires 2 mana");}
		else {
			System.out.println("Increase attack of your allies: ");
			int number = 0;
			for(Chara x: players) {
				number ++;
				System.out.print( (number+1) +  x.getName());
				}
			System.out.println("Choose");
			int choice = userIn.nextInt();
			if(choice < players.size()) {
				players.get(choice).setAttack(players.get(choice).getAttack() + 5);
				
				setMana(getMana() - 2);
				didSomething = true;
		
			}
		}
		return didSomething;
	}
}
