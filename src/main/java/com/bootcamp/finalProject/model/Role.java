package com.bootcamp.finalProject.model;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_WAREHOUSE, ROLE_SUBSIDIARY;

    public String getAuthority() {
        return name();
    }

}
