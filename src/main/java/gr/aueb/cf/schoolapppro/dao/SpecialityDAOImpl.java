package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImpl implements ISpecialityDAO {

    @Override
    public Speciality insertSpeciality(Speciality speciality) throws SpecialityDAOException {
        String sql = "INSERT INTO SPECIALITIES (SPECIALITY) VALUES (?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, speciality.getSpeciality());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                speciality.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new SpecialityDAOException("Error inserting speciality");
        }

        return speciality;
    }

    @Override
    public List<Speciality> updateSpecialityByName(String specialityName) throws SpecialityDAOException {
        String sql = "UPDATE SPECIALITIES SET SPECIALITY = ? WHERE SPECIALITY = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, specialityName);
            ps.setString(2, specialityName);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new SpecialityDAOException("Error updating speciality by name");
        }

        return getSpecialityByName(specialityName);
    }

    @Override
    public void deleteSpecialityById(int id) throws SpecialityDAOException {
        String sql = "DELETE FROM SPECIALITIES WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new SpecialityDAOException("Error deleting speciality by ID");
        }
    }

    @Override
    public List<Speciality> getSpecialityByName(String specialityName) throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES WHERE SPECIALITY LIKE ?";

        List<Speciality> specialities = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + specialityName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Speciality speciality = new Speciality(rs.getInt("ID"),
                            rs.getString("SPECIALITY"));

                    specialities.add(speciality);
                }
            }

        } catch (SQLException e) {
            throw new SpecialityDAOException("Error retrieving speciality by name");
        }

        return specialities;
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES";
        List<Speciality> specialities = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Speciality speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialities.add(speciality);
            }

        } catch (SQLException e) {
            throw new SpecialityDAOException("Error retrieving all specialities");
        }

        return specialities;
    }
}
