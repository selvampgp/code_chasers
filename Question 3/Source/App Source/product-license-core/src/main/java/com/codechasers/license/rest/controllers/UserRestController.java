package com.codechasers.license.rest.controllers;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codechasers.license.core.mappers.UserMapper;
import com.codechasers.license.core.models.BCryptPasswordEncoder;
import com.codechasers.license.core.models.User;
import com.codechasers.license.core.models.UserDto;
import com.codechasers.license.core.services.UserService;
import com.codechasers.license.core.util.ExceptionLogger;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	UserMapper userMapper;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<UserDto> userList() {

		return userMapper.toUserDto(userService.fetchAll());

	}

	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {

		User user = userMapper.toUserModel(userDto);

		if (user.getPassword() != null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}

		try {
			userService.create(user);
		} catch (ConstraintViolationException e) {

			new ExceptionLogger(e).logException();
			return new ResponseEntity<String>("User already exists",
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {

			new ExceptionLogger(e).logException();
			return new ResponseEntity<String>("Error in creating user",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
	}
}
