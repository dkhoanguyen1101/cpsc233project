

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
//method for attack handler by choose different enemy
public class AttackChooseEnemyHandler implements EventHandler<MouseEvent>{
//instance variable
	Game game;
	Chara attacker;
	//constructor for handler
	public AttackChooseEnemyHandler(Game box, Chara a) {
		game = box; attacker = a;
	}
	//main method the event handler
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		if(x < game.mapSizeX && y < game.mapSizeY) {
			int selectedID = game.currentMap.getID(x, y);
			if(game.checkIfIDInList(selectedID, game.enemies)) {
				Chara receiver = game.getCharaFromID(selectedID, game.enemies);
				System.out.println(receiver.getID());
				System.out.println(attacker.getID());
				if(game.isLegalAttack(attacker, receiver)) {
					attacker.attack(receiver);
					game.notActed.remove(attacker);
					game.killChecker(game.enemies);
				}
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
