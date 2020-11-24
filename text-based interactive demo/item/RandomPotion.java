package item;
import java.util.Random;
import Chara.*;
public class RandomPotion extends Item{

	public RandomPotion() {
		super(35, "Random Potion");
	}
	
	//RandomPotion raises the Health and Mana of the user by a random amount
	public boolean use(Chara c) {
		Random rand = new Random();
		int hlthGain = rand.nextInt(15);
		int manaGain = rand.nextInt(3);
		c.setHealth(c.getHealth() + (hlthGain * 10));
		c.setMana(c.getMana() + manaGain);
		return true;
	}
}
