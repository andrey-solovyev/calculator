package com.surf_test.calculator.data.repository;

import com.surf_test.calculator.data.models.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RoleRepository extends PagingAndSortingRepository<UserRole, UUID> {
    UserRole findByName(String name);
}
