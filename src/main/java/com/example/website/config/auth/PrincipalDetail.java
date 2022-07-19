package com.example.website.config.auth;

import com.example.website.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getNickname() {
        return user.getNickname();
    }

    public Long getId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //계정이 만료되었는지 (true: 만료되지 않음)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //계정이 잠겨있는지 (true: 잠겨있지 않음)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //패스워드가 만료되지 않았는지 (true: 만료되지 않음)
    }

    @Override
    public boolean isEnabled() {
        return true; //계정이 활성화되어 있는지 (true: 활성화)
    }
}
