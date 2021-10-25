package org.sid.service;

import java.util.Optional;

import org.sid.entity.User;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public Optional<User> findById(Long id);

    public User findByEmail(String email);

    public User save(UserRegistrationDto registration);
    
    public User saveAdmin(UserRegistrationDto registration);

    public void updatePassword(String password, Long userId);

    public void deleteUg(Long id);

    public void update(User user,Long id);

    public void autologin(String username, String password);

    public String findLoggedInUsername();

}
