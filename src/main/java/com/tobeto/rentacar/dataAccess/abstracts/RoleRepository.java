package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
