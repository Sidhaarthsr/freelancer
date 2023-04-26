package com.mainproject.talentbridge.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.talentbridge.portfolio.model.Portfolio;
import com.mainproject.talentbridge.portfolio.repository.PortfolioRepository;

import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public Portfolio createPortfolio(final Portfolio portfolio) {
        log.info("Creating portfolio in repository");
        return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio updatePortfolio(Portfolio portfolio) {
        log.info("Updating portfolio in repository");
        return portfolioRepository.update(portfolio);
    }

    @Override
    public Portfolio getPortfolio(final long portfolioId) {
        log.info("Fetching from repository");
        return portfolioRepository.findById(portfolioId).orElse(null);
    }

    @Override
    public void deletePortfolio(long portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }

    @Override
    @Transactional
    public Portfolio getPortfolioWithSkills(long portfolioId) {
        log.info("Fetching from repository");
        return portfolioRepository.findByIdWithSkills(portfolioId).orElse(null);
    }

    @Override
    public List<Portfolio> getAll() {
        return portfolioRepository.findAll();
    }

    @Override
    public List<Portfolio> search(String searchTerm) {
        return portfolioRepository.searchByField(searchTerm);
    }

    @Override
    public List<Portfolio> getFreelancerPortfolios(final long freelancerId) {
        return portfolioRepository.findByFreelancer(freelancerId);
    }
}
