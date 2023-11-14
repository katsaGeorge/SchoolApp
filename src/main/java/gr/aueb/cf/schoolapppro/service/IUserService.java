package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.exceptions.UserAlreadyExists;

public interface IUserService {
        User register(UserRegisterDTO userRegisterDTO) throws UserDaoException;
        boolean login(UserLoginDTO userLoginDTO) throws UserDaoException;

//     boolean isUserValid(UserLoginDTO userLoginDTO) throws UserDaoException;
}
