
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Chara implements Serializable{
	
	//instance variables
	private int health;
	private int maxHealth;
	private int attack;
	private int maxAttack;
	private int mana;
	private int maxMana;
	private int ID;
	private String name;
	private Item[] inventory = { new EmptyItem(),  new EmptyItem(),  new EmptyItem()};
	private int range;
	private int move;
	private String specDesc = "No special description";
	private String imageUrl;
	
	//constructors
	public Chara(String name, int id, int att, int health, int mana, int maxAtt, int maxHealth, int maxMana, int move, int range, String specDesc, String imageUrl) {
		this.name = new String(name);
		this.ID = id;
		setMove(move);
		setRange(range);
		setMaxAttack(maxAtt);
		setMaxHealth(maxHealth);
		setMaxMana(maxMana);
		setAttack(att);
		setHealth(health);
		setMana(mana);
		this.specDesc = specDesc;
		setImageUrl(imageUrl);
	}
	
	//methods
	public String getImageUrl(){
		return imageUrl;
	}
	
	public void setImageUrl(String uRl) {
		imageUrl = uRl;
	}
	public int getMana() {return mana;}
	
	public void setMana(int newMana) {
		if(newMana >= maxMana) mana = maxMana;
		else mana = newMana;
	}
	
	public int getMaxMana() {return maxMana;}
	
	public void setMaxMana(int newMax) {maxMana = newMax;}
	
	public int getMove() {return move;}
	
	public void setMove(int newMove) {move = newMove;}
	
	public int getAttack() {
      return attack;
	}
	public void setAttack(int att) {
      attack = att;
	}
	public void setMaxAttack(int i) {
		maxAttack = i;
	}
	public int getMaxAttack() {
		int toReturn = maxAttack;
		return toReturn;
	}
	public int getMaxHealth() {
		int toReturn = maxHealth;
		return toReturn;
	}
	public void setMaxHealth(int i) {
		maxHealth = i;
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
	
	public String getSpecDesc() {
		return specDesc;
	}
	
	public abstract boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos);
	
	public void attack(Chara receiver) {
		receiver.setHealth(receiver.getHealth() - getAttack());
	}
}

