package by.epam.grodno.pronych.eshop.model.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.User;

public interface UserDao {
    List<User> getAll();
    void save(User user);
    void delete(User user);
    void delete(int id);
    void update(User user);
    User getById(int id);
    
    void moveToBlackList(User user); 
    void removeFromBlackList(User user);
    
    User getByUserName(String name);
    User getByName(String name);
}
