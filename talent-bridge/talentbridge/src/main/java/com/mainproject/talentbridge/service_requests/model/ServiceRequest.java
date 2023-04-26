package com.mainproject.talentbridge.service_requests.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.mainproject.talentbridge.skills.model.Skill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    @PreUpdate
    public void setStatusAndStartTime() {
        if (status == Status.ASSIGNED) {
            this.projectStartTime = LocalDateTime.now();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clientId;

    @Nullable
    private Long assignee;

    @NotNull
    @Size(min = 1, max = 64)
    private String name;

    @NotNull
    @Size(min = 1, max = 1024)
    private String description;

    @Size(max = 5)
    private Set<String> files;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(255) default 'INITIATED'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(255) default 'FIXED_PRICE'")
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Positive
    private double minBudget;

    @Positive
    private double maxBudget;

    @Size(max = 5)
    private Set<Long> sharedRequests;

    @OneToMany(mappedBy = "serviceRequest", cascade = CascadeType.ALL)
    private Set<Bid> bids;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "service_request_skill", joinColumns = @JoinColumn(name = "service_request_id", referencedColumnName = "id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false, updatable = false))
    private Set<Skill> skills;

    @Nullable
    private LocalDateTime projectStartTime;

    @Nullable
    private Long timeLimit;
}
