package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;
import gr.aueb.cf.schoolapppro.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();

    private final IUserService service = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/schoolappPro/static/templates/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String conpass = request.getParameter("conpassword").trim();

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(username, password, conpass);

        try {
            Map<String, String> errors = UserValidator.validate(userRegisterDTO);

            if (!errors.isEmpty()) {
                String usernamemessage = (errors.get("username") != null) ? "Username " + errors.get("username") : "";
                String passwordmessage = (errors.get("password") != null) ? "Password " + errors.get("password") : "";
                request.setAttribute("error", usernamemessage + " " + passwordmessage);
                request.getRequestDispatcher("/schoolappPro/static/templates/register.jsp").forward(request, response);
                return;
            }

           User user = userDAO.getByUsername(userRegisterDTO.getUsername());
            if (user == null){
             service.register(userRegisterDTO);
            }else {
                request.setAttribute("usernameExists",userRegisterDTO.getUsername());
            }


        } catch (UserDaoException e) {
                e.printStackTrace();
        }

    }
}

