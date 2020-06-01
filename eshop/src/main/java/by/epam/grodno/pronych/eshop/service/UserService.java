package by.epam.grodno.pronych.eshop.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.pronych.eshop.dao.UserDao;
import by.epam.grodno.pronych.eshop.entity.Role;
import by.epam.grodno.pronych.eshop.entity.User;
import by.epam.grodno.pronych.eshop.service.dts.UserMsg;

@Service("userService")
@Transactional(readOnly=true)
public class UserService {
	private final UserDao dao;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
    @Transactional
	public void save(User user) {
        dao.save(user);
    }
	
    @Transactional
	public void update(User user) {
        dao.update(user);
    }
    
    @Transactional
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
    
    public User getByUserName(String userName) {
		User user = dao.getByUserName(userName);
        return user;
    }
    
    public List<User> getAll() {
    	return dao.getAll();
    }
    
    public List<UserMsg> getAllDebts() {
    	return orderService.getAllDebts();
    }

    public boolean isUserAdmin(User user) {
    	//RoleName roleName = RoleName.ROLE_ADMIN;//.valueOf(enumType, name)
    	Role role = roleService.getByName("ROLE_ADMIN");
    	//Role role = roleService.getByName(RoleName.ROLE_ADMIN);
    	Set<Role> roles = user.getRoles();
    	return roles.contains(role);//user.getRoles().contains(role);
    }
    
    
}
