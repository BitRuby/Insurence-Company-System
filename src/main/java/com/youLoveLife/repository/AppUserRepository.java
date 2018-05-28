package com.youLoveLife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youLoveLife.domain.AppUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}
