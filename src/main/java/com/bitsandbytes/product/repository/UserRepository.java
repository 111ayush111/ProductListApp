package com.bitsandbytes.product.repository;

import com.bitsandbytes.product.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

        Optional<User> findByUsername(String username);
}
