package ru.tyshchuk.newsline.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tyshchuk.newsline.domain.Roles;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String secondName;
    private final String surname;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final List<Roles> roles;

    public JwtUser(
            Long id,
            String username,
            String firstName,
            String secondName,
            String surname, String email,
            String password, Collection<? extends GrantedAuthority> authorities,
            List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.roles = roles;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Roles> getRoles() {
        return roles;
    }
}
