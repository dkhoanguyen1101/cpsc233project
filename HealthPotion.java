public class HealthPotion extends Item {

	public HealthPotion(int id){
	super(id, "HealthPotion");
}
	public void  use(Chara c){
	c.setHealth(c.getHealth() + 50);
	
	
}
}
