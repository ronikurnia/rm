package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
