package com.example.serverBooksOnly.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.serverBooksOnly.Token.Token;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_data")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;
    private boolean active;
    
    private String password;
    private String name;
    private String email;

    @ManyToMany (cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE
    })
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> favorites;

    public void addFavorite(Book book){
        favorites.add(book);
    }
    public void delFavorite(Book book){
        favorites.remove(book);
    }

    //@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public User(String email, String password, Role role, String name) {
        this.active = true;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
    }
    

    public Role getRole() {
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
        return role.getAuthorities();
    }
}
