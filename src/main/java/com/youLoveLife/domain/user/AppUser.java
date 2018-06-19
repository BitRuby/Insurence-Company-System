package com.youLoveLife.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.youLoveLife.domain.Company;
import com.youLoveLife.domain.Contribution.*;
import com.youLoveLife.domain.Contribution.Calculators.HealthContributionCalculator;
import com.youLoveLife.domain.Contribution.Calculators.LaborFundCalculator;
import com.youLoveLife.domain.Contribution.Calculators.PensionCalculator;
import com.youLoveLife.domain.Contribution.Calculators.SocialContributionCalculator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

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
	private Date dateOfBirth;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("appUser")
	private List<Job> jobsList;

	@ManyToOne
	@JsonIgnoreProperties(value = {"employees", "owner"})
	private Company currentCompany;

	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "owner")
	@JsonIgnoreProperties(value = {"employees", "owner"})
	private Company ownCompany;

	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "appUser")
	@JsonIgnoreProperties("appUser")
    private SocialContribution socialContribution;
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "appUser")
	@JsonIgnoreProperties("appUser")
    private HealthContribution healthContribution;
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "appUser")
	@JsonIgnoreProperties("appUser")
    private LaborFundContribution laborFundContribution;

	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ElementCollection
	private List<String> roles = new ArrayList<>();

	public AppUser() {

	}

    public AppUser(String name, String surname, Address address, Date dateOfBirth, String username, String password, List<String> roles) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.socialContribution = new SocialContribution(this);
        this.healthContribution = new HealthContribution(this);
        this.laborFundContribution = new LaborFundContribution(this);
        this.jobsList = new ArrayList<>();
    }

    public void updateData() {
        HealthContributionCalculator healthContributionCalculator = new HealthContributionCalculator(getLastJob());
		this.healthContribution.setAmount(healthContributionCalculator.calculateContribution());
        if(this.healthContribution.getAmount() <= 0)
            this.healthContribution.setInsured(false);
        else
            this.healthContribution.setInsured(true);

        if(jobsList != null) {
			Job firstJob = jobsList.get(0);
			Job lastJob = this.getLastJob();
			Date startDate = null, toDate = null;

			if (firstJob != null)
				startDate = firstJob.getFromDate();
			if (lastJob != null)
				toDate = lastJob.getToDate();

			this.healthContribution.setFromDate(startDate);
			this.healthContribution.setToDate(toDate);
			this.laborFundContribution.setFromDate(startDate);
			this.laborFundContribution.setToDate(toDate);
			this.socialContribution.setFromDate(startDate);
		}

        LaborFundCalculator laborFundCalculator = new LaborFundCalculator(this.jobsList, this.laborFundContribution.getFromDate());

        if(laborFundCalculator.checkIfActuallyWork())
			laborFundContribution.setActive(true);
        else
        	laborFundContribution.setActive(false);

        laborFundContribution.setAmount(laborFundCalculator.calculateBenefit());


        SocialContributionCalculator socialContributionCalculator = new SocialContributionCalculator(this.jobsList);
        socialContribution.setAmount(socialContributionCalculator.calculateContribution());

        PensionCalculator pensionCalculator = new PensionCalculator(socialContributionCalculator, this.dateOfBirth);
        this.socialContribution.getPension().setFromDate(pensionCalculator.calculateFrom());
        this.socialContribution.getPension().setAmount(pensionCalculator.calculatePension());
    }

    public void addNewJob(Job job) {
		jobsList.add(job);
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Job> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Job> jobsList) {
        this.jobsList = jobsList;
    }

    @JsonIgnore
    public Job getLastJob() {
	    if(jobsList != null) {
			Iterator<Job> it = jobsList.iterator();
			Job lastJob = null;

			while (it.hasNext())
				lastJob = it.next();

			return lastJob;
		}
		return null;
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

    public SocialContribution getSocialContribution() {
        return socialContribution;
    }

    public void setSocialContribution(SocialContribution socialContribution) {
        this.socialContribution = socialContribution;
    }

    public HealthContribution getHealthContribution() {
        return healthContribution;
    }

    public void setHealthContribution(HealthContribution healthContribution) {
        this.healthContribution = healthContribution;
    }

    public LaborFundContribution getLaborFundContribution() {
        return laborFundContribution;
    }

    public void setLaborFundContribution(LaborFundContribution laborFundContribution) {
        this.laborFundContribution = laborFundContribution;
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

	public Company getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}

	public Company getOwnCompany() {
		return ownCompany;
	}

	public void setOwnCompany(Company ownCompany) {
		this.ownCompany = ownCompany;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", address=" + address +
				", dateOfBirth=" + dateOfBirth +
				", jobsList=" + jobsList +
				//", currentCompany=" + currentCompany +
				//", ownCompany=" + ownCompany +
				", socialContribution=" + socialContribution +
				", healthContribution=" + healthContribution +
				", laborFundContribution=" + laborFundContribution +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", roles=" + roles +
				'}';
	}
}
