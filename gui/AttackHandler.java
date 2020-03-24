package GUI;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class AttackHandler implements EventHandler<ActionEvent>{
	
	Game game;
	Chara attacker;
	
	public AttackHandler(Game box, Chara a) {
		game = box;
	}
	
	public void handle(ActionEvent event) {
		
		game.setButtonTextAttack();
		game.btn1.setOnAction(null);
		game.btn2.setOnAction(null);
		game.btn3.setOnAction(null);
		game.btn4.setOnAction(null);
		game.scene.setOnMouseClicked(new AttackChooseEnemyHandler(game));
	}

}
