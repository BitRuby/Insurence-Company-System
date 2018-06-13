package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.LaborFundContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborFundContributionRepository extends JpaRepository<LaborFundContribution, Long> {
}
