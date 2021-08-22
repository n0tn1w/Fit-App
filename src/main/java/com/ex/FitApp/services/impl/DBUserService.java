package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBUserService implements UserDetailsService {

  private final UserRepository userRepository;

  public DBUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.ex.FitApp.models.entities.UserEntity userEntity = userRepository.
        findByUsername(username).
        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " was not found!"));

    return mapToUserDetails(userEntity);
  }

  private UserDetails mapToUserDetails(UserEntity userEntity) {
    List<GrantedAuthority> authorities =
        userEntity.
            getRoles().
            stream().
            map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
            collect(Collectors.toList());

    return new User(
        userEntity.getUsername(),
        userEntity.getPassword(),
        authorities
    );
  }
}
