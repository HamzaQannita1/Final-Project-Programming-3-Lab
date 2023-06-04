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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza qannita
 */
public class Dashboard_adminController implements Initializable {

    @FXML
    private TextField txt_id;
    @FXML
    private TableView<User> Tabel_view;

    @FXML
    private Button btn_add_patients;

    @FXML
    private Button btn_app;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_del;

    @FXML
    private Button btn_out;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<User, String> col_age;

    @FXML
    private TableColumn<User, String> col_email;

    @FXML
    private TableColumn<User, String> col_fname;

    @FXML
    private TableColumn<User, String> col_gender;

    @FXML
    private TableColumn<User, String> col_lname;

    @FXML
    private TableColumn<User, String> col_phone;

    @FXML
    private TableColumn<User, String> col_role;

    @FXML
    private TableColumn<User, String> col_user;

    @FXML
    private TableColumn<User, Integer> id_col;

    @FXML
    private Label lab_info1;

    @FXML
    private Label lab_info11;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_search_table;
    @FXML
    private TextField txt_fname;

    @FXML
    private TextField txt_gender;

    @FXML
    private TextField txt_lname;

    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_role;
    @FXML
    private Label name_lab;
    @FXML
    private TextField txt_username;
    ObservableList<User> userlist;
    int index = -1;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;
    AlertManager alert = new AlertManager();
    Database db = new Database();
    @FXML
    private Button btn_reset1;
    @FXML
    private Button btn_show;

    @FXML
    void add_appointment(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Add a new appointment ?? ");
        alert.confirmatinMessage("Add a new appointment ??");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dashbord_patients.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.close();
        primaryStage.show();

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
    void clear(ActionEvent event) {
        txt_id.setText("");
        txt_age.setText("");
        txt_email.setText("");
        txt_phone.setText("");
        txt_username.setText("");
        txt_gender.setText("");
        txt_role.setText("");
        txt_fname.setText("");
        txt_lname.setText("");
        Tabel_view.getItems().clear();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        conn = Database.ConnectioDB();
        String sql = "delete from users where id = ?";
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
        System.out.println("Login page home ??");
        alert.confirmatinMessage("Login page home ??");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/home.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.close();
        primaryStage.show();

    }
    User user = new User();

    @FXML
    void Show(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<User> usersList;
        usersList = FXCollections.observableArrayList(User.getAllPatients());
        System.out.println("Show All Data role='paluents'");
        Tabel_view.setItems(usersList);
    }

    @FXML
    void login_profile(ActionEvent event) {

    }

    @FXML
    void out(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException {
        conn = Database.ConnectioDB();
        String sql = "UPDATE  users SET username=?,firstname=?,lastname=?,age=?,email=?,phone=?,gender=?,role=? WHERE id=?";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, txt_username.getText());
            pst.setString(2, this.txt_fname.getText());
            pst.setString(3, this.txt_lname.getText());
            pst.setString(4, this.txt_age.getText());
            pst.setString(5, this.txt_email.getText());
            pst.setString(6, this.txt_phone.getText());
            pst.setString(7, this.txt_gender.getText());
            pst.setString(8, this.txt_role.getText());
            pst.setString(9, String.valueOf(txt_id.getText()));
            pst.execute();
            alert.successMessage("SuccessFully Update Data");
        } catch (SQLException e) {
            alert.errorMessage("Error Delete");
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
        id_col.setCellValueFactory(new PropertyValueFactory("id"));
        col_user.setCellValueFactory(new PropertyValueFactory("username"));
        col_fname.setCellValueFactory(new PropertyValueFactory("firstname"));
        col_lname.setCellValueFactory(new PropertyValueFactory("lastname"));
        col_email.setCellValueFactory(new PropertyValueFactory("email"));
        col_gender.setCellValueFactory(new PropertyValueFactory("gender"));
        col_role.setCellValueFactory(new PropertyValueFactory("role"));
        col_age.setCellValueFactory(new PropertyValueFactory("age"));
        col_phone.setCellValueFactory(new PropertyValueFactory("phone"));
        ObservableList<User> usersList = null;
        Tabel_view.getSelectionModel().selectedIndexProperty().addListener(event -> showSelectesUser());
        try {
            usersList = FXCollections.observableArrayList(User.getAllPatients());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dashboard_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tabel_view.setItems(usersList);
    }
    ObservableList<User> datauser;

    public void search_user() throws ClassNotFoundException {
        try {
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_lname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
            col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
            col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            datauser = Database.getAllUsers();
            Tabel_view.setItems(datauser);

            FilteredList<User> filterData = new FilteredList<>(datauser, b -> true);
            txt_search_table.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String searchKey = newValue.toLowerCase();
                    return user.getFirstname().toLowerCase().contains(searchKey);
                });
            });

            SortedList<User> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(Tabel_view.comparatorProperty());
            Tabel_view.setItems(sortedData);

            // Update the filter whenever the search text changes
            txt_search_table.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.predicateProperty().setValue(null);
                System.out.println(datauser);
            });
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void reset(ActionEvent event) {
        resetControls();
    }

    public void resetControls() {
        txt_id.setText("");
        txt_age.setText("");
        txt_email.setText("");
        txt_phone.setText("");
        txt_username.setText("");
        txt_gender.setText("");
        txt_role.setText("");
        txt_fname.setText("");
        txt_lname.setText("");

    }
//add data in textfileds 

    private void showSelectesUser() {
        User user = Tabel_view.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(user.getId()));
        txt_age.setText(user.getAge());
        txt_email.setText(user.getEmail());
        txt_phone.setText(user.getPhone());
        txt_username.setText(user.getUsername());
        txt_gender.setText(user.getGender());
        txt_role.setText(user.getRole());
        txt_fname.setText(user.getFirstname());
        txt_lname.setText(user.getLastname());
    }

}
