package org.example.dao.Impl;

import org.example.config.Config;
import org.example.dao.UserDao;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    Connection connection = Config.getConnection();

    @Override
    public String saveUser(User users) {
        String sql = "insert into \"users\"(id, userName, password, email)" +
                "values(?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, users.getId());
            preparedStatement.setString(2, users.getUserName());
            preparedStatement.setInt(3, users.getPassword());
            preparedStatement.setString(4, users.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Saved";
    }


    @Override
    public Boolean existByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void removeUserById(long id) {
        String sql = "delete from \"users\" where id=" + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("User by this id " + id + " deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateTableUser(String newColumnName, String dataType) {
        String sql = "ALTER TABLE users ADD COLUMN " + newColumnName + " " + dataType;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Column " + newColumnName + " added to the 'users' table.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating 'users' table: " + e.getMessage());
        }
    }

    @Override
    public String saveLanguage(Long id, String language) {
        String sql = "UPDATE users SET language = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, language);
            preparedStatement.setLong(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return "Language saved successfully.";
            } else {
                return "No user found with the provided ID.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}