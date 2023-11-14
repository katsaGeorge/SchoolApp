package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dto.StudentDeleteDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.service.IStudentService;
import gr.aueb.cf.schoolapppro.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/deletestudent")
public class StudentsDeleteController extends HttpServlet {
    private final IStudentDAO dao = new StudentDAOImpl();
    private final IStudentService service = new StudentServiceImpl(dao);



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("txt/html; charset = UTF-8");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String firstname = request.getParameter("firstname").trim();
            String lastname = request.getParameter("lastname").trim();
            char gender = request.getParameter("gender").charAt(0);

            Date birthdate = dateFormat.parse(request.getParameter("birthdate"));

            int cityid = Integer.parseInt(request.getParameter("city"));
            int userid = Integer.parseInt(request.getParameter("userid"));

            StudentDeleteDTO deleteDTO = new StudentDeleteDTO(id, firstname, lastname, gender, birthdate, cityid, userid);

            try {
                service.deleteStudent(deleteDTO.getId());
                request.setAttribute("student",deleteDTO);
                // TODO: 26/8/2023 dispacher στην σελιδα που θα δειχνει τον διεγραμμενο student
            }catch (StudentDAOException e){
                e.printStackTrace();
                request.setAttribute("deleteError",true);
                request.setAttribute("message",e.getMessage());
                request.getRequestDispatcher("/schoolappPro/static/templates/studentstable.jsp").forward(request,response);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
