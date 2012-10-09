package com.mehdi.abbes.tm.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id")
	private Long id;

	@NotNull
	@Size(min = 2)
	@Column(name = "profile_firstname")
	private String firstname;

	@NotNull
	@Size(min = 2)
	@Column(name = "profile_lastname")
	private String lastname;

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+")
	@Column(name = "profile_email")
	private String email;

	@Max(30L)
	@Column(name = "profile_ey")
	private int experienceYears;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "profile_hire_date")
	private Date hireDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<com.mehdi.abbes.tm.domain.Profile> subordinates = new HashSet<com.mehdi.abbes.tm.domain.Profile>();

	@ManyToOne
	@JoinColumn(name = "profile_manager", referencedColumnName = "profile_id")
	private com.mehdi.abbes.tm.domain.Profile manager;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
	// @Transient
	private Set<MarkedTool> markedTools = new HashSet<MarkedTool>();

	@Version
	@Column(name = "profile_version")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Set<Profile> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Profile> subordinates) {
		this.subordinates = subordinates;
	}

	public Profile getManager() {
		return manager;
	}

	public void setManager(Profile manager) {
		this.manager = manager;
	}

	public Set<MarkedTool> getMarkedTools() {
		return markedTools;
	}

	public void setMarkedTools(Set<MarkedTool> markedTools) {
		this.markedTools = markedTools;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
