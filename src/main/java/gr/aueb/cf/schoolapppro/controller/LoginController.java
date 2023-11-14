package gr.aueb.cf.schoolapppro.controller;

import gr.aueb.cf.schoolapppro.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;
import gr.aueb.cf.schoolapppro.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService service = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.getRequestDispatcher("/schoolappPro/static/templates/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        Map<String,String> errors = UserValidator.validatelog(userLoginDTO);

        if (!errors.isEmpty()){
            String usernamemessage = (errors.get("username") != null) ? "Username " + errors.get("username") : "";
            String passwordmessage = (errors.get("password") != null) ? "Password " + errors.get("password") : "";
            request.setAttribute("error", usernamemessage + " " + passwordmessage);
            request.getRequestDispatcher("/schoolappPro/static/templates/login.jsp").forward(request, response);
            return;
        }
            boolean principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);

        if (principleIsAuthenticated){
            HttpSession session = request.getSession(false);
            session.setAttribute("loginName", username);
            response.sendRedirect(request.getContextPath() + "/schoolapppro/menu");
        }else {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }
    }

}
