package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Connection.*;
public class signupcontroller implements Initializable {
    @FXML
    private JFXTextField tf_firstname;

    @FXML
    private JFXTextField tf_lastname;

    @FXML
    private JFXTextField tf_contact_no;

    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXPasswordField pf_password;
    @FXML
    private Alert a;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public  void handle(ActionEvent event) throws SQLException, IOException {
        if ((tf_firstname.getText()).equals("") || (tf_lastname.getText()).equals("") || (tf_contact_no.getText()).equals("") || (tf_username.getText()).equals("") || (pf_password.getText()).equals("")) {
            a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter all the details");
            a.show();
        } else {
            ConnectionUtil connectionClass = new ConnectionUtil();
            Connection connection = connectionClass.conDB();
            String sql = "insert into userdata(firstname,lastname,contact_no,username,password)" + "values(?,?,?,?,?)";
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tf_firstname.getText());
            statement.setString(2, tf_lastname.getText());
            statement.setString(3, tf_contact_no.getText());
            statement.setString(4, tf_username.getText());
            statement.setString(5, pf_password.getText());
            statement.executeUpdate();
            showDialog("Successfully signed up!!", "Sign up");
            System.out.println("Successfully inserted");
            Parent backparent;
            backparent = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
            Scene scene = new Scene(backparent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    private void showDialog(String info, String header)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();

    }
}
