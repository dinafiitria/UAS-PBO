package com.dina.studentmaster.dao;

import com.dina.studentmaster.config.DatabaseConnection;
import com.dina.studentmaster.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> findAll() throws SQLException {
        String sql = "SELECT student_id, student_name, major, email, phone, address FROM students ORDER BY student_id";
        List<Student> data = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()) {
            while (r.next()) data.add(map(r));
        }
        return data;
    }

    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO students(student_id,student_name,major,email,phone,address) VALUES(?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            bind(p, s); p.executeUpdate();
        }
    }

    public void update(Student s) throws SQLException {
        String sql = "UPDATE students SET student_name=?,major=?,email=?,phone=?,address=? WHERE student_id=?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1,s.getStudentName()); p.setString(2,s.getMajor()); p.setString(3,s.getEmail());
            p.setString(4,s.getPhone()); p.setString(5,s.getAddress()); p.setString(6,s.getStudentId());
            if (p.executeUpdate()==0) throw new SQLException("Student ID tidak ditemukan.");
        }
    }

    public void delete(String id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM students WHERE student_id=?")) {
            p.setString(1,id); p.executeUpdate();
        }
    }

    private void bind(PreparedStatement p, Student s) throws SQLException {
        p.setString(1,s.getStudentId()); p.setString(2,s.getStudentName()); p.setString(3,s.getMajor());
        p.setString(4,s.getEmail()); p.setString(5,s.getPhone()); p.setString(6,s.getAddress());
    }

    private Student map(ResultSet r) throws SQLException {
        return new Student(r.getString("student_id"),r.getString("student_name"),r.getString("major"),r.getString("email"),r.getString("phone"),r.getString("address"));
    }
}
