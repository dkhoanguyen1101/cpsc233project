
public class MovementPotion extends Item{

	public MovementPotion(int id, String aName) {
		super(id, aName);
	}
	
	//increases the users movement by two permenently
	public boolean use(Chara c) {
		c.setMove(c.getMove() + 2);
		return true;
	}
}

