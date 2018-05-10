package com.schen.projectdevice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "project_name", nullable = false)
	private String name;

	@Column(name = "device_name", nullable = false)
	private String deviceName;

	@Column(name = "contact_name", nullable = false)
	private String contactName;

	@Column(name = "contact_email", nullable = false)
	private String contactEmail;

	@OneToMany(mappedBy = "project", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH, CascadeType.REMOVE })
	private Set<Version> versions;

	public Project(String name, String deviceName, String contactName, String contactEmail) {
		this.name = name;
		this.deviceName = deviceName;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
	}
	
	public Project() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Set<Version> getVersions() {
		return versions;
	}

	public void setVersions(Set<Version> versions) {
		this.versions = versions;
	}

	public void add(Version version) {
		if (versions == null) {
			versions = new HashSet<>();
		}
		versions.add(version);
		version.setProject(this);
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
