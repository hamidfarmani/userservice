package com.hamid.userservice.service;

import com.hamid.userservice.domain.Role;
import com.hamid.userservice.domain.User;
import com.hamid.userservice.repository.RoleRepository;
import com.hamid.userservice.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {
  private final Logger LOGGER = LogManager.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if(user == null){
      LOGGER.error("User not found in the database");
      throw new UsernameNotFoundException("User not found in the database");
    }
    LOGGER.info("User found in the database");
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

  public User saveUser(User user){
    LOGGER.info("Saving new user {} to the database", user.getUsername());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Role saveRole(Role role){
    LOGGER.info("Saving new role {} to the database", role.getName());
    return roleRepository.save(role);
  }

  public void addRoleToUser(String username, String roleName){
    LOGGER.info("Adding role {} to the user {}", roleName, username);
    User user = userRepository.findByUsername(username);
    Role role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
  }

  public User getUser(String username){
    LOGGER.info("Getting user {} from the database", username);
    return userRepository.findByUsername(username);
  }

  public List<User> getUsers(){
    LOGGER.info("Getting all users from the database");
    return userRepository.findAll();
  }

}
