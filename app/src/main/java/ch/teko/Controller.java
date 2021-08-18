package ch.teko;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML private ComboBox <Integer> comboxMessages;
    @FXML private TableView <Field> tblContent;
    @FXML private TableColumn<Field, String> tblCTime;
    @FXML private TableColumn<Field, String> tblCDate;
    @FXML private TableColumn<Field, String> tblCTitle;
    private ArrayList<Field> fields = new ArrayList<Field>();

    @FXML
    public void initialize () {
        tblCTime.setCellValueFactory(new PropertyValueFactory<Field, String>("time"));
        tblCDate.setCellValueFactory(new PropertyValueFactory<Field, String>("date"));
        tblCTitle.setCellValueFactory(new PropertyValueFactory<Field, String>("title"));
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
