package com.assignment.vendor_management.ratelimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RateLimitConfig implements WebMvcConfigurer {
   
   @Autowired
   private RateLimitInterceptor rateLimitInterceptor;
   
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(rateLimitInterceptor);
   }
}
