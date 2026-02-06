package database;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void insertPatient(Patient p) {
        String sql = "INSERT INTO patient (name, age, diagnosis, admission_date) " +
                "VALUES (?, ?, ?, CURRENT_DATE)";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getDisease());
            ps.executeUpdate();
            ps.close();
            System.out.println("Patient inserted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        "",
                        rs.getString("diagnosis")
                ));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
        return list;
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        "",
                        rs.getString("diagnosis")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
        return null;
    }

    public void updatePatient(Patient p) {
        String sql = "UPDATE patient SET name=?, age=?, diagnosis=? WHERE patient_id=?";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getDisease());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            ps.close();
            System.out.println("Patient updated");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
    }

    public void deletePatient(int id) {
        String sql = "DELETE FROM patient WHERE patient_id=?";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            System.out.println("Patient deleted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
    }

    public List<Patient> searchByName(String name) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE name ILIKE ?";
        Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        "",
                        rs.getString("diagnosis")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
        return list;
    }
}
