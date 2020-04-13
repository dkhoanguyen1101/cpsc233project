package main;


import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for go back handler
public class GoBackHandler implements EventHandler<ActionEvent>{
	//instance variable
	Game game;
	//constructor
	public GoBackHandler(Game box) {
		game = box;
	}
	//main method for back handler
	public void handle(ActionEvent event) {
		game.setButtonTextPlayerActionChoice();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new EndTurnHandler(game));
		game.scene.setOnMouseClicked(new PlayerActionSelection(game));
	}

}
