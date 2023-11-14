package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;

import java.util.List;

public interface ICityDAO {
    City insertCity(City city) throws CityDAOException;
    City updateCity(City city) throws CityDAOException;
    void deleteCity(int id) throws CityDAOException;
    List<City> getCityByName(String cityName) throws CityDAOException;
    List<City> getAllCities() throws CityDAOException;
}
