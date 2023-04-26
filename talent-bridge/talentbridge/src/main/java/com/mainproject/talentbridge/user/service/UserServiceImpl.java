package com.mainproject.talentbridge.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.talentbridge.user.dto.UserDTO;
import com.mainproject.talentbridge.user.mapper.UserMapper;
import com.mainproject.talentbridge.user.model.User;
import com.mainproject.talentbridge.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Override
  public boolean isExistingUser(final String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public UserDTO createUser(final User user) {
    log.info("Creating user in repository : {}", user.getId());
    final User savedUser = userRepository.save(user);
    return userMapper.toUserDTO(savedUser);
  }

  @Override
  public UserDTO updateUser(final User user) {
    log.info("Updating user in repository : {}", user.getId());
    final User updatedUser = userRepository.update(user);
    return userMapper.toUserDTO(updatedUser);
  }

  @Override
  public UserDTO getUserById(final long userId) {
    log.info("Fetching from repository via id : {}", userId);
    final User user = userRepository.findById(userId).orElse(null);
    return user != null ? userMapper.toUserDTO(user) : null;
  }

  @Override
  public UserDTO getUserByEmail(final String email) {
    log.info("Fetching from repository via email : {}", email);
    final User user = userRepository.findByEmail(email).orElse(null);
    return user != null ? userMapper.toUserDTO(user) : null;
  }

  @Override
  public void deleteById(final long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserDTO getUserByOauthId(final String oauthId) {
    log.info("Fetching from repository via oauthId : {}", oauthId);
    final User existingUser = userRepository.findByOauthId(oauthId);
    return userMapper.toUserDTO(existingUser);
  }
}
