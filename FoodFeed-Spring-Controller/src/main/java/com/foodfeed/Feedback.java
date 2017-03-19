package com.foodfeed;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

@DynamicInsert(true)
@Entity //Feedback table
public class Feedback {
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//private Integer id;
	
	@Id
	private String foodName;
	
	@Column(columnDefinition = "int default 0")
	private Integer good;
	
	@Column(columnDefinition = "int default 0")
	private Integer bad;
	
	@ManyToOne
	@JoinColumn(name="date", nullable=false)
	private FeedbackCollection feedbackCollection;
	
	@Override
	public boolean equals (Object o)
	{
		if(o == this) return true;
		if(! (o instanceof Feedback)) return false;
		
		Feedback f = (Feedback) o;
		
		return f.foodName.equals(foodName);
	}
	
	@Override
	public int hashCode() {
		return foodName.hashCode();
	}
	
	/*public Integer getId() {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}*/
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public Integer getGood() {
		return good;
	}
	
	public void setGood(Integer good) {
		this.good = good;
	}
	
	public Integer getBad() {
		return bad;
	}
	
	public void setBad(Integer bad) {
		this.bad = bad;
	}
	
	@JsonIgnore
	public FeedbackCollection getFeedbackCollection() {
		return feedbackCollection;
	}
	
	public void setFeedbackCollection(FeedbackCollection feedbackCollection) {
		this.feedbackCollection = feedbackCollection;
	}
}
