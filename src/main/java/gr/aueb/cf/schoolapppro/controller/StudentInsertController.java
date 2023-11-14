package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.service.IStudentService;
import gr.aueb.cf.schoolapppro.service.StudentServiceImpl;
import gr.aueb.cf.schoolapppro.validator.StudentValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/schoolapppro/studentinsert")
public class StudentInsertController extends HttpServlet {

    private final IStudentDAO dao = new StudentDAOImpl();
    private final IStudentService service = new StudentServiceImpl(dao);



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String firstname = request.getParameter("firstname").trim();
            String lastname = request.getParameter("lastname").trim();
            char gender = request.getParameter("gender").charAt(0);

            Date birthdate =  dateFormat.parse(request.getParameter("birthdate"));

            int cityid = Integer.parseInt(request.getParameter("city"));
            int userid = Integer.parseInt(request.getParameter("userid"));

        StudentInsertDTO insertDTO = new StudentInsertDTO(firstname,lastname,gender,birthdate,cityid,userid);

         Map<String,String> errors = StudentValidator.valid(insertDTO);

         if(!errors.isEmpty()){
             String firstnameMessage = (errors.get("firstname") !=null ) ? "Firstname " + errors.get("firstanme") : "";
             String lastnameMessage = (errors.get("lastname") !=null ) ? "Lastname " + errors.get("lastname") : "";
             String ageMessage = (errors.get("age") != null) ? "Age " + errors.get("age") : "";
             request.setAttribute("error",firstnameMessage + " " + lastnameMessage + " " + ageMessage);
             request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
             return;
         }
        try {
            service.insertStudent(insertDTO);
            request.setAttribute("inserted",true);
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);

        }catch (StudentDAOException e){
                e.printStackTrace();
            request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }


        } catch (ParseException e) {
           request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
        }


    }
}
