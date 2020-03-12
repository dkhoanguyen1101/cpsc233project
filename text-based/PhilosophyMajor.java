
import java.util.ArrayList;
import java.util.Scanner;

public class PhilosophyMajor extends Chara{
	Scanner userIn = new Scanner(System.in);
	
	public PhilosophyMajor(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
	
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
			System.out.println("Choose");
			int choice = userIn.nextInt();
			if(choice < enemies.size()) {
				enemies.get(choice).setAttack(enemies.get(choice).getAttack() - 5);
				
				setMana(getMana() - 2);
				didSomething = true;
		
			}
		}
		return didSomething;
	}
}
