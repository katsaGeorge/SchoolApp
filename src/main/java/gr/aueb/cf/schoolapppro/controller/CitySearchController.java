package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.model.City;
import gr.aueb.cf.schoolapppro.service.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.ICityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/citysearch")
public class CitySearchController extends HttpServlet {

    private final ICityDAO dao = new CityDAOImpl();
    private final ICityService service = new CityServiceImpl(dao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cityName = request.getParameter("city").trim();

        try {
               List<City> cities = service.getCityByName(cityName);
                request.setAttribute("cities", cities);

                // TODO: 26/8/2023  requset.getRequestDispatcher("/schoolappPro/static/templates/tablecities.jsp").forward(request,response);
        } catch (CityDAOException e) {
            request.setAttribute("citynotfound",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }


    }
}
