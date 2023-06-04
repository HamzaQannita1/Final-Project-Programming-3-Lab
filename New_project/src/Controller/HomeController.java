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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class HomeController implements Initializable {
    
    @FXML
    private TableView<User> Tabel_view;
    
    @FXML
    private Button btn_addPatirent;
    
    @FXML
    private Button btn_app;
    
    @FXML
    private Button btn_dashboard;
    
    @FXML
    private Button btn_out;
    
    @FXML
    private Button btn_profile;
    
    @FXML
    private Button btn_show;
    
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
    private TableColumn<User, String> id_col;
    
    @FXML
    private Label lab_info;
    
    @FXML
    private Label lab_info4;
    
    @FXML
    private TextField txt_search_user;
    AlertManager alert;
    Statement stam;
    Connection conn;
    ObservableList<User> datauser;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            search_user();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void Add_patients(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/register.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        //primaryStage.close();
        primaryStage.show();
    }
    
    @FXML
    void add_appointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/register.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        //primaryStage.close();
        primaryStage.show();
    }
    
    @FXML
    void login_profile(ActionEvent event) throws IOException {
     
        
    }
    
    @FXML
    void out(ActionEvent event) {
        System.exit(0);
        
    }
    ResultSet rs;
    
    @FXML
    void showAllUsers(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<User> usersList;
        usersList = FXCollections.observableArrayList(User.getAllUsers());
        System.out.println("Show All user of database");
        Tabel_view.setItems(usersList);
    }    
    
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
            //  Tabel_view.setItems(datauser);

            FilteredList<User> filterData = new FilteredList<>(datauser, b -> true);
            txt_search_user.textProperty().addListener((observable, oldValue, newValue) -> {
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
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
