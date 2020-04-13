import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveHandler implements EventHandler<ActionEvent> {

    Game game;

    public SaveHandler(Game box) {
        game = box;
    }

    public void handle(ActionEvent event) {
//        try (OutputStream output = new FileOutputStream("save.properties")) {
//
//            Properties prop = new Properties();
//
//            // set the properties value
//            prop.setProperty("mapImageURL", game.currentMap.getImageURL());
//
//            ArrayList<Integer> healths = new ArrayList<>();
//            ArrayList<Integer> playerPositions = new ArrayList<>();
//            for (Chara player :
//                    game.players) {
//                int id = player.getID();
//
//                playerPositions.add(game.currentMap.getPos(id)[0]);
//                playerPositions.add(game.currentMap.getPos(id)[1]);
//
//                healths.add(player.getHealth());
//            }
//
//            ArrayList<Integer> enemyPositions = new ArrayList<>();
//            for (Chara enemy :
//                    game.enemies) {
//                int id = enemy.getID();
//
//                enemyPositions.add(game.currentMap.getPos(id)[0]);
//                enemyPositions.add(game.currentMap.getPos(id)[1]);
//
//                healths.add(enemy.getHealth());
//            }
//
//
//
//            prop.setProperty("mapCharPos", Arrays.toString(playerPositions.toArray()));
//            prop.setProperty("mapEnemyPos", Arrays.toString(enemyPositions.toArray()));
//            prop.setProperty("mapHealths", Arrays.toString(healths.toArray()));
//
//            // save properties to project root folder
//            prop.store(output, null);

          try {
        	  ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("output.bin"));
        	  output.writeObject(game.players);
        	  output.writeObject(game.enemies);
//        	  output.writeObject(game.images);
        	  output.writeObject(game.currentMap);
        	  output.writeObject(game.getScore());
        	  output.writeObject(game.getGPA());
        	  output.writeObject(game.turnNo);
        	  output.writeObject(game.mapNo);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
