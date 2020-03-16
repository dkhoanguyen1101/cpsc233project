
public class RandomPotion extends Item{

	public RandomPotion(int id, String aName) {
		super(id, aName);
	}
	
	public boolean use(Chara c) {
		return false;
	}
}
