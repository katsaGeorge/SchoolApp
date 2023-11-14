package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.ICityService;
import gr.aueb.cf.schoolapppro.service.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuController extends HttpServlet {
    ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
    ISpecialityService specialityService = new SpecialityServiceImpl(specialityDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();
            request.setAttribute("specialities", specialities);
        } catch (SpecialityDAOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<City> cities = cityService.getAllCities();
            request.setAttribute("cities", cities);
        } catch (CityDAOException e) {
            throw new RuntimeException(e);
        }


        request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
    }
}
