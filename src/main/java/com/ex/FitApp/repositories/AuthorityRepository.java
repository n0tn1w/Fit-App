package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.AuthorityEntity;
import com.ex.FitApp.models.entities.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity,Long> {

    AuthorityEntity findByAuthority(String authority);
}
