package com.youLoveLife.web;

import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.repository.AppUserRepository;
import com.youLoveLife.repository.AppUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserRestController {
    @Autowired
    @Qualifier("appUserRepository")
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserRepositoryImpl appUserRepositoryImpl;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<AppUser> users() {
        return appUserRepository.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)//headers = ("content-type=multipart/form-data"),
    public ResponseEntity<AppUser> userById(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);

        if (appUser == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(appUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/searchUsersBySurname/{surname}", method = RequestMethod.GET)
    public ResponseEntity getUsersBySurname(@PathVariable String surname) {
        List<AppUser> list = appUserRepositoryImpl.getUsersBySurname(surname);

        if(list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        } else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
            throw new RuntimeException("You cannot delete your account");
        } else {
            appUserRepository.delete(appUser);
            return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        return new ResponseEntity<AppUser>(appUserRepository.save(appUser), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public AppUser updateUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null
                && appUserRepository.findOneByUsername(appUser.getUsername()).getId() != appUser.getId()) {
            throw new RuntimeException("Username already exist");
        }
        return appUserRepository.save(appUser);
    }

}
