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
import java.util.ResourceBundle;
public class ProcedureInfo implements Initializable {
    private ObservableList<AppointmentData> procedureInfo = FXCollections.observableArrayList();
    @FXML
    private TableView<AppointmentData> tableView;
    @FXML
    private TableColumn<ProcedureData, String> tc1;
    @FXML
    private TableColumn<ProcedureData, String> tc2;
    @FXML
    private TableColumn<ProcedureData, String> tc3;
    @FXML
    private TableColumn<ProcedureData, String> tc4;
    @FXML
    private TableColumn<ProcedureData, String> tc5;

    //Метод присваивания полям таблицы значений из БД
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> name = null;
        try {
            name = DB.getProcedure();
        } catch (SQLException e){
            throw new RuntimeException();
        }
        for(int i = 0; i < name.size(); ++i){
            procedureInfo.add(new ProcedureData(name.get(i), name.get(++i), name.get(++i), name.get(++i), name.get(++i)));
        }
        tc1.setCellValueFactory(new PropertyValueFactory<ProcedureData, String>("id"));
        tc2.setCellValueFactory(new PropertyValueFactory<ProcedureData, String>("doc"));
        tc3.setCellValueFactory(new PropertyValueFactory<ProcedureData, String>("animal"));
        tc4.setCellValueFactory(new PropertyValueFactory<ProcedureData, String>("date"));
        tc5.setCellValueFactory(new PropertyValueFactory<ProcedureData, String>("procedure"));
        tableView.setItems(procedureInfo);
    }
}
