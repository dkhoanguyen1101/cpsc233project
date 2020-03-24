

public abstract class Item {
private  int ID;
private int staticID;
private String name;
public Item(int id, String aName) {
	setID(id);
	setName(aName);
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

public abstract boolean use(Chara c);


}
