import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class SaveHandler implements EventHandler<ActionEvent> {

    Game game;

    public SaveHandler(Game box) {
        game = box;
    }

    public void handle(ActionEvent event) {
        try (OutputStream output = new FileOutputStream("save.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("mapImageURL", game.currentMap.getImageURL());

            ArrayList<Integer> healths = new ArrayList<>();
            ArrayList<Integer> playerPositions = new ArrayList<>();
            for (Chara player :
                    game.players) {
                int id = player.getID();

                playerPositions.add(game.currentMap.getPos(id)[0]);
                playerPositions.add(game.currentMap.getPos(id)[1]);

                healths.add(player.getHealth());
            }

            ArrayList<Integer> enemyPositions = new ArrayList<>();
            for (Chara enemy :
                    game.enemies) {
                int id = enemy.getID();

                enemyPositions.add(game.currentMap.getPos(id)[0]);
                enemyPositions.add(game.currentMap.getPos(id)[1]);

                healths.add(enemy.getHealth());
            }



            prop.setProperty("mapCharPos", Arrays.toString(playerPositions.toArray()));
            prop.setProperty("mapEnemyPos", Arrays.toString(enemyPositions.toArray()));
            prop.setProperty("mapHealths", Arrays.toString(healths.toArray()));

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}