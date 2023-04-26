package com.mainproject.talentbridge.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.MediaType;
import com.mainproject.talentbridge.communication.EmailService;
import com.mainproject.talentbridge.user.dto.UserDTO;
import com.mainproject.talentbridge.user.model.User;
import com.mainproject.talentbridge.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping()
  public UserDTO create(@RequestBody User user) {
    log.info("Creating user with email {}", user.getEmail());
    return userService.createUser(user);
  }

  @PutMapping()
  public UserDTO update(@RequestBody User user) {
    log.info("Updating user with id {}", user.getId());
    return userService.updateUser(user);
  }

  @GetMapping("/{id}")
  public UserDTO getUser(@PathVariable("id") final Long userId) {
    log.info("Fetching user with Id {}", userId);
    return userService.getUserById(userId);
  }

  @GetMapping("/email/{email}")
  public UserDTO getUserByEmail(@PathVariable("email") final String email) {
    log.info("Fetching user with email {}", email);
    return userService.getUserByEmail(email);
  }

  @GetMapping("/username/exists/{username}")
  public boolean isExistingUser(@PathVariable("username") final String username) {
    log.info("Verifying if the username {} exists already ", username);
    return userService.isExistingUser(username);
  }

  @GetMapping("/oauth-id/{oauthId}")
  public UserDTO isExistingOAuthUser(@PathVariable("oauthId") final String oauthId) {
    log.info("Verifying if the username {} exists already ", oauthId);
    return userService.getUserByOauthId(oauthId);
  }

  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean deleteUser(@PathVariable("id") final Long userId) {
    log.info("Deleting portfolio with id {}", userId);
    try {
      userService.deleteById(userId);
      return true;
    } catch (final Exception e) {
      log.error("Exception while deleting portfolio", e);
      throw new ResponseStatusException(HttpStatusCode.valueOf(500),
          "Exception while deleting portfolio");
    }
  }

  @PostMapping("/invite")
  public boolean inviteViaEmail(@RequestParam("email") String userEmail) throws UnsupportedEncodingException {
    log.info(userEmail);
    final String decodedEmail = URLDecoder.decode(userEmail, "UTF-8");
    String body = "Dear " + decodedEmail + " you have been invited to TalentBridge application."
        + " Please visit to signup and use the exclusive features.";
    return EmailService.sendMail(decodedEmail, "Invitation for TalentBridge", body);
  }
}
