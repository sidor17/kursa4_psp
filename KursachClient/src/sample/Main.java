package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.clientActions.Client;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("graphics/sample.fxml"));
        primaryStage.setScene(new Scene(root, 700, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
        launch(args);
    }
}
