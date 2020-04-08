package item;
import Chara.*;
public class MovementPotion extends Item{

	public MovementPotion() {
		super(34, "Movement Potion");
	}
	
	//increases the users movement by two permenently
	public boolean use(Chara c) {
		c.setMove(c.getMove() + 2);
		return true;
	}
}

