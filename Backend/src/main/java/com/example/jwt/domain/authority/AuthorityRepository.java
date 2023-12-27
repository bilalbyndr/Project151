package com.example.jwt.domain.authority;

import com.example.jwt.core.generic.ExtendedRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityRepository extends ExtendedRepository<Authority> {
    Optional<Authority> findByName(String name);

}
