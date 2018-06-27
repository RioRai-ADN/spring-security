package com.duyanh.repository;

import org.springframework.data.repository.CrudRepository;

import com.duyanh.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByEmail(String email);
}
