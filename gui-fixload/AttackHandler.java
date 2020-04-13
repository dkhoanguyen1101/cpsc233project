

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for attack handler by normal attack
public class AttackHandler implements EventHandler<ActionEvent>{
	//instance variable
	Game game;
	Chara attacker;
	//constructor for attack handler
	public AttackHandler(Game box, Chara a) {
		game = box; attacker = a;
	}
	//main method for attack event
	public void handle(ActionEvent event) {
		
		game.setButtonTextAttack();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(new GoBackHandler(game));
		game.scene.setOnMouseClicked(new AttackChooseEnemyHandler(game, attacker));
	}

}
