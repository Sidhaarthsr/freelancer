package com.mainproject.talentbridge.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "user_name"),
    @UniqueConstraint(columnNames = "oauth_id")
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "oauth_id", length = 64, nullable = false, updatable = false)
  private String oauthId;

  @Column(name = "user_name", length = 16, nullable = false, updatable = false)
  private String username;

  @Column(name = "first_name", length = 32, nullable = true, updatable = true)
  private String firstName;

  @Column(name = "last_name", length = 32, nullable = true, updatable = true)
  private String lastName;

  @Column(name = "email", length = 64, nullable = false, updatable = false)
  private String email;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", updatable = false, nullable = false)
  private Role role;
}
