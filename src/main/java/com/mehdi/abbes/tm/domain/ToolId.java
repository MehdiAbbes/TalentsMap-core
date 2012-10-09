package com.mehdi.abbes.tm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Configurable;

@Embeddable
@Configurable
public class ToolId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "tool_id")
	private Long toolId;

	private ConceptId conceptId;

	private ToolId() {
		super();
	}

	public ToolId(Long toolId, ConceptId conceptId) {
		super();
		this.toolId = toolId;
		this.conceptId = conceptId;
	}

	public Long getToolId() {
		return toolId;
	}

	public ConceptId getConceptId() {
		return conceptId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((conceptId == null) ? 0 : conceptId.hashCode());
		result = (prime * result) + ((toolId == null) ? 0 : toolId.hashCode());
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
		ToolId other = (ToolId) obj;
		if (conceptId == null) {
			if (other.conceptId != null) {
				return false;
			}
		} else if (!conceptId.equals(other.conceptId)) {
			return false;
		}
		if (toolId == null) {
			if (other.toolId != null) {
				return false;
			}
		} else if (!toolId.equals(other.toolId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ToolId [toolId=" + toolId + ", conceptId=" + conceptId + "]";
	}

}
