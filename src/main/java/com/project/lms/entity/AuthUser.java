package com.project.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.lms.utils.Constants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String authorities;

    @OneToOne(mappedBy = "authUser")
    @JsonIgnoreProperties("authUser")
    private Student student;

    @OneToOne(mappedBy = "authUser")
    @JsonIgnoreProperties("authUser")
    private Admin admin;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities=this.authorities.split(Constants.DELIMITER);
        return Arrays.stream(authorities)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
}
