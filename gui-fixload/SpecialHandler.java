package main;

import characters.Chara;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for special handler
public class SpecialHandler implements EventHandler<ActionEvent>{
	//instance variable
	Game game;
	Chara user;
	//constructor
	public SpecialHandler(Game box, Chara u) {
		game = box; user = u;
	}
        //main method for special handler
	public void handle(ActionEvent event) {
		game.setButtonTextSpecial(user.getID());
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new GoBackHandler(game));
		game.scene.setOnMouseClicked(new SpecialChooseTargetHandler(game, user));
	}
}
