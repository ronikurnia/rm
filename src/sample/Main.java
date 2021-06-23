package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    //    primaryStage.setTitle("RM Information Syst.");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene layar = new Scene(root, 1200, 600);
        layar.getStylesheets().add("sample/style.css");
        primaryStage.setScene(layar);
 //       primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
//        makeCanDrag(root);



    }



    public static void main(String[] args) {
        launch(args);
    }


/*
Double x, y;
    private void makeCanDrag(Node n)	//make sure this method is called at the start.
    {

        n.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        n.setOnMouseDragReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setX(event.getSceneX() - x);
                stage.setY(event.getSceneY() - y);
//                stage.setOpacity(.8f);
            }
        });

    }

 */
}



