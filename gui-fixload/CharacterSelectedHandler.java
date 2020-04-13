
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
//method for character selection handler
public class CharacterSelectedHandler implements EventHandler<MouseEvent>{
//instance variable
	Game game;
	Chara selected;
	//constructor
	public CharacterSelectedHandler(Game box, Chara s) {
		game = box; selected = s;
	}
	//main method for mouse click handle
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		if(x < game.mapSizeX && y < game.mapSizeY) {
			int[] selectedCoords = game.currentMap.getPos(selected.getID());
			if(x == selectedCoords[0] - 1 || x == selectedCoords[0] + 1 ||
					y == selectedCoords[1] - 1 || y == selectedCoords[1] + 1) {
				int newSelectedID = game.currentMap.getID(x, y);
				Item newItem = new EmptyItem();
				boolean itemFound = false;
				switch(newSelectedID) {
				case 30:
					newItem = new AttackPotion(); itemFound = true; break;
				case 32:
					newItem = new HealthPotion(); itemFound = true; break;
				case 33:
					newItem = new ManaPotion(); itemFound = true; break;
				case 34:
					newItem = new MovementPotion(); itemFound = true; break;
				case 35:
					newItem = new RandomPotion(); itemFound = true; break;
				}
				if(itemFound) {
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
					if(added) {
						game.currentMap.setPos(0, x, y);
						ImageView image = game.getImageViewFromID(newSelectedID, game.images);
						game.root.getChildren().remove(image);
						System.out.println(game.currentMap.toString());
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
