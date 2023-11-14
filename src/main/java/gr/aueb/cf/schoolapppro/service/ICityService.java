package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dto.CityInsertDTO;
import gr.aueb.cf.schoolapppro.model.City;

import java.util.List;

public interface ICityService {

    City insertCity(CityInsertDTO dto) throws CityDAOException;

    void delteCity(int id) throws CityDAOException;
    List<City> getCityByName(String CityName) throws CityDAOException;
    List<City> getAllCities() throws CityDAOException;
}
