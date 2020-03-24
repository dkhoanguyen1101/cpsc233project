package GUI;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class MoveSpaceChoiceHandler implements EventHandler<MouseEvent>{
	
	Game game;
	Chara mover;
	
	public MoveSpaceChoiceHandler(Game box, Chara m) {
		game = box; mover = m;
	}
	
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		Chara selected;
		if(x < game.mapSizeX && y < game.mapSizeY) {
			int selectedID = game.currentMap.getID(x, y);
			boolean moved = game.currentMap.move(selectedID, x, y, mover.getMove());
			if(moved) {
				game.notMoved.remove(mover);
			}
		}
		game.setButtonTextPlayerActionChoice();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(null);
		game.scene.setOnMouseClicked(new PlayerActionSelection(game));
	}
}
