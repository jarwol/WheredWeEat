package com.jarwol.wheredweeat.models;

import com.j256.ormlite.field.DatabaseField;

public abstract class BaseModel {
	@DatabaseField(generatedId = true)
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
