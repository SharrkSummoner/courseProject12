package com.example.courseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Profile implements Initializable{
    @FXML
    private ImageView photo;
    @FXML
    private Text textHi;
    @FXML
    private Text doljnost;
    @FXML
    private Button registerBtn;
    @FXML
    private Button animalRegBtn;
    @FXML
    private Button appointmentBtn;
    @FXML
    private Button appointmentInfoBtn;
    @FXML
    private Button procedureInfoBtn;
    @FXML
    private Button complAppointmentBtn;
    @FXML
    private Button procedureRegBtn;

    public  static int empId;

    public  static String name = "";
    public  static String post = "";
    public  static String specialization = "";

    //Метод вывода информации о сторуднике и разграничении доступа
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       File file= new File("src/img/" + name.split(" ")[0] + ".jpeg");
        Image image = new Image(file.toURI().toString());
        photo.setImage(image);
        textHi.setText("Здравствуйте, "+name+" !");
        if(!specialization.isEmpty()){
        doljnost.setText("Ваша должность: "+post+", "+specialization);
        }else {
            doljnost.setText("Ваша должность: "+post);
        }
        if(post.equals("Администратор")){
            complAppointmentBtn.setDisable(true);
            complAppointmentBtn.setVisible(false);
            procedureRegBtn.setDisable(true);
            procedureRegBtn.setVisible(false);
        }else if(post.equals("Фельдшер")){
            registerBtn.setDisable(true);
            registerBtn.setVisible(false);
            animalRegBtn.setDisable(true);
            animalRegBtn.setVisible(false);
            appointmentBtn.setDisable(true);
            appointmentBtn.setVisible(false);
            appointmentInfoBtn.setDisable(true);
            appointmentInfoBtn.setVisible(false);
            complAppointmentBtn.setDisable(true);
            complAppointmentBtn.setVisible(false);
            procedureRegBtn.setDisable(true);
            procedureRegBtn.setVisible(false);
        }else {
            registerBtn.setDisable(true);
            registerBtn.setVisible(false);
            animalRegBtn.setDisable(true);
            animalRegBtn.setVisible(false);
            appointmentBtn.setDisable(true);
            appointmentBtn.setVisible(false);
            appointmentInfoBtn.setDisable(true);
            appointmentInfoBtn.setVisible(false);
        }
    }
    //Обработка кнопки выхода
    public void exit(ActionEvent event) throws IOException {
        Stage st3 = new Stage();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        st3.getIcons().add(new Image("logo.png"));
        st3.setTitle("Ветеринарная клиника");
        st3.setScene(new Scene(root, 320, 240));
        st3.setResizable(false);
        st3.show();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    //Метод открытия окна регистрации клиентов
    public void clientRegister(ActionEvent event) throws IOException {
        Stage register = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
        register.getIcons().add(new Image("logo.png"));
        register.setTitle("Регистрация клиента");
        register.setScene(new Scene(parent, 250, 300));
        register.setResizable(false);
        register.show();
    }
    //Метод открытия окна регистрации приемов
    public void appointmentRegister(ActionEvent event) throws IOException {
        Stage appointment = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("appointmentRegister.fxml")));
        appointment.getIcons().add(new Image("logo.png"));
        appointment.setTitle("Запись на прием");
        appointment.setScene(new Scene(parent, 400, 400));
        appointment.setResizable(false);
        appointment.show();
    }
    //Метод открытия окна регистрации животных
    public void animalRegister(ActionEvent event) throws IOException {
        Stage animalRegister = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("animalRegister.fxml")));
        animalRegister.getIcons().add(new Image("logo.png"));
        animalRegister.setTitle("Регистрация паспорта животного");
        animalRegister.setScene(new Scene(parent, 400, 400));
        animalRegister.setResizable(false);
        animalRegister.show();
    }
    //Метод открытия окна вывода информации о приемах
    public void appointmentInfo(ActionEvent event) throws IOException {
        Stage animalRegister = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("appointmentInfo.fxml")));
        animalRegister.getIcons().add(new Image("logo.png"));
        animalRegister.setTitle("Информация о приемах");
        animalRegister.setScene(new Scene(parent, 1000, 400));
        animalRegister.setResizable(false);
        animalRegister.show();
    }
    //Метод открытия окна вывода информации о процедурах
    public void procedureInfo(ActionEvent event) throws IOException {
        Stage animalRegister = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("procedureInfo.fxml")));
        animalRegister.getIcons().add(new Image("logo.png"));
        animalRegister.setTitle("Информация о процедурах");
        animalRegister.setScene(new Scene(parent, 800, 400));
        animalRegister.setResizable(false);
        animalRegister.show();
    }
    //Метод открытия окна доплнения данных о приеме
    public void appointmentCompl(ActionEvent event) throws IOException {
        Stage appointment = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("appointmentCompl.fxml")));
        appointment.getIcons().add(new Image("logo.png"));
        appointment.setTitle("Ветеринарная клиника");
        appointment.setScene(new Scene(parent, 400, 400));
        appointment.setResizable(false);
        appointment.show();
    }
    //Метод открытия окна регистрации процедур
    public void procedureRegister(ActionEvent event) throws IOException {
        Stage appointment = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("procedureRegister.fxml")));
        appointment.getIcons().add(new Image("logo.png"));
        appointment.setTitle("Запись на процедуры");
        appointment.setScene(new Scene(parent, 400, 400));
        appointment.setResizable(false);
        appointment.show();
    }
}
