package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.SocialContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialContributionRepository extends JpaRepository<SocialContribution, Long>{
}
