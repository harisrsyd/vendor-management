package com.assignment.vendor_management.security;

import com.assignment.vendor_management.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(authorizeRequests ->
              authorizeRequests
                  .requestMatchers("/api/v1/user/register").permitAll()
                  .anyRequest().authenticated()
          )
          .httpBasic(Customizer.withDefaults())
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .exceptionHandling(exceptionHandling ->
              exceptionHandling
                  .authenticationEntryPoint((HttpServletRequest request, HttpServletResponse response,
                                             org.springframework.security.core.AuthenticationException authException) -> {
                     response.setStatus(HttpStatus.UNAUTHORIZED.value());
                     response.getWriter().write("Unauthorized: You must register or log in to access this resource.");
                  })
          );
      return http.build();
   }
   
   @Bean
   protected PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
