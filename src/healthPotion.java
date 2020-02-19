
public class healthPotion extends item {
		//Instance variables
	    private String name;
		private int health;
		private int heal_value;
		
		//constructor
		public healthPotion (String name, int health){
			this.name = name;
			this.health = health;
			this.setAttributes();
		}
		
		//determines heal value
		public void setAttributes() {
			this.heal_value = this.health + 20;
		}
		
		//getter for heal value
		public int getHealValue() {
			return this.heal_value;
		}
	}


