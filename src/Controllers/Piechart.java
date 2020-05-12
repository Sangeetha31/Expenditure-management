package Controllers;

import Connection.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Piechart extends logincontroller {
    @FXML
    private PieChart Piechart;


    @FXML
    public void showpiechart(javafx.event.ActionEvent event) throws SQLException {
        ConnectionUtil connectionClass = new ConnectionUtil();
        Connection connection = connectionClass.conDB();
        assert connection != null;
        String sql = "Select * from daily where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,ID);
        ResultSet rs = statement.executeQuery();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

        while (rs.next()) {
            list.add(new PieChart.Data(rs.getString("category"), (rs.getInt("amount"))));
        }
        Piechart.setData(list);
        Piechart.setTitle("Daily expenditure");
        Piechart.setLabelsVisible(true);

    }

    @FXML
    public void backbutton(ActionEvent event) throws IOException {

        Parent backparent;
        backparent = FXMLLoader.load(getClass().getResource("../fxml/reports.fxml"));
        Scene scene = new Scene(backparent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
