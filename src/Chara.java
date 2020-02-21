import java.util.Scanner;

public class Chara {
	private int attack;
	private int health;
	private int ID;
	private String name;
	private int faculty;
	private int inventory;
	private String[] facultys = {"CS","ENG","NURSE"};
	private String[] inventorys = {"item 0","item 1","item 2"};

	//constructor
	public Chara (int attack,  int health, int ID, String name, int faculty, int inventory,  String[] facultys, String[] inventorys) {
		this.attack = attack;
		this.health = health;
		this.ID = ID;
		this.name = name;
		this.faculty = faculty; 
		this.inventory = inventory;
		this.facultys = facultys;
		this.inventorys = inventorys;
	}
	
	public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }
	
    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
   
    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public int getFaculty() {
        return faculty;
    }

    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
   
   
    //output player name
    public String Setting() {
        System.out.println("Enter the character name：");
        Scanner sc = new Scanner(System.in);
        this.name = sc.next();
        return name;
    }

    public void outputName(){
        System.out.println("name:"+this.name);
    }
    
    // output player faculty
    public int Selectfaculty(){
        while(true){
            System.out.println("Choose Faculty （0,CS，1,ENG，2,NURSE）：");
            Scanner sc = new Scanner(System.in);
            this.faculty = sc.nextInt();
            if(faculty >= 0 && faculty <= 2){
                break;
            }else{
                System.out.println("Enter the number to choose faculty");
            }
        }
        return faculty;
    }
   
    public void outputFaculty(){
        System.out.println("faculty"+facultys[this.faculty]);
        
    }
    // output player itemlist
    public void outputItemList(){
        System.out.println("Character" + inventorys);
    }
    
}
	


