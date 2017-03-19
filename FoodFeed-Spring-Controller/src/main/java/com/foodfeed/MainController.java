package com.foodfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.ui.Model;
import java.util.*;

@Controller
@RequestMapping(path="/feedback")
public class MainController {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private FoodMenuRepository foodMenuRepository;
	
	@PostMapping(path="/addMenu")
	public @ResponseBody String addFoodMenu (@RequestBody List<String> foodList) {
		foodMenuRepository.deleteAll();
		for (String food:foodList) {
			FoodMenu foodName = new FoodMenu();
			foodName.setFoodName(food);
			foodMenuRepository.save(foodName);
		}
		return "Saved Menu";
	}
	
	@CrossOrigin
	@GetMapping(path="/menu")
	public @ResponseBody Iterable<FoodMenu> getFoodMenu() {
		return foodMenuRepository.findAll();
	}
	
	@CrossOrigin
	@PostMapping(path="/add")
	public @ResponseBody String addNewFeedback (@RequestBody List<FeedbackInput> feedbackInputs) {
		
		FeedbackCollection feedCollection;
		if(false == feedbackRepository.exists("testdate")) {
			feedCollection = new FeedbackCollection();
			feedCollection.setDate("testdate");
			Set<Feedback> f = new HashSet<Feedback>();
			feedCollection.setFeedbacks(f);
		}
		else {
			feedCollection = feedbackRepository.findOne("testdate");
		}
			
		Set<Feedback> feeds = feedCollection.getFeedbacks();
		
		for (FeedbackInput input:feedbackInputs) 
		{
			Feedback fb = new Feedback();
			fb.setFoodName(input.getFoodName());
			
			if(feeds.contains(fb)) 
			{
				for (Feedback iter:feeds) 
				{
					if(iter.equals(fb)) 
					{
						if(input.getFeed().equals("good")) 
						{
							iter.setGood(iter.getGood() + 1);
						}
						else
						{
							iter.setBad(iter.getBad() + 1);
						}
					}
				}
			}
			else 
			{
				fb.setFeedbackCollection(feedCollection);
				if(input.getFeed().equals("good")) 
				{
					// TODO: Add defaults for good and bad
					fb.setGood(1);
					fb.setBad(0);
				}
				else 
				{
					fb.setBad(1);
					fb.setGood(0);
				}
				feeds.add(fb);
			}
				
		}
		feedbackRepository.save(feedCollection);
		return "Updated";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<FeedbackCollection> getAllFeedbacks() {
		return feedbackRepository.findAll();
	}

}
