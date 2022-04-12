package com.hungerheroes.backend.jwt.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hungerheroes.backend.jwt.model.Role;
import com.hungerheroes.backend.jwt.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String username;

    private final String phoneNo;

    private final String name;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    private Set<Role> roles;

/*    public UserPrinciple(String id, Set<Role> roles,
                         String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.authorities = authorities;
    }*/

    public static UserPrinciple build(UserModel user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());


        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getPhoneNo(),
                user.getName(),
                user.getPassword(),
                authorities,
                user.getRoles()
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
