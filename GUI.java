package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;



public class GUI extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new AnchorPane();
        Scene scene = new Scene(root,800,600);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int xPos = (int) (event.getX()/37.5);
                int yPos = (int) (event.getY()/37.5);

                //currentMap.move(charToMove.getID(), newY, newX, charToMove.getMove());

                System.out.println(event.getX()+","+ event.getY());
                System.out.println("x:  "+ xPos + "Y: " + yPos);
            }
        });

        Image img = new Image("map.jpg");
        ImageView imv = new ImageView(img);
        imv.setLayoutX(0);
        imv.setLayoutY(0);
        imv.fitHeightProperty();
        imv.fitWidthProperty();
        root.getChildren().add(imv);


        Rectangle rectangle = new Rectangle(600, 0, 200, 600);
        rectangle.setStroke(Color.PURPLE);
        root.getChildren().add(rectangle);

        Image btnimg = new Image("btn.png");


        Button button1 = new Button("1");
        button1.setMinSize(120,60);
        button1.setLayoutX(630);
        button1.setLayoutY(30);
        button1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button1.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(button1);




        Button button2 = new Button("2");
        button2.setMinSize(120,60);
        button2.setLayoutX(630);
        button2.setLayoutY(240);
        button2.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button2.setStyle("-fx-background-color: #A9A9A9");
        button2.textFillProperty();
        root.getChildren().add(button2);

        Button button3 = new Button("3");
        button3.setMinSize(120,60);
        button3.setLayoutX(630);
        button3.setLayoutY(360);
        button3.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button3.setStyle("-fx-background-color: #A9A9A9");
        button3.textFillProperty();
        root.getChildren().add(button3);

        Button button4 = new Button("4");
        button4.setMinSize(120,60);
        button4.setLayoutX(630);
        button4.setLayoutY(480);
        button4.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button4.setStyle("-fx-background-color: #A9A9A9");
        button4.textFillProperty();
        root.getChildren().add(button4);

        button1.setOnAction(value ->  {
            button1.setText("whatever");
        });

        Button btnChara = new Button();
        btnChara.setLayoutX(Map.getCharPos()[0]*37.5);
        btnChara.setLayoutY(Map.getCharPos()[1]*37.5);
        btnChara.setStyle("-fx-background-color: #00FF00");
        root.getChildren().add(btnChara);


        Button btnEnemy = new Button();
        btnEnemy.setLayoutX(Map.getEnemyPos()[0]);
        btnEnemy.setLayoutY(Map.getEnemyPos()[1]);
        btnEnemy.setStyle("-fx-background-color: #FF8347");
        root.getChildren().add(btnEnemy);




        stage.setScene(scene);
        stage.show();

    }



}
