package com.mainproject.talentbridge.service_requests.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mainproject.talentbridge.service_requests.model.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    default ServiceRequest update(final ServiceRequest serviceRequest) {
        if (serviceRequest.getId() == null) {
            throw new IllegalArgumentException("Entity ID cannot be null");
        }
        return save(serviceRequest);
    }

    List<ServiceRequest> findByClientId(final long clientId);

    List<ServiceRequest> findByAssignee(final long assignee);

    @Query("SELECT DISTINCT sr FROM ServiceRequest sr " +
            "LEFT JOIN FETCH sr.skills s " +
            "WHERE LOWER(sr.name) LIKE LOWER(concat('%', :searchTerm, '%')) " +
            "OR LOWER(sr.description) LIKE LOWER(concat('%', :searchTerm, '%')) " +
            "OR EXISTS (SELECT skill FROM sr.skills skill WHERE LOWER(skill.name) LIKE LOWER(concat('%', :searchTerm, '%')))")
    List<ServiceRequest> searchByField(@Param("searchTerm") String searchTerm);

}