package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.PasswordUpdateRequest;
import com.hcmut.advancedprogramming.flidi.dto.request.UserUpdateRequest;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User update(UserUpdateRequest userUpdateRequest);

    void changePassword(PasswordUpdateRequest passwordUpdateRequest);
}
