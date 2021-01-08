package com.codechasers.license.core.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.models.User;
import com.codechasers.license.core.models.UserDto;

/** Mapper class to covert the entity to DTO class object
 * 
 * 
 *
 */
@Component
public class UserMapper {

	
	public List<UserDto> toUserDto(List<User> users){
		
		List<UserDto> userDtos=new ArrayList<UserDto>();
		
		for (User user : users) {
			
			userDtos.add(this.toUserDto(user));
		}
		
		return userDtos;
		
	}
	
	public User toUserModel(UserDto userDto){
		
		User user = new User();
		
		BeanUtils.copyProperties(userDto, user);
		user.setActive("Y");
		user.setLockStatus("N");
		return user;
		
	}
	
	public UserDto toUserDto(User user){
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(user,userDto);
		return userDto;
		
	}
}
