package GUI;

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
				switch(newSelectedID) {
				case 30:
					
				}
			}
		}
	}
	
}
