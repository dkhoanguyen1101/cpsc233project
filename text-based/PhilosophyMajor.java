
import java.util.ArrayList;
import java.util.Scanner;

public class PhilosophyMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);	
	
	//constructor
	public PhilosophyMajor(int id) {
		super("Philosophy Major", id, 15, 200, 2, 15, 200, 5, 5, 2, "This special lowers the attack of a chosen enemy");
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
