
public class ManaPotion extends Item{

	public ManaPotion() {
		super(33, "Mana Potion");
	}
	
	//increases users mana points by 3
	public boolean use(Chara c) {
		c.setMana(c.getMana() + 3);
		return true;
	}
}
