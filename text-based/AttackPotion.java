
public class AttackPotion extends Item{

	public AttackPotion(int id, String aName) {
		super(id, aName);
	}
	
	//AttackPotion raises the users attack value by 10
	public boolean use(Chara c) {
		c.setAttack(c.getAttack(0 + 10);
		return true;
	}
}
