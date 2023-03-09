package com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.model;

import com.example.Movie.Reviewing.and.Ticket.Booking.Microservice.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class User implements Serializable,UserDetails{

    private static final String AUTHORITY_DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @OneToOne(mappedBy = "myUser")
    @JsonIgnoreProperties("myUser")
    private Customer customer;

    @OneToOne(mappedBy = "myUser")
    @JsonIgnoreProperties("myUser")
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities = this.authority.toString().split(AUTHORITY_DELIMITER);
        return Arrays.stream(authorities)
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.name;
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

    public UserResponse to(Admin admin){
        return UserResponse.builder()
                .email(admin.getEmail())
                .mobile(admin.getMobile())
                .username(this.name)
                .name(admin.getName())
                .authority(this.authority)
                .build();
    }

    public UserResponse to(Customer customer){
        return UserResponse.builder()
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .username(this.name)
                .name(customer.getName())
                .authority(this.authority)
                .build();
    }
}
