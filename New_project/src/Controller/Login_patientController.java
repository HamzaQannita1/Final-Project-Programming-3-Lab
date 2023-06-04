/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertManager;
import Model.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza qannita
 */
public class Login_patientController implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private Hyperlink create_p;

    @FXML
    private Hyperlink forgot;

    @FXML
    private ImageView image_logo;

    @FXML
    private ImageView image_p;

    @FXML
    private Label lab_patient;

    @FXML
    private TextField txt_name_patient;

    @FXML
    private PasswordField txt_pass;

    @FXML
    void create_p_page(ActionEvent event) {

    }
    Connection conn;
    Database db;
    PreparedStatement pst;
    AlertManager alert = new AlertManager();
    ResultSet rs;

    @FXML
    void login(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String username = txt_name_patient.getText();
        String password = txt_pass.getText();
        if (username.equals("") && password.equals("")) {
            alert.errorMessage("  OOps !! \n username && password Blank ");
        } else {
            conn = Database.ConnectioDB();
            pst = conn.prepareStatement("SELECT * FROM users WHERE username=? and password=? and role ='patient'");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                alert.successMessage("Success Login Patient " + "\n Welcome ^_^ \n User Name : " + username);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dashbord_patients.fxml"));
                Parent root = loader.load();
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Dashboard Patient");
                primaryStage.show();
            } else {
                alert.errorMessage("Login Failed");
                txt_name_patient.setText("");
                txt_pass.setText("");
                txt_name_patient.requestFocus();
            }

        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
