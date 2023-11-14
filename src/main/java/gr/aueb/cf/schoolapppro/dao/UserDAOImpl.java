package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.UserDaoException;
import gr.aueb.cf.schoolapppro.model.User;
import gr.aueb.cf.schoolapppro.service.dbutil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User register(User user) throws UserDaoException {
        String sql = "INSERT INTO USERS (USERNAME,PASSWORD) VALUES (?,?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {


            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, password);

            DBUtil.beginTransaction();

            ps.executeUpdate();

            DBUtil.commitTransaction();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            int genkey = 0;
            if (generatedKeys.next()) {
                genkey = generatedKeys.getInt(1);
            }

            user.setId(genkey);
            generatedKeys.close();


        } catch (SQLException e) {
            // e.printStackTrace();
               DBUtil.rollbackTransaction();
            throw new UserDaoException("Sql Error with user" + user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws UserDaoException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);

            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();


        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new UserDaoException("SQL Error in " + user);
        }
        return user;
    }

    @Override
    public void delteUser(int id) throws UserDaoException {

        String sql = "DELETE FROM USERS WHERE ID= ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setInt(1, id);


            DBUtil.beginTransaction();
            ps.executeUpdate();
            DBUtil.commitTransaction();

        } catch (SQLException e) {
            DBUtil.rollbackTransaction();
            throw new UserDaoException("Sql error with id " + id);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) {
        String sql = "SELECT USERNAME, PASSWORD WHERE USERNAME=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getString("PASSWORD").equals(password)) {

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getByUsername(String username) throws UserDaoException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        User user = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
            }

        } catch (SQLException e) {
            throw new UserDaoException("User not exists");

        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws UserDaoException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new UserDaoException("Error while retrieving all users");
        }

        return userList;
    }

}

