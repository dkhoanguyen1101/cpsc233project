
public class healthPotion extends Item {
		//Instance variables
	    private String name;
		private int health;
		private int heal_value;
		
		//constructor
		public healthPotion (String name, int health, int ID){
			this.name = name;
			this.health = health;
			this.setAttributes();
		}
		//determines heal value
		public void setAttributes() {
			this.heal_value = this.health + 20;
		}
		public characterHealth(Chara) {
			Chara.getHealth = this.heal_value;
			return Character.getHealth;
		}
		//getter for heal value
		public int getHealValue() {
			return this.heal_value;
		}
}

