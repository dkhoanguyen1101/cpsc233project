import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;

public class ZoologyMajor extends Chara {

	//instance variables
	Scanner userIn = new Scanner(System.in);
	
	//constructor
	public ZoologyMajor(int id) {
		super("Zoology Major", id, 20, 180, 2, 20, 180, 5, 8, 1);
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

