package Controllers;

import Connection.ConnectionUtil;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
public class daily extends logincontroller implements Initializable {

    ObservableList list=FXCollections.observableArrayList();
    @FXML
    ChoiceBox<String> category;

    @FXML
    private JFXTextField tf_amount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loaddata();
    }
    public void loaddata()
    {
        String a="Food";
        String b="Shopping";
        String c="Groceries";
        String d="Fuel";
        String e="Medical";
        String f="Others";
        list.addAll(a,b,c,d,e,f);
        category.getItems().addAll(list);
    }
    @FXML
    public void onclickofback(ActionEvent event) throws  Exception
    {

        Parent loginparent;
        loginparent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/info.fxml"));
        Scene scene=new Scene(loginparent);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handle(ActionEvent event) throws SQLException {

        ResultSet resultSet1 , resultSet2,resultSet3,resultSet4;
        System.out.println("The id is:" + ID);
        ConnectionUtil connectionClass= new ConnectionUtil();
        Connection connection= connectionClass.conDB();
        assert connection != null;


        String checkempty = "Select * from daily where id=?";
        PreparedStatement statement3 = connection.prepareStatement(checkempty);
        statement3.setInt(1,ID);
        resultSet1 = statement3.executeQuery();

        if(resultSet1.next()) {
            String sum = "Select sum(amount) as sum1 from daily where id = ?";
            String fetchcategory = "Select category from daily where id = ?";
            String income = "Select * from income where id = ?";
            PreparedStatement statement1 = connection.prepareStatement(fetchcategory);
            PreparedStatement statement4 = connection.prepareStatement(sum);
            PreparedStatement statement5 = connection.prepareStatement(income);
            statement1.setInt(1, ID);
            statement4.setInt(1,ID);
            statement5.setInt(1,ID);
            resultSet3 = statement4.executeQuery();
            resultSet2 = statement1.executeQuery();
            resultSet4 = statement5.executeQuery();
            resultSet3.next();
            resultSet4.next();

            if(resultSet3.getInt("sum1") >= resultSet4.getInt("amount")) {
                showDialog("You have exceeded your set income!","Alert");
                return;
            }

            while (resultSet2.next()) {
                if (category.getValue().equals(resultSet2.getString("category"))) {

                    String add = "update daily set amount= amount + ? where category = ?";
                    PreparedStatement statement2 = connection.prepareStatement(add);
                    statement2.setInt(1, Integer.parseInt(tf_amount.getText()));
                    statement2.setString(2, (String) category.getValue());
                    statement2.executeUpdate();
                    showDialog("Successfully updated the same category!", category.getValue());
                    return;
                }
            }

        }

        String sql = "insert into daily(amount,category,date,id)" + "values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(tf_amount.getText()));
        statement.setString(2, (String) category.getValue());
        statement.setString(3, LocalDate.now().toString());
        statement.setInt(4, ID);
        statement.executeUpdate();
        showDialog("Successfully added!!", category.getValue());
        System.out.println("Successfully inserted");

    }

    private void showDialog(String info, String header)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();

    }
}
