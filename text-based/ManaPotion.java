
public class ManaPotion extends Item{

	public ManaPotion(int id, String aName) {
		super(id, aName);
	}
	
	public boolean use(Chara c) {
		c.setMana(c.getMana() + 3);
		return true;
	}
}
