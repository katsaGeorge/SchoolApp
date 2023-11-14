package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.model.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {

    User register(User user) throws UserDaoException;
    //boolean login(UserLoginDTO userLoginDTO);
    User updateUser(User user) throws UserDaoException;

    void delteUser(int id ) throws UserDaoException;

    boolean isUserValid(String username, String Password);

    User getByUsername(String username) throws UserDaoException;

     List<User> getAllUsers() throws UserDaoException;
}
