package main;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//method for What happens when you press the save button
public class SaveHandler implements EventHandler<ActionEvent> {
//instance variable
    Game game;
//constructor
    public SaveHandler(Game box) {
        game = box;
    }
//main method for save and use try catch exception
    public void handle(ActionEvent event) {

          try {
              //create save file
        	  ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("output.bin"));
              //write required data to save file
        	  output.writeObject(game.players);
        	  output.writeObject(game.enemies);
        	  output.writeObject(game.notMoved);
        	  output.writeObject(game.notActed);
        	  output.writeObject(game.currentMap);
        	  output.writeObject(game.getScore());
        	  output.writeObject(game.getGPA());
        	  output.writeObject(game.turnNo);
        	  output.writeObject(game.mapNo);
              //close when finished
        	  output.close();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
