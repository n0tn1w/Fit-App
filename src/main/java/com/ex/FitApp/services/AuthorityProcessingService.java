package com.ex.FitApp.services;


import com.ex.FitApp.models.entities.AuthorityEntity;

public interface AuthorityProcessingService {

    void seedAuthorities();

    AuthorityEntity getUserAuthority();

    AuthorityEntity getAdminAuthority();

    AuthorityEntity getRootAdminAuthority();
}
