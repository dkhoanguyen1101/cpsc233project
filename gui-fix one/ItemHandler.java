

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ItemHandler implements EventHandler<ActionEvent>{
	
	Game game;
	Chara user;
	
	public ItemHandler(Game box, Chara u) {
		game = box; user = u;
	}
	
	public void handle(ActionEvent event) {
		game.setButtonTextItems(user.getID());
		game.btn1.setOnAction(new ItemChooseUseHandler(game, user, 0));
		game.btn2.setOnAction(new ItemChooseUseHandler(game, user, 1));
		game.btn3.setOnAction(new ItemChooseUseHandler(game, user, 2));
		game.btn4.setOnAction(new GoBackHandler(game));
		game.scene.setOnMouseClicked(null);
	}

}
