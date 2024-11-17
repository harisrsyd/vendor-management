package com.assignment.vendor_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vendors")
public class Vendors {
   @Id
   private UUID id;
   
   private String name;
   
   private String city;
   
   @Column(name= "business_type")
   private String businessType;
}
