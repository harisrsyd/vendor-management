package com.assignment.vendor_management.repository;

import com.assignment.vendor_management.entity.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorsRepository extends JpaRepository<Vendors, UUID> {
}
