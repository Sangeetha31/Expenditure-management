package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class info implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public void onclickofaddincome(ActionEvent event) throws Exception {
        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/income.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void onclickofadddaily(ActionEvent event) throws Exception {
        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/daily.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    public void clickedbackbutton(ActionEvent event) throws IOException {

        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void onclicked(ActionEvent event) throws IOException {
        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/reports.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}