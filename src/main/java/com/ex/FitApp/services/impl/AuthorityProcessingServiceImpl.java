package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.entities.AuthorityEntity;
import com.ex.FitApp.repositories.AuthorityRepository;
import com.ex.FitApp.services.AuthorityProcessingService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityProcessingServiceImpl implements AuthorityProcessingService {

    private final AuthorityRepository authorityRepository;

    public AuthorityProcessingServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void seedAuthorities() {
        this.authorityRepository.save(new AuthorityEntity("ROLE_ROOT_ADMIN"));
        this.authorityRepository.save(new AuthorityEntity("ROLE_ADMIN"));
        this.authorityRepository.save(new AuthorityEntity("ROLE_USER"));
    }

    @Override
    public AuthorityEntity getUserAuthority() {
        return this.authorityRepository.findByAuthority("ROLE_USER");
    }

    @Override
    public AuthorityEntity getAdminAuthority() {
        return this.authorityRepository.findByAuthority("ROLE_ADMIN");
    }

    @Override
    public AuthorityEntity getRootAdminAuthority() {
        return this.authorityRepository.findByAuthority("ROLE_ROOT_ADMIN");
    }
}
