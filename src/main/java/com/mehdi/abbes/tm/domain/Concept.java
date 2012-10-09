package com.mehdi.abbes.tm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Concept {

	@EmbeddedId
	private ConceptId id;

	@NotNull
	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "cpt_cat_id", referencedColumnName = "cat_id")
	private Category category;

	@NotNull
	@Size(max = 25)
	@Column(name = "cpt_label", unique = true)
	private String label;

	@NotNull
	@Size(max = 250)
	@Column(name = "cpt_desc")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "concept")
	private final Set<Tool> tools = new HashSet<Tool>();

	@Version
	@Column(name = "cpt_version")
	private Integer version;

	public ConceptId getId() {
		return id;
	}

	public void setId(ConceptId id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Tool> getTools() {
		return tools;
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
		Concept other = (Concept) obj;
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
		return "Concept [category=" + category + ", label=" + label
				+ ", description=" + description + ", tools=" + tools
				+ ", version=" + version + "]";
	}

	public int getScore() {
		return 1;
	}

}
