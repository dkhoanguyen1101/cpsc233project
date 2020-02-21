
public class healthPotion extends item {
		//Instance variables
		
		//constructor
		public healthPotion ("Health Poison"){
			upper("Health Poison");
		}
		public void use(Character c) {
			c.setHealth(c.getHealth + 100);
		}
}

