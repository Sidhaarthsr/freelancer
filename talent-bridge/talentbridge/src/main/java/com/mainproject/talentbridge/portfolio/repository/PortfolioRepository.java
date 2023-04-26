package com.mainproject.talentbridge.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mainproject.talentbridge.portfolio.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    default Portfolio update(final Portfolio portfolio) {
        if (portfolio.getId() == null) {
            throw new IllegalArgumentException("Entity ID cannot be null");
        }
        return save(portfolio);
    }

    @Query("SELECT p FROM Portfolio p LEFT JOIN FETCH p.skills WHERE p.id = :id")
    Optional<Portfolio> findByIdWithSkills(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Portfolio p "
            + "LEFT JOIN FETCH p.skills s "
            + "WHERE LOWER(p.title) LIKE LOWER(concat('%', :searchTerm, '%')) "
            + "OR LOWER(p.description) LIKE LOWER(concat('%', :searchTerm, '%')) "
            + "OR LOWER(p.sample) LIKE LOWER(concat('%', :searchTerm, '%')) "
            + "OR LOWER(p.type) LIKE LOWER(concat('%', :searchTerm, '%')) "
            + "OR EXISTS (SELECT skill FROM p.skills skill WHERE LOWER(skill.name) LIKE LOWER(concat('%', :searchTerm, '%')))")
    List<Portfolio> searchByField(@Param("searchTerm") String searchTerm);

    List<Portfolio> findByFreelancer(final long freelancer);

}
