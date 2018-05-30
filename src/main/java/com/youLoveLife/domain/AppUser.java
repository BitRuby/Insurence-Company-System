package com.youLoveLife.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Model class for application user
 *
 */
@Entity
public class AppUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name, surname;
	@Embedded
	private Address address;

	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ElementCollection
	private List<String> roles = new ArrayList<>();

	public AppUser() {

	}

	public AppUser(String name, String surname, Address address, String username, String password, List<String> roles) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address addressID) {
		this.address = addressID;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AppUser appUser = (AppUser) o;
		return Objects.equals(name, appUser.name) &&
				Objects.equals(surname, appUser.surname) &&
				Objects.equals(address, appUser.address) &&
				Objects.equals(username, appUser.username) &&
				Objects.equals(password, appUser.password);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, surname, address, username, password, roles);
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", address=" + address +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", roles=" + roles +
				'}';
	}
}
