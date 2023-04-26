package com.mainproject.talentbridge.portfolio.service;

import java.util.List;

import com.mainproject.talentbridge.portfolio.model.Portfolio;

public interface PortfolioService {
    Portfolio createPortfolio(final Portfolio portfolio);

    Portfolio updatePortfolio(final Portfolio portfolio);

    Portfolio getPortfolio(final long portfolioId);

    List<Portfolio> getFreelancerPortfolios(final long userId);

    List<Portfolio> getAll();

    Portfolio getPortfolioWithSkills(final long portfolioId);

    void deletePortfolio(final long portfolioId);

    List<Portfolio> search(final String searchTerm);

}
