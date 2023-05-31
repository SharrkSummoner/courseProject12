package com.example.courseproject;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/course_project?useSSL=false";
    public static final String DATABASE_USERNAME = "roott";
    public static final String DATABASE_PASSWORD = "1234";
    public static final String SELECT_QUERY = "SELECT * FROM `employee` WHERE login = ? and password = ?";

    //Метод сравнения введеного пароля с фактическим
    public boolean validate(String login, String password) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                connection.close();
                return true;

            }
            else { connection.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }


    }
    //Метод для получения информации о сотрудниках
    public  String getEmpInfo(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if((resultSet.next()) && (!resultSet.wasNull())){
            Profile.empId = resultSet.getInt(1);
            Profile.name = resultSet.getString(4);
            Profile.post = resultSet.getString(2);
            Profile.specialization = resultSet.getString(3);
        }
        connection.close();
        return "Failed";
    }
    //Метод для получения информации о приемах
    public static ArrayList<String> getAppointment() throws SQLException{
        String query = "SELECT a.appointment_id, (select concat( e.specialization,\", \", e.name)), a.animal_passport_passport_id, a.date, a.complaint, a.diagnosis, a.therapy, a.cost FROM appointment a, employee e\n" +
                " where e.specialization<>\"\" and e.employee_id = a.employee_employee_id";
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        ArrayList<String> appointment = new ArrayList<>();
        while (resultSet.next()){
            appointment.add(resultSet.getString(1));
            appointment.add(resultSet.getString(2));
            appointment.add(resultSet.getString(3));
            appointment.add(resultSet.getString(4));
            appointment.add(resultSet.getString(5));
            appointment.add(resultSet.getString(6));
            appointment.add(resultSet.getString(7));
            appointment.add(resultSet.getString(8));
        }
        return appointment;
    }
    //Метод для получения информации о процедурах
    public static ArrayList<String> getProcedure() throws SQLException{
        String query = "SELECT p.procedure_id, (select concat( e.specialization,\", \", e.name)), p.animal_passport_passport_id, p.date, p.procedure_type FROM procedures p, employee e\n" +
                " where e.specialization<>\"\" and e.employee_id = p.employee_employee_id";
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        ArrayList<String> procedure = new ArrayList<>();
        while (resultSet.next()){
            procedure.add(resultSet.getString(1));
            procedure.add(resultSet.getString(2));
            procedure.add(resultSet.getString(3));
            procedure.add(resultSet.getString(4));
            procedure.add(resultSet.getString(5));
        }
        return procedure;
    }
}
