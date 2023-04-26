package com.mainproject.talentbridge.user.mapper;

import org.mapstruct.Mapper;

import com.mainproject.talentbridge.portfolio.dto.PortfolioDTO;
import com.mainproject.talentbridge.portfolio.model.Portfolio;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {
    PortfolioDTO toPortfolioDTO(final Portfolio portfolio);
}
