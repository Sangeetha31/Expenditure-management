package Controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import Connection.ConnectionUtil;

import java.sql.*;

import com.jfoenix.controls.JFXTextField;

import static java.sql.Date.valueOf;


public class income extends logincontroller {


    @FXML
    private JFXTextField tf_amount;

    @FXML
    private JFXDatePicker tf_date;


    @FXML
    private Alert a;

    @FXML
    public void clicking(ActionEvent event) throws Exception {
        Parent loginparent;
        loginparent = FXMLLoader.load(getClass().getResource("../fxml/info.fxml"));
        Scene scene = new Scene(loginparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handle() throws SQLException {
        ConnectionUtil connectionClass = new ConnectionUtil();
        Connection connection = connectionClass.conDB();
        String sql1 = "SELECT * FROM income where id=?";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        java.sql.Date d = java.sql.Date.valueOf(tf_date.getValue());
        if (rs.next() == false) {
            String sql = "INSERT INTO income(amount,date,id) " + "VALUES(?,?,?)";
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tf_amount.getText()));
            statement.setDate(2, d);
            statement.setInt(3, ID);
            statement.executeUpdate();
            System.out.println("Successfully inserted");
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Your data has been successfully saved");
            a.show();
        } else {
            String edit = "update income set amount= ?,date =? where id = ?";
            PreparedStatement statement2 = connection.prepareStatement(edit);
            statement2.setInt(1, Integer.parseInt(tf_amount.getText()));
            statement2.setDate(2, d);
            statement2.setInt(3, ID);        
            statement2.executeUpdate();
            System.out.println("Successfully inserted");
            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Your changes in data has been successfully saved");
            a.show();
        }
    }
}



