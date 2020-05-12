package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Connection.*;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class logincontroller {

    public static  int ID;

    Connection con;
    public logincontroller(){
        System.out.println("Entered Constructor!");
        con = ConnectionUtil.conDB();
    }
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField tf_Username;

    PreparedStatement preparedStatement ;
    ResultSet resultSet;

    @FXML
    public void clickingoflogin(ActionEvent event) throws  Exception
    {
        String username = tf_Username.getText();
        String password = txtPassword.getText();
        //query

        try {
            preparedStatement = con.prepareStatement("SELECT * from userdata where Username = ? and Password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Entered here!");
                showDialog("Wrong username/password!","Alert!");
            }
            else {
                 ID = resultSet.getInt("id");
                System.out.println("The id is :" + ID);
                Parent loginparent;
                loginparent = FXMLLoader.load(getClass().getResource("../fxml/info.fxml"));
                Scene scene = new Scene(loginparent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }

        } catch (Exception e) {
            Logger.getLogger(logincontroller.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    @FXML
    public void clickingofsignup(ActionEvent event) throws Exception
    {
            Parent signuparent;
            signuparent = FXMLLoader.load(getClass().getResource("../fxml/signup.fxml"));
            Scene scene = new Scene(signuparent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }
    private void showDialog(String info, String header)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();

    }
}
