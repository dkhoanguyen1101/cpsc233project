package main;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for end turn handler
public class EndTurnHandler implements EventHandler<ActionEvent>{
//constructor
	Game game;
	//main method for action handler
	public EndTurnHandler(Game box) {
		game = box;
	}
	
	public void handle(ActionEvent event) {
		game.startNewTurn();
		game.setButtonTextPlayerActionChoice();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new EndTurnHandler(game));
		game.scene.setOnMouseClicked(new PlayerActionSelection(game));
	}
}
