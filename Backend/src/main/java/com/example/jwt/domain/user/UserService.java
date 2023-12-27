package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.role.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService, ExtendedService<User> {
  @Override
  User findById(UUID id);

  User register(User user);
  List<User> findAll();
  UserDetails loadUserByUsername(String email);

}
