
public class MovementPotion extends Item{

	public MovementPotion(int id, String aName) {
		super(id, aName);
	}
	
	public boolean use(Chara c) {
		return false;
	}
}

