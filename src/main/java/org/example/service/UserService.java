package org.example.service;

import org.example.model.Theatre;
import org.example.model.User;

public interface UserService {


    Boolean existByEmail(String email);

    String saveUser(User users);
    void removeUserById(long id);

    void updateTableUser(String newColumnName, String dataType);

    String saveLanguage(Long id, String language);

}

