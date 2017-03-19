package com.foodfeed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity //FoodMenu table
public class FoodMenu {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@Column
	private String foodName;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
