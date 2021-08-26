package ch.teko;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import tray.notification.TrayNotification;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Controller {
    @FXML private ComboBox <Integer> comboxMessages;
    @FXML private TableView <Field> tblContent;
    @FXML private TableColumn<Field, String> tblCTime;
    @FXML private TableColumn<Field, String> tblCDate;
    @FXML private TableColumn<Field, String> tblCTitle;
    private ArrayList<Field> fieldsCurr = new ArrayList<Field>();
    private ArrayList<Field> fieldsNew = new ArrayList<Field>();

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
        fieldsNew = request.doCall(comboxMessages.getValue().toString());

        if (!fieldsCurr.isEmpty()){
            if (!fieldsCurr.get(0).getTimestamp().equals(fieldsNew.get(0).getTimestamp())){
                if (fieldsCurr.size() != fieldsNew.size()){
                    //Notification
                    TrayNotification tray = new TrayNotification();
                    NotificationType notification = NotificationType.SUCCESS;
                    tray.setTray("New Entry", "Messages updated successfully", notification);
                    tray.showAndWait();
                }
                fieldsCurr = fieldsNew;
                tblContent.getItems().clear();
                tblContent.getItems().addAll(fieldsNew);
            }
        } else {
            fieldsCurr = fieldsNew;
            tblContent.getItems().addAll(fieldsNew);
        }
    }

    public void onClick(){
        TablePosition pos  = tblContent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        Alert descInfo = new Alert(AlertType.INFORMATION);
        descInfo.setTitle(fieldsCurr.get(row).getTitle());
        descInfo.setHeaderText(null);
        descInfo.setContentText(fieldsCurr.get(row).getDescription());
        descInfo.showAndWait();
    }
    
    public void startService(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        Runnable loop = new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        makeRequest();
                    }
                });
            }
        };
        executor.scheduleAtFixedRate(loop, 0, 20000, TimeUnit.MILLISECONDS);
    }
}
