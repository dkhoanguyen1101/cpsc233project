package item;

import characters.Chara;

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
