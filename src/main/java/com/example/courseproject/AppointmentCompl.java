package com.example.courseproject;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.courseproject.DB.*;
import static com.itextpdf.text.html.HtmlTags.FONT;

public class AppointmentCompl implements Initializable {
    @FXML
    private ComboBox clientSelect;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextArea therapyField;
    @FXML
    private TextField priceField;
    @FXML
    private Button submitBtn;
    @FXML
    private Button printBtn;
    @FXML
    private Label label;
    private String buffer = "";
    String[]arr = new String[3];

    private String client = "";
    private String diagnosis = "";
    private String therapy = "";
    private String price = "";
    private final String QUERY = "insert into appointment(diagnosis, therapy, cost) values(?, ?, ?, ?) where appointment_id=?";
    private final String QUERY2 = "SELECT concat(a.appointment_id,\", id:\", a.animal_passport_passport_id,\", \", a.date,\", \", a.complaint) FROM appointment a, employee e \n" +
            "where diagnosis is null and therapy is null and cost is null and a.employee_employee_id=e.employee_id and e.name=?";

    //for PDF
    private String rId;
    private String rDoc;
    private String rAnimalId;
    private String rDate;
    private String rComplaint;
    private String rDiagnosis;
    private String rTherapy;
    private String rCost;

    //Метод обращения к БД и выборки доступных к дополнению приемов
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        String docName = Profile.name;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(QUERY2);
            preparedStatement.setString(1, docName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                clientSelect.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Метод внесения данных о приеме в БД
    public void appointmentComplete() throws SQLException {
        buffer = clientSelect.getValue().toString();
        arr = buffer.split(",");
        client = arr[0];
        System.out.println(client);
        diagnosis=diagnosisField.getText();
        therapy=therapyField.getText();
        price=priceField.getText();
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("update appointment set " +
                "diagnosis =\""+diagnosis+"\", therapy =\""+therapy+"\", cost ="+price+"  where appointment_id="+client);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            label.setTextFill(Color.web("#ff0000"));
            label.setText("Ошибка");
            throw new RuntimeException(e);
        }
        label.setTextFill(Color.web("#00ff26"));
        label.setText("Успешно");
    }
    //Создание pdf отчета
    public void getReport() throws IOException, DocumentException, SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT a.appointment_id," +
                " concat(e.specialization,\", \", e.name), a.animal_passport_passport_id, a.date, a.complaint," +
                " a.diagnosis, a.therapy, a.cost\n" +
                "FROM appointment a, employee e where a.employee_employee_id=e.employee_id and a.appointment_id="+client);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            rId = resultSet.getString(1);
            rDoc = resultSet.getString(2);
            rAnimalId = resultSet.getString(3);
            rDate = resultSet.getString(4);
            rComplaint = resultSet.getString(5);
            rDiagnosis = resultSet.getString(6);
            rTherapy = resultSet.getString(7);
            rCost = resultSet.getString(8);
        }
        connection.close();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Report"+client+".pdf"));
        document.open();
        Font f1 = FontFactory.getFont("/DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);

        List list = new List();
        list.add(new ListItem("№ приема: "+rId+"\t", f1));
        list.add(new ListItem("Дата приема: "+rDate+"\t", f1));
        list.add(new ListItem("ID паспорта животного: "+rAnimalId+"\t", f1));
        list.add(new ListItem("Лечащий врач: "+rDoc+"\t", f1));
        list.add(new ListItem("Жалоба: "+rComplaint+"\t", f1));
        list.add(new ListItem("Диагноз: "+rDiagnosis+"\t", f1));
        list.add(new ListItem("Лечение: "+rTherapy+"\t", f1));
        list.add(new ListItem("Общая стоимость оказаных услуг: "+rCost+" руб.\t", f1));

        document.add(list);
        document.close();
        File file = new File("Report"+client+".pdf");
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
    }
}
