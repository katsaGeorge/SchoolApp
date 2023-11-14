package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDto;
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

@WebServlet("/updateteacher")
public class TeacherUpdateController extends HttpServlet {

    private final ITeacherDAO dao = new TeacherDAOImpl();

    private final ITeacherService service = new TeacherServiceImpl(dao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String lastname = request.getParameter("lastname").trim();
        String firstname = request.getParameter("firstname").trim();
        int ssn = Integer.parseInt(request.getParameter("ssn").trim());
        int specID  = Integer.parseInt(request.getParameter("specid"));
        int userid = Integer.parseInt(request.getParameter("userid"));

        TeacherUpdateDto updateDto = new TeacherUpdateDto(id,firstname,lastname,ssn,specID,userid);

        try {
            Map<String,String> errors = TeacherValidator.valid(updateDto);
            if(!errors.isEmpty()){
                String firstnameMessage = (errors.get("firstname") !=null ) ? "Firstname " + errors.get("firstanme") : "";
                String lastnameMessage = (errors.get("lastname") !=null ) ? "Lastname " + errors.get("lastname") : "";
                request.setAttribute("error",firstnameMessage + "" + lastnameMessage + "");
          //θελει την jsp του update     // request.getRequestDispatcher("/schoolappPro/static/templates/menu.jsp").forward(request,response);
                return;
            }

            service.updateTeacher(updateDto);
            // TODO: 25/8/2023  τα request.setAttribute και dipsatcher οταν φτιαχτει η σελίδα jsp για το update


        }catch (TeacherDAOException e){
        String message = e.getMessage();
        request.setAttribute("message",message);
        // TODO: 25/8/2023     request.getRequestDispatcher("/schoolappPro/static/templates/update.jsp").forward(request,response);
        }





    }
}
