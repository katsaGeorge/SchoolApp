package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.service.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.ICityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
@WebServlet("/citydelete")
public class CityDeleteController extends HttpServlet {
    private  final ICityDAO dao = new CityDAOImpl();
    private final ICityService service = new CityServiceImpl(dao);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String cityName = request.getParameter("cityName");

        try {
            service.delteCity(id);
            request.setAttribute("id",id);
            request.setAttribute("name",cityName);
            request.getRequestDispatcher("/schoolappPro/static/templates/deletecity.jsp").forward(request,response);
        } catch (CityDAOException e) {
            request.setAttribute("error",true);
            request.setAttribute("message",e.getMessage());

            request.getRequestDispatcher("/schoolappPro/static/templates/deletecity.jsp").forward(request,response);
        }


    }
}
