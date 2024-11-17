package com.assignment.vendor_management.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {
   
   @NotBlank
   @Size(max = 100)
   private String name;
   
   @NotBlank
   @Size(max = 100)
   private String city;
   
   @NotBlank
   private String businessType;
}
