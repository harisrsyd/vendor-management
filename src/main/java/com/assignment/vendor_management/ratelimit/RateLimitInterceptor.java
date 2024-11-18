package com.assignment.vendor_management.ratelimit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
   private final ConcurrentHashMap<String, Long> requestCounts = new ConcurrentHashMap<>();
   private final ConcurrentHashMap<String, Long> requestTimestamps = new ConcurrentHashMap<>();
   private final int MAX_REQUESTS_PER_SECOND = 5;
   
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String ip = request.getRemoteAddr();
      long currentTime = System.currentTimeMillis() / 1000;
      
      requestCounts.merge(ip, 1L, Long::sum);
      requestTimestamps.putIfAbsent(ip, currentTime);
      
      if (currentTime != requestTimestamps.get(ip)) {
         requestCounts.put(ip, 1L);
         requestTimestamps.put(ip, currentTime);
      }
      
      if (requestCounts.get(ip) > MAX_REQUESTS_PER_SECOND) {
         response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
         response.getWriter().write("Too many requests. Please try again later.");
         return false;
      }
      return true;
   }
}
