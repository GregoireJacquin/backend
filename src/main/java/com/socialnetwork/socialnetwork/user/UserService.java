package com.socialnetwork.socialnetwork.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }
}
