import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlayerActionSelection implements EventHandler<MouseEvent>{

	public Game game;
	
	public PlayerActionSelection(Game box) {
		game = box;
		game.setButtonTextPlayerActionChoice();
	}
	
	public void handle(MouseEvent event) {
		int x = (new Double(event.getSceneX()/game.cellSizeX)).intValue();
		int y = (new Double(event.getSceneY()/game.cellSizeY)).intValue();
		Chara selected;
		if(x < game.mapSizeX && y < game.mapSizeY) {
			int selectedID = game.currentMap.getID(x, y);
			game.setCharStatsLabel(selectedID);
			game.setButtonTextActions(selectedID);
			if(game.checkIfIDInList(selectedID, game.players)) {
				selected = game.getCharaFromID(selectedID, game.players);
				System.out.println(selected.getName());
				if(game.notMoved.contains(selected)) {
					game.btn1.setOnAction(new MoveHandler(game, selected));
				}
				if(game.notActed.contains(selected)) {
					game.btn2.setOnAction(new AttackHandler(game, selected));
					game.btn3.setOnAction(new ItemHandler(game, selected));
					game.btn4.setOnAction(new SpecialHandler(game, selected));
				}
				game.scene.setOnMouseClicked(new CharacterSelectedHandler(game, selected));
			}
		}
	}
	
}
