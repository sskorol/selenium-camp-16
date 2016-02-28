package com.blogspot.notes.automation.qa.core.db.entities;

import com.blogspot.notes.automation.qa.core.db.dao.DAO;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	private int id;

	@Transient
	private DAO dao;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setDAO(final DAO dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public void save() {
		if (dao != null) {
			dao.save(this);
		}
	}
}
