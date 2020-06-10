package by.epam.grodno.pronych.eshop.model.service;

import java.util.List;

import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.User;

public interface UserService {
	public void save(User user);
	public void update(User user);
    public void delete(int id);
    public User getById(int id);
    public User getByName(String name);
    public UserDto getByUserName(String userName);
    public List<User> getAll();
    public List<UserDto> getAllUserDebts();
    public boolean isUserAdmin(User user); 
	public void setToBlackList(UserDto userMsg);
	public void removeFromBlackList(UserDto userMsg);
	public boolean isUserInBlackList(UserDto userMsg);
    public void setBeanOrderService(OrderService orderService);
}
