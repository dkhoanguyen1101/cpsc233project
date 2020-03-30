

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class MoveHandler implements EventHandler<ActionEvent>{

	Game game;
	Chara mover;
	
	public MoveHandler(Game box, Chara m) {
		game = box; mover = m;
	}
	
	public void handle(ActionEvent event) {
		game.setButtonTextMove();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new GoBackHandler(game));
		game.scene.setOnMouseClicked(new MoveSpaceChoiceHandler(game, mover));
	}
}
