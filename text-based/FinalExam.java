import java.util.ArrayList;

public class FinalExam extends Chara {

	public FinalExam(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super(name, id, att, health, mana, maxAtt, maxHealth, maxMana, move, range);
	}
	@Override
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
