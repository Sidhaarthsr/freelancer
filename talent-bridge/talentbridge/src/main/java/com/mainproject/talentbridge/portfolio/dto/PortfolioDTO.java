package com.mainproject.talentbridge.portfolio.dto;

import java.util.Set;

import com.mainproject.talentbridge.skills.model.Skill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDTO {
    private long id;
    private String sample;
    private Set<Skill> skills;
}
