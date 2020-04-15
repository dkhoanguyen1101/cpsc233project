package item;

import characters.Chara;

//this class is a place holder for when a character is not holding an item
public class EmptyItem extends Item {
	public EmptyItem() {
		super(31, "Empty");
	}
	
	@Override
	public boolean use(Chara c) {
		// TODO Auto-generated method stub
		return false;
	}
}
