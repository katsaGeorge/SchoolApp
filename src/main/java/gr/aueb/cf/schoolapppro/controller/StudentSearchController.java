package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.IStudentService;
import gr.aueb.cf.schoolapppro.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchstudent")
public class StudentSearchController extends HttpServlet {
    private final IStudentDAO dao = new StudentDAOImpl();
    private final IStudentService service = new StudentServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String lastname = request.getParameter("lastname").trim();
        List<Student> students;

        try {
            students = service.getListByLastName(lastname);

            if (students.size() == 0) {
                request.setAttribute("studentNotFound", true);
                request.getRequestDispatcher("schoolappPro/static/templates/menu.jsp").forward(request, response);
            }

            request.setAttribute("teachers", students);
            request.getRequestDispatcher("/schoolappPro/static/templates/studentstable.jsp").forward(request, response);


        } catch (StudentDAOException e) {
            e.printStackTrace();
        }
    }
}
