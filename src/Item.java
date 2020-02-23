//import java.util.Scanner;
//import java.util.ArrayList;
//
//
//public class Item {
//	private int inventory;
//	private String[] inventorys = {"item A","item B","item C"};
//	
//	
//	//constructor
//	public Item(int inventory, String[] inventorys) {
//		this.inventory =  inventory;
//		this.inventorys = inventorys;
//	}
//	
//
//	public int getInventory() {
//	        return inventory;
//	    }
//
//	public void setInventory(int inventory) {
//	        this.inventory = inventory;
//	    }
//	
//	public String[] getInventorys() {
//		return inventorys;
//	}
//
//	public void setInventorys(String[] inventorys) {
//		this.inventorys = inventorys;
//	}
//	
//	public Item(ArrayList<String> inventorys){
//	
//		System.out.println("Choose item use （A，B，C）：");
//		Scanner sc = new Scanner(System.in);
//        this.inventory = sc.nextInt();
//     
//		for(int i = 0; i < inventorys.size(); i++) {
//		if (inventorys.get(i) == "A"){
//	        System.out.println("You use item A");
//	        inventorys.remove(i);
//	        i--;
//	        }
//		else if (inventorys.get(i) == "B"){
//	        System.out.println("You use item B");
//	        inventorys.remove(i);
//	        i--;
//	        }
//		else if (inventorys.get(i) == "C"){
//	        System.out.println("You use item C");
//	        inventorys.remove(i);
//	        i--;
//	        }
//		}
//	   
//	}



public class Item {
	String name;
	int id;
	
	public Item(String name) {
		this.name = name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		String toReturn;
		toReturn = this.name;
		return toReturn;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int i) {
		id = i;
	}
	
}


//	
//}
//
//
//
//
