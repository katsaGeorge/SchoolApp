package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.service.ISpecialityService;
import gr.aueb.cf.schoolapppro.service.SpecialityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shcoolapppro/specialityinsert")
public class SpecialityInsertController extends HttpServlet {

    private final ISpecialityDAO dao = new SpecialityDAOImpl();
    private final ISpecialityService service = new SpecialityServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String speciality = request.getParameter("speciality").trim();

        SpecialityInsertDTO insertDTO = new SpecialityInsertDTO(speciality);

        try {
            service.insertSpeciality(insertDTO);
            request.setAttribute("specialityinsert",true);
            response.sendRedirect(request.getContextPath() + "/menu");
        } catch (SpecialityDAOException e) {
            request.setAttribute("error",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }

    }
}
