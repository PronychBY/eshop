package by.epam.grodno.pronych.eshop.model.dao;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Role;
import by.epam.grodno.pronych.eshop.model.entity.RoleName;

public interface RoleDao {
    List<Role> getAll();
    void save(Role role);
    void delete(Role role);
    void delete(int id);
    void update(Role role);
    Role getById(int id);
    Role getByName(RoleName name);
    Role getByName(String name);
    
}
