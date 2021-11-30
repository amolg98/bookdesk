package com.amolgupta.bookdesk.respository;

import java.util.Optional;

import com.amolgupta.bookdesk.entity.BDRole;
import com.amolgupta.bookdesk.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(BDRole name);
}
