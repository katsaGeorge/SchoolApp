package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.CityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.ICityDAO;
import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
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

@WebServlet("/specialitydelete")
public class SpecialityDeleteController extends HttpServlet {
    private  final ISpecialityDAO dao = new SpecialityDAOImpl();
    private final ISpecialityService service = new SpecialityServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String speciality = request.getParameter("speciality");

        try {
            service.deleteSpeciality(id);
            request.setAttribute("id",id);
            request.setAttribute("name",speciality);
            request.getRequestDispatcher("/schoolappPro/static/templates/deletespeciality.jsp").forward(request,response);
        } catch (SpecialityDAOException e) {
            request.setAttribute("error",true);
            request.setAttribute("message",e.getMessage());

            request.getRequestDispatcher("/schoolappPro/static/templates/deletespeciality.jsp").forward(request,response);
        }
    }
}
