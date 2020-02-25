
//character class @author Yuwen


public class Chara {
	private int health;
	private int attack;
	private int ID;
	private String name;
	private String[] inventory = {"empty", "empty", "empty"};
	public Chara() {
		
	}
	public Chara(String name, int id, int att, int health) {
		this.name = name;
		this.ID = id;
		this.attack = att;
		this.health = health;
	}
	public int getAttack() {
      return attack;
	}
	public void setAttack(int att) {
      attack = att;
	}
	 public String[] getInventory() {
		String[] toReturn = new String[3];
	    for (int index = 0; index < this.inventory.length; index++) {
	    	toReturn[index] = this.inventory[index];
	    	}
	    return toReturn;
	    }
	 public void setItem(String newItem,int index) {
	        inventory[index] = newItem;
	    }
	 public String getItem(int ind) {
		 return new String(this.inventory[ind]);
	 }
	
	 public void setHealth(int i) {
		health  = i;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setID(int i) {
		ID = i;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setName(String i) {
		name = i;
	}
	
	public String getName() {
		return name;
	}
}	


