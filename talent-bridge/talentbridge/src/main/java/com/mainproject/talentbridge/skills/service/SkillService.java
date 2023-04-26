package com.mainproject.talentbridge.skills.service;


import java.util.List;

import jakarta.transaction.Transactional;

public interface SkillService {
    
    List<String> getAvailableSkills();

    List<String> searchSkills(final String name);
}
