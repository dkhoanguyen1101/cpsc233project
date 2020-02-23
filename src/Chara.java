public class Chara {
	private int health;
	private int attack;
	private int ID;
	private String name;
	private String[] inventory = new String[3];
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
	public void setHealth(int i) {
		health  = i;
	}
	public int getHealth() {
		return health;
	}
	
	public void setId(int i) {
		ID = i;
		
	}
	
	public int getId() {
		return ID;
	}
	}