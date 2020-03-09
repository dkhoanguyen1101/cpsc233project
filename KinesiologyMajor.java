import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;
public class KinesiologyMajor extends Chara{
	Scanner userIn = new Scanner(System.in);
	
	public KinesiologyMajor(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
	
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 2) System.out.println("This special requires 2 mana");
		else {
			System.out.println("Attack an enemy for 2x damage - within 1 cell (costs 2 mana)");
			System.out.println("Enter the ID of an enemy to attack");
			int choice = userIn.nextInt();
			boolean isEnemy = false;
			int enemyID = 0;
			int enemyIndex = 0;
			for(int i = 0; i < enemies.size(); i++) {
				if(!isEnemy) {
					enemyID = enemies.get(i).getID();
					isEnemy = enemyID == choice;
					enemyIndex = i;
				}
			}
			if(isEnemy) {
				int[] enemyPos = theMap.getPos(enemyID);
				int[] playerPos = theMap.getPos(getID());
				int range = Math.abs(enemyPos[0] - playerPos[0]) + Math.abs(enemyPos[1] - playerPos[1]);
				if(range <= 1) {
					int regAttack = getAttack();
					setAttack(regAttack * 2);
					attack(enemies.get(enemyIndex));
					setAttack(regAttack);
					setMana(getMana - 2);
					didSomething = true;
				} else {
					System.out.println("Enemy is not in range");
				}
			} else {
				System.out.println("That enemy does not exist");
			}
		}
		return didSomething;
	}
}
