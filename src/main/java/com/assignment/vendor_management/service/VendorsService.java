package com.assignment.vendor_management.service;

import com.assignment.vendor_management.entity.Vendors;
import com.assignment.vendor_management.model.VendorRequest;
import com.assignment.vendor_management.model.VendorResponse;
import com.assignment.vendor_management.repository.VendorsRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VendorsService {
   
   private VendorsRepository vendorsRepository;
   
   private ValidationService validationService;
   
   public VendorsService(VendorsRepository vendorsRepository, ValidationService validationService) {
      this.vendorsRepository = vendorsRepository;
      this.validationService = validationService;
   }
   
   @Transactional
   public VendorResponse addVendor(VendorRequest request) {
      validationService.validate(request);
      Vendors vendor = new Vendors();
      vendor.setId(UUID.randomUUID());
      vendor.setName(request.getName());
      vendor.setCity(request.getCity());
      vendor.setBusinessType(request.getBusinessType());
      
      vendorsRepository.save(vendor);
      
      return toVendorResponse(vendor);
   }
   
   public VendorResponse toVendorResponse(Vendors vendor) {
      return VendorResponse.builder()
         .id(vendor.getId())
         .name(vendor.getName())
         .city(vendor.getCity())
         .businessType(vendor.getBusinessType())
         .build();
   }
   
   public List<VendorResponse> getAllVendors() {
      List<Vendors> vendors = vendorsRepository.findAll();
      return vendors.stream()
         .map(this::toVendorResponse)
         .collect(Collectors.toList());
   }
   
   public VendorResponse getVendor(UUID id) {
      Vendors vendor = vendorsRepository.findById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor data not found"));
      return toVendorResponse(vendor);
   }
   
   @Transactional
   public VendorResponse updateVendor(UUID id, VendorRequest request) {
      validationService.validate(request);
      Vendors vendor = vendorsRepository.findById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor data not found"));
      vendor.setName(request.getName());
      vendor.setCity(request.getCity());
      vendor.setBusinessType(request.getBusinessType());
      
      vendorsRepository.save(vendor);
      
      return toVendorResponse(vendor);
   }
   
   @Transactional
   public void deleteVendor(UUID id) {
      Vendors vendor = vendorsRepository.findById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor data not found"));
      
      vendorsRepository.delete(vendor);
   }
}
