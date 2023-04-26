package com.mainproject.talentbridge.skills.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mainproject.talentbridge.skills.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Stream<Skill> findByNameContainingIgnoreCase(String name);
}
