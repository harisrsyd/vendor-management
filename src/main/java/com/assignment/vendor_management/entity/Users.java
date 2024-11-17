package com.assignment.vendor_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
   @Id
   private String username;
   private String name;
   private String email;
   private String password;
   private String token;
   
   @Column(name = "token_exp_at")
   private Long tokenExpAt;
}
