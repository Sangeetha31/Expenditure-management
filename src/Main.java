
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception{

        Stage.setTitle("Total paisa");
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene home = new Scene(root);
        Stage.setScene(home);
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
