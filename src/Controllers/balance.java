package Controllers;

import Connection.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class balance extends logincontroller {


    @FXML
    public ProgressBar pb_income =new ProgressBar(0);

    @FXML
    private Label foodlabel;

    @FXML
    private Label shoppinglabel;

    @FXML
    private Label grocerieslabel;

    @FXML
    private Label fuellabel;

    @FXML
    private Label medicallabel;

    @FXML
    private Label nonelabel;


    @FXML
    private Label balance;




    public void onpressed(ActionEvent event) throws SQLException {

        ConnectionUtil connectionClass= new ConnectionUtil();
        Connection connection= connectionClass.conDB();
        assert connection != null;
        String fetchincomedata = "Select amount from income where id = ?";
        String fetchtotaldata = "Select sum(amount) as sum1 from daily where id = ?";
        String food = "Select amount from daily where category = ? and id = ?";
        String shopping = "Select amount from daily where category = ? and id = ?";
        String groceries = "Select amount from daily where category = ? and id = ?";
        String fuel = "Select amount from daily where category = ? and id = ?";
        String medical = "Select amount from daily where category = ? and id = ?";
        String none = "Select amount from daily where category = ? and id = ?";
        PreparedStatement statement1 = connection.prepareStatement(fetchincomedata);
        PreparedStatement statement2 = connection.prepareStatement(fetchtotaldata);
        PreparedStatement statement3 = connection.prepareStatement(food);
        PreparedStatement statement4 = connection.prepareStatement(shopping);
        PreparedStatement statement5 = connection.prepareStatement(groceries);
        PreparedStatement statement6 = connection.prepareStatement(fuel);
        PreparedStatement statement7 = connection.prepareStatement(medical);
        PreparedStatement statement8 = connection.prepareStatement(none);
        statement2.setInt(1,ID);
        statement1.setInt(1,ID);
        statement3.setString(1,"Food");
        statement3.setInt(2,ID);
        statement4.setString(1,"Shopping");
        statement4.setInt(2,ID);
        statement5.setString(1,"Groceries");
        statement5.setInt(2,ID);
        statement6.setString(1,"Fuel");
        statement6.setInt(2,ID);
        statement7.setString(1,"Medical");
        statement7.setInt(2,ID);
        statement8.setString(1,"None");
        statement8.setInt(2,ID);
        ResultSet rs1 = statement1.executeQuery();
        ResultSet rs2 = statement2.executeQuery();
        ResultSet rs3 = statement3.executeQuery();
        ResultSet rs4 = statement4.executeQuery();
        ResultSet rs5 = statement5.executeQuery();
        ResultSet rs6 = statement6.executeQuery();
        ResultSet rs7 = statement7.executeQuery();
        ResultSet rs8 = statement8.executeQuery();
        rs1.next();
        rs2.next();

        System.out.println("The sum is:" + rs2.getInt("sum1"));
        System.out.println(("The income is"+rs1.getInt("amount")));
        double p=(((rs2.getDouble("sum1"))/(rs1.getDouble("amount"))));
        System.out.println(p);
        pb_income.setProgress(p);

          balance.setText(String.valueOf(rs1.getInt("amount")-rs2.getInt("sum1")));

        if(rs3.next())
            foodlabel.setText(rs3.getString("amount"));

        if(rs4.next())
            shoppinglabel.setText(rs4.getString("amount"));

        if(rs5.next())
            grocerieslabel.setText(rs5.getString("amount"));

        if(rs6.next())
            fuellabel.setText(rs6.getString("amount"));

        if(rs7.next())
            medicallabel.setText(rs7.getString("amount"));

        if(rs8.next())
            nonelabel.setText(rs8.getString("amount"));
    }


    @FXML
    public void onclickofback(ActionEvent event) throws Exception {
        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/reports.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}

