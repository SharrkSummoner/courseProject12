package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AppointmentInfo implements Initializable {

    private ObservableList<AppointmentData> appointmentInfo = FXCollections.observableArrayList();

    @FXML
    private TableView<AppointmentData> tableView;
    @FXML
    private TableColumn<AppointmentData, String> tc1;
    @FXML
    private TableColumn<AppointmentData, String> tc2;
    @FXML
    private TableColumn<AppointmentData, String> tc3;
    @FXML
    private TableColumn<AppointmentData, String> tc4;
    @FXML
    private TableColumn<AppointmentData, String> tc5;
    @FXML
    private TableColumn<AppointmentData, String> tc6;
    @FXML
    private TableColumn<AppointmentData, String> tc7;
    @FXML
    private TableColumn<AppointmentData, String> tc8;
    //Метод присваивания полям таблицы значений из БД
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> name = null;
        try {
            name = DB.getAppointment();
        } catch (SQLException e){
            throw new RuntimeException();
        }
        for(int i = 0; i < name.size(); ++i){
            appointmentInfo.add(new AppointmentData(name.get(i), name.get(++i), name.get(++i),
                    name.get(++i), name.get(++i), name.get(++i), name.get(++i), name.get(++i)));
        }
        tc1.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("id"));
        tc2.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("doc"));
        tc3.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("animal"));
        tc4.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("date"));
        tc5.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("complaint"));
        tc6.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("diagnosis"));
        tc7.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("therapy"));
        tc8.setCellValueFactory(new PropertyValueFactory<AppointmentData, String>("cost"));
        tableView.setItems(appointmentInfo);
    }
}
