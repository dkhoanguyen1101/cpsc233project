
public class healthPotion extends Item {
		//Instance variables
	    private String name;
		private int health;
		private int heal_value;
		private int charaHealth;
		
		//constructor
		public healthPotion (String name, int health, int charaHealth){
			this.name = name;
			this.health = health;
			this.charaHealth = charaHealth;
			this.setAttributes();
			
		}
		//determines heal value
		public void setAttributes() {
			this.heal_value = this.health + 20;
		}
		
		public int characterHealth(int Chara) {
			this.charaHealth = this.heal_value;
			return this.charaHealth;
		}
		//getter for heal value
		public int getHealValue() {
			return this.heal_value;
		}
}
