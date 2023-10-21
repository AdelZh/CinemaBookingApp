package org.example.service.Impl;

import org.example.dao.Impl.UserDaoImpl;
import org.example.dao.UserDao;
import org.example.model.User;
import org.example.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDaoImpl();

    @Override
    public String saveUser(User users) {
        return userDao.saveUser(users);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public void updateTableUser(String newColumnName, String dataType) {
        userDao.updateTableUser(newColumnName, dataType);
    }

    @Override
    public Boolean existByEmail(String email) {
        return userDao.existByEmail(email);
    }

    @Override
    public String saveLanguage(Long id, String language) {
        return userDao.saveLanguage(id, language);
    }
}
