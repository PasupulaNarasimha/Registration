package com.edutech.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.model.User;
import com.edutech.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	public User save(User user) {
		return userRepo.save(user);
	}
	public List<User> login(String email,String password) {
		return userRepo.findEmailAndPassword(email, password);
	}
	public User findByEmail(String email) {
		return userRepo.findByEmailid(email);
	}
	public User findByID(int id) {
		Optional<User> optional = userRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	public boolean isValidEmail(String  email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
