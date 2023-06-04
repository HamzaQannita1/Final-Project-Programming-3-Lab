/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hamza qannita
 */
public class Database {

    public static Connection ConnectioDB() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_projects", "root", "");
            System.out.println(" Connection DataBase ^_^");
            return conn;

        } catch (ClassNotFoundException ex) {
            System.out.println("Error Connection b DataBase ??");
            return null;
        }
    }

    public static ObservableList<User> getAllUsers() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectioDB();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM `users` WHERE 1");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                list.add(new User(Integer.parseInt(rs.getString("id")),
                        rs.getString("username"), rs.getString("fname"), rs.getString("lname"),
                        rs.getString("age"), rs.getString("email"), rs.getString("phone"),
                          rs.getString("gender"), rs.getString("role")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

}
