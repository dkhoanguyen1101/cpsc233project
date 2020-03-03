
//character class @author Yuwen


public class Chara {
	private int health;
	private int maxHealth;
	private int attack;
	private int maxAttack;
	private int ID;
	private String name;
	private Item[] inventory = { new EmptyItem(),  new EmptyItem(),  new EmptyItem()};
	private int range;
	public Chara() {
		
	}
	public Chara(String name, int id, int att, int health) {
		this.name = name;
		this.ID = id;
		setAttack(att);
		setHealth(health);
	}
	public int getAttack() {
      return attack;
	}
	public void setAttack(int att) {
	if (att > maxAttack) {
		attack = maxAttack;
	}else {
      attack = att;}
	}
	
	public int getRange() {
	      return range;
		}
		public void setRange(int r) {
	      range = r;
		}
	 public Item[] getInventory() {
		Item[] toReturn = new Item[3];
	    for (int index = 0; index < this.inventory.length; index++) {
	    	toReturn[index] = this.inventory[index];
	    	}
	    return toReturn;
	    }
	 public void setItem(Item newItem,int index) {
	        inventory[index] = newItem;
	    }
	 public String getItemName(int ind) {
		 return new String(this.inventory[ind].getName());
	 }
	 
	 public Item getItem(int ind) {
		 return this.inventory[ind];
	 }
	
	 public void setHealth(int i) {
		if (i >= maxHealth) {
			health = maxHealth;
		}else {
			health = i;
		}
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
	public boolean Special() {
		return false;
	}
}	


