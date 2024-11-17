package com.assignment.vendor_management.controller;

import com.assignment.vendor_management.model.UserRegisterRequest;
import com.assignment.vendor_management.model.WebResponse;
import com.assignment.vendor_management.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsersController {
   
   private UsersService usersService;
   
   public UsersController(UsersService usersService) {
      this.usersService = usersService;
   }
   
   @PostMapping("/register")
   public WebResponse<String> register(@RequestBody UserRegisterRequest request) {
      usersService.register(request);
      return WebResponse.<String>builder().data("User registered successfully").build();
   }
}
