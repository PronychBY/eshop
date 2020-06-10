package by.epam.grodno.pronych.eshop.model.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.grodno.pronych.eshop.model.dao.UserDao;
import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.Role;
import by.epam.grodno.pronych.eshop.model.entity.User;
import by.epam.grodno.pronych.eshop.model.service.OrderService;
import by.epam.grodno.pronych.eshop.model.service.RoleService;
import by.epam.grodno.pronych.eshop.model.service.UserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private final UserDao dao;
	private final RoleService roleService;
	private OrderService orderService;

	@Autowired
	public UserServiceImpl(UserDao dao, RoleService roleService) {
		this.dao = dao;
		this.roleService = roleService;
		// this.orderService = orderService;
	}

	public void setBeanOrderService(OrderService orderService) {
		this.orderService = orderService;
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

	public UserDto getByUserName(String userName) {
		User user = dao.getByUserName(userName);
		UserDto userDto = new UserDto(user);
		userDto.setAdmin(isUserAdmin(user));
		return userDto;
	}

	public List<User> getAll() {
		return dao.getAll();
	}

	public List<UserDto> getAllUserDebts() {
		return orderService.getAllUserDebts();
	}

	public boolean isUserAdmin(User user) {
		// RoleName roleName = RoleName.ROLE_ADMIN;//.valueOf(enumType, name)
		Role role = roleService.getByName("ROLE_ADMIN");
		// Role role = roleService.getByName(RoleName.ROLE_ADMIN);
		Set<Role> roles = user.getRoles();
		return roles.contains(role);// user.getRoles().contains(role);
	}

	@Transactional
	public void setToBlackList(UserDto userMsg) {
		User user = getById((int) userMsg.getId());
		user.setInBlackList(true);
		update(user);
	}

	@Transactional
	public void removeFromBlackList(UserDto userMsg) {
		User user = getById((int) userMsg.getId());
		user.setInBlackList(false);
		update(user);

	}

	public boolean isUserInBlackList(UserDto userMsg) {
		User user = getById((int) userMsg.getId());
		return user.isInBlackList();
	}

}
