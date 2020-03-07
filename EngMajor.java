import java.util.ArrayList;
import java.util.Scanner;

public class EngMajor extends Chara{
	Scanner userIn = new Scanner(System.in);
	
	public EngMajor(String name, int id, int att, int health, int maxAtt, int maxHealth, int move, int range) {
		super(name, id, att, health, maxAtt, maxHealth, move, range);
	}
	
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		System.out.println("Attack all enemies in the same row as you");
		System.out.println("Enter 1 to confirm");
		int choice = userIn.nextInt();
		if(choice == 1) {
			int row = theMap.getPos(getID())[1];
			for(int i = 0; i < enemies.size(); i++) {
				if(theMap.getPos(enemies.get(i).getID())[1] == row) {
					attack(enemies.get(i));
				}
			}
			didSomething = true;
		}
		return didSomething;
	}
}
