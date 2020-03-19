
import java.util.ArrayList;
import java.util.Scanner;

public class PhilosophyMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);
	private static int maxHealth = 200;
	private static int maxAttack = 15;
	private static int maxMana = 5;
	private int ID;
	private static String name = "Philosophy Major";
	private Item[] inventory = { new HealthPotion(),  new EmptyItem(),  new EmptyItem()};	
	
	//constructor
	public PhilosophyMajor(int id) {
		this.ID = id;
		this.setAttack(maxAttack);
		this.setHealth(maxHealth);
		this.setMana(2);
		this.setRange(2);
		this.setMove(5);
	}
	
	//methods
	
	//this special lowers the attack of an enemy
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2)
			{System.out.println("This special requires 2 mana");}
		else {
			System.out.println("Reduce attack of your enemies: ");
			int number = 0;
			for(Chara x: enemies) {
				number ++;
				System.out.print( (number+1) +  x.getName());
				}
			System.out.println("Choose an enemy:");
			int choice = userIn.nextInt();
			if(choice < enemies.size()) {
				enemies.get(choice).setAttack(enemies.get(choice).getAttack() - 5);
				
				setMana(getMana() - 2);
				didSomething = true;
		
			}
		}
		return didSomething;
	}
