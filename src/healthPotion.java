
public class healthPotion extends Item {
		//Instance variables
		
		//constructor
		public healthPotion(){
			upper("Health Poiton");
		}
		
		// main should find the character according to id and pass character into this method
		public void use(Character c) {
			c.setHealth(c.getHealth + 100);
		}
}

