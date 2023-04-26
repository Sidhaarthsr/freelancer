package com.mainproject.talentbridge.user.mapper;

import com.mainproject.talentbridge.portfolio.dto.PortfolioDTO;
import com.mainproject.talentbridge.portfolio.model.Portfolio;
import com.mainproject.talentbridge.skills.model.Skill;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-15T12:39:43-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class PortfolioMapperImpl implements PortfolioMapper {

    @Override
    public PortfolioDTO toPortfolioDTO(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioDTO portfolioDTO = new PortfolioDTO();

        if ( portfolio.getId() != null ) {
            portfolioDTO.setId( portfolio.getId() );
        }
        portfolioDTO.setSample( portfolio.getSample() );
        Set<Skill> set = portfolio.getSkills();
        if ( set != null ) {
            portfolioDTO.setSkills( new HashSet<Skill>( set ) );
        }

        return portfolioDTO;
    }
}
