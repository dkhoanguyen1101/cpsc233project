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

          try {
        	  ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("output.bin"));//create save file
        	  output.writeObject(game.players);//write required data to save file
        	  output.writeObject(game.enemies);
        	  output.writeObject(game.notMoved);
        	  output.writeObject(game.notActed);
        	  output.writeObject(game.currentMap);
        	  output.writeObject(game.getScore());
        	  output.writeObject(game.getGPA());
        	  output.writeObject(game.turnNo);
        	  output.writeObject(game.mapNo);
        	  output.close();//close when finished

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}