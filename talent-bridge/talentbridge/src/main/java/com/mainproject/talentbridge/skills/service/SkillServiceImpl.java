package com.mainproject.talentbridge.skills.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.talentbridge.skills.model.Skill;
import com.mainproject.talentbridge.skills.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<String> getAvailableSkills() {
        return skillRepository.findAll().stream()
                .map(Skill::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> searchSkills(String name) {
        return skillRepository.findByNameContainingIgnoreCase(name)
                .map(Skill::getName)
                .collect(Collectors.toList());
    }

}
