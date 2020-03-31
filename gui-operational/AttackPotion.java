
public class AttackPotion extends Item{

	public AttackPotion() {
		super(30, "Attack Potion");
	}
	
	//AttackPotion raises the users attack value by 10
	public boolean use(Chara c) {
		c.setAttack(c.getAttack() + 10);
		return true;
	}
}
