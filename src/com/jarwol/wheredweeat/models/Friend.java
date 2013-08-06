package com.jarwol.wheredweeat.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Friend extends BaseModel {
	@DatabaseField
	private String name;

	@DatabaseField(foreign = true)
	private Visit visit;

	public Friend() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
}
