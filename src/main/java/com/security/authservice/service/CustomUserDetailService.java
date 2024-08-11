package com.security.authservice.service;

import com.security.authservice.dto.UserDto;
import com.security.authservice.dto.UserRoleDto;
import com.security.authservice.entities.User;
import com.security.authservice.entities.UserRole;
import com.security.authservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    public User registUser(UserDto userDto) {
        LOGGER.info("Registering user : " + userDto.toString());
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(authService.generateToken(userDto.getUserPassword()));
        List<UserRole> userRoleList = new ArrayList<>();
        for(UserRoleDto userRoleDto : userDto.getUserRoleDtoList()){
            UserRole userRole = new UserRole();
            userRole.setRoleName(userRoleDto.getRoleName());
            userRoleList.add(userRole);
        }
        user.setRoles(userRoleList);
        return userRepository.save(user);
    }
}
