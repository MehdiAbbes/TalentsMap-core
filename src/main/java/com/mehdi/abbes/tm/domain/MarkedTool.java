package com.mehdi.abbes.tm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class MarkedTool {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_tool_id")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "profile_tool_profile_id", referencedColumnName = "profile_id")
	private Profile profile;

	@NotNull
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "profile_tool_tool_id", referencedColumnName = "tool_id"),
			@JoinColumn(name = "profile_tool_cpt_id", referencedColumnName = "tool_cpt_id"),
			@JoinColumn(name = "profile_tool_cat_id", referencedColumnName = "tool_cat_id") })
	private Tool tool;

	@NotNull
	@Column(name = "profile_tool_score")
	private Integer score;

	@Version
	@Column(name = "profile_tool_version")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MarkedTool other = (MarkedTool) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MarkedTool [profile=" + profile + ", tool=" + tool + ", score="
				+ score + ", version=" + version + "]";
	}

}
