/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author hamza qannita
 */
public class Appointment {

    private int id;
    private LocalDate appointmentDate;
    private String appointmentDay;
    private String appointmentTime;
    private String status;

    public Appointment(int id, LocalDate appointmentDate, String appointmentDay, String appointmentTime, String status) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Appointment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and setters for all the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //create a new user in users table
    PreparedStatement pst;
    Connection conn;
    Database db;
    int recordCounter = 0;

    public int save() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pst = null;
        int recordCounter = 0;

        try {
            conn = Database.ConnectioDB();
            String sql = "INSERT INTO `appointments`(id, appointment_date, appointment_day, appointment_time, status) VALUES(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, this.getId());
            pst.setDate(2, Date.valueOf(this.getAppointmentDate()));
            pst.setString(3, this.getAppointmentDay());
            pst.setString(4, this.getAppointmentTime());
            pst.setString(5, this.getStatus());

            recordCounter = pst.executeUpdate();
            if (recordCounter > 0) {
                System.out.println("Appointment was added successfully!");
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return recordCounter;
    }

    public static ArrayList<Appointment> getAllAppointment() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointment;
        try (Connection c = Database.ConnectioDB()) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            appointment = new ArrayList<>();
            String sql = "SELECT booked_appointments.*, users.*, appointments.* FROM `booked_appointments` JOIN users ON users.id = booked_appointments.user_id JOIN appointments ON appointments.id = booked_appointments.appointment_id ";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                Date app_date = rs.getDate("appointment_date");
                String app_day = rs.getString("appointment_day");
                String app_time = rs.getString("appointment_time");
              
                String app_status = rs.getString("status");
                Appointment app;
                app = new Appointment(id, LocalDate.MIN, app_day, app_time, app_status);
                app.setId(rs.getInt(1));
                appointment.add(app);

            }
            if (ps != null) {
                ps.close();
            }
        }
        return appointment;
    }
}
