package com.mainproject.talentbridge.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mainproject.talentbridge.portfolio.model.Portfolio;
import com.mainproject.talentbridge.portfolio.service.PortfolioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/portfolio")
@Slf4j
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("")
    public Portfolio createPortfolio(@RequestBody final Portfolio portfolio) {
        log.info("Creating portfolio {}", portfolio.getTitle());
        return portfolioService.createPortfolio(portfolio);
    }

    @PutMapping("")
    public Portfolio updatePortfolio(@RequestBody final Portfolio portfolio) {
        log.info("Updating portfolio with id {}", portfolio.getId());
        return portfolioService.updatePortfolio(portfolio);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Portfolio getPortfolio(@PathVariable("id") final Long portfolioId) {
        log.info("Fetching Portfolio with id {}", portfolioId);
        return portfolioService.getPortfolio(portfolioId);
    }

    @GetMapping(path = "/freelancer-id/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Portfolio> getFreelancerPortfolios(@PathVariable("userId") final long freelancerId) {
        log.info("Fetching portfolios for user : {}", freelancerId);
        return portfolioService.getFreelancerPortfolios(freelancerId);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePortfolio(@PathVariable("id") final Long portfolioId) {
        log.info("Deleting portfolio with id {}", portfolioId);
        try {
            portfolioService.deletePortfolio(portfolioId);
            return true;
        } catch (final Exception e) {
            log.error("Exception while deleting portfolio", e);
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Exception while deleting portfolio");
        }
    }

    @GetMapping("/search")
    public List<Portfolio> search(@RequestParam(name = "searchTerm") final String searchTerm) {
        if (searchTerm == null || "".equals(searchTerm)) {
            return portfolioService.getAll();
        } else {
            return portfolioService.search(searchTerm);
        }
    }
}
