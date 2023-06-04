/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertManager;
import Model.User;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza qannita
 */
public class Dashbord_patientsController implements Initializable {

    @FXML
    private TableView<Appointment> Tabel_view_BApp;

    @FXML
    private TableView<User> Tabel_view_doctor;

    @FXML
    private TableView<User> Tabel_view_patients;

    @FXML
    private TableColumn<User, String> age_p_col1;

    @FXML
    private Button btn_out;

    @FXML
    private TableColumn<Appointment, String> comment_col;

    @FXML
    private TableColumn<Appointment, Date> date_p_bapp_col;

    @FXML
    private TableColumn<Appointment, String> day_p_bapp_col;

    @FXML
    private TableColumn<User, String> email_d_col;

    @FXML
    private TableColumn<User, String> email_p_col;

    @FXML
    private TableColumn<User, String> gender_p_col11;

    @FXML
    private TableColumn<Appointment, Integer> id_bapp_col;

    @FXML
    private TableColumn<User, String> id_d_col;

    @FXML
    private TableColumn<User, String> id_p_col;

    @FXML
    private Label lab_info;

    @FXML
    private Label lab_info4;

    @FXML
    private Label lab_info41;

    @FXML
    private Label lab_info411;

    @FXML
    private Label lab_info4111;

    @FXML
    private TableColumn<User, String> phone_d_col;

    @FXML
    private TableColumn<User, String> phone_p_col;

    @FXML
    private TableColumn<Appointment, String> status_p_bapp_col;

    @FXML
    private TableColumn<Appointment, String> time_p_bapp_col;

    @FXML
    private AnchorPane txt_username_patient;

    @FXML
    private TableColumn<User, String> user_d_col;

    @FXML
    private Button btn_app;
    @FXML
    private TableColumn<User, String> user_p_col;

    @FXML
    void out(ActionEvent event) {
        System.exit(0);
    }
    AlertManager alert = new AlertManager();

    @FXML
    void add_Appointments(ActionEvent event) throws IOException {
        System.out.println("Add a new appointment ?? ");
        alert.confirmatinMessage("Add a new appointment ??");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/appointments.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.close();
        primaryStage.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url````
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                id_d_col.setCellValueFactory(new PropertyValueFactory("id"));
                user_d_col.setCellValueFactory(new PropertyValueFactory("username"));
                email_d_col.setCellValueFactory(new PropertyValueFactory("email"));
                phone_d_col.setCellValueFactory(new PropertyValueFactory("phone"));
                id_p_col.setCellValueFactory(new PropertyValueFactory("id"));
                user_p_col.setCellValueFactory(new PropertyValueFactory("username"));
                email_p_col.setCellValueFactory(new PropertyValueFactory("email"));
                phone_p_col.setCellValueFactory(new PropertyValueFactory("phone"));
                gender_p_col11.setCellValueFactory(new PropertyValueFactory("gender"));
                age_p_col1.setCellValueFactory(new PropertyValueFactory("age"));
                ObservableList<User> usersList;
                usersList = FXCollections.observableArrayList(User.getAllUsers());
                usersList = FXCollections.observableArrayList(User.getAllPatients());
                System.out.println("Show All user of database");
                Tabel_view_doctor.setItems(usersList);
                Tabel_view_patients.setItems(usersList);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Dashbord_patientsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            id_bapp_col.setCellValueFactory(new PropertyValueFactory("id"));
            date_p_bapp_col.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
            day_p_bapp_col.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
            comment_col.setCellValueFactory(new PropertyValueFactory("doctor_comment") );
            time_p_bapp_col.setCellValueFactory(new PropertyValueFactory("AppointmentTime"));
            status_p_bapp_col.setCellValueFactory(new PropertyValueFactory("status"));
            ObservableList<Appointment> appList = null;
            appList = FXCollections.observableArrayList(Appointment.getAllAppointment());
            System.out.println("Show All Appointment of database");
            Tabel_view_BApp.setItems(appList);
        } catch (SQLException ex) {
            Logger.getLogger(Dashbord_patientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashbord_patientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
