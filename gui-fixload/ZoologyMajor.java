import java.util.ArrayList;

public class ZoologyMajor extends Chara {
	
	//constructor
	public ZoologyMajor(int id) {
		super("Zoology Major", id, 20, 180, 2, 20, 180, 5, 6, 1, "This special allows the Zoology Major to increase \n it's overall movement range (Costs 2 mana). \n Click anywhere on the map to activate this", "Zoology.png");
		}
		
	//methods
	
	//Special allows the ZoologyMajor Character to take a second movement action
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		if (getMana() < 2) {
			System.out.println("This special requires 2 mana");
		} else {
			didSomething = true;
			this.setMove(this.getMove() + 1);
		}
		if (didSomething == true) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
