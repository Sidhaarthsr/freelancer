package com.mainproject.talentbridge.user.service;

import com.mainproject.talentbridge.user.dto.UserDTO;
import com.mainproject.talentbridge.user.model.User;

public interface UserService {

    boolean isExistingUser(final String username);

    UserDTO getUserByOauthId(final String oauthId);

    UserDTO createUser(final User user);

    UserDTO updateUser(final User user);

    UserDTO getUserById(final long userId);

    UserDTO getUserByEmail(final String email);

    void deleteById(final long userId);
}
