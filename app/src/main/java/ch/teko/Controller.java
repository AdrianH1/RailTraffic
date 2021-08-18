package ch.teko;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML private ComboBox <Integer> comboxMessages;
    @FXML private TableView tblContent;
    @FXML private TableColumn tblCTime;
    @FXML private TableColumn tblCDate;
    @FXML private TableColumn tblCTitle;
    private ArrayList<Field> fields = new ArrayList<Field>();

    @FXML
    public void initialize () {
        tblCTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblCDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    }


    public void onSetMessages(ActionEvent event) {
        makeRequest();
    }


    public void makeRequest() {
         Request request = new Request();
         fields = request.doCall(comboxMessages.getValue().toString());
         tblContent.getItems().addAll(fields);
    }
}
