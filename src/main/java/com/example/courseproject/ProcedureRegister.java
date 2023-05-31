package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.courseproject.DB.*;

public class ProcedureRegister implements Initializable {

    @FXML
    private TextField animalField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField procedureField;
    @FXML
    private Label label;

    private String animal = "";
    private String date = "";
    private String procedure = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    //Внесение введенных данных в БД
    public void procedureRegistration() throws SQLException {
        animal=animalField.getText();
        date=dateField.getText();
        procedure=procedureField.getText();
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into procedures(employee_employee_id," +
                " animal_passport_passport_id, date, procedure_type) values(?, ?, ?, ?)");
        preparedStatement.setInt(1, Profile.empId);
        preparedStatement.setString(2, animal);
        preparedStatement.setString(3, date);
        preparedStatement.setString(4, procedure);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            label.setTextFill(Color.web("#ff0000"));
            label.setText("Ошибка");
            throw new RuntimeException(e);
        }
        label.setTextFill(Color.web("#00ff26"));
        label.setText("Запись прошла успешно");
    }
}