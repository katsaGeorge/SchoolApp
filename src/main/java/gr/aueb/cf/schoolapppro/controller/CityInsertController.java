package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dto.CityInsertDTO;
import gr.aueb.cf.schoolapppro.service.CityServiceImpl;
import gr.aueb.cf.schoolapppro.service.ICityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cityinsert")
public class CityInsertController extends HttpServlet {

    private final ICityDAO dao = new CityDAOImpl();
    private final ICityService service = new CityServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String city = request.getParameter("city").trim();

        CityInsertDTO dto = new CityInsertDTO(city);

        try {
            service.insertCity(dto);
            request.setAttribute("insertCity",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);

        } catch (CityDAOException e) {
            request.setAttribute("error",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }


    }
}
