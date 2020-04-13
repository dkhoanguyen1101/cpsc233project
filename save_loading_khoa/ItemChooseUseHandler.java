

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class ItemChooseUseHandler implements EventHandler<ActionEvent>{
	
	Game game;
	Chara user;
	int itemSpace;
	
	public ItemChooseUseHandler(Game box, Chara u, int i) {
		game = box; user = u; itemSpace = i;
	}
	
	public void handle(ActionEvent event) {
		boolean used = user.getItem(itemSpace).use(user);
		if(used) {
			Item newItem = new EmptyItem();
			user.setItem(newItem, itemSpace);
		}
		game.setButtonTextPlayerActionChoice();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new EndTurnHandler(game));
		game.scene.setOnMouseClicked(new PlayerActionSelection(game));
	}

}
