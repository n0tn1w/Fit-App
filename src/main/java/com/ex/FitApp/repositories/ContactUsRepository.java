package com.ex.FitApp.repositories;

import com.ex.FitApp.models.entities.ContactUsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUsEntity, Long> {
}
