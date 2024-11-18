package com.assignment.vendor_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements UserDetails {
   @Id
   @Column(nullable = false, unique = true)
   private String username;
   @Column(nullable = false)
   private String name;
   @Column(nullable = false, unique = true)
   private String email;
   @Column(nullable = false)
   private String password;
   @Enumerated(EnumType.STRING)
   private Role role;
   
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
      return Collections.singletonList(authority);
   }
   
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }
   
   @Override
   public boolean isAccountNonLocked() {
      return true;
   }
   
   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }
   
   @Override
   public boolean isEnabled() {
      return true;
   }
}
