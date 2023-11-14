package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {
    @Override
    public City insertCity(City city) throws CityDAOException {
        String sql = "INSERT INTO CITIES (CITY) VALUES (?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, city.getCity());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                city.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new CityDAOException("Error while inserting city");
        }

        return city;
    }

    @Override
    public City updateCity(City city) throws CityDAOException {
        String sql = "UPDATE CITIES SET CITY = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, city.getCity());
            ps.setInt(2, city.getId());

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new CityDAOException("Error while updating city");
        }

        return city;
    }

    @Override
    public void deleteCity(int id) throws CityDAOException {
        String sql = "DELETE FROM CITIES WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new CityDAOException("Error while deleting city");

        }
    }

    @Override
    public List<City> getCityByName(String cityName) throws CityDAOException {
        String sql = "SELECT * FROM CITIES WHERE CITY LIKE ?";
        List<City> cities = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + cityName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("CITY")
                    );

                    cities.add(city);
                }

            }

        } catch (SQLException e) {
            throw new CityDAOException("Error while retrieving city by ID");
        }

        return cities;
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM CITIES";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                City city = new City(
                        rs.getInt("ID"),
                        rs.getString("CITY")
                );
                cityList.add(city);
            }

        } catch (SQLException e) {
            throw new CityDAOException("Error while retrieving cities");
        }

        return cityList;
    }
}
