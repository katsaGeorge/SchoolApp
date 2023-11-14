package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.ITeacherService;
import gr.aueb.cf.schoolapppro.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teachersearch")
public class TeacherSearchController extends HttpServlet {

    private final ITeacherDAO dao = new TeacherDAOImpl();
    private final ITeacherService service = new TeacherServiceImpl(dao);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        String lastname = request.getParameter("lastname").trim();
        List <Teacher> teachers;

        try {

            if (lastname.equals("")) {
                teachers = dao.getAllTeachers();
            }else {
                teachers = service.getListByLastname(lastname);
            }



            if (teachers.size() == 0) {
                request.setAttribute("teachersNotFound", true);
                request.getRequestDispatcher("schoolappPro/static/templates/teacherstable.jsp").forward(request, response);
            }

            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/schoolappPro/static/templates/teacherstable.jsp").forward(request, response);


        } catch (TeacherDAOException e) {
            e.printStackTrace();
        }

    }
}
