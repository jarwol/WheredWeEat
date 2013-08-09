package com.jarwol.wheredweeat.models;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Visit extends BaseModel {
	@ForeignCollectionField
	private ForeignCollection<Item> items;

	@ForeignCollectionField
	private ForeignCollection<Friend> friends;

	@DatabaseField
	private String name;

	@DatabaseField
	private Date date;

	@DatabaseField
	private String comments;

	@DatabaseField
	private float rating;

	public Visit() {
	}

	public ForeignCollection<Item> getItems() {
		return this.items;
	}

	public ForeignCollection<Friend> getFriends() {
		return this.friends;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getFriendString() {
		StringBuilder sb = new StringBuilder();
		if (this.friends != null && this.friends.size() > 0) {
			for (Friend f : this.friends) {
				sb.append(f).append(", ");
			}
			sb.delete(sb.length() - 2, sb.length());
		}
		return sb.toString();
	}
}
