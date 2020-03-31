

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
		System.out.println(x + ", " + y);
		Chara selected;
		if(x < game.mapSizeX && y < game.mapSizeY) {
			boolean moved = game.currentMap.move(mover.getID(), y, x, mover.getMove());
			if(moved) {
				game.notMoved.remove(mover);
				ImageView image = game.getImageViewFromID(mover.getID(), game.images);
				image.setLayoutX(x * game.cellSizeX);
				image.setLayoutY(y * game.cellSizeY);
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
