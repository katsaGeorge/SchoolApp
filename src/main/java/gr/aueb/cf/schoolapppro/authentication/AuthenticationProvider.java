package gr.aueb.cf.schoolapppro.authentication;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.service.IUserService;
import gr.aueb.cf.schoolapppro.service.UserServiceImpl;

public class AuthenticationProvider {


    private static final IUserDAO dao = new UserDAOImpl();
    private static final IUserService serv = new UserServiceImpl(dao);

    public AuthenticationProvider (){}


            public static boolean authenticate (UserLoginDTO dto){

                try{
                    return serv.login(dto);
                }catch (UserDaoException e){
                    e.printStackTrace();
                }
                return false;
            }
}
