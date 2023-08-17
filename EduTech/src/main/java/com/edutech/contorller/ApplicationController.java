package com.edutech.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.model.Login;
import com.edutech.model.User;
import com.edutech.service.UserService;
@RestController
public class ApplicationController {
	@Autowired
	UserService service;
	@PostMapping("/registration")
	public@ResponseBody ResponseEntity<?> signup(@RequestBody User user) {
		if(user.getFname()==null||user.getFname().length()<1) {
			return new ResponseEntity<>("Please enter the First Name", HttpStatus.BAD_REQUEST);
		}
		if(user.getLname()==null||user.getLname().length()<1) {
			return new ResponseEntity<>("Please enter the Last Name", HttpStatus.BAD_REQUEST);
		}
		if(user.getEmailid()==null||!service.isValidEmail(user.getEmailid())) {
			return new ResponseEntity<>("Enter Proper EmailID", HttpStatus.BAD_REQUEST);
		}
		if(service.findByEmail(user.getEmailid())!=null) {
			return new ResponseEntity<>("EmailId is Already exist", HttpStatus.BAD_REQUEST);
		}
		if(user.getPassword()==null||!service.isValidPassword(user.getPassword())) {
			return new ResponseEntity<>("Please enter Valid password", HttpStatus.BAD_REQUEST);
		}
		User user1 = service.save(user);
		return new ResponseEntity<>("User registered successfully Registration Number is: "+user1.getId(), HttpStatus.OK);
			
	}
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<?> display(@RequestBody Login login) {
		User user = service.findByEmail(login.getEmailid());
		if(user==null) {
			return new ResponseEntity<>("Enter A valid EmailId", HttpStatus.BAD_REQUEST);
		}
		if(!user.getPassword().equals(login.getPassword())) {
			return new ResponseEntity<>("Enter A valid password", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
	}
}
