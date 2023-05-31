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

public class animalRegister implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField birthField;
    @FXML
    private TextField speciesField;
    @FXML
    private TextField breedField;
    @FXML
    private TextField sexField;
    @FXML
    private TextField coatField;
    @FXML
    private TextField clientIdField;
    @FXML
    private Label label;

    private String id = "";
    private String name = "";
    private String birth = "";
    private String species = "";
    private String breed = "";
    private String sex = "";
    private String coat= "";
    private String clientId = "";

    private final String QUERY= "insert into animal_passport values(?, ?, ?, ?, ?, ?, ?, ?)";


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
    }
    //Метод добавления введенных данных в БД
    public void animalRegistration() throws SQLException {
        id = idField.getText();
        name = nameField.getText();
        birth = birthField.getText();
        species = speciesField.getText();
        breed = breedField.getText();
        sex = sexField.getText();
        coat = coatField.getText();
        clientId = clientIdField.getText();
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, birth);
        preparedStatement.setString(4, species);
        preparedStatement.setString(5, breed);
        preparedStatement.setString(6, sex);
        preparedStatement.setString(7, coat);
        preparedStatement.setString(8, clientId);
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
