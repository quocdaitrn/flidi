package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.PasswordUpdateRequest;
import com.hcmut.advancedprogramming.flidi.dto.request.UserUpdateRequest;
import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userUpdateRequest.getId()));

        user.setUsername(userUpdateRequest.getUsername());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());

        return userRepository.save(user);
    }

    @Override
    public void changePassword(PasswordUpdateRequest passwordUpdateRequest) {
        User user = userRepository.findById(passwordUpdateRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", passwordUpdateRequest.getId()));

        if (!passwordEncoder.matches(passwordUpdateRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong password");
        }

        String password = passwordEncoder.encode(passwordUpdateRequest.getNewPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}
