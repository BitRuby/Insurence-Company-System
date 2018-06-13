package com.youLoveLife.repository;

import com.youLoveLife.domain.Contribution.HealthContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthContributionRepository extends JpaRepository<HealthContribution, Long> {

}
