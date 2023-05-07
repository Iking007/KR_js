package com.example.serverBooksOnly.Model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
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
}
