
import java.util.ArrayList;
import java.util.Scanner;

public class ChemistryMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);	
	
	//constructor
	public ChemistryMajor(int id) {
		super("Chemistry Major", id, 15, 200, 2, 15, 200, 5, 5, 1);
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
