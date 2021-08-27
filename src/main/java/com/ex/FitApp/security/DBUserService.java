package com.ex.FitApp.security;

import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
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

  private CustomUserDetails mapToUserDetails(UserEntity userEntity) {

    Collection<GrantedAuthority> grantedAuthoritySet = userEntity.
            getAuthorities().
            stream().
            map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toSet());

    return new CustomUserDetails(
        userEntity.getUsername(),
        userEntity.getPassword(),
            grantedAuthoritySet
    );
  }
}
