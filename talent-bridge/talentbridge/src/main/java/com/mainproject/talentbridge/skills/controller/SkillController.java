package com.mainproject.talentbridge.skills.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.mainproject.talentbridge.skills.service.SkillService;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/search")
    public List<String> getSkills(@RequestParam(name = "searchTerm") @DefaultValue("") final String searchTerm) {
        final List<String> skills;
        log.info("Searching for Skills containing : " + searchTerm);
        if (searchTerm != null && !searchTerm.equals("")) {
            skills = skillService.getAvailableSkills();
        } else {
            skills = skillService.searchSkills(searchTerm);
        }
        return skills;
    }

}
