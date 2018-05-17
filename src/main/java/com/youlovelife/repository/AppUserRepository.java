package com.youlovelife.repository;


import com.youlovelife.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sarath Muraleedharan
 *
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findOneByUsername(String username);
    //public AppUser findOne(Long id);
}