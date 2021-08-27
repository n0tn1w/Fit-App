package com.ex.FitApp.models.entities;

import com.ex.FitApp.models.entities.enums.UserRole;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthorityEntity extends BaseEntity implements GrantedAuthority {

    @NonNull
    @Column(name = "authority", nullable = false)
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public AuthorityEntity() {
    }

    public AuthorityEntity(@NonNull String authority) {
        this.authority = authority;
    }

    public void setAuthority(@NonNull String authority) {
        this.authority = authority;
    }
}
