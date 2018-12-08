package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.response.UserSummary;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
                StringUtils.join(currentUser.getFirstName(), " ", currentUser.getLastName()));
        return userSummary;
    }


}
