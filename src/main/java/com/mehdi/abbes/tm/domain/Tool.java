package com.mehdi.abbes.tm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tool {

	@EmbeddedId
	private ToolId id;

	@NotNull
	@ManyToOne
	@MapsId("conceptId")
	@JoinColumns({
			@JoinColumn(name = "tool_cpt_id", referencedColumnName = "cpt_id"),
			@JoinColumn(name = "tool_cat_id", referencedColumnName = "cpt_cat_id") })
	private Concept concept;

	@NotNull
	@Column(name = "tool_label", unique = true)
	@Size(max = 25)
	private String label;

	@NotNull
	@Size(max = 250)
	@Column(name = "tool_desc")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tool")
	private final Set<MarkedTool> markedTools = new HashSet<MarkedTool>();

	@Version
	@Column(name = "tool_version")
	private Integer version;

	public ToolId getId() {
		return id;
	}

	public void setId(ToolId id) {
		this.id = id;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
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

	public Set<MarkedTool> getMarkedTools() {
		return markedTools;
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
		Tool other = (Tool) obj;
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
		return "Tool [label=" + label + ", description=" + description
				+ ", version=" + version + "]";
	}

}
