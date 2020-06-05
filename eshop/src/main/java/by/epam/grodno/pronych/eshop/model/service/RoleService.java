package by.epam.grodno.pronych.eshop.model.service;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.entity.Role;
import by.epam.grodno.pronych.eshop.model.entity.RoleName;

public interface RoleService {
    public List < Role > getAll();
    public void save(Role role);
    public Role getById(int theId);
    public Role getByName(RoleName name);
    public Role getByName(String name);
    public void delete(int theId);
}
