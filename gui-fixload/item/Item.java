package item;
import java.io.Serializable;

import characters.Chara;

public abstract class Item implements Serializable {
private  int ID;
private int staticID;
private String name, imageURL;

//constructor for item class
public Item(int id, String aName) {
	setID(id);
	setName(aName);
	imageURL = "Item.png";
	
//getters and setters
}
public int getStatic() {
	int toReturn = staticID;
	return toReturn;
}
public String getName() {
	String toReturn = name;
	return toReturn;
}
public int getID() {
	int toReturn = ID;
	return toReturn;
}

public void setID(int i) {
	ID = i;
}
public void setName(String aName) {
	name = aName;
}

public String getImageURL() {
	return new String(imageURL);
}

//abstract method to be used by other items
public abstract boolean use(Chara c);


}
