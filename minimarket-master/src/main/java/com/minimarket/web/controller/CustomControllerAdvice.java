package com.minimarket.web.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CustomControllerAdvice {

	@ModelAttribute("username")
	public String mensajeUsuario(Principal principal) 
	{
		//si principal viene nullo quiere decir que usuario jamás inicio sesion
		if (principal!=null) 
		{
			return principal.getName();
			
		}
		return "";
	}
}
