package com.assignment.vendor_management.controller;

import com.assignment.vendor_management.model.UserRegisterRequest;
import com.assignment.vendor_management.model.WebResponse;
import com.assignment.vendor_management.service.UsersService;
import org.springframework.http.HttpStatus;
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
   
   @PostMapping("/user/register")
   public WebResponse<String> register(@RequestBody UserRegisterRequest request) {
      usersService.register(request);
      return WebResponse.<String>builder().status(HttpStatus.CREATED.value())
          .data("User registered successfully, You can access immediately with your username and password").build();
   }
}
