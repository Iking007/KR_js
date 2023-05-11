package com.example.serverBooksOnly.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;
import lombok.Data;

@Entity
@Data
@Table(name = "user_data")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private boolean active;
    
    private String password;
    private String name;
    private String email;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    public User() {
    }

    public User(String email, String password) {
        this.active = true;
        this.password = password;
        this.email = email;        
    }
    
    public void newRole(Set<Role> role){
        this.role = null;
        this.role = role;
    }
    public Set<Role> getRole() {
        return role;
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
        return isActive();
    }


    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }
}
