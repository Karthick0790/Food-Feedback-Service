package com.foodfeed;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity //FeedbackCollection table
public class FeedbackCollection {
	
	@Id
	private String date;
	
	@Column
	@OneToMany(mappedBy="feedbackCollection", cascade = CascadeType.ALL)
	private Set<Feedback> feedbacks;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	

}
