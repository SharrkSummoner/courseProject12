package com.example.courseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField passwordFieldNotHidden;
    @FXML
    CheckBox passwordCheckBox;
    @FXML
    Button submitButton;
    @FXML
    Label label;
    //Метод для скрытия/показа пароля
    public void visiblePass(ActionEvent event){
        if(passwordCheckBox.isSelected()){
            passwordFieldNotHidden.setText(passwordField.getText());
            passwordFieldNotHidden.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordField.getText());
        passwordField.setVisible(true);
        passwordFieldNotHidden.setVisible(false);
    }
    //Метод авторизации
    public void login(ActionEvent event) throws SQLException, IOException {
        Window window = submitButton.getScene().getWindow();
        System.out.println(loginField.getText());
        System.out.println(passwordField.getText());

        if (passwordField.getText().isEmpty()){
            label.setText("Ввдите пароль");
        }
        if(loginField.getText().isEmpty()){
            label.setText("Введите логин");
        }

        String login = loginField.getText();
        String password = passwordField.getText();

        DB db = new DB();
        boolean flag = db.validate(login, password);

        if(!flag){
            label.setText("Неверный логин или пароль");
        }
        else{
            db.getEmpInfo(login, password);
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            stage.getIcons().add(new Image("logo.png"));
            stage.setTitle("Ветеринарная клиника");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            stage.setResizable(false);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
}