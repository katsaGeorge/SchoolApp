package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insertTeacher(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (SSN, FIRSTNAME, LASTNAME, SPECIALITY_ID, USER_ID) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, teacher.getSsn());
            ps.setString(2, teacher.getFirstname());
            ps.setString(3, teacher.getLastname());
            ps.setInt(4, teacher.getSpecialityId());
            ps.setInt(5, teacher.getUserId());

            DBUtil.beginTransaction();

            ps.executeUpdate();

            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            int genkey = 0;
            if (generatedKeys.next()) {
                genkey = generatedKeys.getInt(1);
            }

            teacher.setId(genkey);
            generatedKeys.close();



        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("Error Inserting Teacher with lastname" + teacher.getLastname());
        }
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET SSN = ?, FIRSTNAME = ?, LASTNAME = ?, SPECIALITY_ID = ?, USER_ID = ? WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, teacher.getSsn());
            ps.setString(2, teacher.getFirstname());
            ps.setString(3, teacher.getLastname());
            ps.setInt(4, teacher.getSpecialityId());
            ps.setInt(5, teacher.getUserId());
            ps.setInt(6, teacher.getId());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("Error Update Teacher with id" + teacher.getId());
        }
        return teacher;
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new TeacherDAOException("Error while deleting teacher with id " + id);
        }
    }


    @Override
    public Teacher getTeacherByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, lastname);

            Teacher teacher = null;

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teacher.setId(rs.getInt("ID"));
                    teacher.setSsn(rs.getInt("SSN"));
                    teacher.setFirstname(rs.getString("FIRSTNAME"));
                    teacher.setLastname(rs.getString("LASTNAME"));
                    teacher.setSpecialityId(rs.getInt("SPECIALITY_ID"));
                    teacher.setUserId(rs.getInt("USER_ID"));
                }
                return teacher;
            } catch (SQLException e) {
                throw new TeacherDAOException("Error while retrieving teacher by lastname");
            }

        } catch (SQLException e) {
            throw new TeacherDAOException("Error while retrieving teacher by lastname");
        }

    }

    @Override
    public List<Teacher> getTeachersListByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + lastname + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Teacher teacher = new Teacher(
                            rs.getInt("ID"),
                            rs.getInt("SSN"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("LASTNAME"),
                            rs.getInt("SPECIALITY_ID"),
                            rs.getInt("USER_ID")
                    );
                    teachers.add(teacher);
                }
            }

        } catch (SQLException e) {
            throw new TeacherDAOException("Error while retrieving teachers by lastname");
        }

        return teachers;
    }


    @Override
    public List<Teacher> getAllTeachers() throws TeacherDAOException {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT * FROM TEACHERS";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getInt("SSN"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getInt("SPECIALITY_ID"),
                        rs.getInt("USER_ID")
                );
                teacherList.add(teacher);
            }

        } catch (SQLException e) {
            throw new TeacherDAOException("Error while retrieving all teachers");
        }
        return teacherList;
    }

}

