/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertManager;
import Model.Appointment;
import Model.Database;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza qannita
 */
public class AppointmentsController implements Initializable {

    @FXML
    private RadioButton booked_radio;
    @FXML
    private RadioButton free_radio;
    @FXML
    private TextField txt_status;
    @FXML
    private TableColumn<Appointment, Date> AppDate_col;

    @FXML
    private TableColumn<Appointment, String> AppDays_col;

    @FXML
    private TableColumn<Appointment, String> AppTime_col;

    @FXML
    private TableView<Appointment> Tabel_view;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_add_patients;

    @FXML
    private Button btn_app;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_del;

    @FXML
    private Button btn_out;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_reset1;

    @FXML
    private Button btn_show;

    @FXML
    private Button btn_update;

    @FXML
    private ToggleGroup days_groups;

    @FXML
    private TableColumn<Appointment, Integer> id_col;

    @FXML
    private Label lab_app_days;

    @FXML
    private Label lab_info1;

    @FXML
    private Label lab_info11;

    @FXML
    private Label lab_status;

    @FXML
    private TableColumn<Appointment, String> status_col;

    @FXML
    private ToggleGroup status_groups;

    @FXML
    private DatePicker txt_date;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_time;
    AlertManager alert = new AlertManager();
    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;
    LocalDate date = LocalDate.MIN;

//    @FXML
//    void add_app(ActionEvent event) throws SQLException, ClassNotFoundException {
//        // Validation input
//        if (txt_id.getText().isEmpty() || txt_time.getText().isEmpty()) {
//            alert.errorMessage("All Fields are necessary to be filled.");
//            System.out.println("Please fill all fields.");
//        } else {
//            // Get data from all text fields
//            int id = Integer.parseInt(txt_id.getText());
//            LocalDate date = LocalDate.parse((CharSequence) txt_date);
//            String time = txt_time.getText();
//            String days = ((RadioButton) days_groups.getSelectedToggle()).getText();
//            String status = ((RadioButton) status_groups.getSelectedToggle()).getText();
//
//            // Create an appointment object with the retrieved data
//            Appointment app = new Appointment(id, date, days, time, status);
//            // Save the appointment in the database using the save method
//            app.save();
//
//            alert.successMessage("Successfully inserted data.");
//            System.out.println("Successfully inserted data.");
//        }
//    }
    @FXML
    void add_app(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Validation input
        if (txt_id.getText().isEmpty() || txt_time.getText().isEmpty()) {
            alert.errorMessage("All Fields are necessary to be filled.");
            System.out.println("Please fill all fields.");
        } else {
            // Get data from all text fields
            int id = Integer.parseInt(txt_id.getText());
            LocalDate date = null;
            String time = txt_time.getText();
            String days = ((RadioButton) days_groups.getSelectedToggle()).getText();
            String status = ((RadioButton) status_groups.getSelectedToggle()).getText();

            try {
                // Parse the date from the txt_date DatePicker
                date = txt_date.getValue();
            } catch (Exception e) {
                alert.errorMessage("Invalid date format. Please select a valid date.");
                System.out.println("Invalid date format. Please select a valid date.");
                return; // Exit the method if the date parsing fails
            }

            // Create an appointment object with the retrieved data
            Appointment app = new Appointment(id, date, days, time, status);
            // Save the appointment in the database using the save method
            app.save();

            alert.successMessage("Successfully inserted data.");
            System.out.println("Successfully inserted data.");
        }
    }

    @FXML
    void add_appointment(ActionEvent event) throws IOException {

        System.out.println("You are already on a page  Appointment");
        alert.confirmatinMessage("You are already on a page ??" + "\n" + "Appointment");

    }

    @FXML
    void add_patients(ActionEvent event) throws IOException {
        System.out.println("Add a new patients ?? login Register Page ??");
        alert.confirmatinMessage("Add a new patients ??");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/register.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.close();
        primaryStage.show();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        conn = Database.ConnectioDB();
        String sql = "delete from ppointment where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(txt_id.getText()));
            pst.execute();
            alert.confirmatinMessage("Can you Delete Data Users");
        } catch (SQLException e) {
            alert.errorMessage("Error Delete");
        }
    }

    @FXML
    void login_dashborad(ActionEvent event) throws IOException {
        System.out.println("Login page Dashboard Admin ??");
        alert.confirmatinMessage("Login page Dashboard Admin ??");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dashboard_admin.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.close();
        primaryStage.show();
    }

    @FXML
    void login_profile(ActionEvent event) {

    }

    @FXML
    void out(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void reset(ActionEvent event) {
        txt_id.setText("");
        txt_time.setText("");
        ((RadioButton) days_groups.getSelectedToggle()).setText("");
        ((RadioButton) status_groups.getSelectedToggle()).setText("");
        Tabel_view.getItems().clear();

    }

    @FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException {
        conn = Database.ConnectioDB();
        String sql = "UPDATE appointments SET appointment_date=?, appointment_day=?, appointment_time=?, status=? WHERE id=?";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, ((TextField) txt_date.getEditor()).getText());
            pst.setString(2, ((RadioButton) days_groups.getSelectedToggle()).getText());
            pst.setString(3, txt_time.getText());
            pst.setString(4, this.txt_status.getText());
            pst.setString(5, String.valueOf(txt_id.getText()));
            pst.execute();
            alert.successMessage("Successfully Update Data");
        } catch (SQLException e) {
            alert.errorMessage("Error Updating Data");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_col.setCellValueFactory(new PropertyValueFactory("id"));
        AppDate_col.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        AppDays_col.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        AppTime_col.setCellValueFactory(new PropertyValueFactory("AppointmentTime"));
        status_col.setCellValueFactory(new PropertyValueFactory("status"));
        ObservableList<Appointment> appList = null;
        Tabel_view.getSelectionModel().selectedIndexProperty().addListener(event -> showSelectesApp());
        try {
            appList = FXCollections.observableArrayList(Appointment.getAllAppointment());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dashboard_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tabel_view.setItems(appList);
    }

    @FXML
    void Show(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Appointment> applist;
        applist = FXCollections.observableArrayList(Appointment.getAllAppointment());
        System.out.println("Show All user of database");
        Tabel_view.setItems(applist);

    }
    //add data in textfileds 

    private void showSelectesApp() {
        Appointment app = Tabel_view.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(app.getId()));
        txt_time.setText(app.getAppointmentTime());
        txt_status.setText(app.getStatus());
        txt_date.setValue(app.getAppointmentDate());
        if (app.getStatus().equals("free")) {
            status_groups.selectToggle(free_radio);
        } else {
            status_groups.selectToggle(booked_radio);
        }
          if (app.getAppointmentDay().equals("t")) {
            days_groups.selectToggle(free_radio);
        }
         

    }

}
