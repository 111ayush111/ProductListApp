package com.bitsandbytes.product.service;

import com.bitsandbytes.product.entity.User;
import com.bitsandbytes.product.repository.UserRepository;
import com.bitsandbytes.product.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    /*create*/
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user   =userRepository.findByUsername(username);
       if(user.isEmpty()) {throw new UsernameNotFoundException("username  not found");}
       return  new UserPrincipal(user.get());
    }
}
