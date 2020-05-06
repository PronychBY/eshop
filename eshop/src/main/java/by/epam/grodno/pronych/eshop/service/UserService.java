package by.epam.grodno.pronych.eshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.grodno.pronych.eshop.dao.UserDao;
import by.epam.grodno.pronych.eshop.entity.User;

@Component("userService")
@Transactional
public class UserService {
	private final UserDao dao;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
	public void save(User user) {
        dao.save(user);
    }
	
	public void update(User user) {
        dao.update(user);
    }
    
    public void delete(int id) {
        dao.delete(id);
    }

    public User getById(int id) {
		User user = dao.getById(id);
        return user;
    }
    
    public User getByName(String name) {
		User user = dao.getByName(name);
        return user;
    }
    
    public List<User> getAll() {
    	List<User> users = dao.getAll();
        return users;
    }

    public boolean isUserAdmin(User user) {
    	return user.getRoles().contains(roleService.getByName("ROLE_ADMIN"));
    }
    
    
}
