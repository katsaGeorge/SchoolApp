package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.TeacherDeleteDTO;
import gr.aueb.cf.schoolapppro.service.ITeacherService;
import gr.aueb.cf.schoolapppro.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteteacher")
public class TeacherDeleteController extends HttpServlet {

    ITeacherDAO dao = new TeacherDAOImpl();
    ITeacherService service = new TeacherServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("txt/html; charset =UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int ssn = Integer.parseInt(request.getParameter("ssn"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int specId = Integer.parseInt(request.getParameter("specId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        TeacherDeleteDTO deleteDTO = new TeacherDeleteDTO(id,firstname,lastname,ssn,specId,userId);

        try {
            service.deleteTeacher(id);
            request.setAttribute("teacher",deleteDTO);
            // TODO: 25/8/2023 requestgetdispatcher στην σελιδα που θα δείχνει τον διεγραμμενο teacher
        }catch (TeacherDAOException e){
            request.setAttribute("deleteError",true);
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/schoolappPro/static/templates/teacherstable.jsp").forward(request,response);
        }


    }


}
