package com.gabriel.challenge.domain.users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="users")
@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="usersId")
public class Users implements UserDetails {

    @Id @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private String usersId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    public String getUsersId() {
        return this.usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }
    
    @Override
    public boolean isEnabled() { return true; }

    public Users(RequestUsers requestUsers) {
        this.username = requestUsers.username();
        this.password = requestUsers.password();
    }

}
