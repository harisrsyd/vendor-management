package com.assignment.vendor_management.repository;

import com.assignment.vendor_management.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
   boolean existsByEmail(String email);
}
