package database;

import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO {

    public void addDoctor(Doctor d) {
        String sql = """
                INSERT INTO doctor (name, age, specialization, salary)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getName());
            ps.setInt(2, d.getAge());
            ps.setString(3, d.getSpecialization());
            ps.setDouble(4, d.getSalary());

            ps.executeUpdate();
            System.out.println("Doctor added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchDoctorByName(String name) {
        String sql = "SELECT * FROM doctor WHERE name ILIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                printDoctor(rs);
            }

            if (!found) {
                System.out.println("No doctors found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(Doctor d) {
        String sql = """
                UPDATE doctor
                SET name=?, age=?, specialization=?, salary=?
                WHERE doctor_id=?
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getName());
            ps.setInt(2, d.getAge());
            ps.setString(3, d.getSpecialization());
            ps.setDouble(4, d.getSalary());
            ps.setInt(5, d.getId());

            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Doctor updated");
            } else {
                System.out.println("Doctor not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int id) {
        String sql = "DELETE FROM doctor WHERE doctor_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int deleted = ps.executeUpdate();

            if (deleted > 0) {
                System.out.println("Doctor deleted");
            } else {
                System.out.println("Doctor not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM doctor WHERE doctor_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("specialization"),
                        rs.getDouble("salary")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printDoctor(ResultSet rs) throws SQLException {
        System.out.println(
                "(" + rs.getInt("doctor_id") + ") " +
                        rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Specialization: " + rs.getString("specialization") +
                        ", Salary: " + rs.getDouble("salary")
        );
    }

    public void searchByMinSalary(double minSalary) {
        String sql = "SELECT * FROM doctor WHERE salary >= ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, minSalary);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        "(" + rs.getInt("doctor_id") + ") " +
                                rs.getString("name") +
                                ", Age: " + rs.getInt("age") +
                                ", Specialization: " + rs.getString("specialization") +
                                ", Salary: " + rs.getDouble("salary")
                );
            }

            if (!found) {
                System.out.println("No doctors found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBySalaryRange(double min, double max) {
        String sql = "SELECT * FROM doctor WHERE salary BETWEEN ? AND ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        "(" + rs.getInt("doctor_id") + ") " +
                                rs.getString("name") +
                                ", Age: " + rs.getInt("age") +
                                ", Specialization: " + rs.getString("specialization") +
                                ", Salary: " + rs.getDouble("salary")
                );
            }

            if (!found) {
                System.out.println("No doctors found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
