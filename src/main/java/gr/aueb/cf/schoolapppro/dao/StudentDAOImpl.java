package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    @Override
    public Student insertStudent(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTANME, GENDER, BIRTH_DATE, CITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            Date date = new Date(student.getBirthDate().getTime());
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setDate(4,date);
            ps.setInt(5, student.getCityId());
            ps.setInt(6, student.getUserId());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new StudentDAOException("Error while inserting student");
        }

        return student;
    }

    @Override
    public Student updateStudent(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, GENDER = ?, CITY_ID = ?, USER_ID = ? WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setInt(4, student.getCityId());
            ps.setInt(5, student.getUserId());
            ps.setInt(6, student.getId());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new StudentDAOException("Error while updating student");
        }

        return student;
    }

    @Override
    public void deleteStudent(int id) throws StudentDAOException {
        String sql = "DELETE FROM STUDENTS WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new StudentDAOException("Error while deleting student");
        }
    }

    @Override
    public Student getStudentByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, lastname);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("ID"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("LASTNAME"),
                            rs.getString("GENDER").charAt(0),
                            rs.getDate("DATE"),
                            rs.getInt("CITY_ID"),
                            rs.getInt("USER_ID")
                    );
                }
            }

        } catch (SQLException e) {
            throw new StudentDAOException("Error while retrieving student by last name");
        }

        return null; // Student not found
    }

    @Override
    public List<Student> getStudentsListByLastname(String lastname) throws StudentDAOException {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + lastname + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getInt("ID"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("LASTNAME"),
                            rs.getString("GENDER").charAt(0),
                            rs.getDate("DATE"),
                            rs.getInt("CITY_ID"),
                            rs.getInt("USER_ID")
                    );
                    studentList.add(student);
                }
            }

        } catch (SQLException e) {
            throw new StudentDAOException("Error while retrieving students by last name");
        }

        return studentList;
    }


    @Override
    public List<Student> getAllStudents() throws StudentDAOException {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("GENDER").charAt(0),
                        rs.getDate("DATE"),
                        rs.getInt("CITY_ID"),
                        rs.getInt("USER_ID")
                );
                studentList.add(student);
            }

        } catch (SQLException e) {
            throw new StudentDAOException("Error while retrieving students");
        }

        return studentList;
    }
}
