package com.skilldistillery.journeyjournals.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Blog {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	@Column(name="trip_start")
	private LocalDate tripStart;

	@Column(name="trip_end")
	private LocalDate tripEnd;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name="place_id")
	private Place place;
	
	@ManyToMany
	@JoinTable(name = "blog_category", joinColumns = @JoinColumn(name="blog_id"), inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories;
	
	
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private boolean enabled;

	public Blog() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTripStart() {
		return tripStart;
	}

	public void setTripStart(LocalDate tripStart) {
		this.tripStart = tripStart;
	}

	public LocalDate getTripEnd() {
		return tripEnd;
	}

	public void setTripEnd(LocalDate tripEnd) {
		this.tripEnd = tripEnd;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", description=" + description + ", tripStart=" + tripStart
				+ ", tripEnd=" + tripEnd + ", imageUrl=" + imageUrl + ", updatedAt=" + updatedAt + ", createdAt="
				+ createdAt + ", enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		return id == other.id;
	}
	
	
	
	
}
