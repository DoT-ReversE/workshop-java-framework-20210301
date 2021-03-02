package com.example.kbtg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse getInfo(int id) {
        Optional<MyUser> myUserOptional = userRepository.findById(id);
        if(myUserOptional.isPresent()) {
            // Success
            MyUser myUser = myUserOptional.get();
            UserResponse userResponse = new UserResponse(myUser.getId(), myUser.getName(), myUser.getAge());
            return userResponse;
        }
        // Fail
        throw new UserNotFoundException("User not found id="+ id);
    }
}
