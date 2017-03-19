package com.foodfeed;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UiController {

	@CrossOrigin
	@GetMapping(path="/name")
	public @ResponseBody String getUserName(Principal principal) {
		String name = principal.getName(); //get logged in username
		return name;
	}
	
}
