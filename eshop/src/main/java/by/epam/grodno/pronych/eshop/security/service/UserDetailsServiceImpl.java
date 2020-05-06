package by.epam.grodno.pronych.eshop.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.grodno.pronych.eshop.dao.UserDao;
import by.epam.grodno.pronych.eshop.entity.User;
 
@Service //("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserDao userDao;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
      
        User user = userDao.getByUserName(userName);
        if (user == null) {
        	throw new UsernameNotFoundException("User Not Found with -> username or email : " + userName);
        }
 
        return UserPrinciple.build(user);
    }
    
    
    
    
}