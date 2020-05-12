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
import javafx.scene.chart.PieChart;


public class reports  extends  logincontroller implements Initializable {

    @FXML
    private PieChart pie_chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void onclickofback(javafx.event.ActionEvent event) throws Exception {
        Parent loginparent;
        loginparent = FXMLLoader.load(getClass().getResource("../fxml/info.fxml"));
        Scene scene = new Scene(loginparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    @FXML
    public void balancehandle(ActionEvent event) throws IOException {

        Parent loginparent;
        loginparent = FXMLLoader.load(getClass().getResource("../fxml/balance.fxml"));
        Scene scene = new Scene(loginparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void dailyhandle(ActionEvent event) throws IOException {

        Parent loginparent;
        loginparent = FXMLLoader.load(getClass().getResource("../fxml/Piechart.fxml"));
        Scene scene = new Scene(loginparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}

