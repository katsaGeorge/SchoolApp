package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dto.CityInsertDTO;
import gr.aueb.cf.schoolapppro.model.City;

import java.util.List;

public class CityServiceImpl  implements ICityService{

    private final ICityDAO cityDAO;

    public CityServiceImpl(ICityDAO cityDAO){this.cityDAO = cityDAO;}


    @Override
    public City insertCity(CityInsertDTO dto) throws CityDAOException {
        if (dto == null) return null;

        City city;

        try{
            city = map(dto);
            cityDAO.insertCity(city);
        }catch (CityDAOException e){
            e.printStackTrace();
            throw e;
        }
        return city;
    }

    @Override
    public void delteCity(int id) throws CityDAOException {

        try{
            cityDAO.deleteCity(id);
        }catch (CityDAOException e){
            e.printStackTrace();
            throw e;
        }


    }

    @Override
    public List<City> getCityByName(String CityName)    {
        List<City> cities;
        try{
          cities = cityDAO.getCityByName(CityName);
          return cities;
        }catch (CityDAOException e){
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public List<City> getAllCities()  {

        try {
            List<City> cities = cityDAO.getAllCities();
            return cities;
        }catch (CityDAOException e){
            e.printStackTrace();
        }
       return null;
    }

    City map(CityInsertDTO dto){
        return new City(null, dto.getCity());
    }
}
