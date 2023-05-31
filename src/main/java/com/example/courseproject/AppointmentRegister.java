package com.example.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.courseproject.DB.*;

public class AppointmentRegister implements Initializable {
    @FXML
    private ComboBox docSelect;
    @FXML
    private TextField docId;
    @FXML
    private TextField animalId;
    @FXML
    private TextField date;
    @FXML
    private TextField complaint;
    @FXML
    private Label label;
    private String buffer = "";
    String[]arr = new String[3];

    private String doc = "";
    private String animal = "";
    private String datedate = "";
    private String compl = "";

    private final String QUERY= "insert into appointment(employee_employee_id, animal_passport_passport_id, date, complaint) values(?, ?, ?, ?)";
    //Метод добавления данных в элемент ComboBox
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT concat(employee_id,\", \", specialization,\", \", name) FROM employee where specialization<> \"\"");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                docSelect.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Метод внесения данных о приеме в БД
    public void appointmentRegistration() throws SQLException {
        buffer = docSelect.getValue().toString();
        System.out.println(buffer);
        arr = buffer.split(",");
        doc = arr[0];
        animal = animalId.getText();
        datedate = date.getText();
        compl = complaint.getText();
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, doc);
        preparedStatement.setString(2, animal);
        preparedStatement.setString(3, datedate);
        preparedStatement.setString(4, compl);
        try {
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            label.setTextFill(Color.web("#ff0000"));
            label.setText("Ошибка регистрации");
            throw new RuntimeException(e);
        }
        label.setTextFill(Color.web("#00ff26"));
        label.setText("Регистрация прошла успешно");
    }
}
