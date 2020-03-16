
public class AttackPotion extends Item{

	public AttackPotion(int id, String aName) {
		super(id, aName);
	}
	
	public boolean use(Chara c) {
		return false;
	}
}
