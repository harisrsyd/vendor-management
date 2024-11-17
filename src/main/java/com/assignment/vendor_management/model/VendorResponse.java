package com.assignment.vendor_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {
   
   private UUID id;
   
   private String name;
   
   private String city;
   
   private String businessType;
}
