package item;
import Chara.*;
public class HealthPotion extends Item {

	public HealthPotion(){
	super(32, "HealthPotion");
}
	//increases the users health points by 50
	public boolean  use(Chara c){
	c.setHealth(c.getHealth() + 50);
	return true;
	
	
}
}
