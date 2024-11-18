package com.assignment.vendor_management.service;

import com.assignment.vendor_management.entity.Role;
import com.assignment.vendor_management.entity.Users;
import com.assignment.vendor_management.model.UserRegisterRequest;
import com.assignment.vendor_management.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class UsersService implements UserDetailsService {
   
   private UsersRepository usersRepository;
   private ValidationService validationService;
   
   public UsersService(UsersRepository usersRepository, ValidationService validationService) {
      this.usersRepository = usersRepository;
      this.validationService = validationService;
   }
   
   @Transactional
   public void register(UserRegisterRequest request) {
      validationService.validate(request);
      if (usersRepository.existsById(request.getUsername())) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
      }
      if (usersRepository.existsByEmail(request.getEmail())) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
      }
      
      Users user = new Users();
      user.setUsername(request.getUsername());
      user.setName(request.getName());
      user.setEmail(request.getEmail());
      user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
      user.setRole(request.getRole() == Role.ADMIN? Role.ADMIN : Role.USER);
      
      usersRepository.save(user);
   }
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return usersRepository.findById(username)
         .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username '%s' not found", username)));
   }
}
