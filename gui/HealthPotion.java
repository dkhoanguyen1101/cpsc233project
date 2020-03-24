public class HealthPotion extends Item {

	public HealthPotion(int id){
	super(id, "HealthPotion");
}
	//increases the users health points by 50
	public boolean  use(Chara c){
	c.setHealth(c.getHealth() + 50);
	return true;
	
	
}
}
