package com.youLoveLife.repository;

import com.youLoveLife.domain.applications.RentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentApplicationRepository extends JpaRepository<RentApplication, Long>{
}
