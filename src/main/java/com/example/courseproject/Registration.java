package com.example.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.courseproject.DB.*;

public class Registration implements Initializable {

    @FXML
    private TextField clientId;
    @FXML
    private TextField clientFio;
    @FXML
    private TextField clientPhone;
    @FXML
    private TextField clientBirth;
    @FXML
    private Button registerBtn;
    @FXML
    private Label regLabel;

    private String id = "";
    private String fio = "";
    private String phone = "";
    private String birth = "";
    private final String QUERY= "insert into clients values(?, ?, ?, ?)";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    //Метод добавления введенных данных в БД
    public void clientRegistration() throws SQLException {
        id=clientId.getText();
        fio=clientFio.getText();
        phone=clientPhone.getText();
        birth=clientBirth.getText();
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, fio);
        preparedStatement.setString(3, phone);
        preparedStatement.setString(4, birth);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            regLabel.setTextFill(Color.web("#ff0000"));
            regLabel.setText("Ошибка регистрации");
            throw new RuntimeException(e);
        }
        regLabel.setTextFill(Color.web("#00ff26"));
        regLabel.setText("Клиент зарегистрирован");
    }
}
