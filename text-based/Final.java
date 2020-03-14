	
	import java.util.ArrayList;
	import java.util.Scanner;
	public class Final extends Chara{
	Scanner userIn = new Scanner(System.in);
		
	public Final(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
		
	//this special move is a powerful AOE attack that hits all players within one space of the final three times
	//costs three mana points to for the final to use
		
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		boolean didSomething = false;
		if(getMana() < 3) System.out.println("This special requires 3 mana");
		else {
			
			//these two for loops check each spot around the final
			
			for (int a = -1; a < 2; a++) {
				for (int b = -1; b < 2; b++) {
					int zoneX = theMap.getPos(this.getID())[0] + a;
					int zoneY = theMap.getPos(this.getID())[1] + b;
					int[] zone = new int[2];
					zone[0] = zoneX;
					zone[1] = zoneY;
				
					/* this for loop checks if any player is in the spot being
					 * checked and attacks them three times if it is */
					
					for(int i = 0; i < players.size(); i++) {
						if((theMap.getPos(players.get(i).getID())[0] == zone[0]) && (theMap.getPos(players.get(i).getID())[1] == zone[1])) {
							attack(players.get(i));
							attack(players.get(i));
							attack(players.get(i));
						}
					}
				}
			}
			setMana(getMana() - 3);
			didSomething = true;
		}	
		return didSomething;
	}
}
