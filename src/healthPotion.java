
public class healthPotion extends item {
		//Instance variables
		
		//constructor
		public healthPotion ("Health Poison"){
			upper("Health Poison");
		}
		
		// main should find the character according to id and pass character into this method
		public void use(Character c) {
			c.setHealth(c.getHealth + 100);
		}
}

