
/**import GUI.AttackPotion;
import GUI.HealthPotion;
import GUI.Item;
import GUI.ManaPotion;
import GUI.MovePotion;
import GUI.RandomPotion;*/
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class CharacterSelectedHandler implements EventHandler<MouseEvent>{

	Game game;
	Chara selected;
	
	public CharacterSelectedHandler(Game box, Chara s) {
		game = box; selected = s;
	}
	
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		if(x < game.mapSizeX && y < game.mapSizeY) {
			int[] selectedCoords = game.currentMap.getPos(selected.getID());
			if(x == selectedCoords[0] - 1 || x == selectedCoords[0] + 1 ||
					y == selectedCoords[1] - 1 || y == selectedCoords[1] + 1) {
				int newSelectedID = game.currentMap.getID(x, y);
				Item newItem = new EmptyItem();
				switch(newSelectedID) {
				case 30:
					newItem = new AttackPotion(); break;
				case 32:
					newItem = new HealthPotion(); break;
				case 33:
					newItem = new ManaPotion(); break;
				//case 34:
				//	newItem = new MovePotion(); break;
				case 35:
					newItem = new RandomPotion(); break;
				}
				boolean added = false;
				Item[] inventory = selected.getInventory();
				for(int i = 0; i < inventory.length; i++) {
					if(!added) {
						if(inventory[i].getID() == 31) {
							selected.setItem(newItem, i);
							added = true;
						}
					}
				}
			}
			game.setButtonTextPlayerActionChoice();
			game.btn1.setOnAction(null);
			game.btn2.setOnAction(null);
			game.btn3.setOnAction(null);
			game.btn4.setOnAction(new EndTurnHandler(game));
			game.scene.setOnMouseClicked(new PlayerActionSelection(game));
		}
	}
	
}
