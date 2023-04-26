package com.mainproject.talentbridge.portfolio.model;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mainproject.talentbridge.skills.model.Skill;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio", indexes = {
    @Index(name = "idx_freelancer", columnList = "freelancer")
})
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "freelancer", nullable = false, updatable = false)
    private Long freelancer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentType type;

    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "description", length = 1024, nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "portfolio_skill", joinColumns = @JoinColumn(name = "portfolio_id", referencedColumnName = "id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false, updatable = false))
    private Set<Skill> skills;

    @Nullable
    @Size(max = 5)
    @Column(name = "files")
    private Set<String> files;

    @Nullable
    @Column(name = "sample", length = 2048, nullable = false)
    private String sample;
}
