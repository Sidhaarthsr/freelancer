package com.mainproject.talentbridge.user.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainproject.talentbridge.user.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  boolean existsByUsername(final String username);

  User findByOauthId(final String oauthId);

  Optional<User> findByEmail(String email);

  default User update(User user) {
    if (user.getId() == null) {
      logger.info("Entity ID should be present");
    }
    return save(user);
  }
}
