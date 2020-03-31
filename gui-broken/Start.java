



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.print.attribute.standard.PrinterName;
import java.awt.*;

public class Start extends Application {
    public static void main(String[] args){launch(args);}




    @Override
    public void start(Stage stage) throws Exception {
        int CharaX = 0;
        int CharaY;

        ImageView player1 = new ImageView(new Image("Kinesiology.png"));

        ImageView player2 = new ImageView(new Image("BioChem.png"));



        Pane root = new Pane();
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
//the game scene setting

        Pane gameroot = new Pane();
        Scene gamescene = new Scene(gameroot,800,600);

        //Win Scene
        Pane winroot = new Pane();
        Scene winscene = new Scene(winroot,800,600);

        //Lose Scene
        Pane loseroot = new Pane();
        Scene losescene = new Scene(loseroot,800,600);

        //Chara Scene
        Pane CharaRoot = new Pane();
        Scene CharaScene = new Scene(CharaRoot,800,600);



        //start scene
        BackgroundImage bg = new BackgroundImage(new Image("start.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));

        Image duck = new Image("duck.png");
        ImageView dk = new ImageView(duck);
        dk.setLayoutX(53);
        dk.setLayoutY(55);
        root.getChildren().add(dk);

        Button button1 = new Button("About");
        button1.setMinSize(120,60);
        button1.setLayoutX(340);
        button1.setLayoutY(230);
        button1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button1.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(button1);

        Label label = new Label();
        Popup popup = new Popup();
        label.setLayoutX(100);
        label.setLayoutY(200);
        popup.getContent().add(label);
        label.setGraphic(new ImageView(new Image("name.png")));
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e)
                    {

                        if (!popup.isShowing()){
                            popup.show(stage);
                            button1.setText("BACK");}
                        else{
                            popup.hide();
                            button1.setText("About");}
                    }
                };

        // when button is pressed
        button1.setOnAction(event);

        //The PLAY button, if pressed the character selection scene will comes out
        Button PLAY = new Button("play");
        PLAY.setMinSize(120,60);
        PLAY.setLayoutX(340);
        PLAY.setLayoutY(330);
        PLAY.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        PLAY.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(PLAY);


        /*PLAY.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
                stage.setScene(CharaScene);
                stage.show();
            	 

            }
        });*/
        
        //Character selection scene

        
        //BioChem
        Button BioChem = new Button();
        BioChem.setMinSize(80,40);
        BioChem.setLayoutX(100);
        BioChem.setLayoutY(100);
        BioChem.setGraphic(new ImageView(new Image("BioChem.png")));
        CharaRoot.getChildren().add(BioChem);

        //Zoology
        Button Zoology = new Button();
        Zoology.setMinSize(80,40);
        Zoology.setLayoutX(200);
        Zoology.setLayoutY(100);
        Zoology.setGraphic(new ImageView(new Image("Zoology.png")));
        CharaRoot.getChildren().add(Zoology);

        //Kinesiology
        Button Kinesiology = new Button();
        Kinesiology.setMinSize(80,40);
        Kinesiology.setLayoutX(300);
        Kinesiology.setLayoutY(100);
        Kinesiology.setGraphic(new ImageView(new Image("Kinesiology.png")));
        CharaRoot.getChildren().add(Kinesiology);



        //Engineering
        Button Engineering = new Button();
        Engineering.setMinSize(80,40);
        Engineering.setLayoutX(400);
        Engineering.setLayoutY(100);
        Engineering.setGraphic(new ImageView(new Image("Engineering.png")));
        CharaRoot.getChildren().add(Engineering);

        //Philosophy
        Button Philosophy = new Button();
        Philosophy.setMinSize(80,40);
        Philosophy.setLayoutX(500);
        Philosophy.setLayoutY(100);
        Philosophy.setGraphic(new ImageView(new Image("Philosophy.png")));
        CharaRoot.getChildren().add(Philosophy);

        //Nursing
        Button Nursing = new Button();
        Nursing.setMinSize(80,40);
        Nursing.setLayoutX(600);
        Nursing.setLayoutY(100);
        Nursing.setGraphic(new ImageView(new Image("Nursing.png")));
        CharaRoot.getChildren().add(Nursing);




        //Finish select
        Button Finish = new Button();
        Finish.setText("FINISH SELECT");
        Finish.setMinSize(100,200);
        Finish.setLayoutX(350);
        Finish.setLayoutY(400);
        //Finish.setGraphic(new ImageView(new Image("Nursing.png")));
        CharaRoot.getChildren().add(Finish);


        Finish.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                stage.setScene(gamescene);
                stage.show();

            }
        });

        



        //game scene
        Button button = new Button("1");
        button.setMinSize(80,40);
        button.setLayoutX(30);
        button.setLayoutY(530);
        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button.setStyle("-fx-background-color: #A9A9A9");
        gameroot.getChildren().add(button);


        Button button2 = new Button("2");
        button2.setMinSize(80,40);
        button2.setLayoutX(140);
        button2.setLayoutY(530);
        button2.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button2.setStyle("-fx-background-color: #A9A9A9");
        button2.textFillProperty();
        gameroot.getChildren().add(button2);

        Button button3 = new Button("3");
        button3.setMinSize(80,40);
        button3.setLayoutX(250);
        button3.setLayoutY(530);
        button3.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button3.setStyle("-fx-background-color: #A9A9A9");
        button3.textFillProperty();
        gameroot.getChildren().add(button3);

        Button button4 = new Button("4");
        button4.setMinSize(80,40);
        button4.setLayoutX(360);
        button4.setLayoutY(530);
        button4.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button4.setStyle("-fx-background-color: #A9A9A9");
        button4.textFillProperty();
        gameroot.getChildren().add(button4);
        gameroot.getChildren().add(player1);
        player2.setLayoutX(32);
        player2.setLayoutY(32);
        gameroot.getChildren().add(player2);




        BackgroundImage GameBg = new BackgroundImage(new Image("tan.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        gameroot.setBackground(new Background(GameBg));
        
        PLAY.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	/*
                stage.setScene(CharaScene);
                stage.show();
            	 */
            	Game game = new Game(stage);
            }
        });


        player1.setOnMouseClicked(f -> {
            gamescene.setOnMouseClicked(e -> {
                double x =  (e.getX()%32);
                double y =  (e.getY()%32);
                //System.out.println(x+","+y);
                double cx =e.getX()-x;
                double cy = e.getY()-y;
                player1.setLayoutX(cx);
                player1.setLayoutY(cy);
                //System.out.println(cx + ","+ cy);


            });

        });
        player2.setOnMouseClicked(f -> {
            gamescene.setOnMouseClicked(e -> {
                double x =  (e.getX()%32);
                double y =  (e.getY()%32);
                //System.out.println(x+","+y);
                double cx =e.getX()-x;
                double cy = e.getY()-y;
                player2.setLayoutX(cx);
                player2.setLayoutY(cy);
                //System.out.println(cx + ","+ cy);


            });

        });







        Button Replay = new Button("Replay");
        Replay.setMinSize(80,40);
        Replay.setLayoutX(360);
        Replay.setLayoutY(510);
        Replay.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        Replay.setStyle("-fx-background-color: #A9A9A9");
        Replay.textFillProperty();





        //lose scene
        BackgroundImage LoseBg = new BackgroundImage(new Image("end.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        loseroot.setBackground(new Background(LoseBg));
        loseroot.getChildren().add(Replay);
        //When Player lose activate  the flowing code
        //stage.setScene(losescene);
        //        stage.show();




        //win scene
        BackgroundImage WinBg = new BackgroundImage(new Image("WIN.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        winroot.setBackground(new Background(WinBg));
        winroot.getChildren().add(Replay);
        //When Player Lose activate the flowing code
        //stage.setScene(winscene);
        //        stage.show();*/


    }
}
