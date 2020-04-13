

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for special choose target handler
public class SpecialChooseTargetHandler implements EventHandler<MouseEvent>{
	//instance variable
	Game game;
	Chara user;
	//constructor
	public SpecialChooseTargetHandler(Game box, Chara u) {
		game = box; user = u;
	}
	//main method for mouse event handler
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		if(x < game.mapSizeX && y < game.mapSizeY) {
			boolean used = user.Special(game.currentMap, game.players, game.enemies, x, y);
			if(used) {
				game.notActed.remove(user);
				game.killChecker(game.enemies);
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
