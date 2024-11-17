package com.assignment.vendor_management.controller;

import com.assignment.vendor_management.model.VendorRequest;
import com.assignment.vendor_management.model.VendorResponse;
import com.assignment.vendor_management.model.WebResponse;
import com.assignment.vendor_management.service.VendorsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class VendorsController {
   
   private VendorsService vendorsService;
   
   public VendorsController(VendorsService vendorsService) {
      this.vendorsService = vendorsService;
   }
   
   @GetMapping("/vendors")
   public WebResponse<List<VendorResponse>> getAll() {
      List<VendorResponse> vendors = vendorsService.getAllVendors();
      return WebResponse.<List<VendorResponse>>builder().status(HttpStatus.OK.value()).data(vendors).build();
   }
   
   @PostMapping("/vendors")
   public WebResponse<VendorResponse> add(@RequestBody VendorRequest request) {
      VendorResponse vendor = vendorsService.addVendor(request);
      return WebResponse.<VendorResponse>builder().status(HttpStatus.CREATED.value()).data(vendor).build();
   }
   
   @GetMapping("/vendors/{id}")
   public WebResponse<VendorResponse> get(@PathVariable UUID id) {
      VendorResponse vendor = vendorsService.getVendor(id);
      return WebResponse.<VendorResponse>builder().status(HttpStatus.OK.value()).data(vendor).build();
   }
   
   @PutMapping("/vendors/{id}")
   public WebResponse<VendorResponse> update(@PathVariable UUID id, @RequestBody VendorRequest request) {
      VendorResponse vendor = vendorsService.updateVendor(id, request);
      return WebResponse.<VendorResponse>builder().status(HttpStatus.OK.value()).data(vendor).build();
   }
   
   @DeleteMapping("/vendors/{id}")
   public WebResponse<String> delete(@PathVariable UUID id) {
      vendorsService.deleteVendor(id);
      return WebResponse.<String>builder().status(HttpStatus.OK.value()).data("Vendor data deleted Successfully").build();
   }
}
