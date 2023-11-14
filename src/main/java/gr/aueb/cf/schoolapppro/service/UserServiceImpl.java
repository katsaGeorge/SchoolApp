package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.IUserDAO;
import gr.aueb.cf.schoolapppro.dao.UserDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.dto.UserLoginDTO;
import gr.aueb.cf.schoolapppro.dto.UserRegisterDTO;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.security.SecUtil;
import gr.aueb.cf.schoolapppro.service.exceptions.UserAlreadyExists;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO dao) {
        this.userDAO = dao;
    }


    @Override
    public User register(UserRegisterDTO userRegisterDTO) throws UserDaoException {
      if (userRegisterDTO == null) return null;

      User user;

      try{
          user = map(userRegisterDTO);
          return userDAO.register(user);
      }catch (UserDaoException e){
        e.printStackTrace();
        throw e;
      }
    }

    @Override
    public boolean login(UserLoginDTO dto) throws UserDaoException {
        User checkuser;
        try {
            checkuser = userDAO.getByUsername(dto.getUsername());
            if (checkuser != null && SecUtil.checkPassword(dto.getPassword(),checkuser.getPassword())){
                return true;
            }

        } catch (UserDaoException e) {
            e.printStackTrace();
        }
        return false;
    }





   // @Override
//    public boolean usernameExists(UserRegisterDTO userRegisterDTO)  {
//       // List<String> usernames = userDAO.getUsernames();
//        for (String s : usernames) {
//            if (!s.equals(userRegisterDTO.getUsername())) {
//                return false;
//            }
//
//        }
//        return true;
//    }

//    @Override
//    public boolean isUserValid(UserLoginDTO dto) throws UserDaoException {
//        User checkuser;
//        try {
//            checkuser = userDAO.getByUsername(dto.getUsername());
//            if (checkuser != null && SecUtil.checkPassword(dto.getPassword(),checkuser.getPassword())){
//                return true;
//            }
//
//        } catch (UserDaoException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    public User map(UserRegisterDTO dto) {
            return new User(null, dto.getUsername(), SecUtil.hashpassword(dto.getPassword()));
    }
}