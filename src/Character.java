import java.util.Scanner;

public class Character {
	private int attack;
	private int health;
	private int ID;
	private String name;
	private item[] inventory = item[3];

	public Character(String name, int id, int att, int health) {
		this.name = name;
		this.id = id;
		this.attack = att;
		this.health = health;
	}
	
	public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }
	
    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
   
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  

    public int getInventory() {
    	toReturn = new item[3];
    	for (int index = 0; index < this.inventory.size(); index++) {
    		toReturn[index] = this.inventory[index]
    	}
        return toReturn;
    }

    public void setItem(item newItem, index) {
        inventory[index] = newItem;
    }
    
   
   //output player name
    
}
	


