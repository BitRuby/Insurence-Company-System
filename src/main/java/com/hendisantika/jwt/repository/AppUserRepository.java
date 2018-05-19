package com.hendisantika.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hendisantika.jwt.domain.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}
