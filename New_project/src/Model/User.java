/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author hamza qannita
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(int id, String username, String firstname, String lastname, String age, String email, String phone, String gender, String role) {
        this.id = id;
        this.username = username;

        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public User(int id, String username, String password, String firstname, String lastname, String age, String email, String phone, String gender, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public User() {
    }

    // Getters and setters for all the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //create a new user in users table
    PreparedStatement pst;
    Connection conn;
    Database db;
    int recordCounter = 0;

    public int save() throws SQLException, ClassNotFoundException {
        conn = Database.ConnectioDB();
        pst = null;

        String sql;
        sql = "INSERT INTO users (id, username, password, firstname, lastname, age, email, phone, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, this.getId());
        pst.setString(2, this.getUsername());
        pst.setString(3, this.getPassword());
        pst.setString(4, this.getFirstname());
        pst.setString(5, this.getLastname());
        pst.setString(6, this.getAge());
        pst.setString(7, this.getEmail());
        pst.setString(8, this.getPhone());
        pst.setString(9, this.getGender());
        pst.setString(10, this.getRole());
        recordCounter = pst.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername() + " User was added successfully!");
        }
        if (pst != null) {
            pst.close();
        }
        conn.close();
        return recordCounter;
    }

    //get all users from users table
    public static ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> users;
        try (Connection c = Database.ConnectioDB()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            users = new ArrayList<>();
            String sql = "SELECT * FROM users  ";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String username = rs.getString("username");
                String age = rs.getString("age");
                String phone = rs.getString("phone");
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String role = rs.getString("role");
                String email = rs.getString("email");
                User user = new User(id, username, fname, lname, age, email, phone, gender, role);
                user.setId(rs.getInt(1));
                users.add(user);

            }
            if (ps != null) {
                ps.close();
            }
        }
        return users;
    }

    public static ArrayList<User> getAllPatients() throws SQLException, ClassNotFoundException {
        ArrayList<User> users;
        try (Connection c = Database.ConnectioDB()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            users = new ArrayList<>();
            String sql = "SELECT * FROM users where role = 'patient' ";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String username = rs.getString("username");
                String age = rs.getString("age");
                String phone = rs.getString("phone");
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String role = rs.getString("role");
                String email = rs.getString("email");
                User user = new User(id, username, fname, lname, age, email, phone, gender, role);
                user.setId(rs.getInt(1));
                users.add(user);

            }
            if (ps != null) {
                ps.close();
            }
        }
        return users;
    }

}
