package by.epam.grodno.pronych.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.grodno.pronych.eshop.dao.RoleDaoImpl;
import by.epam.grodno.pronych.eshop.entity.Role;
import by.epam.grodno.pronych.eshop.entity.RoleName;

@Service
@Transactional(readOnly=true)
public class RoleService {
    @Autowired
    private RoleDaoImpl roleDao;

    public List < Role > getAll() {
        return roleDao.getAll();
    }

    @Transactional
    public void save(Role role) {
    	roleDao.save(role);
    }

    public Role getById(int theId) {
        return roleDao.getById(theId);
    }

    public Role getByName(RoleName name) {
    	return roleDao.getByName(name);
    }
    
    public Role getByName(String name) {
    	return roleDao.getByName(name);
    }
    
    @Transactional
    public void delete(int theId) {
    	roleDao.delete(theId);
    }
}