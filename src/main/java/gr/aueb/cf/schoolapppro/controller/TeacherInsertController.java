package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.service.ITeacherService;
import gr.aueb.cf.schoolapppro.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapppro.validator.TeacherValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/schoolapppro/teacherinsert")
public class TeacherInsertController extends HttpServlet {

        private final ITeacherDAO dao = new TeacherDAOImpl();
        private final ITeacherService service = new TeacherServiceImpl(dao);



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        Integer ssn = Integer.parseInt(request.getParameter("ssn").trim());
        Integer specID  = Integer.parseInt(request.getParameter("speciality"));
        Integer userID = Integer.parseInt(request.getParameter("userid").trim());

        TeacherInsertDTO teacherInsertDTO = new TeacherInsertDTO(ssn,firstname,lastname,specID,userID);

        try{
            Map<String,String> errors = TeacherValidator.valid(teacherInsertDTO);
            if(!errors.isEmpty()){
                String firstnameMessage = (errors.get("firstname") !=null ) ? "Firstname " + errors.get("firstanme") : "";
                String lastnameMessage = (errors.get("lastname") !=null ) ? "Lastname " + errors.get("lastname") : "";
                request.setAttribute("error",firstnameMessage + " " + lastnameMessage + " ");
                request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
                return;
            }
            service.insertTeacher(teacherInsertDTO);
            String message = "The Teacher inserted successfully";
            request.setAttribute("message",message);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
