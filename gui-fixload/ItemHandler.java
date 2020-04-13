

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for item handler
public class ItemHandler implements EventHandler<ActionEvent>{
	//instance variable
	Game game;
	Chara user;
	//constructor
	public ItemHandler(Game box, Chara u) {
		game = box; user = u;
	}
	//main method for item handler
	public void handle(ActionEvent event) {
		game.setButtonTextItems(user.getID());
		game.btn1.setOnAction(new ItemChooseUseHandler(game, user, 0));
		game.btn2.setOnAction(new ItemChooseUseHandler(game, user, 1));
		game.btn3.setOnAction(new ItemChooseUseHandler(game, user, 2));
		game.btn4.setOnAction(new GoBackHandler(game));
		game.scene.setOnMouseClicked(null);
	}

}
