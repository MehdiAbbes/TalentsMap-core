package com.mehdi.abbes.tm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Embeddable
@Configurable
public final class ConceptId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cpt_id")
	private Long conceptId;

	private Long categoryId;

	private ConceptId() {
		super();
	}

	public ConceptId(Long conceptId, Long categoryId) {
		super();
		this.conceptId = conceptId;
		this.categoryId = categoryId;
	}

	public Long getConceptId() {
		return conceptId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ConceptId)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		ConceptId rhs = (ConceptId) obj;
		return new EqualsBuilder().append(categoryId, rhs.categoryId)
				.append(conceptId, rhs.conceptId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryId).append(conceptId)
				.toHashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}