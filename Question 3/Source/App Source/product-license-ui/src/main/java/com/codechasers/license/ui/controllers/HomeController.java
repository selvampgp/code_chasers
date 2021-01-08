package com.codechasers.license.ui.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codechasers.license.core.mappers.UserMapper;
import com.codechasers.license.core.models.User;
import com.codechasers.license.core.models.UserDto;
import com.codechasers.license.core.services.UserService;
import com.codechasers.license.core.services.UserSessionLogService;
import com.codechasers.license.core.util.ExceptionLogger;

@Controller
public class HomeController {

	
	@Autowired
	UserSessionLogService userSessionLogService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService service;
	
	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String homePage(){
		
		return "home";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String createUserPage(ModelMap modelMap){
		
		modelMap.addAttribute("userDto",new UserDto());
		return "createUser";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String createUser(@ModelAttribute("userDto") UserDto userDto,ModelMap modelMap,BindingResult bindingResult){
		
				
		User user= userMapper.toUserModel(userDto);
		
		if(user.getPassword()!=null){
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		
		try{
			service.create(user);
		}
		catch(ConstraintViolationException e){
			
			new ExceptionLogger(e).logException();
			modelMap.addAttribute("userDto",userDto);
			modelMap.addAttribute("error","User already exists");
			
			return "createUser";
		}
		catch(Exception e){
			
			new ExceptionLogger(e).logException();
			modelMap.addAttribute("userDto",userDto);
			modelMap.addAttribute("error","Error in creating user");
			
			return "createUser";
		}
		
		
		modelMap.addAttribute("msg","User Created Successfully");
		return createUserPage(modelMap);
	}
	
	
	@RequestMapping(value = "/AccessDenied",method=RequestMethod.GET)
	public String accesssDenied(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			return "redirect:/home";
		}
		return "access-restricted";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, ModelMap model,HttpSession ses) {

	      

			if (ses.getAttribute("username") == null) {

				model.addAttribute("j_username",ses.getAttribute("j_username"));
				
				if (error != null) {
					String errorMsg="";
					if(request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")!=null){
						errorMsg = getErrorMessage(request,"SPRING_SECURITY_LAST_EXCEPTION");
						model.addAttribute("error", errorMsg.replace("principal", "user"));
					}
				}

				
				
				return "login";

			
			} 
			else if(logout != null && ses.getAttribute("username") != null){
				Integer userId = Integer.parseInt(ses.getAttribute("id").toString());
				userSessionLogService.saveUserSession(userId,0);
				return "login";
			}
			else {
				return "redirect:/home";
			}
	}
	
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		
		error = exception.getMessage();
		


		return error;
	}
	
	
	
	
}


