package org.example.dao;

import org.example.model.User;

public interface UserDao {

    Boolean existByEmail(String email);

    String saveUser(User users);

    void removeUserById(long id);
    void updateTableUser(String newColumnName, String dataType);

    String saveLanguage(Long id, String language);


}
