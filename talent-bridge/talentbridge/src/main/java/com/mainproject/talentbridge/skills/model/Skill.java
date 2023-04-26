package com.mainproject.talentbridge.skills.model;

import java.util.Set;

import org.hibernate.annotations.SQLInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mainproject.talentbridge.portfolio.model.Portfolio;
import com.mainproject.talentbridge.service_requests.model.ServiceRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "skill", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@SQLInsert(sql = "insert into skill (name) values (?) on duplicate key update name=VALUES(name), id = LAST_INSERT_ID(id)")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "portfolios", "serviceRequests" })
@JsonIgnoreProperties({ "portfolios", "serviceRequests" })
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("skills")
    private Set<ServiceRequest> serviceRequests;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("skills")
    private Set<Portfolio> portfolios;

    @Column(name = "name", nullable = false, length = 32)
    private String name;
}