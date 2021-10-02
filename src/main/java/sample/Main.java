package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/main.fxml"));
        primaryStage.setTitle("DenIvan app");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/**
 @Override
 public void start(Stage primaryStage) throws Exception{
 Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/sample.fxml"));
 primaryStage.setTitle("Diffie-Hellman algorithm");
 primaryStage.setScene(new Scene(root, 700, 400));
 primaryStage.show();
 }
 */