package GUI;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class SpecialChooseTargetHandler implements EventHandler<MouseEvent>{
	
	Game game;
	Chara user;
	
	public SpecialChooseTargetHandler(Game box, Chara u) {
		game = box; user = u;
	}
	
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		Chara selected;
		if(x < game.mapSizeX && y < game.mapSizeY) {
			user.Special(game.currentMap, game.players, game.enemies);
		}
	}

}
