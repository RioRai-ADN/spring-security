package com.duyanh.repository;

import org.springframework.data.repository.CrudRepository;

import com.duyanh.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

	Role findByName(String name);
}
