package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.UserRoleEntity;
import com.ex.FitApp.models.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    UserRoleEntity findByRole(UserRole user);


}
