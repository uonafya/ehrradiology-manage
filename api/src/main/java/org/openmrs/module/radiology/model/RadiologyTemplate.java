package org.openmrs.module.radiology.model;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.Concept;

public class RadiologyTemplate {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String content;
	private String description;
	private Set<Concept> tests = new HashSet<Concept>();

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Concept> getTests() {
		return tests;
	}

	public void setTests(Set<Concept> tests) {
		this.tests = tests;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadiologyTemplate other = (RadiologyTemplate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RadiologyTemplate [id=" + id + "]";
	}
}
