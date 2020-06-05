package by.epam.grodno.pronych.eshop.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.grodno.pronych.eshop.model.dao.RoleDao;
import by.epam.grodno.pronych.eshop.model.entity.Role;
import by.epam.grodno.pronych.eshop.model.entity.RoleName;

@Service
@Transactional(readOnly=true)
public class RoleServiceImpl {
    private RoleDao roleDao;

    @Autowired
    RoleServiceImpl(RoleDao roleDao){
		this.roleDao = roleDao;
    	
    }
    
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
