package com.edutech.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edutech.model.User;
import com.edutech.service.UserService;
@RestController
public class ApplicationController {
	@Autowired
	UserService service;
	@PostMapping("/registration")
	public ResponseEntity<?> signup(User user) {
		if(service.findByEmail(user.getEmailid())!=null) {
			return new ResponseEntity<>("EmailId is Already exist", HttpStatus.BAD_REQUEST);
		}
		if(user.getPassword()!=null&&!service.isValidPassword(user.getPassword())) {
			return new ResponseEntity<>("Please enter Valid password", HttpStatus.BAD_REQUEST);
		}
		service.save(user);
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
			
	}
	@PostMapping("/login")
	public ResponseEntity<?> display(String emailid,String password) {
		User user = service.findByEmail(emailid);
		if(user==null) {
			return new ResponseEntity<>("Enter A valid EmailId", HttpStatus.BAD_REQUEST);
		}
		if(!user.getPassword().equals(password)) {
			return new ResponseEntity<>("Enter A valid password", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
	}
}
