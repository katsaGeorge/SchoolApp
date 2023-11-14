package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;
import gr.aueb.cf.schoolapppro.service.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/specialitysearch")
public class SpecialitySearchController extends HttpServlet {
    private final ISpecialityDAO dao = new SpecialityDAOImpl();
    private final ISpecialityService service = new SpecialityServiceImpl(dao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String speciality = request.getParameter("speciality").trim();
        try {

          List<Speciality> specialities  = service.getSpecialityByName(speciality);
            request.setAttribute("specialities", specialities);

            // TODO: 27/8/2023 dispacher στην σελιδα ton specialitiestsble.
        }catch (SpecialityDAOException e){
            request.setAttribute("specialityNotFound",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }


    }
}
