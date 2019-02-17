package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.PasswordUpdateRequest;
import com.hcmut.advancedprogramming.flidi.dto.request.UserUpdateRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.UserIdentityAvailability;
import com.hcmut.advancedprogramming.flidi.dto.response.UserProfile;
import com.hcmut.advancedprogramming.flidi.dto.response.UserSummary;
import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.exception.UpdateIdMismatchException;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
                StringUtils.join(currentUser.getFirstName(), " ", currentUser.getLastName()));
        return userSummary;
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getUsername(),
                String.join(" ", user.getFirstName(), user.getLastName()), user.getFirstName(), user.getLastName(), user.getCreatedAt());

        return userProfile;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id,
                                        @CurrentUser UserPrincipal currentUser) {
        if (userUpdateRequest.getId() != id) {
            throw new UpdateIdMismatchException();
        }

        if (!StringUtils.equals(userUpdateRequest.getEmail(), currentUser.getEmail())) {
            throw new BadRequestException("Can not update information of other!");
        }

        userService.update(userUpdateRequest);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "User updated successfully"));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                            @ PathVariable Long id, @CurrentUser UserPrincipal currentUser) {

        if (passwordUpdateRequest.getId() != id) {
            throw new UpdateIdMismatchException();
        }

        if (!StringUtils.equals(passwordUpdateRequest.getUsername(), currentUser.getUsername())) {
            throw new BadRequestException("Can not change password of other!");
        }

        userService.changePassword(passwordUpdateRequest);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Password changed successfully"));
    }
}
