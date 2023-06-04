/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlertManager;
import Model.Database;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza qannita
 */
public class RegisterController implements Initializable {

    @FXML
    private Label lab_wlcome;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField full_name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField age;
    @FXML
    private Label lab_gender;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton male;
    @FXML
    private Button register;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lab_role;
    @FXML
    private RadioButton patient_radio;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private RadioButton admin_radio;
    @FXML
    private Hyperlink login_link;
    @FXML
    private Label lab_info;
    @FXML
    private Button btn_out;

    @FXML
    private TextField txt_id;

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
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Statement stam;
    AlertManager alert = new AlertManager();

    @FXML
    private void btn_register(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        //validation input 
        if (txt_password.getText().isEmpty()
                || full_name.getText().isEmpty()
                || first_name.getText().isEmpty()
                || last_name.getText().isEmpty()
                || email.getText().isEmpty()
                || age.getText().isEmpty()
                || phone.getText().isEmpty()) {
            alert.errorMessage("All Fields are necessary to be filled ??");
            System.out.println("Enter Fill filrdes");

        } else if (txt_password.getText().length() < 8) {
            alert.errorMessage("Invalid Password , al least 8 characters needs ??");
            System.out.println("password >8 ??");
        } else {
            // get data from all text fields 
            int id = Integer.parseInt(txt_id.getText());
            String username = full_name.getText().trim();
            String password = txt_password.getText();
            String email = this.email.getText();
            String fname = this.first_name.getText();
            String lname = this.last_name.getText();
            String age = this.age.getText();
            String phone = this.phone.getText();
            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();
            // make an user object having this data
            User user = new User(id, username, password, fname, lname, age, email, phone, gender, role);
            // save the user in database by save method
            user.save();
            alert.successMessage("SuccessFully inserted Data ^_^" + "\n " + username);
            System.out.println("SuccessFully inserted Data ^_^");
        }

    }

    @FXML
    private void login(ActionEvent event
    ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login_admin.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void out(ActionEvent event
    ) {
        System.exit(0);
    }

}
